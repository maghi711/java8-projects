package com.wibmo.trident.couchbase;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;
import com.couchbase.client.java.error.TemporaryFailureException;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.wibmo.trident.entity.Entity;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the Repository interface that uses the synchronous API 
 * exposed by the Couchbase Java SDK.
 * 
 * @author ifteqar
 */
public class CouchbaseRepository implements Repository {
	private static final Logger logger = Logger.getLogger(CouchbaseRepository.class.getName());
	private final JsonConverter converter = new JacksonConverter();
	private final JsonTranscoder transcoder = new JsonTranscoder();
	private Bucket bucket;
	private static int retryAttempCount =  5;

	public Bucket getBucket() {
		return bucket;
	}

	public static int getRetryAttempCount() {
		return retryAttempCount;
	}

	public static void setRetryAttempCount(int retryAttempCount) {
		retryAttempCount = retryAttempCount;
	}

	public CouchbaseRepository(Bucket bucket) {
		this.bucket = bucket;
	}
		
	public void addObject(JsonDocument jd){		
		bucket.upsert(jd);
	}	
	
	public JsonDocument getObject(String id){
		logger.info("Getting key :"+id);
		return bucket.get(id);
	}

	public  List <? extends Entity> findByListOfIds(final List<String> exceptionIds, Class<? extends Entity> type) {
		JsonDocument doc;
		StringBuilder queryStatement = new StringBuilder();
		formUseKeyQuery(exceptionIds, queryStatement);

		long t1 = System.currentTimeMillis();
		logger.info("Statement"+queryStatement.toString()+"placeHolders:"+exceptionIds);
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(queryStatement.toString(), JsonArray.empty(), params);
		N1qlQueryResult result = bucket.query(query);
		List data = new ArrayList();
		for (N1qlQueryRow row : result)	{
			JsonObject firstRowObject = row.value();
			doc = JsonDocument.create(firstRowObject.getString("id"), firstRowObject);
			if(doc != null){
				data.add(fromJsonDocument(doc, type));
			}
		}
		long t2 = System.currentTimeMillis();
		logger.info("Time taken by Query ("+(t2-t1)+"):"+queryStatement);
		if(t2-t1 > 500){
			logger.info("Long running query, Time taken by Query ("+(t2-t1)+"):"+queryStatement);
		}
		return data;
	}

	private void formUseKeyQuery(List<String> exceptionIds, StringBuilder statement) {
		statement.append("SELECT ");
		statement.append(bucket.name());
		statement.append(".* FROM ");
		statement.append(bucket.name());
		statement.append(" USE KEYS [ ");
		StringBuilder stringBuilder = new StringBuilder();
		int count = 1;
		for(String id: exceptionIds) {
			stringBuilder.append("'")
					.append(id)
					.append("'");
			if(exceptionIds.size() != count){
				stringBuilder.append(", ");
			}
			count++;
		}
		stringBuilder.append("]");
		statement.append(stringBuilder.toString());
	}

