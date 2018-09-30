package com.lj.base.mvc.web.view;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：支持ITEXT5的PDF VIEW
 * 这里就全部复制spring 的，然后引入的东西改成第5版的就行了
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class IText5PdfView extends AbstractIText5PdfView {

	// public static final String GET_ADDRESS = "getAddress";

	/** The Constant HTML. */
	public static final String HTML = "html";
	/**
	 * Builds the pdf document.
	 *
	 * @param model the model
	 * @param document the document
	 * @param writer the writer
	 * @param request the request
	 * @param response the response
	 * @throws Exception the exception
	 */
	protected void buildPdfDocument(Map<String, Object> model,  
			Document document, PdfWriter writer, HttpServletRequest request,  
			HttpServletResponse response) throws Exception{
		/*if(!StringUtils.isEmpty((String) model.get(GET_ADDRESS)) )
    			this.buildPdfDocumentHelpUrl((String) model.get(GET_ADDRESS), document, writer);
    		else if(!StringUtils.isEmpty((String) model.get(HTML)))
    			this.buildPdfDocumentHelpHtml((String) model.get(HTML), document, writer);*/
		this.buildPdfDocumentHelpHtml((String) model.get(HTML), document, writer);
	} 


	/**
	 * 方法说明：传入URL生成PDF VIEW。通过GET方式获取.
	 *
	 * @param getAddress the get address
	 * @return the model and view
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳 CreateDate: 2017年06月28日
	 */
	public static ModelAndView generatePdfViewUrl(String getAddress) throws ClientProtocolException, IOException {
		/*Map<String,Object> map = new HashMap<String,Object>();  
    		map.put(IText5PdfView.GET_ADDRESS, getAddress);
    		IText5PdfView view = new IText5PdfView();  
    		view.setAttributesMap(map);  
    		return new ModelAndView(view);*/
		return generatePdfViewHtml(getFromWeb(getAddress));
	}

	/**
	 * 方法说明：传入URL生成PDF VIEW。通过POST方式获取.
	 *
	 * @param postAddress the post address
	 * @param params the params
	 * @return the model and view
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳 CreateDate: 2017年06月28日
	 */
	public static ModelAndView generatePdfViewUrl(String postAddress,Map<String,String> params) throws ClientProtocolException, IOException {
		return generatePdfViewHtml(postToWeb(postAddress,params));
	}

	/**
	 * 方法说明：传入HTML字符串生成PDF VIEW.
	 *
	 * @param html the html
	 * @return the model and view
	 * @author 彭阳 CreateDate: 2017年8月28日
	 */
	public static ModelAndView generatePdfViewHtml(String html) {
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put(IText5PdfView.HTML, html);
		IText5PdfView view = new IText5PdfView();  
		view.setAttributesMap(map);  
		return new ModelAndView(view);
	}

	public static void main(String args []) throws DocumentException, ClientProtocolException, IOException{
		StringBuffer html = new StringBuffer();
		//组装成符合W3C标准的html文件，否则不能正确解析
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
		.append("<head>")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
		.append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")
		.append("<style type=\"text/css\">img {width: 700px;}</style>")
		.append("</head>")
		.append("<body>");
		html.append("<table><tr><td>我靠</td><td>333</td></tr></table>");
		html.append("<center><h1>4444</h1></center>");
		html.append("<center>");
		//html.append("<img src=\"images/chart.jpg\"/>");
		html.append("</center>");

		html.append("</body></html>");

		IText5PdfView iText5PdfView = new IText5PdfView();
		// step 1  
		Document document = new Document();  
		// step 2  
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/workspace/test.pdf"));  
		// step 3  
		document.open();  
		iText5PdfView.buildPdfDocumentHelpHtml(html.toString() , document, writer);
	}


}
