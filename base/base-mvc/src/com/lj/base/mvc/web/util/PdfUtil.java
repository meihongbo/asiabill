package com.lj.base.mvc.web.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

/**
 * 
 * 
 * 类说明：这仅仅作为itext5 提供一些方法，更多的需要自己去看相关API
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
public class PdfUtil {
	/** 对参数的封装形式比如{name}. */
	public static final String BEGIN = "{";
	
	/** The Constant END. */
	public static final String END = "}";
	/** 换行形式{#}. */
	public static final String NEW_LINE = "#";
	/** 默认的行间距、首行距离等，自己添加. */
	public static final float DEFAULT_LEADING = 20;
	
	/** The Constant DEFAULT_LINE_INDENT. */
	public static final float DEFAULT_LINE_INDENT = 30;
	
	
	/** 基本字体和样式. */
	public static BaseFont bfChinese;
	
	/** The font chinese. */
	public static Font fontChinese;
	
	/** The under line. */
	public static Font UNDER_LINE = null;
	static{
		try {
		// SIMKAI.TTF 默认系统语言，这里没使用第三方语言包
		//bfChinese = BaseFont.createFont(PDFTest.class.getResource("/content/")+"SIMKAI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			
			fontChinese = new Font(bfChinese, 14, Font.NORMAL);
			UNDER_LINE = new Font(bfChinese, 14,Font.UNDERLINE);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 默认样式.
	 *
	 * @param context the context
	 * @return the paragraph
	 */
	public static Paragraph getParagraph(String context){
		return getParagraph(context,fontChinese);
	}
	
	/**
	 * Gets the paragraph.
	 *
	 * @param chunk the chunk
	 * @return the paragraph
	 */
	public static Paragraph getParagraph(Chunk chunk){
		return new Paragraph(chunk);
	}
	
	/**
	 *指定字体样式.
	 *
	 * @param context the context
	 * @param font the font
	 * @return the paragraph
	 */
	public static Paragraph getParagraph(String context,Font font){
		return new Paragraph(context,font);
	}
	
	/**
	 * 获得新行,首行缩进,和行间距.
	 *
	 * @param context the context
	 * @param fixedLeading the fixed leading
	 * @param firstLineIndent the first line indent
	 * @return the new paragraph
	 */
	public static Paragraph getNewParagraph(String context,float fixedLeading,float firstLineIndent){
		Paragraph p = getParagraph(context);
		p.setLeading(fixedLeading);
		p.setFirstLineIndent(firstLineIndent);
		return p;
	}
	
	 /**
 	 * Gets the paragraph.
 	 *
 	 * @param content the content
 	 * @param font the font
 	 * @param fixedLeading the fixed leading
 	 * @param alignment the alignment
 	 * @return the paragraph
 	 */
 	public static Paragraph getParagraph(String content , Font font , float fixedLeading , int alignment){  
		 Paragraph p = getParagraph(content);
		 p.setFont(font);
		 p.setLeading(fixedLeading);
		 p.setAlignment(alignment);
		 return p;  
	  }
	
	/**
	 *默认段落样式.
	 *
	 * @param context the context
	 * @return the default paragraph
	 */
	public static Paragraph getDefaultParagraph(String context){
		Paragraph p = getParagraph(context);
		// 默认行间距
		p.setLeading(DEFAULT_LEADING);
		// 默认首行空隙
		p.setFirstLineIndent(DEFAULT_LINE_INDENT);
		return p;
	}
	
	/**
	 * 方法说明：将参数和字符串内容组合成集合.
	 *
	 * @param context the context
	 * @param map the map
	 * @return the List
	 */
	public static List<Paragraph> createParagraphs(String context ,Map<String,Object> map){
		int index = 0;
		List<Paragraph> list = new ArrayList<Paragraph>();
		Paragraph p = getDefaultParagraph(null);
		while((index  = context.indexOf(BEGIN)) > -1){
			String text = context.substring(0,index);
			context = context.substring(index, context.length());
			index = context.indexOf(END);
			String param =  null;
			if(index > 0){
				 param = context.substring(BEGIN.length(),index);
			}
			p.add(text);
			if(!NEW_LINE.equals(param)){
				Object value = map.get(param);
				if(value != null){
					p.add(new Chunk(value.toString(),UNDER_LINE));
				}else{
					p.add(new Chunk(""));
				}
			}else{
				list.add(p);
				p = getDefaultParagraph(null);
				p.setSpacingBefore(0);
			}
			context = context.substring(index+END.length(),context.length());
		}
		list.add(p);
		list.add(getParagraph(context));
		return list;
	}
	
	
}
