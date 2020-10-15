package com.wibmo.trident.definition;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.fasterxml.jackson.core.JsonFactory;
import com.fins.org.json.JSONArray;
import com.fins.org.json.JSONException;
import com.fins.org.json.JSONObject;
import com.wibmo.trident.couchbase.JacksonConverter;
import com.wibmo.trident.couchbase.JsonConverter;
import com.wibmo.trident.entity.Entity;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class GeneralUtility {
	private static final Logger logger = Logger.getLogger(GeneralUtility.class.getName());
	public static boolean isCommonDebug = false; // Temp cahnges
	public static boolean isCacheDebug = false;
    public static boolean useSnappyCompression = true;
	private static int deCompressionBufferLenghCheck = 8;

    public static boolean isUseSnappyCompression() {
        return useSnappyCompression;
    }

    public static void setUseSnappyCompression(boolean useSnappyCompression) {
        GeneralUtility.useSnappyCompression = useSnappyCompression;
    }

    public static int getDeCompressionBufferLenghCheck() {
		return deCompressionBufferLenghCheck;
	}

	public static void setDeCompressionBufferLenghCheck(int deCompressionBufferLenghCheck) {
		GeneralUtility.deCompressionBufferLenghCheck = deCompressionBufferLenghCheck;
	}

	public static boolean isIsCacheDebug() {
		return isCacheDebug;
	}

	public static void setIsCacheDebug(boolean isCacheDebug) {
		GeneralUtility.isCacheDebug = isCacheDebug;
	}

	public static long getRelativeDate(String timeType, int unit, String datePattern){
		Format sdfYYYYMM = null;
		Calendar calendar = Calendar.getInstance();
		if("DAY".equalsIgnoreCase(timeType)){
			calendar.add(Calendar.DATE , unit);
		}
		if("MONTH".equalsIgnoreCase(timeType)){
			calendar.add(Calendar.MONTH , unit);
		}
		if("HOUR".equalsIgnoreCase(timeType)){
			calendar.add(Calendar.HOUR , unit);
		}

		if("MIN".equalsIgnoreCase(timeType)){
			calendar.add(Calendar.MINUTE , unit);
		}
		if(GeneralUtility.isBlankNull(datePattern)) {
			sdfYYYYMM = new SimpleDateFormat("yyyyMMddHHmmss");
		}else{
			sdfYYYYMM = new SimpleDateFormat(datePattern);
		}
		Date date = new Date(calendar.getTimeInMillis());
		long time = Long.parseLong(sdfYYYYMM.format(date));
		return time;
	}

	public static Object deepCopy(Object object) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
			outputStrm.writeObject(object);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
			return objInputStream.readObject();
		}
		catch (Exception e) {
			logger.warning("Error:"+getStackTrace(e));
			return null;
		}
	}
	public static String maskNode(String ip) {
		try {
			StringBuilder sb = new StringBuilder();
			if(ip != null && ip.length()>0) {
				ip = ip.replace(".",",");
				String[] element = ip.split(",", -1);
				sb.append(element[2]);
				sb.append("-");
				sb.append(element[3]);
				return sb.toString();
			}
		} catch (Exception e) {
			logger.warning("Error:"+getStackTrace(e));
		}
		return null;
	}
	public static List<String> getDelimittedStringToList(String argumentString, String delimmiter){
		List<String> result = new ArrayList<>();
		String[] args = argumentString.split(delimmiter, -1);
		if(args.length != 0){
			for(String data : args){
				result.add(data);
			}
		}
		return result;
	}


	public static Map getCSVtoMap(String arg){
		Map result = new HashMap();		
		String[] tocken = arg.split(",",-1);		
		for(int i = 0; i< tocken.length; i++){			
			String value = tocken[i];
			
			String[] keyValue = value.split(":",-1);
			if(keyValue.length == 2){
				result.put(keyValue[0], keyValue[1]);
			}			
		}
		return result;
	}
	
	public static List getCSVtoList(String arg, String seperator) {
		List result = new ArrayList();

		String[] tocken = arg.split(seperator, -1);

		for (int i = 0; i < tocken.length; i++) {
			result.add(tocken[i].trim());
		}
		return result;
	}
	
	public static List getCSVtoList(String arg) {
		List result = new ArrayList();

		String[] tocken = arg.split(",", -1);

		for (int i = 0; i < tocken.length; i++) {
			result.add(tocken[i].trim());
		}
		return result;
	}
	public static String readFile(String fileLocation) {
		StringBuilder content = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(fileLocation);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileLocation));

			while ((sCurrentLine = br.readLine()) != null) {
				content.append(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return content.toString();
	}
	
	public static String getCurrentTableSuffix(){
		Date date = new Date();
		return getCurrentTableSuffix(date);
	}

	public static String getCurrentTableSuffix(Date date){
		Format sdfYYYYMM = new SimpleDateFormat("yyyyMM");
		return sdfYYYYMM.format(date);
	}
	
	public static String readFileAsItIs(String fileLocation) {
		StringBuilder content = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(fileLocation);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileLocation));

			while ((sCurrentLine = br.readLine()) != null) {
				content.append(sCurrentLine);
				content.append("\n");
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return content.toString();
	}

	public static boolean isBlankNull(String arg) {
		//logger.info("Testing for Null *********remove it*************"+arg);

		if (arg == null) {
			return true;
		}

		if ("".equals(arg)) {
			return true;
		}
		
		if ("null".equals(arg)) {
			return true;
		}
		
		if ("NULL".equals(arg)) {
			return true;
		}

		if ("nil".equals(arg)) {
			return true;
		}

		return false;
		/*if(!(arg == null) || "".equals(arg)){
		return true;
		}else{
		return false;
		}*/

	}
	
	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	
	public static void printStringArray(String[] elements){
		
		for (String string : elements) {
			System.out.println("Elements "+elements);
		}
	}
	
	public static String getStackTrace(Throwable e) {
		StringWriter writer = new StringWriter(1024);
		e.printStackTrace(new PrintWriter(writer));
		String stackTrace = writer.toString();
		return stackTrace;
	}


	public static Map<String, Object> jsonToMap(String data) {
		JSONObject jsonObject = new JSONObject(data);

		return jsonToMap(jsonObject);
	}


	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if(json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public static String mask(String data){
		String maskChar = "*";

		//number of characters to be masked
		String maskString = StringUtils.repeat( maskChar, 4);

		//string to be masked
		String str = "FirstName";

		//this will mask first 4 characters of the string
		System.out.println( StringUtils.overlay(str, maskString, 0, 4) );
		return null;
	}

	private static String maskString(String strText, int start, int end, char maskChar)	throws Exception{

		if(strText == null || strText.equals(""))
			return "";

		if(start < 0)
			start = 0;

		if( end > strText.length() )
			end = strText.length();

		if(start > end)
			throw new Exception("End index cannot be greater than start index");

		int maskLength = end - start;

		if(maskLength == 0)
			return strText;

		StringBuilder sbMaskString = new StringBuilder(maskLength);

		for(int i = 0; i < maskLength; i++){
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start)		+ sbMaskString.toString()		+ strText.substring(start + maskLength);
	}

	private static String mask(String data, char maskChar)
			throws Exception{



		//mask first part
		String strId = "";
		if(data.length() < 4)
			strId = maskString(data, 0, data.length(), '*');
		else
			strId = maskString(data, 1, data.length()-1, '*');

		//now append the domain part to the masked id part
		return strId ;
	}

	private static String maskEmailAddress(String strEmail, char maskChar)
			throws Exception{

		String[] parts = strEmail.split("@");

		//mask first part
		String strId = "";
		if(parts[0].length() < 4)
			strId = maskString(parts[0], 0, parts[0].length(), '*');
		else
			strId = maskString(parts[0], 1, parts[0].length()-1, '*');

		//now append the domain part to the masked id part
		return strId + "@" + parts[1];
	}

	public static void main(String args[]){

		mask("");

		String[] emailIds = {
				"a@abc.com",
				"helloworld@gmail.com",
				"alex@yahoo.com",
				"gz@alpha.com"
		};

		for(String emailId : emailIds) {
			try {
				//System.out.println(maskEmailAddress(emailId, '*'));

				System.out.println(mask(emailId,'*'));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final JsonConverter converter = new JacksonConverter();
	private static final JsonTranscoder transcoder = new JsonTranscoder();
	/**
	 * Converts a JsonDocument into an object of the specified type
	 *
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	public static <T extends Entity> T fromJsonDocument(JsonDocument doc, Class<T> type) {
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

	/**
	 * Converts an object to a JsonDocument
	 *
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected static <T extends Entity> JsonDocument toJsonDocument(T source) {
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


	public static <T> T fromJsonDocumentGeneric(JsonDocument doc, Class<T> type) {
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

}
