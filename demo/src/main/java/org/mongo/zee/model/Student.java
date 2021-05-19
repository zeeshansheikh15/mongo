package org.mongo.zee.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
	
	private String name;	
	private String id;
	private String city;
	private long salary;
	
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
