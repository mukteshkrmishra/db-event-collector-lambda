package com.event.collector.model;

import java.util.Arrays;
import java.util.Map;

public class HystrixCircuitEvent {
	String measurement;
	String[] tags;
	Map<String,String> fields;
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public Map<String, String> getFields() {
		return fields;
	}
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {
		return "HystrixCircuitEvent [measurement=" + measurement + ", tags=" + Arrays.toString(tags) + ", fields="
				+ fields + "]";
	}
	
}
