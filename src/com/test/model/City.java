package com.test.model;

import java.util.UUID;

public class City {

	private final String id;
	private String name;
	
	
	public City(String name)
	{
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
