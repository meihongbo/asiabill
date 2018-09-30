package com.asiabill.framework.core.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <p>Title: JacksonUtil</p>  
 * <p>Description: TODO</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午9:59:26
 * @version v1.0
 */
public class JacksonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	private static final JsonFactory JSONFACTORY = new JsonFactory();

	public static String beanToJson(Object o) throws JsonParseException {
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGenerator = null;
		try {
			jsonGenerator = JSONFACTORY.createGenerator(sw);
			MAPPER.writeValue(jsonGenerator, o);
			return sw.toString();
		} catch (Exception e) {
			throw new RuntimeException("转换Java Bean 为 json错误", e);
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static<T> T jsonToBean(String json, Class<T> clazz) throws JsonParseException {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e + "json 转 javabean错误");
		}
	}

	public static Map<String, Object> beanToMap(Object o) throws JsonParseException {
		try {
			return MAPPER.readValue(beanToJson(o), HashMap.class);
		} catch (Exception e) {
			throw new RuntimeException(e + "转换Java Bean 为 HashMap错误");
		}
	}

	public static Map<String, Object> jsonToMap(String json, boolean collToString) throws JsonParseException {
		Map<String, Object> map = null;
		try {
			map = MAPPER.readValue(json, HashMap.class);
		} catch (IOException e) {
			throw new RuntimeException(e + "转换Java Bean 为 HashMap错误");
		}
		if (collToString) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (((entry.getValue() instanceof Collection)) || ((entry.getValue() instanceof Map))) {
					entry.setValue(beanToJson(entry.getValue()));
				}
			}
		}
		return map;
	}

	public static String listToJson(List<Map<String, String>> list) throws JsonParseException {
		JsonGenerator jsonGenerator = null;
		StringWriter sw = new StringWriter();
		try {
			jsonGenerator = JSONFACTORY.createGenerator(sw);
			new ObjectMapper().writeValue(jsonGenerator, list);
			jsonGenerator.flush();
			return sw.toString();
		} catch (Exception e) {
			throw new RuntimeException(e + "List 转换成json错误");
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.flush();
					jsonGenerator.close();
				} catch (Exception e) {
					throw new RuntimeException(e + "List 转换成json错误");
				}
			}
		}
	}

	public static List<Map<String, String>> jsonToList(String json) throws JsonParseException {
		try {
			if ((json != null) && (!"".equals(json.trim()))) {
				JsonParser jsonParse = JSONFACTORY.createParser(new StringReader(json));

				return new ObjectMapper().readValue(jsonParse, ArrayList.class);
			}
			throw new RuntimeException("json 转List错误");
		} catch (Exception e) {
			throw new RuntimeException(e + "json 转List错误");
		}
	}
}
