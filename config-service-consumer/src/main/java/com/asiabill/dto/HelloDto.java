package com.asiabill.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String age;
	private Date time;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "HelloDto [name=" + name + ", age=" + age + ", time=" + sdf.format(time) + "]";
	}

}
