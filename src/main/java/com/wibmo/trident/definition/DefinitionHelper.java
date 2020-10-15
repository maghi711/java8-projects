package com.wibmo.trident.definition;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.wibmo.trident.entity.ExistingDefinition;
import com.wibmo.trident.entity.Entity;
import com.wibmo.trident.couchbase.CouchbaseRepository;
import com.wibmo.trident.couchbase.CouchbaseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefinitionHelper extends Entity {
	private static final Logger logger = Logger.getLogger(DefinitionHelper.class.getName());
	private static Map<String, ExistingDefinition> allDefinitions = new HashMap<String, ExistingDefinition>();

	private static Set<String> allInstanceIds = new HashSet<>();
	private static Set<String> allChannelIds = new HashSet<>();
	private static Set<String> alltxnSourceTypesCommon = new HashSet<>();
	private static Map<String,Set<String>> alltxnSourceTypes = new ConcurrentHashMap<>();
	
	public static void load(){
		CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getMainBucket());
		//List allDef = cbrData.findAll(cbrData.getBucket().name(), Definition.class.getName(), Definition.class);
		List allDef = cbrData.findByListOfIds(Arrays.asList("8198::3DS", "8111::3DS", "8117::3DS","1001::PAYZAPP123"), ExistingDefinition.class);
		Map _allDefinitions = new HashMap<String, ExistingDefinition>();
		Set<String> _allInstanceIds = new HashSet<>();
		Set<String> _allChannelIds = new HashSet<>();
		Map<String,Set<String>> _alltxnSourceTypes = new HashMap<>();
		Set<String> _alltxnSourceTypesCommon = new HashSet<>();

		Iterator it = allDef.iterator();
		//List entityList = cbrData.findAllByType(EntityProperty.class);
		while(it.hasNext()) {
			ExistingDefinition def = (ExistingDefinition)it.next();

			//logger.info("remove it Defin:"+def.toString());
			
			/*for (Map.Entry<String, EntityProperty> eachEntity : def.getEntitiesUsed().entrySet()) {
				EntityProperty ep = eachEntity.getValue();
				ep.setName(eachEntity.getKey());
				ep.setAlternateName(eachEntity.getKey());
			}*/
			//def.getEntitiesUsed().clear();
			/*
			for (Object object : entityList) {
				EntityProperty ep = (EntityProperty) object;
				//logger.info("Resetting :"+ep.getId());
				if(ep!= null && ep.getInstanceId().equals(def.getInstanceId()) && ep.getChannelId().equalsIgnoreCase(def.getChannelId())) {
					//def.getEntitiesUsed().put(ep.getEntityId(), ep);
				}
			}*/


			
			_allDefinitions.put(def.getInstanceId()+"::"+def.getChannelId(), def);
			_allInstanceIds.add(def.getInstanceId());
			_allChannelIds.add(def.getChannelId());
			if(def.getTxnSource()!= null && def.getTxnSource().size() > 0){
				_alltxnSourceTypesCommon.addAll(def.getTxnSource());
				Set<String> alltxnSource =  _alltxnSourceTypes.get(def.getInstanceId());
				if(alltxnSource == null){
					alltxnSource = new HashSet<>();
				}
				alltxnSource.addAll(def.getTxnSource());
				_alltxnSourceTypes.put(def.getInstanceId(), alltxnSource);
			}

		}
		allDefinitions = _allDefinitions;
		allInstanceIds = _allInstanceIds;
		allChannelIds = _allChannelIds;
		alltxnSourceTypes = _alltxnSourceTypes;
		alltxnSourceTypesCommon = _alltxnSourceTypesCommon;
		logger.info("Definition load completed");
		//logger.info("AllDef Loaded"+allDefinitions);
	}

	/*
	public static void reloadStaticParams(){
		String retryCountStr = TridentParameterHelper.getTridentParameters("CB_INSERT_RETRY_COUNT");
		int retryCount = 1;
		try {
			retryCount = Integer.parseInt(retryCountStr);
		}catch(Exception e){
			//ignore
		}
		CouchbaseRepository.setRetryAttempCount(retryCount);


	}
	*/

	public static Map<String, ExistingDefinition> getAllDefinitions() {
		return allDefinitions;
	}

	public static Set<String> getAlltxnSourceTypesCommon() {
		return alltxnSourceTypesCommon;
	}

	public static void setAlltxnSourceTypesCommon(Set<String> alltxnSourceTypesCommon) {
		DefinitionHelper.alltxnSourceTypesCommon = alltxnSourceTypesCommon;
	}

	public static void setAllDefinitions(Map<String, ExistingDefinition> allDefinitions) {
		DefinitionHelper.allDefinitions = allDefinitions;
	}

	public static Map<String, Set<String>> getAlltxnSourceTypes() {
		return alltxnSourceTypes;
	}

	public static void setAlltxnSourceTypes(Map<String, Set<String>> alltxnSourceTypes) {
		DefinitionHelper.alltxnSourceTypes = alltxnSourceTypes;
	}

	public boolean addDefaultDefinition(String instanceId, String channelId, String instanceName){
		boolean documentAdded = false;


		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource("DefaultDefinition.txt");

		BufferedReader bufferedReader = null;

		InputStream is = classloader.getResourceAsStream("DefaultDefinition.txt");
		bufferedReader = new BufferedReader(new InputStreamReader(is));
		StringBuilder content = new StringBuilder();
		try {
			while(true) {
				String line = bufferedReader.readLine();

				if (line == null) break;
				line = line.trim();
				content.append(line);
				if(line.isEmpty()) continue;

			}
		} catch(Exception e) {
			logger.log(Level.WARNING, "Error: "+e, e);
		} finally {
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bufferedReader = null;
			}
		}

		logger.info("content:"+content);
		String docContent = content.toString();

		docContent = docContent.replaceAll("<INSTANCE_NAME>", instanceName);
		docContent = docContent.replaceAll("<INSTANCE_ID2>", instanceId);
		docContent = docContent.replaceAll("<INSTANCE_CHANNEL>", channelId);

		JsonObject jsonObject = JsonObject.fromJson(docContent);
		String id = instanceId+"::"+channelId;
		JsonDocument jsonDocument = JsonDocument.create(id,jsonObject);

		ExistingDefinition definition = GeneralUtility.fromJsonDocument(jsonDocument, ExistingDefinition.class);
		System.out.println("Definition:"+definition.toString());

		CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getMainBucket());
		cbrData.create(definition, ExistingDefinition.class);

		return documentAdded;
	}


	public boolean deleteDefinition(String instanceId, String channelId){

		CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getMainBucket());

		String id = instanceId+"::"+channelId;
		ExistingDefinition definition = cbrData.findById(id, ExistingDefinition.class);

		if(definition != null){
			logger.info("Deleting the definition");
			cbrData.delete(definition);
			return true;
		}

		return false;
	}
	
	/*
	public static void updateDefinition(Definition def) {
		Runnable r = new Runnable() {
			public void run() {
				try {
				//logger.info("Updating definition :"+def.getId());
				CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getMainBucket());
				cbrData.update(def, Definition.class);
				load();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		};
		com.enstage.eguard.server.enthread.EnsPool.execute("XJOB", r);
	}
	*/
	public  static ExistingDefinition getDef(String instanceId, String channelId) {
		return (ExistingDefinition)allDefinitions.get(instanceId+"::"+channelId);
	}
        
       public static List<ExistingDefinition> getListOfDefinitionByInstanceId(String instanceId) {

        List<ExistingDefinition> list = new ArrayList<>();

        allDefinitions.keySet().stream().filter((key) -> (key.contains(instanceId))).forEachOrdered((key) -> {
            list.add(allDefinitions.get(key));
            });

        return list;

    }
}
