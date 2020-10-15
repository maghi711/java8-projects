package com.wibmo.trident.entity;

import java.util.*;
import java.util.logging.Logger;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnore;
import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.wibmo.trident.definition.EntityProperty;

public class Definition extends Entity {

    private static final Logger logger = Logger.getLogger(Definition.class.getName());

    private String instanceId;
    private String instanceName;
    private String channelId;
    private Map<String, String> configuration = new HashMap<String, String>();
    private Map<String, String> vocabulary = new HashMap<String, String>();
    private Map<String, String> derivedVariables = new HashMap<String, String>();
    private LinkedHashMap<String,String> auditTrailReportFiltersOptions = new LinkedHashMap<>();
    private LinkedHashMap<String,String> reportFilters = new LinkedHashMap<>();
    private LinkedHashMap<String,String> reportHeaders = new LinkedHashMap<>();
    private LinkedHashMap<String,String> csvReportHeaders = new LinkedHashMap<>();
    private Map<String, String> ruleApplicable = new HashMap<String, String>();
    private Map<String, String> ruleParameters = new HashMap<String, String>();
    private Map<String, Map<String, RatingDefinition>> ratingDefinitionMap = new HashMap<>();
    private String defaultRating;
    private Set<String> exceptionableColumns = new HashSet<String>();
    private Set<String> classificationEntityColumns = new HashSet<String>();
    private Set<String> groupEntityColumns = new HashSet<String>(); // Added for Entity grouping module ..to populate entity type dropdown
    private Set<String> primaryStatsRequired = new HashSet<String>();
    private LinkedHashMap<String,String> csvAuditTrailReportHeaders = new LinkedHashMap<>();
    //private Map<String, EntityProperty> entitiesUsed = new HashMap<String, EntityProperty>();
    boolean validationEnabled = true;
    boolean allowUnidentifiedFileds = true;
    boolean updateUnidentifiedFieldsInDefinition = true;
    private Set<String> txnSource = new HashSet<String>();
    private Set<String> primaryIdSet = new HashSet<String>();
    private Set<String> secureDataPoints = new HashSet<String>();
    private Map<String, String> secureDataPointsMasking = new HashMap<String, String>();

    private Map<String, Long> cacheTTL = new HashMap();
    private Map<String, Long> commonStatsCacheTTL = new HashMap();
    boolean saveDefinitionToDB = false;
    boolean savePrimaryStatsToDB = true;
    boolean quickRuleDebug = false;
    boolean debug = false;
    String dataEncryptionKey = "";

    boolean multiThreadedQuickRule = true;
    long quickRuleFutureTimeOut = 1000;
    long buildRuleHelperFutureTimeOut = 10000;
    long ruleExecuteFutureTimeOut = 1500;
    int elasticIndexParameter = 8;

    int sessionTTLCounter = 6;

    private long caseLockingTimeOut=120;

    //Caliberation
    int responseWaitCount = 200;
    long responseWaitSleepTime = 10;
    int dataPerperationWaitCount = 200;
    long dataPerperationWaitSleepTime = 10;

    int ttlCaseRecordsInHours = 24;
    int ttlHistoryRecordsInDays = 180;
    int ttlObservationRecordsInDays = 180;
    int ttlPrimaryStatsInMins = 600;
    int ttlPrimaryStatsCacheInMins = 60;
    boolean updatPrimaryStatsAsyncToCache = false;

    boolean storePrimaryStatsInCache = true;
    boolean storeSessionInCache = true;

    private String preProcessingImplClass;
    private String postProcessingImplClass;

    private Map<String,Set<String>> autoCaseCloseAuthType = new HashMap<String,Set<String>>();
    private String autoCaseCloseStatusActionId ;

    //Case History dependency parameters
    private LinkedHashMap<String,String> caseHistoryDisplayFilters = new LinkedHashMap<>();
    private LinkedHashMap<String,String> caseHistoryResultHeaders = new LinkedHashMap<>();
    private LinkedHashMap<String,String> csvCaseHistoryReportHeaders = new LinkedHashMap<>();

    private String cacheVersion = "1";

    private int primaryStatsMinsLimit = 60;
    private int primaryStatsHourLimit = 24;
    private int primaryStatsDayLimit = 20;

    private boolean trimPriamryStats = true;

    //Start : Maker Checker
    private Map<String,String> makerCheckerPermissions = new HashMap<>();
    boolean allowDuplicateRequestForMakerChecker = false;
    //End : Maker Checker

