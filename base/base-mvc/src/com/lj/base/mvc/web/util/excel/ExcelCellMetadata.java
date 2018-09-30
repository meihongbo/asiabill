
package com.lj.base.mvc.web.util.excel;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */


/**
 * 
 * 
 * 类说明：excel文件描述信息
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
public class ExcelCellMetadata  {
	
	/** * 数据类型定义（dataType）文本. */
	public static final String DATA_TYPE_TEXT = "text";
	
	/** * 数据类型定义（dataType）数字. */
	public static final String DATA_TYPE_NUMBER = "number";
	
	/** * 数据类型定义（dataType）小数（只精确到小数点后2两位）. */
	public static final String DATA_TYPE_DOUBLE = "double";
	
	/** * 数据类型定义（dataType）日期 yyyy-MM-dd. */
	public static final String DATA_TYPE_DATE = "date";
	
	/** * 数据类型定义（dataType）日期 yyyy-MM-dd HH:mm:ss. */
	public static final String DATA_TYPE_DATETIME = "datetime";
	
	/** * 列标题. */
	private String title;
	
	/** * 列字段属性. */
	private String dataType;
	
	/** * 列字符宽度，默认10个字符. */
	private short columnCharWidth = 10;
	
	/**
	 * *
	 * 列字符宽度，默认10个字符.
	 *
	 * @return the * 列字符宽度，默认10个字符
	 */
	public short getColumnCharWidth() {
		return columnCharWidth;
	}
	
	/**
	 * *
	 * 列字符宽度，默认10个字符.
	 *
	 * @param columnCharWidth the new * 列字符宽度，默认10个字符
	 */
	public void setColumnCharWidth(short columnCharWidth) {
		this.columnCharWidth = columnCharWidth;
	}
	
	/**
	 * *
	 * 列标题.
	 *
	 * @return the * 列标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * *
	 * 列标题.
	 *
	 * @param title the new * 列标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * *
	 * 列字段属性.
	 *
	 * @return the * 列字段属性
	 */
	public String getDataType() {
		return dataType;
	}
	
	/**
	 * *
	 * 列字段属性.
	 *
	 * @param dataType the new * 列字段属性
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelCellMetadata [title=" + title + ", dataType=" + dataType
				+ ", columnWidth=" + columnCharWidth + "]";
	}
	
	/**
	 * 默认定义文本类型的文件信息.
	 *
	 * @param title the title
	 */
	public ExcelCellMetadata(String title){
		this.title = title;
		this.dataType = DATA_TYPE_TEXT; 
	}
	
	/**
	 * The Constructor.
	 *
	 * @param title the title
	 * @param dataType the data type
	 */
	public ExcelCellMetadata(String title,String dataType){
		this.title = title;
		this.dataType = dataType; 
	}
	
	/**
	 * The Constructor.
	 *
	 * @param title the title
	 * @param dataType the data type
	 * @param columnCharWidth the column char width
	 */
	public ExcelCellMetadata(String title,String dataType,short columnCharWidth){
		this.title = title;
		this.dataType = dataType;
		this.columnCharWidth = columnCharWidth;
	}
}
