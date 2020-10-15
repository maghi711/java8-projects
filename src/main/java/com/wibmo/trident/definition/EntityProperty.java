package com.wibmo.trident.definition;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnore;
import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.fins.org.json.JSONObject;
import com.wibmo.trident.entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EntityProperty extends Entity implements Serializable {
	static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EntityProperty.class.getName());
	//same as columnid
	
	
	String instanceId;
	String channelId;
	
	String paramId = "";
	String name = "";
	boolean buildRuleHelper = false;
	boolean buildRuleSummary = false;	
	boolean buildMinStatistics = false;
	boolean buildDayStatistics = false;
	boolean buildMonthStatistics = false;	
	boolean skipValidation = false;	
	String alternateName;
	
	String dataType = ""; //numeric, string, date,  
	String format = ""; // specific format 
	String description = "";
	int minLength = 0;
	int maxLength = 0;
	int requirement = 1; // 1-Required, 2-optional 0-not required
	boolean nullable = false;	
	
	//QuickRules related
	private String displayName;
	private String group;
	private String entityId;
	private String label;
	private String default_value;
	private String size;
	private String values;
	private String ruleDatatype; // 'string', 'integer', 'double', 'date', 'time', 'datetime',	// 'boolean'
	private String input; // 'text', 'textarea', 'radio', 'checkbox', 'select'
	private List<String> operators = new ArrayList(); //

	private String validation;
	private String multiple; // works in the case of select onlyu
	private String rows; // works in the case of text area only
	private String qrStatus;
	private String classificationStatus;
    private Map<String,String> selectOptionValues;
    //Auto Closure Filter Types :: start
    private String filterType;
    private LinkedHashMap<String,String> filterValues = new LinkedHashMap<>();;
    //Auto Closure Filter Types :: end 

	@JsonProperty
	public String getClassificationStatus() {
		return classificationStatus;
	}

	@JsonProperty
	public void setClassificationStatus(String classificationStatus) {
		this.classificationStatus = classificationStatus;
	}

	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getAlternateName() {
		return alternateName;
	}

	@JsonProperty
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	@JsonProperty
	public boolean isSkipValidation() {
		return skipValidation;
	}

	@JsonProperty
	public void setSkipValidation(boolean skipValidation) {
		this.skipValidation = skipValidation;
	}

	@JsonProperty
	public String getDataType() {
		return dataType;
	}
	
	@JsonProperty
	public void setDataType(String type) {
		this.dataType = type;
	}
	
	@JsonProperty
	public String getFormat() {
		return format;
	}
	
	@JsonProperty
	public void setFormat(String format) {
		this.format = format;
	}
	
	@JsonProperty
	public String getDescription() {
		return description;
	}
	
	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public int getMinLength() {
		return minLength;
	}
	
	@JsonProperty
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	
	@JsonProperty
	public int getMaxLength() {
		return maxLength;
	}
	
	@JsonProperty
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	@JsonProperty
	public int getRequirement() {
		return requirement;
	}
	
	@JsonProperty
	public void setRequirement(int requirement) {
		this.requirement = requirement;
	}
	
	@JsonProperty
	public boolean isNullable() {
		return nullable;
	}
	
	@JsonProperty
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	@JsonProperty
	public String getParamId() {
		return paramId;
	}
	@JsonProperty
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	@JsonProperty
	public boolean isBuildRuleHelper() {
		return buildRuleHelper;
	}
	
	@JsonProperty
	public void setBuildRuleHelper(boolean buildRuleHelper) {
		this.buildRuleHelper = buildRuleHelper;
	}
	
	@JsonProperty
	public boolean isBuildRuleSummary() {
		return buildRuleSummary;
	}
	
	@JsonProperty
	public void setBuildRuleSummary(boolean buildRuleSummary) {
		this.buildRuleSummary = buildRuleSummary;
	}
	
	@JsonProperty
	public boolean isBuildMinStatistics() {
		return buildMinStatistics;
	}
	
	@JsonProperty
	public void setBuildMinStatistics(boolean buildMinStatistics) {
		this.buildMinStatistics = buildMinStatistics;
	}
	
	@JsonProperty
	public boolean isBuildDayStatistics() {
		return buildDayStatistics;
	}
	
	@JsonProperty
	public void setBuildDayStatistics(boolean buildDayStatistics) {
		this.buildDayStatistics = buildDayStatistics;
	}
	
	@JsonProperty
	public boolean isBuildMonthStatistics() {
		return buildMonthStatistics;
	}
	
	@JsonProperty
	public void setBuildMonthStatistics(boolean buildMonthStatistics) {
		this.buildMonthStatistics = buildMonthStatistics;
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
	public String getChannelId() {
		return channelId;
	}

	@JsonProperty
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@JsonProperty
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonProperty
	public String getGroup() {
		return group;
	}

	@JsonProperty
	public void setGroup(String group) {
		this.group = group;
	}

	@JsonProperty
	@Override
	public String getId() {
		StringBuilder id = new StringBuilder();
		id.append("ENTITIES").append("::");
		id.append(instanceId).append("::");
		id.append(channelId).append("::");
		id.append(entityId);
		return id.toString();
	}
	
	
	@JsonProperty
	public String getLabel() {
		return label;
	}
	
	@JsonProperty
	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty
	public String getDefault_value() {
		return default_value;
	}

	
	@JsonProperty
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	
	@JsonProperty
	public String getSize() {
		return size;
	}

	@JsonProperty
	public void setSize(String size) {
		this.size = size;
	}

	@JsonProperty
	public String getValues() {
		return values;
	}

	@JsonProperty
	public void setValues(String values) {
		this.values = values;
	}

	@JsonProperty
	public String getRuleDatatype() {
		return ruleDatatype;
	}

	@JsonProperty
	public void setRuleDatatype(String ruleDatatype) {
		this.ruleDatatype = ruleDatatype;
	}

	@JsonProperty
	public String getInput() {
		return input;
	}

	@JsonProperty
	public void setInput(String input) {
		this.input = input;
	}

	@JsonProperty
	public List<String> getOperators() {
		return operators;
	}

	@JsonProperty
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}

	@JsonProperty
	public String getValidation() {
		return validation;
	}

	@JsonProperty
	public void setValidation(String validation) {
		this.validation = validation;
	}

	@JsonProperty
	public String getMultiple() {
		return multiple;
	}

	@JsonProperty
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	@JsonProperty
	public String getRows() {
		return rows;
	}

	@JsonProperty
	public void setRows(String rows) {
		this.rows = rows;
	}

	@JsonProperty
	public String getQrStatus() {
		return qrStatus;
	}

	@JsonProperty
	public void setQrStatus(String qrStatus) {
		this.qrStatus = qrStatus;
	}
	
	@JsonProperty
	public String getEntityId() {
		return entityId;
	}
	
	@JsonProperty
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	
	//Auto Closure Transaction Search Filter Properties :: Start
	@JsonProperty
	public String getFilterType() {
		return filterType;
	}
	@JsonProperty
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	
	@JsonProperty
	public LinkedHashMap<String, String> getFilterValues() {
		return filterValues;
	}
	@JsonProperty
	public void setFilterValues(LinkedHashMap<String, String> filterValues) {
		this.filterValues = filterValues;
	}
	//Auto Closure Transaction Search Filter Properties :: End
	@Override
	public String toString() {
		return "EntityProperty [paramId=" + paramId + ", buildRuleHelper=" + buildRuleHelper + ", buildRuleSummary="
				+ buildRuleSummary + ", buildMinStatistics=" + buildMinStatistics + ", buildDayStatistics="
				+ buildDayStatistics + ", buildMonthStatistics=" + buildMonthStatistics + ", type=" + dataType + ", format="
				+ format + ", description=" + description + ", minLength=" + minLength + ", maxLength=" + maxLength
				+ ", requirement=" + requirement + ", nullable=" + nullable + ", filterType="+ filterType + ", filterValues="+filterValues+"]";
	}

        public Map<String, String> getSelectOptionValues() {
            return selectOptionValues;
        }

        public void setSelectOptionValues(Map<String, String> selectOptionValues) {
            this.selectOptionValues = selectOptionValues;
        }
	
	
	
	
	/*
	@JsonIgnore
	public static String getAllFilterJson(){
		StringBuilder sb = new StringBuilder();
		
		Iterator<EntityProperty> it = allConditions.iterator();
		while(it.hasNext()){
			ColumnInfo con = it.next();			
			sb.append(con.getJsonString());
			sb.append(",");
		}
		
		//logger.info("All conditions >>"+sb.toString()+"<<");
		return sb.toString();		
	} 
	*/
	@JsonIgnore
	public String getJsonString() {		
		JSONObject j = new JSONObject();
		List arl = null;
		
		arl = operators;
	
		if (arl != null) {
			j.put("operators", arl);
		}

		if (GeneralUtility.isBlankNull(group) == false) {
			j.put("optgroup", group);
		}

		if (GeneralUtility.isBlankNull(getId()) == false) {
			j.put("id", getName());
		}
		if (GeneralUtility.isBlankNull(displayName) == false) {
			j.put("label", displayName);
		}

		if (GeneralUtility.isBlankNull(default_value) == false) {
			j.put("default_value",default_value);
		}
		if (GeneralUtility.isBlankNull(size) == false) {
			j.put("size", size);
		}
		
		if (GeneralUtility.isBlankNull(values) == false) {			
			Map keyval = GeneralUtility.getCSVtoMap(values);
			j.put("values", keyval);
		}else {
                    j.put("values", getSelectOptionValues());
                }
                
		if (GeneralUtility.isBlankNull(getRuleDatatype()) == false) {
			j.put("type", getRuleDatatype().toLowerCase());
		}
		if (GeneralUtility.isBlankNull(ruleDatatype) == false) {
			j.put("input", ruleDatatype);
		}
                if (GeneralUtility.isBlankNull(ruleDatatype) == false) {
			j.put("input", input);
		}else{
                    j.put("input", "text");
                }
		
		if (GeneralUtility.isBlankNull(multiple) == false) {
			j.put("multiple", multiple);
		}
		
		if (GeneralUtility.isBlankNull(rows) == false) {
			j.put("rows", rows);
		}
		
		if (GeneralUtility.isBlankNull(description) == false) {
			j.put("description", description);
		}
                if (GeneralUtility.isBlankNull(label) == false) {
			j.put("label", label);
		}

		return j.toString();
	}
	
	
}