    //Start : ACC
    private LinkedHashMap<String,String> action = new LinkedHashMap<>();
    private LinkedHashMap<String,String> challengeType = new LinkedHashMap<>();
    private LinkedHashMap<String,String> finalStatus = new LinkedHashMap<>();
    private LinkedHashMap<String,String> reason = new LinkedHashMap<>();
    //End : ACC

    //Start :: External Url Configurations
    private LinkedHashMap<String,LinkedHashMap<String,String>> externalAPIConfiguration = new LinkedHashMap<>();
    //End :: External Url Configurations

    private Map<String,LinkedHashSet<String>> entityCategories = new LinkedHashMap<>();

    private int downloadPageSize = 1000;
    private long downloadConnectRetryTime = 60000;
    private int downloadRetryCount = 2;
    private int lessDataRetryCount = 1;
    private Set<String> groupEntityRangeColumns = new HashSet<String>();
    private String baseCurrencyCode = "356";
    private Map<String,String> instanceInfo = new HashMap<>();
    @JsonProperty
    public Set<String> getPrimaryStatsRequired() {
        return primaryStatsRequired;
    }

    @JsonProperty
    public void setPrimaryStatsRequired(Set<String> primaryStatsRequired) {
        this.primaryStatsRequired = primaryStatsRequired;
    }
    @JsonProperty
    public Map<String, Long> getCommonStatsCacheTTL() {
        return commonStatsCacheTTL;
    }
    @JsonProperty
    public void setCommonStatsCacheTTL(Map<String, Long> commonStatsCacheTTL) {
        this.commonStatsCacheTTL = commonStatsCacheTTL;
    }
    @JsonProperty
    public int getPrimaryStatsMinsLimit() {
        return primaryStatsMinsLimit;
    }
    @JsonProperty
    public void setPrimaryStatsMinsLimit(int primaryStatsMinsLimit) {
        this.primaryStatsMinsLimit = primaryStatsMinsLimit;
    }
    @JsonProperty
    public int getPrimaryStatsHourLimit() {
        return primaryStatsHourLimit;
    }
    @JsonProperty
    public void setPrimaryStatsHourLimit(int primaryStatsHourLimit) {
        this.primaryStatsHourLimit = primaryStatsHourLimit;
    }
    @JsonProperty
    public int getPrimaryStatsDayLimit() {
        return primaryStatsDayLimit;
    }
    @JsonProperty
    public void setPrimaryStatsDayLimit(int primaryStatsDayLimit) {
        this.primaryStatsDayLimit = primaryStatsDayLimit;
    }
    @JsonProperty
    public boolean isTrimPriamryStats() {
        return trimPriamryStats;
    }
    @JsonProperty
    public void setTrimPriamryStats(boolean trimPriamryStats) {
        this.trimPriamryStats = trimPriamryStats;
    }

    @JsonProperty
    public boolean isSavePrimaryStatsToDB() {
        return savePrimaryStatsToDB;
    }
    @JsonProperty
    public void setSavePrimaryStatsToDB(boolean savePrimaryStatsToDB) {
        this.savePrimaryStatsToDB = savePrimaryStatsToDB;
    }

    @JsonProperty
    public boolean isDebug() {
        return debug;
    }
    @JsonProperty
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @JsonProperty
    public LinkedHashMap<String, String> getAuditTrailReportFiltersOptions() {
        return auditTrailReportFiltersOptions;
    }

    @JsonProperty
    public void setAuditTrailReportFiltersOptions(LinkedHashMap<String, String> auditTrailReportFiltersOptions) {
        this.auditTrailReportFiltersOptions = auditTrailReportFiltersOptions;
    }

    @JsonProperty
    public LinkedHashMap<String, String> getReportFilters() {
        return reportFilters;
    }

    @JsonProperty
    public void setReportFilters(LinkedHashMap<String, String> reportFilters) {
        this.reportFilters = reportFilters;
    }

    @JsonProperty
    public LinkedHashMap<String, String> getCsvReportHeaders() {
        return csvReportHeaders;
    }

    @JsonProperty
    public void setCsvReportHeaders(LinkedHashMap<String, String> csvReportHeaders) {
        this.csvReportHeaders = csvReportHeaders;
    }

