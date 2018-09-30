package com.mhb.springBoot.configuration;

public class WiselyConfiguration {
	
	public static String getName() {
		return PropertiesListenerConfig.getProperty("wisely2.name");
	}
	
	public static String getGender() {
		return PropertiesListenerConfig.getProperty("wisely2.gender");
	}
	
	public static String getAge() {
		return PropertiesListenerConfig.getProperty("wisely2.age");
	}

}
