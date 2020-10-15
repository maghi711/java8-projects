package com.wibmo.trident.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class CouchbaseUtil {
	private static final Logger logger = Logger.getLogger(CouchbaseUtil.class.getName());

	private static Bucket mainBucket = null;
	private static Bucket quickBucket = null;
    private static Bucket dataBucket = null;

    private static Bucket dataBucketDay = null;
    private static Bucket dataBucketStats = null;
    private static Bucket dataBucketObsn = null;

    private static Bucket auditDataBucket = null;
    private static Bucket externalDataBucket = null;
    
    private static Map<Bucket, CouchbaseRepository> cbrMap = new ConcurrentHashMap<Bucket, CouchbaseRepository>(2);

    public static void initMainBucket(Cluster cluster) {
        logger.info("init main bucket");
        setMainBucket(cluster.openBucket("trident_main"));
    }
    
    public static void initQuickBucket(Cluster cluster) {
        logger.info("init Quick bucket");
        setQuickBucket(cluster.openBucket("trident_stats"));
    }

    public static void initDataBucket(Cluster cluster) {
        logger.info("init data bucket");
        setDataBucket(cluster.openBucket("trident_data"));
    }    
    
    public static void initDataBucketDay(Cluster cluster) {
        logger.info("init Data day bucket");
        setDataBucketDay(cluster.openBucket("trident_data_day"));
    }    

    public static void initDataBucketStats(Cluster cluster) {
        logger.info("init Data Stats bucket");
        setDataBucketStats(cluster.openBucket("trident_stats"));
    }

    public static void initDataBucketObsn(Cluster cluster) {
        logger.info("init Data Obsn bucket");
        setDataBucketObsn(cluster.openBucket("trident_obsn"));
    }
    

    public static void initAuditDataBucket(Cluster cluster) {
        logger.info("init audit data bucket");
        setAuditDataBucket(cluster.openBucket("trident_audit_data"));
    }

   /* public static TokenData getTokenFromCacheOrCBHashedValue(TokenData tokenData){
		CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getDataBucket());  
		TokenData td = null;
		if("true".equalsIgnoreCase(tokenData.getStaticValue())){
			td = CacheManager.getTokenData(tokenData.getHashedValue());
			if(td != null){
				//logger.info("Got from cache"+td);
			}else{
				td = cbrData.findById(tokenData.getHashedValue(), TokenData.class);	
			}
		}
	
		return td;
	}
    
    
    public static TokenData getTokenFromCacheOrCBTokenValue(TokenData tokenData){
		CouchbaseRepository cbrData = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getDataBucket());  
		TokenData td = null;
		td = CacheManager.getTokenData(tokenData.getTokenValue());
		if(td != null){
			//logger.info("Got from cache"+td);
		}else{
			td = cbrData.findByTokenValue(tokenData.getTokenValue(), TokenData.class);	
		}

		return td;
	}*/

   

	public static void close() {
        logger.info("close");
        if (getMainBucket() != null) {
            getMainBucket().close();
            setMainBucket(null);
        }

        if (getDataBucket() != null) {
            getDataBucket().close();
            setDataBucket(null);
        }
        
        if (getDataBucketDay() != null) {
        	getDataBucketDay().close();
        	setDataBucketDay(null);
        }
        
        if (getDataBucketStats() != null) {
            getDataBucketStats().close();
        	setDataBucketStats(null);
        }

        if (getDataBucketObsn() != null) {
            getDataBucketObsn().close();
            setDataBucketObsn(null);
        }
        
        if (getQuickBucket() != null) {
        	getQuickBucket().close();
        	setQuickBucket(null);
        }
               
        
        if (getAuditDataBucket() != null) {
            getAuditDataBucket().close();
            setAuditDataBucket(null);
        }
        
        if (getExternalDataBucket() != null) {
        		getExternalDataBucket().close();
            setExternalDataBucket(null);
        }
    }

    public static Bucket getExternalDataBucket() {
		return externalDataBucket;
	}

	public static void setExternalDataBucket(Bucket externalDataBucket) {
		CouchbaseUtil.externalDataBucket = externalDataBucket;
	}

	public static Bucket getDataBucketDay() {
		return dataBucketDay;
	}

	public static void setDataBucketDay(Bucket dataBucketDay) {
		CouchbaseUtil.dataBucketDay = dataBucketDay;
	}

    public static Bucket getDataBucketStats() {
        return dataBucketStats;
    }

    public static void setDataBucketStats(Bucket dataBucketStats) {
        CouchbaseUtil.dataBucketStats = dataBucketStats;
    }

    public static Bucket getDataBucketObsn() {
        return dataBucketObsn;
    }

    public static void setDataBucketObsn(Bucket dataBucketObsn) {
        CouchbaseUtil.dataBucketObsn = dataBucketObsn;
    }

    public static Bucket getAuditDataBucket() {
        return auditDataBucket;
    }

    public static void setAuditDataBucket(Bucket aAuditDataBucket) {
        auditDataBucket = aAuditDataBucket;
    }

    public static Bucket getMainBucket() {
        return mainBucket;
    }

    public static void setMainBucket(Bucket aMainBucket) {
        mainBucket = aMainBucket;
    }
    
    public static Bucket getQuickBucket() {
        return quickBucket;
    }

    public static void setQuickBucket(Bucket aMainBucket) {
        quickBucket = aMainBucket;
    }

    public static CouchbaseRepository getCouchbaseRepository(Bucket aBucket) {
        if (aBucket == null) {
            logger.warning("bucket passed was null!");
            Exception e = new Exception();
            logger.info("Error");
            e.printStackTrace();
            return null;
        }
        CouchbaseRepository cbr = cbrMap.get(aBucket);
        if (cbr == null) {
            cbr = new CouchbaseRepository(aBucket);            
            logger.info(">>>>>>>>>>>Creating new CouchbaseRepository and returning..>>>>>>>>>>>."+aBucket);
            cbrMap.put(aBucket, cbr);
        }
        return cbr;
    }

    public static Bucket getDataBucket() {
        return dataBucket;
    }

    public static void setDataBucket(Bucket aDefaultDataBucket) {
        dataBucket = aDefaultDataBucket;
    }
	
	public static void insertIntoCollection(JsonObject object) {		
		String clientId = object.getString("_id");
		JsonDocument doc = JsonDocument.create(clientId, object);		
		CouchbaseRepository cbrMain = CouchbaseUtil.getCouchbaseRepository(CouchbaseUtil.getDataBucket());
		cbrMain.addObject(doc);		
	}

	public static JsonArray dateToDateArr(Date date) {
		JsonArray arr = JsonArray.empty();

		arr.add(date.getYear() + 1900);
		arr.add(date.getMonth() + 1);
		arr.add(date.getDate());
		arr.add(date.getHours());
		arr.add(date.getMinutes());
		arr.add(date.getSeconds());

		return arr;
	}

	public static long getTimeStamp(String dateString) {
//			2016-01-06 11:00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		long time = 0;

		Date parsedTimeStamp;
		try {
			parsedTimeStamp = dateFormat.parse(dateString);
			time = parsedTimeStamp.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

}