    @JsonProperty
    public LinkedHashMap<String, String> getReportHeaders() {
        return reportHeaders;
    }
    @JsonProperty
    public void setReportHeaders(LinkedHashMap<String, String> reportHeaders) {
        this.reportHeaders = reportHeaders;
    }

    @JsonProperty
    public Map<String, String> getDerivedVariables() {
        return derivedVariables;
    }

    @JsonProperty
    public void setDerivedVariables(Map<String, String> derivedVariables) {
        this.derivedVariables = derivedVariables;
    }

    @JsonProperty
    public String getCacheVersion() {
        return cacheVersion;
    }
    @JsonProperty
    public void setCacheVersion(String cacheVersion) {
        this.cacheVersion = cacheVersion;
    }

    @JsonProperty
    public String getAutoCaseCloseStatusActionId() {
        return autoCaseCloseStatusActionId;
    }
    @JsonProperty
    public void setAutoCaseCloseStatusActionId(String autoCaseCloseStatusActionId) {
        this.autoCaseCloseStatusActionId = autoCaseCloseStatusActionId;
    }

    @JsonProperty
    public Map<String, Set<String>> getAutoCaseCloseAuthType() {
        return autoCaseCloseAuthType;
    }
    @JsonProperty
    public void setAutoCaseCloseAuthType(Map<String, Set<String>> autoCaseCloseAuthType) {
        this.autoCaseCloseAuthType = autoCaseCloseAuthType;
    }

    @JsonProperty
    public Map<String, String> getSecureDataPointsMasking() {
        return secureDataPointsMasking;
    }


    @JsonProperty
    public void setSecureDataPointsMasking(Map<String, String> secureDataPointsMasking) {
        this.secureDataPointsMasking = secureDataPointsMasking;
    }

    @JsonProperty
    public Set<String> getSecureDataPoints() {
        return secureDataPoints;
    }

    @JsonProperty
    public void setSecureDataPoints(Set<String> secureDataPoints) {
        this.secureDataPoints = secureDataPoints;
    }

    @JsonProperty
    public String getDataEncryptionKey() {
        return dataEncryptionKey;
    }

    @JsonProperty
    public void setDataEncryptionKey(String dataEncryptionKey) {
        this.dataEncryptionKey = dataEncryptionKey;
    }

    @JsonProperty
    public int getTtlCaseRecordsInHours() {
        return ttlCaseRecordsInHours;
    }
    @JsonProperty
    public void setTtlCaseRecordsInHours(int ttlCaseRecordsInHours) {
        this.ttlCaseRecordsInHours = ttlCaseRecordsInHours;
    }

    @JsonProperty
    public int getTtlObservationRecordsInDays() {
        return ttlObservationRecordsInDays;
    }

    @JsonProperty
    public void setTtlObservationRecordsInDays(int ttlObservationRecordsInDays) {
        this.ttlObservationRecordsInDays = ttlObservationRecordsInDays;
    }

    @JsonProperty
    public int getTtlHistoryRecordsInDays() {
        return ttlHistoryRecordsInDays;
    }
    @JsonProperty
    public void setTtlHistoryRecordsInDays(int ttlHistoryRecordsInDays) {
        this.ttlHistoryRecordsInDays = ttlHistoryRecordsInDays;
    }

    @JsonProperty
    public int getTtlPrimaryStatsInMins() {
        return ttlPrimaryStatsInMins;
    }

    @JsonProperty
    public void setTtlPrimaryStatsInMins(int ttlPrimaryStatsInMins) {
        this.ttlPrimaryStatsInMins = ttlPrimaryStatsInMins;
    }

    @JsonProperty
    public int getTtlPrimaryStatsCacheInMins() {
        return ttlPrimaryStatsCacheInMins;
    }

    @JsonProperty
    public void setTtlPrimaryStatsCacheInMins(int ttlPrimaryStatsCacheInMins) {
        this.ttlPrimaryStatsCacheInMins = ttlPrimaryStatsCacheInMins;
    }

    @JsonProperty
    public long getBuildRuleHelperFutureTimeOut() {
        return buildRuleHelperFutureTimeOut;
    }

    @JsonProperty
    public void setBuildRuleHelperFutureTimeOut(long buildRuleHelperFutureTimeOut) {
        this.buildRuleHelperFutureTimeOut = buildRuleHelperFutureTimeOut;
    }

    @JsonProperty
    public long getRuleExecuteFutureTimeOut() {
        return ruleExecuteFutureTimeOut;
    }