	// created for tag support	
	public List findDatails(Map<String,String> arguments, Class<? extends Entity> classType) {

		String type = arguments.get("type");
		String bucketName = arguments.get("bucket");
		
		logger.info("Type:"+type);
		JsonDocument doc;
		//TaggedEntity tresult;
		StringBuilder statement = new StringBuilder();
		JsonArray values = JsonArray.empty();
		statement.append("SELECT "+bucketName+".* FROM "+bucketName+" where type='"+type+"'");
		List resultList = null;
		
		int paramCount = 1;
		
		for (Map.Entry<String,String> entry : arguments.entrySet()) {
			if("type".equals(entry.getKey()) ||"bucket".equals(entry.getKey())){
				continue;
			}
			statement.append(" and "+entry.getKey()+" = '"+entry.getValue()+"' ");				
		}
		
		logger.info("Statement :"+statement);			
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement.toString(), values, params);
		N1qlQueryResult result = bucket.query(query); 
		List<N1qlQueryRow> list = result.allRows(); 
		if (list.isEmpty()) {
			doc = null; 
		} else {
			resultList = new ArrayList();
			for (N1qlQueryRow row : list) {
				JsonObject rowJson = row.value();
				JsonDocument docx = JsonDocument.create(rowJson.getString("id"),  rowJson);
				resultList.add(fromJsonDocumentGeneric(docx, classType));
			}
		}
		return resultList;
	}
	
	public <T extends Entity> T findByTokenValue(String tokenValue, Class<? extends T> type) {

		JsonDocument doc;
		String statement = "SELECT "+bucket.name()+".* FROM "+bucket.name()+" where tokenValue = $1 and type='com.enstage.tokenizer.entity.TokenData'";
		JsonArray values = JsonArray.empty().add(tokenValue);
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement, values, params);
		N1qlQueryResult result = bucket.query(query); 
		List<N1qlQueryRow> list = result.allRows(); 
	    if (list.isEmpty()) {
	    	doc = null; 
	    } else {
	    	N1qlQueryRow firstRow = list.get(0);
	    	JsonObject firstRowObject = firstRow.value(); 
	    	doc = JsonDocument.create(tokenValue, firstRowObject);
	    }
		
		return doc == null ? null : fromJsonDocument(doc, type);
	}

	public  List <? extends Entity> findAll(String bucketname, String typeString, Class<? extends Entity> type) {
		JsonDocument doc;
		String statement = "SELECT "+bucket.name()+".* FROM "+bucket.name()+" where type=$1";
		JsonArray values = JsonArray.empty();
		values = values.add(typeString);
		long t1 = System.currentTimeMillis();
		logger.info("Statement"+statement+"values:"+values);
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement, values, params);
		N1qlQueryResult result = bucket.query(query);
		//List<N1qlQueryRow> list = result.allRows();
		List data = new ArrayList();
		for (N1qlQueryRow row : result)			{
			JsonObject firstRowObject = row.value(); 
			doc = JsonDocument.create(firstRowObject.getString("id"), firstRowObject);
			
			//logger.info("list type:"+type);
			
			if(doc != null){
				data.add(fromJsonDocument(doc, type));
			}
		}
        long t2 = System.currentTimeMillis();
		if(t2-t1 > 500){
		    logger.info("Time taken by Query ("+(t2-t1)+"):"+statement);
        }
		return data;
	}

	public  List <? extends Entity> findAll(  Class<? extends Entity> type, int skipCount, int limit) {
        long t1 = System.currentTimeMillis();
		JsonDocument doc;

		StringBuilder statement = new StringBuilder();
		statement.append( "SELECT "+bucket.name()+".* FROM "+bucket.name()+" where type=$1 ");

		if(skipCount >= 0 && limit > 0){
			statement.append(" offset "+skipCount);
			statement.append(" limit "+limit);
			statement.append(" ");
		}

		JsonArray values = JsonArray.empty();
		values = values.add(type.getName());
		logger.info("Query:("+statement.toString()+")"+"values:"+values);
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement.toString(), values, params);
		N1qlQueryResult result = bucket.query(query);
		//List<N1qlQueryRow> list = result.allRows();
		List data = new ArrayList();
		for (N1qlQueryRow row : result)			{
			JsonObject firstRowObject = row.value();
			doc = JsonDocument.create(firstRowObject.getString("id"), firstRowObject);
			//logger.info("list type:"+type);
			if(doc != null){
				data.add(fromJsonDocument(doc, type));
			}
		}
        long t2 = System.currentTimeMillis();
        if(t2-t1 > 500){
            logger.info("Time taken by Query ("+(t2-t1)+"):"+statement);
        }
		return data;
	}

	public  List <? extends Entity> findAll(  Class<? extends Entity> type, String key, String value) {
        long t1 = System.currentTimeMillis();
	    JsonDocument doc;
		String statement = "SELECT "+bucket.name()+".* FROM "+bucket.name()+" where type=$1 AND "+key+"=$2";
		JsonArray values = JsonArray.empty();
		values = values.add(type.getName());
		values = values.add(value);
		logger.info("Query:("+statement+")"+"values:"+values);
		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement, values, params);
		N1qlQueryResult result = bucket.query(query); 
		//List<N1qlQueryRow> list = result.allRows();
		List data = new ArrayList();
		for (N1qlQueryRow row : result)			{
			JsonObject firstRowObject = row.value(); 
			doc = JsonDocument.create(firstRowObject.getString("id"), firstRowObject);			
			//logger.info("list type:"+type);			
			if(doc != null){
				data.add(fromJsonDocument(doc, type));
			}
		}
        long t2 = System.currentTimeMillis();
        if(t2-t1 > 500){
            logger.info("Time taken by Query ("+(t2-t1)+"):"+statement);
        }
		return data;
	}

	/**
	 * Converts a JsonDocument into an object of the specified type
	 *
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	public <T extends Entity> T fromJsonDocument(JsonDocument doc, Class<T> type) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		T result = converter.fromJson(content.toString(), type);
		result.setCas(doc.cas());
		return result;
	}

	public <T> T fromJsonDocumentGeneric(JsonDocument doc, Class<T> type) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		T result = converter.fromJson(content.toString(), type);
		return result;
	}

	@Override
	public <T extends Entity> T findById(String id, Class<? extends T> type) {
		long t1 = System.currentTimeMillis();
		JsonDocument doc = bucket.get(id);

		//logger.info("doc:"+doc);
//		If un-commenting the following code, you must complete all required imports.
//
//		JsonDocument doc;
//		String statement = "SELECT aadhar_vault_data.* FROM aadhar_vault_data where aadhaarRefNumber = $1";
//		JsonArray values = JsonArray.empty().add(id);
//		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
//		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement, values, params);
//		N1qlQueryResult result = bucket.query(query);
//		List<N1qlQueryRow> list = result.allRows();
//	    if (list.isEmpty()) {
//	    	doc = null;
//	    } else {
//	    	N1qlQueryRow firstRow = list.get(0);
//	    	JsonObject firstRowObject = firstRow.value();
//	    	doc = JsonDocument.create(id, firstRowObject);
//	    }
		//logger.info("JsonDoc:"+doc.content());
		long t2 = System.currentTimeMillis();
		if(t2-t1 > 500){
			logger.info("Time taken by Query ("+(t2-t1)+"):"+id);
		}
		return doc == null ? null : fromJsonDocument(doc, type);
	}

	@Override
	public <T extends Entity> T create(T entity, Class<? extends T> type) {
		return null;
	}

	@Override
	public <T extends Entity> T update(T entity, Class<? extends T> type) {
		JsonDocument docIn = toJsonDocument(entity);
		JsonDocument docOut=null;
		try {
			docOut = bucket.upsert(docIn);
		}
		catch (CouchbaseException e) {
			throw new RuntimeException(e);
		}
		return fromJsonDocument(docOut, type);
	}

	@Override
	public <T extends Entity> T upsert(T entity, Class<? extends T> type) {
		return null;
	}

	@Override
	public <T extends Entity> T upsertAsync(T entity, Class<? extends T> type) {
		return null;
	}

	@Override
	public <T extends Entity> void delete(T entity) {

	}

	/**
	 * Converts an object to a JsonDocument
	 *
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected <T extends Entity> JsonDocument toJsonDocument(T source) {
		if (source == null) {
			throw new IllegalArgumentException("entity is null");
		}
		String id = source.getId();
		if (id == null) {
			throw new IllegalStateException("entity ID is null");
		}
		try {
			JsonObject content =
					transcoder.stringToJsonObject(converter.toJson(source));
			JsonDocument doc = null;
			if(source.getExpiry() != 0){
				doc = JsonDocument.create(id, source.getExpiry(), content, source.getCas());
			}else{
				doc = JsonDocument.create(id, content, source.getCas());
			}

			return doc;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
