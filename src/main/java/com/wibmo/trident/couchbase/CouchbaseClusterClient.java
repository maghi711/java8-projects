package com.wibmo.trident.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketManager;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.wibmo.trident.entity.ExistingDefinition;
import com.wibmo.trident.entity.Definition;
import com.wibmo.trident.entity.RatingDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CouchbaseClusterClient {

    private static final Logger logger = Logger.getLogger(CouchbaseClusterClient.class.getName());
    private static final String DEFAULT_ACS_RATING = "3DS1-E-COMMERCE";
    private static final String DEFAULT_PAYZAPP_RATING = "IAP";

    private static BucketManager manager;
    private static Cluster cluster = null;
    private static Bucket mainBucket = null;

    public static void main(String[] args) {
        //String cacheClusterIp = "192.168.110.16,192.168.110.17,192.168.110.18";
        String cacheClusterIp = "localhost";
        String cacheUserId = "Administrator";
        String cachePassword = "password";
        String bucketName = "trident_main";
        initCouchbase(cacheClusterIp, cacheUserId, cachePassword, bucketName);
        CouchbaseRepository cbrMain = CouchbaseUtil.getCouchbaseRepository(mainBucket);
        List allDef = cbrMain.findAll(cbrMain.getBucket().name(), ExistingDefinition.class.getName(), ExistingDefinition.class);
        /*
        allDef.forEach(def -> {
            Definition d = (Definition) def;
            System.out.println("INSTANCE: " + d.getInstanceId() + " CHANNEL: "+ d.getChannelId());
        });
        */
        /*
        Definition def  =  cbrMain.findById("8117::3DS", Definition.class);
        System.out.println("def = " + def);
        */
        /*
        for(Object obj: allDef) {
            Definition oldDefinition = (Definition) obj;
            System.out.println("oldDefinition = " + oldDefinition);
            NewDefinition newDefinition = convertOldToNew(oldDefinition);
            System.out.println("newDefinition = " + newDefinition);
            System.out.print("Stopping after this one.");
            break;
        }
        */
        // Read from DB
        ExistingDefinition oldDefinition  =  cbrMain.findById("1001::PAYZAPP123", ExistingDefinition.class);
        System.out.println("def = " + oldDefinition);

        // Convert from old changes to new changes
        Definition newDefinition = convertOldToNew(oldDefinition);
        System.out.println("newDefinition = " + newDefinition);
        // Write to DB
        cbrMain.update(newDefinition, ExistingDefinition.class);
        System.out.print("Done Updating.");
    }

    private static Definition convertOldToNew(ExistingDefinition oldDefinition) {
        /*
        Mapper mapper = new DozerBeanMapper();
        NewDefinition destObject =
                mapper.map(oldDefinition, NewDefinition.class, "123");
        */
        Definition newDefinition = new Definition();

        // Setting the default rating based on the instance and channel
        Map<String, RatingDefinition> oldRating = oldDefinition.getRatingDefinitionMap();
        Map<String, Map<String, RatingDefinition>> newRating = new HashMap<>();
        System.out.println("Instance : " + oldDefinition.getInstanceId() + "::CHANNEL is " + oldDefinition.getChannelId());
        if (oldDefinition.getInstanceId().equalsIgnoreCase("1001") || oldDefinition.getChannelId().startsWith("PAYZAP")) {
            System.out.println("Payzapp");
            newDefinition.setDefaultRating(DEFAULT_PAYZAPP_RATING);
            newRating.put(DEFAULT_PAYZAPP_RATING, oldRating);
        } else {
            newDefinition.setDefaultRating(DEFAULT_ACS_RATING);
            newRating.put(DEFAULT_ACS_RATING, oldRating);
        }
        newDefinition.setRatingDefinitionMap(newRating);

        // Base entity properties
        newDefinition.setId(oldDefinition.getId());
        newDefinition.setCreated(oldDefinition.getCreated());
        newDefinition.setUpdated(oldDefinition.getUpdated());
        newDefinition.setCas(oldDefinition.getCas());
        newDefinition.setExpiry(oldDefinition.getExpiry());

        // Definition entity properties
        newDefinition.setInstanceId(oldDefinition.getInstanceId());
        newDefinition.setInstanceName(oldDefinition.getInstanceName());
        newDefinition.setChannelId(oldDefinition.getChannelId());
        newDefinition.setAction(oldDefinition.getAction());
        newDefinition.setConfiguration(oldDefinition.getConfiguration());
        newDefinition.setVocabulary(oldDefinition.getVocabulary());
        newDefinition.setDerivedVariables(oldDefinition.getDerivedVariables());
        newDefinition.setAuditTrailReportFiltersOptions(oldDefinition.getAuditTrailReportFiltersOptions());
        newDefinition.setReportFilters(oldDefinition.getReportFilters());
        newDefinition.setReportHeaders(oldDefinition.getReportHeaders());
        newDefinition.setCsvReportHeaders(oldDefinition.getCsvReportHeaders());
        newDefinition.setRuleApplicable(oldDefinition.getRuleApplicable());
        newDefinition.setRuleParameters(oldDefinition.getRuleParameters());
        newDefinition.setExceptionableColumns(oldDefinition.getExceptionableColumns());
        newDefinition.setClassificationEntityColumns(oldDefinition.getClassificationEntityColumns());
        newDefinition.setGroupEntityColumns(oldDefinition.getGroupEntityColumns());
        newDefinition.setPrimaryStatsRequired(oldDefinition.getPrimaryStatsRequired());
        newDefinition.setCsvAuditTrailReportHeaders(oldDefinition.getCsvAuditTrailReportHeaders());
        newDefinition.setValidationEnabled(oldDefinition.isValidationEnabled());
        newDefinition.setAllowUnidentifiedFileds(oldDefinition.isAllowUnidentifiedFileds());
        newDefinition.setUpdateUnidentifiedFieldsInDefinition(oldDefinition.isUpdateUnidentifiedFieldsInDefinition());
        newDefinition.setTxnSource(oldDefinition.getTxnSource());
        newDefinition.setPrimaryIdSet(oldDefinition.getPrimaryIdSet());
        newDefinition.setSecureDataPoints(oldDefinition.getSecureDataPoints());
        newDefinition.setSecureDataPointsMasking(oldDefinition.getSecureDataPointsMasking());
        newDefinition.setCacheTTL(oldDefinition.getCacheTTL());
        newDefinition.setCommonStatsCacheTTL(oldDefinition.getCommonStatsCacheTTL());
        newDefinition.setSaveDefinitionToDB(oldDefinition.isSaveDefinitionToDB());
        newDefinition.setSavePrimaryStatsToDB(oldDefinition.isSavePrimaryStatsToDB());
        newDefinition.setQuickRuleDebug(oldDefinition.isQuickRuleDebug());
        newDefinition.setDebug(oldDefinition.isDebug());
        newDefinition.setDataEncryptionKey(oldDefinition.getDataEncryptionKey());
        newDefinition.setMultiThreadedQuickRule(oldDefinition.isMultiThreadedQuickRule());
        newDefinition.setQuickRuleFutureTimeOut(oldDefinition.getQuickRuleFutureTimeOut());
        newDefinition.setBuildRuleHelperFutureTimeOut(oldDefinition.getBuildRuleHelperFutureTimeOut());
        newDefinition.setRuleExecuteFutureTimeOut(oldDefinition.getRuleExecuteFutureTimeOut());
        newDefinition.setElasticIndexParameter(oldDefinition.getElasticIndexParameter());
        newDefinition.setSessionTTLCounter(oldDefinition.getSessionTTLCounter());
        newDefinition.setCaseLockingTimeOut(oldDefinition.getCaseLockingTimeOut());
        newDefinition.setResponseWaitCount(oldDefinition.getResponseWaitCount());
        newDefinition.setResponseWaitSleepTime(oldDefinition.getResponseWaitSleepTime());
        newDefinition.setDataPerperationWaitCount(oldDefinition.getDataPerperationWaitCount());
        newDefinition.setDataPerperationWaitSleepTime(oldDefinition.getDataPerperationWaitSleepTime());
        newDefinition.setTtlCaseRecordsInHours(oldDefinition.getTtlCaseRecordsInHours());
        newDefinition.setTtlHistoryRecordsInDays(oldDefinition.getTtlHistoryRecordsInDays());
        newDefinition.setTtlObservationRecordsInDays(oldDefinition.getTtlObservationRecordsInDays());
        newDefinition.setTtlPrimaryStatsInMins(oldDefinition.getTtlPrimaryStatsInMins());
        newDefinition.setTtlPrimaryStatsCacheInMins(oldDefinition.getTtlPrimaryStatsCacheInMins());
        newDefinition.setUpdatPrimaryStatsAsyncToCache(oldDefinition.isUpdatPrimaryStatsAsyncToCache());
        newDefinition.setStorePrimaryStatsInCache(oldDefinition.isStorePrimaryStatsInCache());
        newDefinition.setStoreSessionInCache(oldDefinition.isStoreSessionInCache());
        newDefinition.setPreProcessingImplClass(oldDefinition.getPreProcessingImplClass());
        newDefinition.setPostProcessingImplClass(oldDefinition.getPostProcessingImplClass());
        newDefinition.setAutoCaseCloseAuthType(oldDefinition.getAutoCaseCloseAuthType());
        newDefinition.setAutoCaseCloseStatusActionId(oldDefinition.getAutoCaseCloseStatusActionId());
        newDefinition.setCaseHistoryDisplayFilters(oldDefinition.getCaseHistoryDisplayFilters());
        newDefinition.setCaseHistoryResultHeaders(oldDefinition.getCaseHistoryResultHeaders());
        newDefinition.setCsvCaseHistoryReportHeaders(oldDefinition.getCsvCaseHistoryReportHeaders());
        newDefinition.setCacheVersion(oldDefinition.getCacheVersion());
        newDefinition.setPrimaryStatsMinsLimit(oldDefinition.getPrimaryStatsMinsLimit());
        newDefinition.setPrimaryStatsHourLimit(oldDefinition.getPrimaryStatsHourLimit());
        newDefinition.setPrimaryStatsDayLimit(oldDefinition.getPrimaryStatsDayLimit());
        newDefinition.setTrimPriamryStats(oldDefinition.isTrimPriamryStats());
        newDefinition.setMakerCheckerPermissions(oldDefinition.getMakerCheckerPermissions());
        newDefinition.setAllowDuplicateRequestForMakerChecker(oldDefinition.isAllowDuplicateRequestForMakerChecker());
        newDefinition.setAction(oldDefinition.getAction());
        newDefinition.setChallengeType(oldDefinition.getChallengeType());
        newDefinition.setFinalStatus(oldDefinition.getFinalStatus());
        newDefinition.setReason(oldDefinition.getReason());
        newDefinition.setPrimaryIdSet(oldDefinition.getPrimaryIdSet());
        newDefinition.setExternalAPIConfiguration(oldDefinition.getExternalAPIConfiguration());
        newDefinition.setEntityCategories(oldDefinition.getEntityCategories());
        newDefinition.setDownloadPageSize(oldDefinition.getDownloadPageSize());
        newDefinition.setDownloadConnectRetryTime(oldDefinition.getDownloadConnectRetryTime());
        newDefinition.setDownloadRetryCount(oldDefinition.getDownloadRetryCount());
        newDefinition.setLessDataRetryCount(oldDefinition.getLessDataRetryCount());
        newDefinition.setGroupEntityRangeColumns(oldDefinition.getGroupEntityRangeColumns());
        newDefinition.setBaseCurrencyCode(oldDefinition.getBaseCurrencyCode());
        newDefinition.setInstanceInfo(oldDefinition.getInstanceInfo());

        return newDefinition;
    }

    public static void initCouchbase(String cacheClusterIp, String cacheUserId, String cachePassword, String bucketName) {
        logger.info("Couchbase initializing start");
        try {
            CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().connectTimeout(300 * 1000) // 180 Seconds
                    .keepAliveInterval(3600 * 1000) // 3600 Seconds
                    .build();
            List nodeIps = new ArrayList<String>();
            String ipS[] = cacheClusterIp.split(",", -1);
            for (int i = 0; i < ipS.length; i++) {
                nodeIps.add(ipS[i]);
            }
            logger.info("Cache Nodes configured :" + nodeIps);
            cluster = CouchbaseCluster.create(env, nodeIps);
            cluster.authenticate(cacheUserId, cachePassword);
            mainBucket = cluster.openBucket(bucketName);
            manager = mainBucket.bucketManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Couchbase initializing end");
    }
}
