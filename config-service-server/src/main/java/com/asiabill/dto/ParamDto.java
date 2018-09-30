package com.asiabill.dto;

import java.io.Serializable;

public class ParamDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String age;
	
	private boolean flage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean getFlage() {
		return flage;
	}

	public void setFlage(boolean flage) {
		this.flage = flage;
	}
	
}