    @JsonProperty
    public void setRuleExecuteFutureTimeOut(long ruleExecuteFutureTimeOut) {
        this.ruleExecuteFutureTimeOut = ruleExecuteFutureTimeOut;
    }

    @JsonProperty
    public boolean isQuickRuleDebug() {
        return quickRuleDebug;
    }

    @JsonProperty
    public void setQuickRuleDebug(boolean quickRuleDebug) {
        this.quickRuleDebug = quickRuleDebug;
    }

    @JsonProperty
    public int getSessionTTLCounter() {
        return sessionTTLCounter;
    }

    @JsonProperty
    public void setSessionTTLCounter(int sessionTTLCounter) {
        this.sessionTTLCounter = sessionTTLCounter;
    }

    @JsonProperty
    public long getQuickRuleFutureTimeOut() {
        return quickRuleFutureTimeOut;
    }

    @JsonProperty
    public void setQuickRuleFutureTimeOut(long quickRuleFutureTimeOut) {
        this.quickRuleFutureTimeOut = quickRuleFutureTimeOut;
    }

    @JsonProperty
    public boolean isMultiThreadedQuickRule() {
        return multiThreadedQuickRule;
    }

    @JsonProperty
    public void setMultiThreadedQuickRule(boolean multiThreadedQuickRule) {
        this.multiThreadedQuickRule = multiThreadedQuickRule;
    }

    public boolean isSaveDefinitionToDB() {
        return saveDefinitionToDB;
    }

    public void setSaveDefinitionToDB(boolean saveDefinitionToDB) {
        this.saveDefinitionToDB = saveDefinitionToDB;
    }
    @JsonProperty
    public Map<String, Long> getCacheTTL() {
        return cacheTTL;
    }

    @JsonProperty
    public void setCacheTTL(Map<String, Long> cacheTTL) {
        this.cacheTTL = cacheTTL;
    }

    @JsonProperty
    public Set<String> getPrimaryIdSet() {
        return primaryIdSet;
    }

    @JsonProperty
    public void setPrimaryIdSet(Set<String> primaryIdSet) {
        this.primaryIdSet = primaryIdSet;
    }

    @JsonProperty
    public Set<String> getTxnSource() {
        return txnSource;
    }

    @JsonProperty
    public void setTxnSource(Set<String> txnSource) {
        this.txnSource = txnSource;
    }

    @JsonProperty
    public int getDataPerperationWaitCount() {
        return dataPerperationWaitCount;
    }

    @JsonProperty
    public void setDataPerperationWaitCount(int dataPerperationWaitCount) {
        this.dataPerperationWaitCount = dataPerperationWaitCount;
    }

    @JsonProperty
    public long getDataPerperationWaitSleepTime() {
        return dataPerperationWaitSleepTime;
    }

    @JsonProperty
    public void setDataPerperationWaitSleepTime(long dataPerperationWaitSleepTime) {
        this.dataPerperationWaitSleepTime = dataPerperationWaitSleepTime;
    }

    @JsonProperty
    public int getResponseWaitCount() {
        return responseWaitCount;
    }

    @JsonProperty
    public void setResponseWaitCount(int responseWaitCount) {
        this.responseWaitCount = responseWaitCount;
    }

    @JsonProperty
    public long getResponseWaitSleepTime() {
        return responseWaitSleepTime;
    }

    @JsonProperty
    public void setResponseWaitSleepTime(long responseWaitSleepTime) {
        this.responseWaitSleepTime = responseWaitSleepTime;
    }

    @JsonProperty
    public boolean isValidationEnabled() {
        return validationEnabled;
    }

    @JsonProperty
    public void setValidationEnabled(boolean validationEnabled) {
        this.validationEnabled = validationEnabled;
    }

    @JsonProperty
    public boolean isAllowUnidentifiedFileds() {
        return allowUnidentifiedFileds;
    }

    @JsonProperty
    public void setAllowUnidentifiedFileds(boolean allowUnidentifiedFileds) {
        this.allowUnidentifiedFileds = allowUnidentifiedFileds;
    }

    @JsonProperty
    public boolean isUpdateUnidentifiedFieldsInDefinition() {
        return updateUnidentifiedFieldsInDefinition;
    }

    @JsonProperty
    public void setUpdateUnidentifiedFieldsInDefinition(boolean updateUnidentifiedFieldsInDefinition) {
        this.updateUnidentifiedFieldsInDefinition = updateUnidentifiedFieldsInDefinition;
    }

