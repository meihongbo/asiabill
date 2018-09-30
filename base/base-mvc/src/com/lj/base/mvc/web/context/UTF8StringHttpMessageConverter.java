package com.lj.base.mvc.web.context;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：ResponseBody输出为乱码，必须注入本类
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {  
  
  /** The Constant utf8. */
  private static final MediaType utf8 = new MediaType("text","plain", Charset.forName("UTF-8"));  
  
  /** The write accept charset. */
  private boolean writeAcceptCharset = true;  
  
  /* (non-Javadoc)
   * @see org.springframework.http.converter.AbstractHttpMessageConverter#getDefaultContentType(java.lang.Object)
   */
  @Override  
  protected MediaType getDefaultContentType(String dumy) {  
	  return utf8;  
  }  

  /* (non-Javadoc)
   * @see org.springframework.http.converter.StringHttpMessageConverter#getAcceptedCharsets()
   */
  protected List<Charset> getAcceptedCharsets() {  
	  return Arrays.asList(utf8.getCharSet());  
  } 
 
  /* (non-Javadoc)
   * @see org.springframework.http.converter.StringHttpMessageConverter#writeInternal(java.lang.String, org.springframework.http.HttpOutputMessage)
   */
  protected void writeInternal(String s, HttpOutputMessage outputMessage)    throws IOException {  
	  if (this.writeAcceptCharset) {  
		  outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());  
	  }  
	  Charset charset = utf8.getCharSet();  
	  FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(),  charset));  
  }  
  
  /**
   * Checks if is write accept charset.
   *
   * @return true, if checks if is write accept charset
   */
  public boolean isWriteAcceptCharset() {  
	  return writeAcceptCharset;  
  }  
  
  /* (non-Javadoc)
   * @see org.springframework.http.converter.StringHttpMessageConverter#setWriteAcceptCharset(boolean)
   */
  public void setWriteAcceptCharset(boolean writeAcceptCharset) {  
	  this.writeAcceptCharset = writeAcceptCharset;  
  }  
 } 
