package com.lj.base.mvc.web.util.excel;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.exception.TsfaRuntimeException;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：标题/内容
 * 简单表格模式导出excel处理封装
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class SimpleTableExcelUtils {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(SimpleTableExcelUtils.class);
	//创建Workbook对象（这一个对象代表着对应的一个Excel文件）
    //HSSFWorkbook表示以xls为后缀名的文件
	/** The wb. */
	private Workbook wb = new HSSFWorkbook();
	//获得CreationHelper对象,这个应该是一个帮助类
	/** The helper. */
	private CreationHelper helper = wb.getCreationHelper();
	
	/** * 单元格配置，为了防止excel资源耗尽， 只有列属性不一样，用不同的，不会每个单元格创建. */
	private Map<String,CellStyle> cellStyles;
	
	/**
	 * *
	 * 创建cell，单元格共用，通过key取.
	 *
	 * @param key the key
	 * @param cellStyle the cell style
	 */
	public void putCellStyle(String key,CellStyle cellStyle){
		if(cellStyles==null){
			cellStyles = new HashMap<String,CellStyle>();			
		}
		if(!cellStyles.containsKey(key))
			cellStyles.put(key, cellStyle);
	}
	
	/**
	 * Gets the * 单元格配置，为了防止excel资源耗尽， 只有列属性不一样，用不同的，不会每个单元格创建.
	 *
	 * @return the * 单元格配置，为了防止excel资源耗尽， 只有列属性不一样，用不同的，不会每个单元格创建
	 */
	public Map<String, CellStyle> getCellStyles() {
		return cellStyles;
	}
	
	/**
	 * Sets the cell stype.
	 *
	 * @param cellStyles the cell stype
	 */
	public void setCellStype(Map<String, CellStyle> cellStyles) {
		this.cellStyles = cellStyles;
	}
	
	/**
	 * *
	 * 获取本excel工作簿.
	 *
	 * @return the workbook
	 */
	public Workbook getWorkbook() {
		return wb;
	}
	
	/**
	 * *
	 * 获取excel创建具体单元格等工具类.
	 *
	 * @return the creation helper
	 */
	public CreationHelper getCreationHelper() {
		return helper;
	}

	/**
	 * *.
	 *
	 * @param excelMetadata the excel metadata
	 * @return the file
	 */
	public static File saveExcelFile(ExcelMetadata excelMetadata){
		long beginMill = System.currentTimeMillis();
		log.debug("saveExcelFileBegin:"+excelMetadata.getExcelFilePath());
		SimpleTableExcelUtils excelPOI = new SimpleTableExcelUtils();
		//创建Sheet并给名字(表示Excel的一个Sheet)
		Sheet excelSheet =excelPOI.getWorkbook().createSheet(excelMetadata.getExcelSheetName());
//		excelSheet.autoSizeColumn(0, true);
		//创建字体
		Font font = excelPOI.createFont(excelMetadata.getExcelFontName(), excelMetadata.getExcelFontHeight());
		//创建文件流
		OutputStream os = null;
		File outFile = null;
		try {
			outFile = new File(excelMetadata.getExcelFilePath());
			os = new FileOutputStream(outFile);
		} catch (Throwable e) {
			
			throw new TsfaRuntimeException("excel-savefile-error","saveExcelFile "+e);
		}
		//excel标题栏
		Row row = excelSheet.createRow(0);
//		row.setHeight();
		//------创建样式-------------
		int cellNum = 0;
		CellStyle cellHeaderStyle = excelPOI.createStyleCellText(font);
		//标题的样式就是所有文本的样式，添加到map中
		excelPOI.putCellStyle(ExcelCellMetadata.DATA_TYPE_TEXT, cellHeaderStyle);
		//准备写标题，并且创建每列的样式
		for(ExcelCellMetadata excelCellMetadata : excelMetadata.getHeaderRowCells()){
			//创建单元格
			Cell cell = row.createCell(cellNum);
			//把这个样式加到单元格里面
			cell.setCellStyle(cellHeaderStyle);     
			//给单元格设值
			cell.setCellValue(excelCellMetadata.getTitle());
			//给单元格设置宽度
			excelSheet.setColumnWidth(cellNum, excelCellMetadata.getColumnCharWidth()*256);//poi单位为字符的1/256
			//根据属性设置其他样式
			if(!excelPOI.getCellStyles().containsKey(excelCellMetadata.getDataType())){
				//如果还没有样式，添加样式
				excelPOI.putCellStyle(excelCellMetadata.getDataType(), excelPOI.createStyleCell(excelCellMetadata.getDataType(), font));
			}
			cellNum++;//index
		}
		
		int rowNum = 1;//第二行开始为具体内容，第一行标题		
		for(String []rowDatas : excelMetadata.getDatas()){
			//获得这个sheet的第i行
			row = excelSheet.createRow(rowNum); 
			cellNum = 0;//重置0
			for(String rowData :rowDatas){
				//获得这一行的每j列
				Cell cell = row.createCell(cellNum);
				String dataType = excelMetadata.getHeaderRowCells()[cellNum].getDataType();
				//把这个样式加到单元格里面
				cell.setCellStyle(excelPOI.getCellStyles().get(dataType));
				//给单元格设值
				if(ExcelCellMetadata.DATA_TYPE_DOUBLE.equals(dataType) ||
						 ExcelCellMetadata.DATA_TYPE_NUMBER.equals(dataType)){
					if(StringUtils.isNotEmpty(rowData))//非空
						cell.setCellValue(Double.parseDouble(rowData));
				}
				else{
					cell.setCellValue(rowData);
				}
				
				cellNum++;
			}
			

			//设置每个sheet每一行的宽度,自动,根据需求自行确定
//			excelSheet.autoSizeColumn(rowNum, true);
			rowNum++;
			
		}
		
 
		//输出
		try {
			excelPOI.getWorkbook().write(os);
			os.close();
		} catch (Throwable e) {
			throw new TsfaRuntimeException("excel-savefile-close-error","saveExcelFile "+e);
		}  
		
		long endMill = System.currentTimeMillis();
		log.debug("saveExcelFileEnd,total time:"+(endMill-beginMill));
		return outFile;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		ExcelMetadata meta = new ExcelMetadata();
		meta.setExcelFilePath("D:\\360Downloads\\Software\\ldftext.xls");
//		meta.setExcelFontName(excelFontName);
//		meta.setExcelFontHeight(excelFontHeight);
//		meta.setExcelSheetName(excelSheetName);
		ExcelCellMetadata []headerRowCells = {
				//不指定宽度，默认在10个字符以内
				new ExcelCellMetadata("姓名",ExcelCellMetadata.DATA_TYPE_TEXT),
				new ExcelCellMetadata("年龄",ExcelCellMetadata.DATA_TYPE_NUMBER),
				new ExcelCellMetadata("卡余额",ExcelCellMetadata.DATA_TYPE_DOUBLE),
				//指定宽度
				new ExcelCellMetadata("出生日期",ExcelCellMetadata.DATA_TYPE_DATE,(short)20),
				new ExcelCellMetadata("注册时间",ExcelCellMetadata.DATA_TYPE_DATETIME,(short)30)
		};		
		meta.setHeaderRowCells(headerRowCells);
		List<String[]> list = new ArrayList<String[]>();
		String[] row1 = {"刘登富","33","12.45","1981-07-23","2013-09-08 11:08:09"};
		String[] row2 = {"彭阳","32","14.88","1982-11-21","2013-08-08 11:08:09"};
		for(int i=0;i<10000;i++){
			list.add(row1);
			list.add(row2);
		}
		meta.setDatas(list);
		log.debug("data size:"+list.size());
		saveExcelFile(meta);
 
	}
	
	/**
	 * *
	 * 根据字段属性创建CellStyle.
	 *
	 * @param dataType the data type
	 * @param font the font
	 * @return the cell style
	 */
	public CellStyle createStyleCell(String dataType,Font font){
		switch(dataType){
			case ExcelCellMetadata.DATA_TYPE_NUMBER:
				return this.createStyleCellNumber(font); 
			case ExcelCellMetadata.DATA_TYPE_DOUBLE:
				return this.createStyleCellDouble(font); 
			case ExcelCellMetadata.DATA_TYPE_DATE:
				return this.createStyleCellDate(font); 
			case ExcelCellMetadata.DATA_TYPE_DATETIME:
				return this.createStyleCellDateTime(font); 
		}
		return createStyleCellText(font); 
	}
	
	/**
	 * 方法说明：创建文本格式样式.
	 *
	 * @param font the font
	 * @return the cell style
	 * @author 彭阳
	 * CreateDate 2017-7-1
	 */
	protected CellStyle createStyleCellText(Font font){
		//创建一个基本的样式
		CellStyle cellStyle = this.createStyleCell();
		//设置文字在单元格里面的位置
	   //设置上下
	   cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	   //设置左右
	   cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//先创建字体样式,并把这个样式加到单元格的字体里面
		cellStyle.setFont(font);
		return cellStyle;
	}
	
	/**
	 * 方法说明：创建数字样式.
	 *
	 * @param font the font
	 * @return the cell style
	 * @author 彭阳
	 * CreateDate 2017-7-1
	 */
	protected CellStyle createStyleCellNumber(Font font){
		//创建一个基本的样式
		CellStyle cellStyleNum = this.createStyleCell();
		//设置文字在单元格里面的位置
	   //设置上下
		cellStyleNum.setAlignment(CellStyle.ALIGN_CENTER);
	   //设置左右
		cellStyleNum.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置这个样式的格式(Format)
		cellStyleNum = this.setCellFormat(wb.getCreationHelper(),cellStyleNum, "###0");     
		//先创建字体样式,并把这个样式加到单元格的字体里面
		cellStyleNum.setFont(font);
		return cellStyleNum;
	}
	
	/**
	 * 方法说明：创建数字带小数样式.
	 *
	 * @param font the font
	 * @return the cell style
	 * @author 彭阳
	 * CreateDate 2017-7-1
	 */
	protected CellStyle createStyleCellDouble(Font font){
		//创建一个基本的样式
		CellStyle cellStyleDouble= this.createStyleCell();
		//设置文字在单元格里面的位置
	   //设置上下
		cellStyleDouble.setAlignment(CellStyle.ALIGN_CENTER);
	   //设置左右
		cellStyleDouble.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置这个样式的格式(Format)
		cellStyleDouble = this.setCellFormat(this.helper,cellStyleDouble, "###0.00");     
		//先创建字体样式,并把这个样式加到单元格的字体里面
		cellStyleDouble.setFont(font);
		return cellStyleDouble;
	}
	
	/**
	 * 方法说明：创建带日期时间格式的样式.
	 *
	 * @param font the font
	 * @return the cell style
	 * @author 彭阳
	 * CreateDate 2017-7-1
	 */
	protected CellStyle createStyleCellDateTime(Font font){
		//创建一个基本的样式
		CellStyle cellStyleTime = this.createStyleCell();
	   //设置上下
		cellStyleTime.setAlignment(CellStyle.ALIGN_CENTER);
	   //设置左右
		cellStyleTime.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyleTime = this.setCellFormat(helper,cellStyleTime, "yyyy-MM-dd HH:mm:ss");
		cellStyleTime.setFont(font);
		return cellStyleTime;
	}
	
	/**
	 * 方法说明：创建带日期格式的样式.
	 *
	 * @param font the font
	 * @return the cell style
	 * @author 彭阳
	 * CreateDate 2017-7-1
	 */
	protected CellStyle createStyleCellDate(Font font){
		//创建一个基本的样式
		CellStyle cellStyleTime = this.createStyleCell();
	   //设置上下
		cellStyleTime.setAlignment(CellStyle.ALIGN_CENTER);
	   //设置左右
		cellStyleTime.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyleTime = this.setCellFormat(this.helper,cellStyleTime, "yyyy-MM-dd");
		cellStyleTime.setFont(font);
		return cellStyleTime;
	}
	
	/**
	 * *
	 * 设置边框.
	 *
	 * @return the cell style
	 */
	protected CellStyle createStyleCell(){
	   CellStyle cellStyle = wb.createCellStyle();
	   //设置一个单元格边框颜色
	   cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	   cellStyle.setBorderTop(CellStyle.BORDER_THIN);
	   cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	   cellStyle.setBorderRight(CellStyle.BORDER_THIN);
	   //设置一个单元格边框颜色
	   cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	   cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	   cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	   cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());  
	   return cellStyle;
	}
	
	/**
	 * *
	 * 创建其他格式时间.
	 *
	 * @param helper the helper
	 * @param cellStyle the cell style
	 * @param fmt the fmt
	 * @return the cell style
	 */
	protected CellStyle setCellFormat(CreationHelper helper,CellStyle cellStyle,String fmt){
	   //还可以用其它方法创建format
	   cellStyle.setDataFormat(helper.createDataFormat().getFormat(fmt));
	   return cellStyle;
	}
	
	/**
	 * *
	 * 设置填充色.
	 *
	 * @param cellStyle the cell style
	 * @param bg the bg
	 * @param fg the fg
	 * @param fp the fp
	 * @return the cell style
	 */
	protected CellStyle setFillBackgroundColors(CellStyle cellStyle,short bg,short fg,short fp){
	   //cellStyle.setFillBackgroundColor(bg);
	   cellStyle.setFillForegroundColor(fg);
	   cellStyle.setFillPattern(fp);
	   return cellStyle;
	}
	
	/**
	 * *
	 * 创建字体.
	 *
	 * @param fontName the font name
	 * @param fontHeight the font height
	 * @return the font
	 */
	protected Font createFont(String fontName,short fontHeight){
	   //创建Font对象
	   Font font = wb.createFont();
	   //设置字体
	   font.setFontName(fontName);
	   //字体大小
	   font.setFontHeightInPoints( fontHeight );
	   return font;
	}

}