    @JsonIgnore
    private Map<String, EntityProperty> unidentifiedEntities = new HashMap<String, EntityProperty>();

    @JsonIgnore
    public Map<String, EntityProperty> getUnidentifiedEntities() {
        return unidentifiedEntities;
    }

    @JsonIgnore
    public void setUnidentifiedEntities(Map<String, EntityProperty> unidentifiedEntities) {
        this.unidentifiedEntities = unidentifiedEntities;
    }

/*	@JsonProperty
	public Map<String, EntityProperty> getEntitiesUsed() {
		return entitiesUsed;
	}

	@JsonProperty
	public void setEntitiesUsed(Map<String, EntityProperty> entitiesUsed) {
		this.entitiesUsed = entitiesUsed;
	}*/

    @JsonProperty
    public Map<String, String> getRuleApplicable() {
        return ruleApplicable;
    }

    @JsonProperty
    public void setRuleApplicable(Map<String, String> ruleApplicable) {
        this.ruleApplicable = ruleApplicable;
    }

    @JsonProperty
    public Set<String> getExceptionableColumns() {
        return exceptionableColumns;
    }

    @JsonProperty
    public void setExceptionableColumns(Set<String> exceptionableColumns) {
        this.exceptionableColumns = exceptionableColumns;
    }

    public Set<String> getClassificationEntityColumns() {
        return classificationEntityColumns;
    }
    public void setClassificationEntityColumns(Set<String> classificationEntityColumns) {
        this.classificationEntityColumns = classificationEntityColumns;
    }
    public Set<String> getGroupEntityColumns() {
        return groupEntityColumns;
    }
    public void setGroupEntityColumns(Set<String> groupEntityColumns) {
        this.groupEntityColumns = groupEntityColumns;
    }
    @JsonProperty
    public String getInstanceId() {
        return instanceId;
    }

    @JsonProperty
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty
    public String getInstanceName() {
        return instanceName;
    }

    @JsonProperty
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @JsonProperty
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty
    public Map<String, String>  getConfiguration() {
        return configuration;
    }

    @JsonProperty
    public void setConfiguration(Map configuration) {
        this.configuration = configuration;
    }

    @JsonProperty
    public Map getVocabulary() {
        return vocabulary;
    }

    @JsonProperty
    public void setVocabulary(Map vocabulary) {
        this.vocabulary = vocabulary;
    }

    @JsonProperty
    public Map<String,String> getRuleParameters() {
        return ruleParameters;
    }

    @JsonProperty
    public void setRuleParameters(Map ruleParameters) {
        this.ruleParameters = ruleParameters;
    }

    @JsonProperty
    public Map<String, Map<String, RatingDefinition>> getRatingDefinitionMap() {
        return ratingDefinitionMap;
    }

    @JsonProperty
    public void setRatingDefinitionMap(Map<String, Map<String, RatingDefinition>> ratingDefinitionMap) {
        this.ratingDefinitionMap = ratingDefinitionMap;
    }

    @JsonProperty
    public String getDefaultRating() {
        return defaultRating;
    }

    @JsonProperty
    public void setDefaultRating(String defaultRating) {
        this.defaultRating = defaultRating;
    }

    @JsonProperty
    public String getPreProcessingImplClass() {
        return preProcessingImplClass;
    }

    @JsonProperty
    public void setPreProcessingImplClass(String preProcessingImplClass) {
        this.preProcessingImplClass = preProcessingImplClass;
    }

    @JsonProperty
    public String getPostProcessingImplClass() {
        return postProcessingImplClass;
    }

    @JsonProperty
    public void setPostProcessingImplClass(String postProcessingImplClass) {
        this.postProcessingImplClass = postProcessingImplClass;
    }
    @JsonProperty
    public boolean isStoreSessionInCache() {
        return storeSessionInCache;
    }
    @JsonProperty
    public void setStoreSessionInCache(boolean storeSessionInCache) {
        this.storeSessionInCache = storeSessionInCache;
    }
    @JsonProperty
    public boolean isStorePrimaryStatsInCache() {
        return storePrimaryStatsInCache;
        //return false;
    }
    @JsonProperty
    public void setStorePrimaryStatsInCache(boolean storePrimaryStatsInCache) {
        this.storePrimaryStatsInCache = storePrimaryStatsInCache;
    }

