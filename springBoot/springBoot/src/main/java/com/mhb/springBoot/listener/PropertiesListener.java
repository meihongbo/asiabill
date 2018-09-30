package com.mhb.springBoot.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import com.mhb.springBoot.configuration.PropertiesListenerConfig;

public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {
	
	private String propertyFileName;

    public PropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		PropertiesListenerConfig.loadAllProperties(propertyFileName);
	}

}
