package com.lj.base.mvc.web.view;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;


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
public abstract class AbstractIText5PdfView extends AbstractView {
	
	/**
	 * 类说明：扩展字体，提供默认字体，防止中文无法显示。
	 * 
	 * 
	 * <p>
	 * 详细描述：.
	 *
	 * @author 彭阳
	 * 
	 * CreateDate: 2017年7月1日
	 */
	public static class MyFontsProvider extends XMLWorkerFontProvider{
        
        /**
         * The Constructor.
         */
        public MyFontsProvider(){
            super(null,null);
        }
        
        /* (non-Javadoc)
         * @see com.itextpdf.tool.xml.XMLWorkerFontProvider#getFont(java.lang.String, java.lang.String, float, int)
         */
        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
                                                                                                
            String fntname = fontname;
            if(fntname==null){
                fntname="宋体";
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }
	 
 	/**
 	 * Instantiates a new abstract i text5 pdf view.
 	 */
 	public AbstractIText5PdfView() {  
	        setContentType("application/pdf");  
	    }  
	  
	    /* (non-Javadoc)
    	 * @see org.springframework.web.servlet.view.AbstractView#generatesDownloadContent()
    	 */
    	@Override  
	    protected boolean generatesDownloadContent() {  
	        return true;  
	    }  
	  
	    /* (non-Javadoc)
    	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    	 */
    	@Override  
	    protected final void renderMergedOutputModel(Map<String, Object> model,  
	            HttpServletRequest request, HttpServletResponse response)  
	            throws Exception {  
	    	// 获得流
	        ByteArrayOutputStream baos = createTemporaryOutputStream();  
	        Document document = newDocument();  
	        PdfWriter writer = newWriter(document, baos);  
	        prepareWriter(model, writer, request);  
	        buildPdfMetadata(model, document, request);  
	        document.open();  
	        buildPdfDocument(model, document, writer, request, response);  
	        document.close();  
	        writeToResponse(response, baos);  
	    }  
	  
	    /**
    	 * 方法说明：newDocument.
    	 *
    	 * @return the Document
    	 */
    	protected Document newDocument() {  
	        return new Document(PageSize.A4);  
	    }  
	  
	    /**
    	 * 方法说明：newWriter.
    	 *
    	 * @param document the document
    	 * @param os the os
    	 * @return the PdfWriter
    	 * @throws DocumentException the document exception
    	 */
    	protected PdfWriter newWriter(Document document, OutputStream os)  
	            throws DocumentException {  
	        return PdfWriter.getInstance(document, os);  
	    }  
	  
	    /**
    	 * Prepare writer.
    	 *
    	 * @param model the model
    	 * @param writer the writer
    	 * @param request the request
    	 * @throws DocumentException the document exception
    	 */
    	protected void prepareWriter(Map<String, Object> model, PdfWriter writer,  
	            HttpServletRequest request) throws DocumentException {  
	  
	        writer.setViewerPreferences(getViewerPreferences());  
	    }  
	  
	    /**
    	 * Gets the viewer preferences.
    	 *
    	 * @return the viewer preferences
    	 */
    	protected int getViewerPreferences() {  
	        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;  
	    }  
	  
	    /**
    	 * Builds the pdf metadata.
    	 *
    	 * @param model the model
    	 * @param document the document
    	 * @param request the request
    	 */
    	protected void buildPdfMetadata(Map<String, Object> model,  
	            Document document, HttpServletRequest request) {  
	    }  
	  
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
    	protected abstract void buildPdfDocument(Map<String, Object> model,  
	            Document document, PdfWriter writer, HttpServletRequest request,  
	            HttpServletResponse response) throws Exception;  
    	

    	/**
	     * 方法说明：调用URL并获取HTML字符流，转化成PDF.
	     *
	     * @param url the url
	     * @param document the document
	     * @param writer the writer
	     * @throws ClientProtocolException the client protocol exception
	     * @throws IOException the IO exception
	     * @author 彭阳 
	     * CreateDate: 2017年7月1日
	     */
    	public void buildPdfDocumentHelpUrl(String url,
    			Document document, PdfWriter writer)
    			throws ClientProtocolException, IOException {
    		/*Reader in = new StringReader(getFromWeb(url));
    		XMLWorkerHelper.getInstance().parseXHtml(writer, document, in);*/
    		MyFontsProvider myFontsProvider = new MyFontsProvider();
    		InputStream input = new ByteArrayInputStream(getFromWeb(url).getBytes());
    		XMLWorkerHelper.getInstance().parseXHtml(writer, document, input, null, myFontsProvider);
    		document.close();
    	}
    	
    	
    	/**
	     * 方法说明：获取HTML字符串，转化成PDF.
	     *
	     * @param html the html
	     * @param document the document
	     * @param writer the writer
	     * @throws ClientProtocolException the client protocol exception
	     * @throws IOException the IO exception
	     * @author 彭阳 
	     * CreateDate: 2017年7月1日
	     */
    	public void buildPdfDocumentHelpHtml(String html,
    			Document document, PdfWriter writer)
    			throws ClientProtocolException, IOException {
    		//Reader in = new StringReader(html);
    		//XMLWorkerHelper.getInstance().parseXHtml(writer, document, in);
    		MyFontsProvider myFontsProvider = new MyFontsProvider();
    		InputStream input = new ByteArrayInputStream(html.getBytes());
    		XMLWorkerHelper.getInstance().parseXHtml(writer, document, input, null, myFontsProvider);
    		document.close();
    	}
    	
    	/**
	     * 方法说明：获取指定URL的HTML字符串.
	     *
	     * @param url the url
	     * @return the from web
	     * @throws ClientProtocolException the client protocol exception
	     * @throws IOException the IO exception
	     * @author 彭阳 
	     * CreateDate: 2017年7月1日
	     */
    	public static String getFromWeb(String url) throws ClientProtocolException, IOException{
    		return HttpClientUtils.getFromWeb(url);
    		
    		/*StringBuilder reqEntityStr =  new StringBuilder();
    		//发送充值报文信息
    		DefaultHttpClient httpclient = new DefaultHttpClient();    
    		//目标地址 数据库参数化
    		//String url = "http://10.186.255.47/app/testPdf.html";
    		//String url =  localCacheSystemParams.getSystemParam(SystemAliasName.mf.toString(),PublicConstants.MEMFOX, PublicConstants.AUTHENTICATION_ADDR);
    		HttpGet httpget = new HttpGet(url);    
    		// 构造最简单的字符串数据    
    		//StringEntity reqEntity = new StringEntity(reqEntityStr.toString(), CharEncoding.UTF_8);    
    		// 设置类型    
    		//reqEntity.setContentType("application/x-www-form-urlencoded");    
    		// 设置请求的数据    
    		//httpget.setEntity(reqEntity);    
    		// 执行    
    		HttpResponse httpresponse = httpclient.execute(httpget);  
    		HttpEntity entity = httpresponse.getEntity();  
    		String body = EntityUtils.toString(entity,"UTF-8");  
    		return body; */
    	}
    	
    	/**
	     * 方法说明：获取指定URL的HTML字符串.
	     *
	     * @param url the url
	     * @param params the params
	     * @return the string
	     * @throws ClientProtocolException the client protocol exception
	     * @throws IOException the IO exception
	     * @author 彭阳 
	     * CreateDate: 2017年7月1日
	     */
    	public static String postToWeb(String url,Map<String,String> params) throws ClientProtocolException, IOException{
    		return HttpClientUtils.postToWeb(url, params);
    	}
}
