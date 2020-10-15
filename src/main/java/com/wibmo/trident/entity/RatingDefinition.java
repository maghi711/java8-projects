package com.wibmo.trident.entity;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.logging.Logger;


/**
	 * Author : ifteqar Ahmed
	 */
//TODO this class need some fix - Akshath
public class RatingDefinition implements Serializable {
	static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RatingDefinition.class.getName());
	private int rdId;
	private String name;
	private int scoreFrom;
	private int scoreTo;
	private String color;
	private String description;
	
	@JsonProperty
	public int getRdId() {
		return rdId;
	}

	@JsonProperty
	public void setRdId(int rdId) {
		this.rdId = rdId;
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
	public String getDescription() {
		return description;
	}

	@JsonProperty
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public int getScoreFrom() {
		return scoreFrom;
	}
	
	@JsonProperty
	public int getScoreTo() {
		return scoreTo;
	}
	
	@JsonProperty
	public String getColor() {
		return color;
	}
	
	@JsonProperty
	public void setScoreFrom(int scoreFrom) {
		this.scoreFrom = scoreFrom;
	}
	
	@JsonProperty
	public void setScoreTo(int scoreTo) {
		this.scoreTo = scoreTo;
	}
	
	@JsonProperty
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "RatingDefinition{" +
				"rdId=" + rdId +
				", name='" + name + '\'' +
				", scoreFrom=" + scoreFrom +
				", scoreTo=" + scoreTo +
				", color='" + color + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
