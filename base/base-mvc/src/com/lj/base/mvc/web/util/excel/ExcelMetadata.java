package com.lj.base.mvc.web.util.excel;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Arrays;
import java.util.List;


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
public class ExcelMetadata  {
	
	/**
	 * *
	 * 生成文件全路径.
	 *
	 * @return the * excel文件全路径名称
	 */
	public String getExcelFilePath() {
		return excelFilePath;
	}
	
	/**
	 * *
	 * 生成文件全路径.
	 *
	 * @param excelFilePath the new * excel文件全路径名称
	 */
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	
	/**
	 * *
	 * excel文件sheet名字（只支持生成一个sheet）.
	 *
	 * @return the * excel文件sheet名字（只支持生成一个sheet） 默认名sheet1
	 */
	public String getExcelSheetName() {
		return excelSheetName;
	}
	
	/**
	 * *
	 * excel文件sheet名字（只支持生成一个sheet）.
	 *
	 * @param excelSheetName the new * excel文件sheet名字（只支持生成一个sheet） 默认名sheet1
	 */
	public void setExcelSheetName(String excelSheetName) {
		this.excelSheetName = excelSheetName;
	}
	
	/**
	 * *
	 * excel文件字体（只支持一个字体）
	 * 默认宋体.
	 *
	 * @return the * excel文件字体（只支持一个字体） 默认宋体
	 */
	public String getExcelFontName() {
		return excelFontName;
	}
	
	/**
	 * *
	 * excel文件字体（只支持一个字体）
	 * 默认宋体.
	 *
	 * @param excelFontName the new * excel文件字体（只支持一个字体） 默认宋体
	 */
	public void setExcelFontName(String excelFontName) {
		this.excelFontName = excelFontName;
	}
	 
 	/**
 	 * *
 	 * excel文件字体大小（只支持一个字体）
 	 * 默认12.
 	 *
 	 * @return the * excel文件字体大小（只支持一个字体） 默认12
 	 */
	public short getExcelFontHeight() {
		return excelFontHeight;
	}
	
	/**
	 * *
	 * excel文件字体大小（只支持一个字体）
	 * 默认12.
	 *
	 * @param excelFontHeight the new * excel文件字体大小（只支持一个字体） 默认12
	 */
	public void setExcelFontHeight(short excelFontHeight) {
		this.excelFontHeight = excelFontHeight;
	}
	
	/**
	 * *
	 * 各列设置，本身也是excel的一个cell，为字符串属性.
	 *
	 * @return the * 各列设置，本身也是excel的一个cell，为字符串属性
	 */
	public ExcelCellMetadata[] getHeaderRowCells() {
		return headerRowCells;
	}
	
	/**
	 * *
	 * 各列设置，本身也是excel的一个cell，为字符串属性.
	 *
	 * @param headerRowCells the new * 各列设置，本身也是excel的一个cell，为字符串属性
	 */
	public void setHeaderRowCells(ExcelCellMetadata[] headerRowCells) {
		this.headerRowCells = headerRowCells;
	}
	
	/**
	 * *
	 * 数据列表，数据显示的格式由headerRowCells的dataType定义.
	 *
	 * @return the * 数据列表，数据显示的格式由headerRowCells的dataType定义
	 */
	public List<String[]> getDatas() {
		return datas;
	}
	
	/**
	 * *
	 * 数据列表，数据显示的格式由headerRowCells的dataType定义.
	 *
	 * @param datas the new * 数据列表，数据显示的格式由headerRowCells的dataType定义
	 */
	public void setDatas(List<String[]> datas) {
		this.datas = datas;
	}
	
	/** * excel文件全路径名称. */
	private String excelFilePath;
	
	/** * excel文件sheet名字（只支持生成一个sheet） 默认名sheet1. */
	private String excelSheetName ="sheet1";
	
	/** * excel文件字体（只支持一个字体） 默认宋体. */
	private String excelFontName="宋体";
	
	/** * excel文件字体大小（只支持一个字体） 默认12. */
	private short excelFontHeight = (short)12; 
	
	/** * 各列设置，本身也是excel的一个cell，为字符串属性. */
	private ExcelCellMetadata[]  headerRowCells;
	
	/** * 数据列表，数据显示的格式由headerRowCells的dataType定义. */
	private List<String[]> datas;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelMetadata [excelFilePath=" + excelFilePath
				+ ", excelSheetName=" + excelSheetName + ", excelFontName="
				+ excelFontName + ", excelFontHeight=" + excelFontHeight
				+ ", headerRowCells=" + Arrays.toString(headerRowCells)
				+ ", datas=" + datas + "]";
	}   
 
}