    @JsonProperty
    public boolean isUpdatPrimaryStatsAsyncToCache() {
        return updatPrimaryStatsAsyncToCache;
    }
    @JsonProperty
    public void setUpdatPrimaryStatsAsyncToCache(boolean updatPrimaryStatsAsyncToCache) {
        this.updatPrimaryStatsAsyncToCache = updatPrimaryStatsAsyncToCache;
    }

    @JsonProperty
    public LinkedHashMap<String, String> getCaseHistoryDisplayFilters() {
        return caseHistoryDisplayFilters;
    }
    @JsonProperty
    public void setCaseHistoryDisplayFilters(LinkedHashMap<String, String> caseHistoryDisplayFilters) {
        this.caseHistoryDisplayFilters = caseHistoryDisplayFilters;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getCaseHistoryResultHeaders() {
        return caseHistoryResultHeaders;
    }
    @JsonProperty
    public void setCaseHistoryResultHeaders(LinkedHashMap<String, String> caseHistoryResultHeaders) {
        this.caseHistoryResultHeaders = caseHistoryResultHeaders;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getCsvCaseHistoryReportHeaders() {
        return csvCaseHistoryReportHeaders;
    }
    @JsonProperty
    public void setCsvCaseHistoryReportHeaders(LinkedHashMap<String, String> csvCaseHistoryReportHeaders) {
        this.csvCaseHistoryReportHeaders = csvCaseHistoryReportHeaders;
    }
    @JsonProperty
    public Map<String, String> getMakerCheckerPermissions() {
        return makerCheckerPermissions;
    }
    @JsonProperty
    public void setMakerCheckerPermissions(Map<String, String> makerCheckerPermissions) {
        this.makerCheckerPermissions = makerCheckerPermissions;
    }
    @JsonProperty
    public boolean isAllowDuplicateRequestForMakerChecker() {
        return allowDuplicateRequestForMakerChecker;
    }
    @JsonProperty
    public void setAllowDuplicateRequestForMakerChecker(boolean allowDuplicateRequestForMakerChecker) {
        this.allowDuplicateRequestForMakerChecker = allowDuplicateRequestForMakerChecker;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getAction() {
        return action;
    }
    @JsonProperty
    public void setAction(LinkedHashMap<String, String> action) {
        this.action = action;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getChallengeType() {
        return challengeType;
    }
    @JsonProperty
    public void setChallengeType(LinkedHashMap<String, String> challengeType) {
        this.challengeType = challengeType;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getFinalStatus() {
        return finalStatus;
    }
    @JsonProperty
    public void setFinalStatus(LinkedHashMap<String, String> finalStatus) {
        this.finalStatus = finalStatus;
    }
    @JsonProperty
    public LinkedHashMap<String, String> getReason() {
        return reason;
    }
    @JsonProperty
    public void setReason(LinkedHashMap<String, String> reason) {
        this.reason = reason;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getExternalAPIConfiguration() {
        return externalAPIConfiguration;
    }

    public void setExternalAPIConfiguration(LinkedHashMap<String, LinkedHashMap<String, String>> externalAPIConfiguration) {
        this.externalAPIConfiguration = externalAPIConfiguration;
    }



    //public LinkedHashMap<String,String> getCustomerContactDetailsParams() {
    //	return customerContactDetailsParams;
    //}

    //public void setCustomerContactDetailsParams(LinkedHashMap<String,String> customerContactDetailsParams) {
    //	this.customerContactDetailsParams = customerContactDetailsParams;
    //}

    public int getDownloadPageSize() {
        return downloadPageSize;
    }

    public void setDownloadPageSize(int downloadPageSize) {
        this.downloadPageSize = downloadPageSize;
    }



    public long getDownloadConnectRetryTime() {
        return downloadConnectRetryTime;
    }

    public void setDownloadConnectRetryTime(long downloadConnectRetryTime) {
        this.downloadConnectRetryTime = downloadConnectRetryTime;
    }

    public int getDownloadRetryCount() {
        return downloadRetryCount;
    }

    public void setDownloadRetryCount(int downloadRetryCount) {
        this.downloadRetryCount = downloadRetryCount;
    }



    public int getLessDataRetryCount() {
        return lessDataRetryCount;
    }

    public void setLessDataRetryCount(int lessDataRetryCount) {
        this.lessDataRetryCount = lessDataRetryCount;
    }



    public int getElasticIndexParameter() {
        return elasticIndexParameter;
    }

    public void setElasticIndexParameter(int elasticIndexParameter) {
        this.elasticIndexParameter = elasticIndexParameter;
    }

    @JsonProperty
    public Set<String> getGroupEntityRangeColumns() {
        return groupEntityRangeColumns;
    }
    @JsonProperty
    public void setGroupEntityRangeColumns(Set<String> groupEntityRangeColumns) {
        this.groupEntityRangeColumns = groupEntityRangeColumns;
    }

    @JsonProperty
    @Override
    public String getId() {
        StringBuilder id = new StringBuilder();
        //id.append("DEF").append("::");
        id.append(instanceId).append("::");
        id.append(channelId);
        return id.toString();
    }

    @Override
    public String toString() {
        return "Definition{" +
                "instanceId='" + instanceId + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", channelId='" + channelId + '\'' +
                ", configuration=" + configuration +
                ", vocabulary=" + vocabulary +
                ", ruleApplicable=" + ruleApplicable +
                ", ruleParameters=" + ruleParameters +
                ", ratingDefinitionMap=" + ratingDefinitionMap +
                ", exceptionableColumns=" + exceptionableColumns +
                /*", entitiesUsed=" + entitiesUsed +*/
                ", validationEnabled=" + validationEnabled +
                ", allowUnidentifiedFileds=" + allowUnidentifiedFileds +
                ", updateUnidentifiedFieldsInDefinition=" + updateUnidentifiedFieldsInDefinition +
                ", txnSource=" + txnSource +
                ", primaryIdSet=" + primaryIdSet +
                ", cacheTTL=" + cacheTTL +
                ", saveDefinitionToDB=" + saveDefinitionToDB +
                ", quickRuleDebug=" + quickRuleDebug +
                ", multiThreadedQuickRule=" + multiThreadedQuickRule +
                ", quickRuleFutureTimeOut=" + quickRuleFutureTimeOut +
                ", sessionTTLCounter=" + sessionTTLCounter +
                ", responseWaitCount=" + responseWaitCount +
                ", responseWaitSleepTime=" + responseWaitSleepTime +
                ", dataPerperationWaitCount=" + dataPerperationWaitCount +
                ", dataPerperationWaitSleepTime=" + dataPerperationWaitSleepTime +
                ", preProcessingImplClass='" + preProcessingImplClass + '\'' +
                ", postProcessingImplClass='" + postProcessingImplClass + '\'' +
                ", unidentifiedEntities=" + unidentifiedEntities +
                ", action="+action +
                ", challengeType="+ challengeType +
                ", finalStatus="+ finalStatus  +
                ", reason="+reason +
                ", externalAPIConfiguration="+ externalAPIConfiguration +
                ", downloadPageSize="+ downloadPageSize +
                ", downloadConnectRetryTime="+ downloadConnectRetryTime+
                ", downloadRetryCount="+ downloadRetryCount+
                ", lessDataRetryCount="+ lessDataRetryCount+
                ", entityCategories="+ entityCategories+
                '}';
    }
    @JsonProperty
    public LinkedHashMap<String,String> getCsvAuditTrailReportHeaders() {
        return csvAuditTrailReportHeaders;
    }
    @JsonProperty
    public void setCsvAuditTrailReportHeaders(LinkedHashMap<String,String> csvAuditTrailReportHeaders) {
        this.csvAuditTrailReportHeaders = csvAuditTrailReportHeaders;
    }

    @JsonProperty
    public long getCaseLockingTimeOut() {
        return caseLockingTimeOut;
    }
    @JsonProperty
    public void setCaseLockingTimeOut(long caseLockingTimeOut) {
        this.caseLockingTimeOut = caseLockingTimeOut;
    }
    @JsonProperty
    public Map<String, LinkedHashSet<String>> getEntityCategories() {
        return entityCategories;
    }
    @JsonProperty
    public void setEntityCategories(Map<String, LinkedHashSet<String>> entityCategories) {
        this.entityCategories = entityCategories;
    }


    /*
     * cardNo
     * customerId
     * UID
     * pan
     * deviceId
     */
    @JsonProperty
    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }
    @JsonProperty
    public void setBaseCurrencyCode(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }
    @JsonProperty
    public Map<String, String> getInstanceInfo() {
        return instanceInfo;
    }
    @JsonProperty
    public void setInstanceInfo(Map<String, String> instanceInfo) {
        this.instanceInfo = instanceInfo;
    }

}
