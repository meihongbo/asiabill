package com.lj.base.mvc.web.httpclient;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 
 * 类说明：HttpClient封装
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
public class TsfaHttpClient {
	
	/** The Constant DEFAULT_CHARSET. */
	private static final String DEFAULT_CHARSET = "UTF-8";		// 默认编码	
	
	/** The Constant TIMEOUT. */
	private static final int TIMEOUT = 10000;					// 默认超时时间

	/** The httpclient. */
	private HttpClient httpclient;
	
	/** The url. */
	private String url;
	
	/**
	 * The Constructor.
	 *
	 * @param url the url
	 */
	public TsfaHttpClient(String url) {
		this(url, TIMEOUT);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param url the url
	 * @param timeout the timeout
	 */
	public TsfaHttpClient(String url, int timeout) {
		if(url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("http url must be required.");
		}
		this.url = url;
		if(url.indexOf("https") >= 0) {
			this.httpclient = new DefaultHttpClient();
			this.httpclient = TsfaHttpClient.wrapClient(httpclient);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
		} else {
			httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
		}
	}
	
	/**
	 * Sets the httpclient.
	 *
	 * @param httpclient the httpclient
	 */
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	/**
	 * Gets the httpclient.
	 *
	 * @return the httpclient
	 */
	public HttpClient getHttpclient() {
		return httpclient;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	private String getRequestUrl() {
		if(url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("http url must be required.");
		}
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Wrap client.
	 *
	 * @param client the client
	 * @return the http client
	 */
	public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient client) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, ssf));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
			return new DefaultHttpClient(mgr, client.getParams());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 方法说明：简单get提交.
	 *
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doGet() throws ClientProtocolException, IOException {
		return this.doGet(null, DEFAULT_CHARSET);
	}
	
	/**
	 * 方法说明：get.
	 *
	 * @param httpParameter {@link HttpParameter}
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doGet(HttpParameter httpParameter) throws ClientProtocolException, IOException {
		return this.doGet(httpParameter, DEFAULT_CHARSET);
	}

	/**
	 * 方法说明：get.
	 *
	 * @param httpParameter {@link HttpParameter}
	 * @param charset 	参数编码
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doGet(HttpParameter httpParameter, String charset) throws ClientProtocolException, IOException {
		StringBuilder sb = new StringBuilder(this.getRequestUrl());
		if (httpParameter != null && !httpParameter.isParameterEmpty()) {
			if (url.indexOf("?") == -1) {
				sb.append("?");
			}
			if (sb.charAt(sb.length() - 1) != ('?')) {
				sb.append("&");
			}
			for (Parameter param : httpParameter.getParameters()) {
				sb.append(URLEncoder.encode(param.getName(), charset));
				sb.append("=");
				sb.append(URLEncoder.encode(param.getValue(), charset));
				sb.append("&");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		HttpGet httpGet = new HttpGet(sb.toString());
		if (httpParameter != null && !httpParameter.isEmptyHeader()) {
			Set<Entry<String, String>> set = httpParameter.getHeaderMap().entrySet();
			for (Entry<String, String> entry : set) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		return this.execute(httpGet);
	}
	
	/**
	 * 方法说明：post 字符数据.
	 *
	 * @param string 请求字符串数据
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doPostStringBody(String string) throws ClientProtocolException, IOException {
		return doPostStringBody(string, DEFAULT_CHARSET);
	}

	/**
	 * 方法说明：post 字符数据.
	 *
	 * @param string 请求字符串数据
	 * @param charset 参数编码
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doPostStringBody(String string, String charset) throws ClientProtocolException, IOException {
		HttpEntity entity = new StringEntity(string, charset);
		return this.doPostBody(entity);
	}

	/**
	 * 方法说明：post 字节数据.
	 *
	 * @param bytes 	请求字节数据
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doPostBytesBody(byte[] bytes) throws ClientProtocolException, IOException {
		HttpEntity entity = new ByteArrayEntity(bytes);
		return this.doPostBody(entity);
	}

	/**
	 * Do post body.
	 *
	 * @param entity the entity
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 */
	private HttpResp doPostBody(HttpEntity entity) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(this.getRequestUrl());
		httpPost.setEntity(entity);
		return this.execute(httpPost);
	}
	
	/**
	 * 方法说明：post.
	 *
	 * @param httpParameter 	{@link HttpParameter}
	 * @return 				{@link HttpResp}
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doPost(HttpParameter httpParameter) throws ClientProtocolException, IOException {
		return doPost(httpParameter, DEFAULT_CHARSET);
	}

	/**
	 * 方法说明：post.
	 *
	 * @param httpParameter 	{@link HttpParameter}
	 * @param charset the charset
	 * @return 				{@link HttpResp}
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp doPost(HttpParameter httpParameter, String charset) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(this.getRequestUrl());
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Parameter param : httpParameter.getParameters()) {
			nameValuePairs.add(new BasicNameValuePair(param.getName(), param.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
		if (httpParameter != null && !httpParameter.isEmptyHeader()) {
			Set<Entry<String, String>> set = httpParameter.getHeaderMap().entrySet();
			for (Entry<String, String> entry : set) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		return this.execute(httpPost);
	}

	/**
	 * 方法说明：请求过程.
	 *
	 * @param request the request
	 * @return the http resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public HttpResp execute(HttpRequestBase request) throws ClientProtocolException, IOException {
		HttpEntity entity = null;
		try {
			HttpResponse httpResponse = httpclient.execute(request);
			HttpResp httpResp = new HttpResp();
			httpResp.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			entity = httpResponse.getEntity();
			httpResp.setBytes(EntityUtils.toByteArray(entity));
			return httpResp;
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		}
	}

	/**
	 * 方法说明：释放资源.
	 *
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public void shutdown() {
		this.httpclient.getConnectionManager().shutdown();
	}
}