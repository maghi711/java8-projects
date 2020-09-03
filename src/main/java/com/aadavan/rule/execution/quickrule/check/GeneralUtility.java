package com.aadavan.rule.execution.quickrule.check;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class GeneralUtility {
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
/*


    public static Context getInstanceChannel(String clientId){
        Context context = new Context();
        if(isBlankNull(clientId) == false){
            String[] tokens = clientId.split("_",-1);
            String instanceId =  CacheOp.getString("INSTANCE_"+clientId);
            String  channelId  = CacheOp.getString("CHANNEL_"+clientId);

            if(tokens.length == 4){
                //client id contains instance channel
                instanceId = tokens[1];
                channelId = tokens[2];
            }else{
                //instanceId does not contains context info
                instanceId =  CacheOp.getString("INSTANCE_"+clientId);
                channelId  = CacheOp.getString("CHANNEL_"+clientId);
            }
            context.setChannelId(channelId);
            context.setInstanceId(instanceId);
        }
        return context;
    }

*/
/*

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
*/

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

    public static void main(String[] args) {

/*
        BigDecimal bigDecimal = new BigDecimal("1");

        System.out.println("big:"+bigDecimal.compareTo(new BigDecimal("0")));


        String ss = "'cardNo'|'>'|'10' ";

        List<String> sss=getDelimittedStringToList(ss, "\\|");

        sss.remove(0);
        System.out.println(sss);
        */
        List<String> values = Arrays.asList("1", "aadavan", "5");
        for (String value: values) {
            final boolean numeric = isNumeric(value);
            System.out.println(value + " isNumeric " + numeric);
        }
    }
/*
    private static void print(String key, final JsonNode node) throws IOException {
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = node.fields();

        System.out.println("size :" + node.size());

        if (node.size() == 0) {
            System.out.println("----------(" + key + ")--:" + node);
        }

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            final String xkey = field.getKey();
            System.out.println("Key: " + key);
            final JsonNode value = field.getValue();
            if (value.isContainerNode()) {
                print(xkey, value); // RECURSIVE CALL
            } else {
                System.out.println("(" + xkey + ")Value: " + value);
            }
        }
    }

    public static void parse(String json) {
        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = null;

        try {
            rootNode = mapper.readTree(json);

            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {

                Map.Entry<String, JsonNode> field = fieldsIterator.next();

                print(field.getKey(), field.getValue());
                System.out.println("--------------------Key: " + field.getKey() + "\tValue:" + field.getValue());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void pars2(String json) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
            Map<String, Object> data = mapper.readValue(json, type);

            System.out.println("~~~~~~~~~~~~~~~~~"+data);

            Iterator<String> dataIterator = data.keySet().iterator();

            while(dataIterator.hasNext()){

                String key = (String)dataIterator.next();

                Object obj = data.get(key);

                //System.out.println("Key="+key+"-------vlaue("+obj+")----------"+obj.getClass());


                if(obj instanceof java.util.ArrayList ){
                    System.out.println("Key="+key+"-------vlaue("+obj+")----------"+obj.getClass());
                    ArrayList list = (ArrayList) obj;

                    Iterator it = list.iterator();
                    while(it.hasNext()){
                        Object ob = it.next();
                        System.out.println(ob.getClass());
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static String displayRuileInput(RuleInputs ri){
        StringBuilder sb = new StringBuilder();
        Iterator keyIterator  = ri.parameterMap.keySet().iterator();
        while(keyIterator.hasNext()){
            String key = (String)keyIterator.next();

            //System.out.println("---------key ("+key+") -----");
            Map map = ri.parameterMap.get(key);
            if(map != null){
                Iterator valueIterator = map.keySet().iterator();
                while(valueIterator.hasNext()){
                    String value = (String)valueIterator.next();
                    Param parameter = (Param)map.get(value);

                    sb.append("key ("+key+") --value("+value+")---"+parameter).append("\n\r");

                    //System.out.println("---------key ("+key+") --value("+value+")---"+parameter);
                    //System.out.println("---------key :"+key);
                }
            }
        }
        return sb.toString();
    }

    public static String printJsonOfObject(String calling, Object object){
        String json = null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return("JSON>:("+calling+"):"+json);
    }
*/
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
/*

    public static void printStringArray(String[] elements){

        for (String string : elements) {
            //System.out.println("Elements "+elements);
        }
    }

    public static String getStackTrace(Throwable e) {
        StringWriter writer = new StringWriter(1024);
        e.printStackTrace(new PrintWriter(writer));
        String stackTrace = writer.toString();
        return stackTrace;
    }
*/
}

