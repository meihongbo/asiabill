package com.lj.base.mvc.web.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 
 * 
 * 类说明：SPRING WEB入参转化工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class DateEditor extends PropertyEditorSupport {  
	  
    /** The Constant DATEFORMAT. */
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");  
    
    /** The Constant HOURFORMAT. */
    private static final DateFormat HOURFORMAT = new SimpleDateFormat("yyyy-MM-dd HH");   
    
    /** The Constant MINUTEFORMAT. */
    private static final DateFormat MINUTEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");   
    
    /** The Constant TIMEFORMAT. */
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
  
    /** The date format. */
    private DateFormat dateFormat;  
    
    /** The allow empty. */
    private boolean allowEmpty = true;  
  
    /**
     * The Constructor.
     */
    public DateEditor() {  
    }  
  
    
    /**
     * The Constructor.
     *
     * @param dateFormat the date format
     */
    public DateEditor(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    }  
  
    /**
     * The Constructor.
     *
     * @param dateFormat the date format
     * @param allowEmpty the allow empty
     */
    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {  
        this.dateFormat = dateFormat;  
        this.allowEmpty = allowEmpty;  
    }  
  
    /**
     * Parse the Date from the given text, using the specified DateFormat.
     *
     * @param text the as text
     * @throws IllegalArgumentException the illegal argument exception
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {  
            try {  
                if(this.dateFormat != null)  
                    setValue(this.dateFormat.parse(text));  
                else {  
                    if(text.contains(" ")) {  // 包含空格，则可能有时分秒
                    	if(text.contains(":")) {	// 包含冒号，则可能有分秒
                    		String [] split = text.split(":");
                    		if(split.length == 3) {  // 年月日时分秒
                    			setValue(TIMEFORMAT.parse(text));
                    		} else {  // 年月日时分
                    			setValue(MINUTEFORMAT.parse(text));
                    		}
                    	} else {		// 不包含冒号，则只有年月日时
                    		setValue(HOURFORMAT.parse(text));
                    	}
                	} else {	// 不包含空格，则只有年月日
                    	setValue(DATEFORMAT.parse(text));  
                	}
                }  
            } catch (ParseException ex) {  
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);  
            }  
        }  
    }  
  
    /**
     * Format the Date as String, using the specified DateFormat.
     *
     * @return the as text
     */  
    @Override  
    public String getAsText() {  
        Date value = (Date) getValue();  
        DateFormat dateFormat = this.dateFormat;  
        if(dateFormat == null)  
            dateFormat = TIMEFORMAT;  
        return (value != null ? dateFormat.format(value) : "");  
    }  
}  
