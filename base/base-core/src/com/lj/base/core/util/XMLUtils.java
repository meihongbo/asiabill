
package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;


/**
 * 
 * 
 * 类说明：
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
public class XMLUtils {
	 
	/**
	 * Gets the document.
	 *
	 * @param is the is
	 * @return the document
	 */
	public static Document getDocument(InputStream is) {
		if (is == null) {
			return null;
		}

		Document doc = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = db.parse(is);
		} catch (Exception e) {
			//logger.warn("getDocument method throw exception:",e);
		}
		return doc;
	}

	/**
	 * get the docment by String.
	 *
	 * @param param the param
	 * @return the document
	 */
	public static Document getDocument(String param) {
		ByteArrayInputStream bis = new ByteArrayInputStream(param.getBytes());
		return getDocument(bis);
	}

	/**
	 * Creat document.
	 *
	 * @return the document
	 */
	public static Document creatDocument() {
		Document doc = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = db.newDocument();
		} catch (Exception e) {
			//logger.warn("creatDocument method throw exception:",e);
		}
		return doc;

	}

	/**
	 * create a new document root element.
	 *
	 * @return Element
	 */
	public static Element createRootElement() {
		Element rootElement = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = db.newDocument();
			rootElement = doc.getDocumentElement();
		} catch (Exception e) {
			//logger.warn("createRootElement method throw exception:",e);
		}
		return rootElement;
	}

	/**
	 * get root element of document.
	 *
	 * @param fileName xml file name
	 * @return Element
	 */
	public static Element getRootElementByFile(String fileName) {
		if (null == fileName ||  0 == fileName.length()) {
			return null;
		}
		try {
			Element rootElement = null;
			FileInputStream fis = new FileInputStream(fileName);
			rootElement = getRootElement(fis);
			fis.close();
			return rootElement;
		} catch (Exception e) {
			//logger.warn("getRootElementByFile method throw exception:",e);
		}
		return null;
	}
	

	/**
	 * Description:
	 * <BR>.
	 *
	 * @param file the file
	 * @return the root element
	 */
	public static Element getRootElement(File file) {
		if (file == null) {
			return null;
		}
		try {			
			Element rootElement = null;
			FileInputStream fis = new FileInputStream(file);
			rootElement = getRootElement(fis);
			fis.close();
			return rootElement;
		} catch (Exception e) {
			//logger.warn("getRootElement method throw exception:",e);
		}
		return null;
	}

	/**
	 * Gets the root element.
	 *
	 * @param is the is
	 * @return the root element
	 */
	public static Element getRootElement(InputStream is) {
		if (is == null) {
			return null;
		}

		Element rootElement = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = db.parse(is);
			rootElement = doc.getDocumentElement();
		} catch (Exception e) {
			//logger.warn("getRootElement method throw exception:",e);
		}
		return rootElement;
	}

	/**
	 * Gets the root element.
	 *
	 * @param content the content
	 * @return the root element
	 */
	public static Element getRootElement(String content) {
		if (content == null) {
			return null;
		}
		StringReader reader=new StringReader(content);
		InputSource is=new InputSource(reader);
		return getRootElement(is);
	}
	
	/**
	 * Gets the root element.
	 *
	 * @param reader the reader
	 * @return the root element
	 */
	public static Element getRootElement(Reader reader) {
		if (reader == null) {
			return null;
		}		
		InputSource is=new InputSource(reader);
		return getRootElement(is);
	}
	
	
	/**
	 * Gets the root element.
	 *
	 * @param is the is
	 * @return the root element
	 */
	public static Element getRootElement(InputSource is) {
		if (is == null) {
			return null;
		}
		Element rootElement = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = db.parse(is);
			rootElement = doc.getDocumentElement();
		} catch (Exception e) {
			//logger.warn("getRootElement method throw exception:",e);	
		}
		return rootElement;
	}
	
	/**
	 * Gets the xml stream reader.
	 *
	 * @param is the is
	 * @param encoding the encoding
	 * @return the XML stream reader
	 */
	public XMLStreamReader getXMLStreamReader(InputStream is,String encoding){
		XMLStreamReader reader=null;
		try{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		reader = factory.createXMLStreamReader(is, encoding);
		
		}catch(XMLStreamException xe){
			//logger.warn("getRootElement method throw exception:",xe);	
		}
		return reader;
	}

	/**
	 * Description:
	 * <BR>.
	 *
	 * @param parentElement the parent element
	 * @return the child elements
	 */
	public static Element[] getChildElements(Element parentElement) {
		if (parentElement == null) {
			return null;
		}
		List<Element> childs = new ArrayList<Element>();
		for (Node node = parentElement.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				childs.add((Element) node);
			}
		}
		Element[] elements = new Element[childs.size()];
		childs.toArray(elements);
		return elements;
	}
	
	/**
	 * Description:
	 * <BR>.
	 *
	 * @param element the element
	 * @param tagName the tag name
	 * @return the child elements by tag name
	 */
	public static Element[] getChildElementsByTagName(Element element, String tagName) {
		if (element == null || tagName == null || tagName.length() == 0) {
			return null;
		}
		List<Element> childs = new ArrayList<Element>();
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				String name=node.getNodeName();
				if (name.equals(tagName)) {
					childs.add((Element) node);
				}
			}
		}
		Element[] elements = new Element[childs.size()];
		childs.toArray(elements);
		return elements;
	}	
	


	/**
	 * Description:
	 * <BR>.
	 *
	 * @param element the element
	 * @param childName the child name
	 * @return the child element
	 */
	public static Element getChildElement(Element element, String childName) {
		if (element == null || childName == null || childName.length() == 0) {
			return null;
		}
		Element childs = null;
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				if (node.getNodeName().equals(childName)) {
					childs = (Element) node;
					break;
				}
			}
		}
		return childs;
	}
	
	/**
	 * Description:
	 * <BR>.
	 *
	 * @param element the element
	 * @param tagName the tag name
	 * @return the child element by tag name
	 */
	public static Element getChildElementByTagName(Element element, String tagName) {
		if (element == null || tagName == null || tagName.length() == 0) {
			return null;
		}
		Element childs = null;
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				if (node.getNodeName().equals(tagName)) {
					childs = (Element) node;
					break;
				}
			}
		}
		return childs;
	}	
	
	

	/**
	 * Gets the element values.
	 *
	 * @param element the element
	 * @return the element values
	 */
	public static String[] getElementValues(Element element) {
		if (element == null) {
			return null;
		}
		List<String> childs = new ArrayList<String>();
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Text) {
				childs.add(node.getNodeValue());
			}
		}
		String[] values = new String[childs.size()];
		childs.toArray(values);
		return values;
	}

	/**
	 * Gets the element value.
	 *
	 * @param element the element
	 * @return the element value
	 */
	public static String getElementValue(Element element) {
		if (element == null) {
			return null;
		}
		String retnStr = null;
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Text) {
				String str = node.getNodeValue();
				if (str == null || str.length() == 0
						|| str.trim().length() == 0) {
					continue;
				} else {
					retnStr = str;
					break;
				}
			}
		}
		return retnStr;
	}
	
	/**
	 * Description:
	 * <BR>
	 * get the value of node.
	 *
	 * @param node the node
	 * @return the node value
	 */
	public static String getNodeValue(Node node) {
		if (node == null) {
			return null;
		}else{
			Node child=node.getFirstChild();
			if(child!=null){
			return child.getNodeValue();
			}else{
				return null;
			}
		}		
		
	}	
	
	
	/**
	 * Gets the element by attr.
	 *
	 * @param element the element
	 * @param attrName the attr name
	 * @param attrVal the attr val
	 * @return the element by attr
	 */
	public static Element getElementByAttr(Element element, String attrName,
			String attrVal) {
		return getElementByAttr(element, attrName, attrVal, true);
	}
	
	/**
	 * Gets the element by attr.
	 *
	 * @param element the element
	 * @param attrName the attr name
	 * @param attrVal the attr val
	 * @param dept the dept
	 * @return the element by attr
	 */
	public static Element getElementByAttr(Element element, String attrName,
			String attrVal, boolean dept) {
		if (element == null || attrName == null || attrName.length() == 0
				|| attrVal == null || attrVal.length() == 0) {
			return null;
		}
		String tmpValue = null;
		Element[] childs = getChildElements(element);
		for (int i = 0; i < childs.length; i++) {
			tmpValue = childs[i].getAttribute(attrName);
			if (attrVal.equals(tmpValue)) {
				return childs[i];
			}
		}
		if (dept) {
			for (int i = 0; i < childs.length; i++) {
				Element retn = getElementByAttr(childs[i], attrName, attrVal);
				if (retn != null) {
					return retn;
				}
			}
		}
		return null;
	}

	
	/**
	 * Format xml.
	 *
	 * @param element the element
	 * @return the string
	 */
	public static String formatXml(Element element) {
		return formatXml(element, 0);
	}

	/**
	 * Format xml.
	 *
	 * @param element the element
	 * @param indent the indent
	 * @return the string
	 */
	public static String formatXml(Element element, int indent) {
		indent++;
		for (Node n = element.getFirstChild(); n != null; n = n
				.getNextSibling()) {
			appendIndent(element, n, indent);
			if (!n.getNodeName().equals("#text")) {
				formatXml((Element) n, indent);
			}
		}
		indent--;
		appendIndent(element, indent);
		return element.toString();
	}
	
	/**
	 * Description:
	 * <BR>
	 * convert Iterable to xml.
	 *
	 * @param iterable the iterable
	 * @return the string
	 */
	private static String iterable2XML(Iterable iterable){
		StringBuilder sb=new StringBuilder();
		Iterator iterator=iterable.iterator();		
		 while(iterator.hasNext()){
			 
			Object entity=iterator.next();
			String name=entity.getClass().getName();
			//remove package
			int index=name.lastIndexOf(".");
			if(index!=-1){
			name=name.substring(index+1);
			}
			
			sb.append("<");			 
			sb.append(name);
			sb.append("  type=Object>\r\n");
			String beanXml=entity2XML(entity);
			sb.append(beanXml);
			sb.append("</");
			sb.append(name);
			sb.append(">\r\n");
		}		
		 return sb.toString();
	}
	
	/**
	 * Description:
	 * <BR>
	 * convert IEntity to xml.
	 *
	 * @param entity the entity
	 * @return the string
	 */
	public static String entity2XML(Object entity){
		StringBuilder sb=new StringBuilder();		
		Class clazz=entity.getClass();
		
		Method[] methods = clazz.getDeclaredMethods();		
		for (int i = 0, n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				
				if ((method.getModifiers() & Modifier.PUBLIC) == 1
						&& method.getDeclaringClass() != Object.class
						&& (method.getParameterTypes() == null || method
								.getParameterTypes().length == 0)){
					String methodName = method.getName();
					
					String property = null;
					if (methodName.startsWith("get")) {
						property = methodName.substring(3, 4).toLowerCase()
								+ methodName.substring(4);
					} else if (methodName.startsWith("is")) {
						property = methodName.substring(2, 3).toLowerCase()
								+ methodName.substring(3);
					}
					
					if (property != null) {
						Object value = method.invoke(entity, new Object[0]);
						sb.append("<");
						sb.append(property);						
						sb.append(" type=");
						
						String name=method.getReturnType().getName();
						//remove package
						int index=name.lastIndexOf(".");
						if(index!=-1){
						name=name.substring(index+1);
						}
						sb.append(name);
						sb.append(">\r\n");						
					
						
						if(value instanceof Iterable){							 
							String iteraXml=iterable2XML((Iterable)value);
							sb.append(iteraXml);							
						 }if(value instanceof CharSequence||value instanceof Number){
							 sb.append(value==null?"":value.toString());
						 }else{
							 sb.append(value==null?"":value);
						 }						
						
						sb.append("</");
						sb.append(property);						
						sb.append(">\r\n");					
					}
				}
			} catch (Exception e) {
				//logger.warn("entity2XML method throw exception:",e);
			}			
		}
		return sb.toString();
	}
	
	/**
	 * Object2 xml.
	 *
	 * @param obj the obj
	 * @param name the name
	 * @return the string
	 */
	public static String object2XML(Object obj,String name){
		StringBuilder sb=new StringBuilder();
		sb.append("<");
		sb.append(name);
		sb.append(">\r\n");
		
		if(obj instanceof Iterable){			
			String iteXml=iterable2XML((Iterable)obj);
			sb.append(iteXml);
		}else{			
			String entityXml=entity2XML(obj);
			sb.append(entityXml);
		}
		sb.append("</");
		sb.append(name);
		sb.append(">\r\n");
		return sb.toString();
	
	}
	
	/**
	 * Append child.
	 */
	public static void appendChild(){
		
	}

	/**
	 * Append indent.
	 *
	 * @param element the element
	 * @param pos the pos
	 * @param indent the indent
	 */
	
	private static void appendIndent(Element element, Node pos, int indent) {
		
		Document document = element.getOwnerDocument();		
		if (indent == 0) {
			element.insertBefore(document.createTextNode("\n"), pos);
		}
		for (int i = 0; i < indent; i++) {
			if (i == 0) {
				element.insertBefore(document.createTextNode("\n\t"), pos);
			} else {
				element.insertBefore(document.createTextNode("\t"), pos);
			}
		} 
	}

	/**
	 * Append indent.
	 *
	 * @param element the element
	 * @param indent the indent
	 */
	private static void appendIndent(Element element, int indent) {
		Document document = element.getOwnerDocument();
		if (indent == 0) {
			element.appendChild(document.createTextNode("\n"));
		}
		for (int i = 0; i < indent; i++) {
			if (i == 0) {
				element.appendChild(document.createTextNode("\n\t"));
			} else {
				element.appendChild(document.createTextNode("\t"));
			}
		}
	}
	
	
	/**
	 * Removes the.
	 *
	 * @param node the node
	 * @return the node
	 */
	public static Node remove(Node node){
		Node parent=node.getParentNode();
		return parent.removeChild(node);
	}

	/**
	 * Sets the attribute.
	 *
	 * @param element the element
	 * @param name the name
	 * @param value the value
	 */
	public static void setAttribute(Element element, String name, String value) {
		if (element == null || name == null || name.length() == 0
				|| value == null || value.length() == 0)
			return;
		else
			element.setAttribute(name, value);
	}

	/**
	 * Gets the attribute.
	 *
	 * @param element the element
	 * @param name the name
	 * @return the attribute
	 */
	public static String getAttribute(Element element, String name) {
		return getAttribute(element, name, null);
	}

	/**
	 * Gets the attribute.
	 *
	 * @param element the element
	 * @param name the name
	 * @param defval the defval
	 * @return the attribute
	 */
	public static String getAttribute(Element element, String name,
			String defval) {
		if (element == null || name == null || name.length() == 0)
			return defval;
		else
			return element.getAttribute(name);
	}
	
	/**
	 * Retrieves an attribute as a boolean.
	 *
	 * @param element the element
	 * @param attr the attr
	 * @param def the def
	 * @return True if the attribute exists and is not equal to "false"
	 * false if equal to "false", and def if not present.
	 */
	
	 public static boolean getBoolAttribute(Element element, String attr, boolean def) {
		    String value = getAttribute(element, attr);
		    if (value == null) {
		      return def;
		    }
		    return Boolean.parseBoolean(value);
		  }
	 
	 /**
 	 * Gets the bool attribute.
 	 *
 	 * @param element the element
 	 * @param attr the attr
 	 * @return True if the attribute exists and is not equal to "false"
 	 * false otherwise.
 	 */
	  public static boolean getBoolAttribute(Element element, String attr) {
	    return getBoolAttribute(element, attr, false);
	  }

	/**
	 * Transformer write.
	 *
	 * @param element the element
	 * @param filename the filename
	 * @throws TransformerException the transformer exception
	 */
	public static void transformerWrite(Node element, String filename)
			throws TransformerException {
		DOMSource domSource = new DOMSource(element);
		File f = new File(filename);
		StreamResult streamResult = new StreamResult(f);
		transformerWrite(domSource, streamResult);
	}

	/**
	 * Transformer write.
	 *
	 * @param element the element
	 * @param file the file
	 * @throws TransformerException the transformer exception
	 */
	public static void transformerWrite(Node element, File file) throws TransformerException {
		DOMSource domSource = new DOMSource(element);
		StreamResult streamResult = new StreamResult(file);
		transformerWrite(domSource, streamResult);
	}

	/**
	 * Transformer write.
	 *
	 * @param element the element
	 * @param outStream the out stream
	 * @throws TransformerException the transformer exception
	 */
	public static void transformerWrite(Node element, OutputStream outStream)
			throws TransformerException {
		DOMSource domSource = new DOMSource(element);
		StreamResult streamResult = new StreamResult(outStream);
		transformerWrite(domSource, streamResult);
	}

	/**
	 * Transformer write.
	 *
	 * @param element the element
	 * @param writer the writer
	 * @throws TransformerException the transformer exception
	 */
	public static void transformerWrite(Node element, Writer writer)
			throws TransformerException {
		DOMSource domSource = new DOMSource(element);
		StreamResult streamResult = new StreamResult(writer);
		transformerWrite(domSource, streamResult);
	}

	/**
	 * Transformer write.
	 *
	 * @param domSource the dom source
	 * @param streamResult the stream result
	 * @throws TransformerException the transformer exception
	 */
	public static void transformerWrite(DOMSource domSource,
			StreamResult streamResult) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		
		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		t.transform(domSource, streamResult);
	}
	
	/**
	 * To string.
	 *
	 * @param element the element
	 * @param type the type
	 * @return the string
	 * @throws TransformerException the transformer exception
	 */
	public static String toString(Node element,String type) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();		
		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		t.setOutputProperty(OutputKeys.METHOD, type);
		
		StringWriter writer=new StringWriter();
		StreamResult streamResult = new StreamResult(writer);		
		DOMSource domSource = new DOMSource(element);		 
		
		t.transform(domSource, streamResult);
		return writer.toString();		
	}
	
	/**
	 * To string.
	 *
	 * @param element the element
	 * @param pros the pros
	 * @return the string
	 * @throws TransformerException the transformer exception
	 */
	public static String toString(Node element,Properties pros) throws TransformerException{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();		
		t.setOutputProperties(pros);
		Writer writer=new StringWriter();
		StreamResult streamResult = new StreamResult(writer);		
		DOMSource domSource = new DOMSource(element);
		t.transform(domSource, streamResult);
		return writer.toString();		
	}
	
	/**
	 * To text.
	 *
	 * @param element the element
	 * @return the string
	 * @throws TransformerException the transformer exception
	 */
	public static String toText(Node element) throws TransformerException {
		return toString(element,"text");		
	}
	
	/**
	 * To html.
	 *
	 * @param element the element
	 * @return the string
	 * @throws TransformerException the transformer exception
	 */
	public static String toHtml(Node element) throws TransformerException {
		return toString(element,"html");		
	}
	
	/**
	 * To xml.
	 *
	 * @param element the element
	 * @param omitDeclaration the omit declaration
	 * @return the string
	 * @throws TransformerException the transformer exception
	 */
	public static String toXML(Node element, boolean omitDeclaration) throws TransformerException {
		Properties props=new Properties();
		props.setProperty(OutputKeys.OMIT_XML_DECLARATION , omitDeclaration?"yes":"no");
		props.setProperty(OutputKeys.ENCODING, "UTF-8");
		props.setProperty(OutputKeys.METHOD, "xml");
		props.setProperty(OutputKeys.VERSION, "1.0");
		props.setProperty(OutputKeys.MEDIA_TYPE, "text");
		return toString(element,props);		
	}

	/**
	 * Gets the value by x path.
	 *
	 * @param doc the doc
	 * @param keyPath the key path
	 * @return the value by x path
	 * @throws XPathExpressionException the x path expression exception
	 */
	public static String getValueByXPath(Element doc, String keyPath)
			throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(keyPath);
		String result = (String) expr.evaluate(doc, XPathConstants.STRING);
		return result;
	}

	/**
	 * Gets the single node by x path.
	 *
	 * @param doc the doc
	 * @param keyPath the key path
	 * @return the single node by x path
	 * @throws XPathExpressionException the x path expression exception
	 */
	public static Node getSingleNodeByXPath(Element doc, String keyPath)
			throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(keyPath);
		Node result = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return result;
	}

	/**
	 * Gets the node list by x path.
	 *
	 * @param doc the doc
	 * @param keyPath the key path
	 * @return the node list by x path
	 * @throws XPathExpressionException the x path expression exception
	 */
	public static NodeList getNodeListByXPath(Element doc, String keyPath)
			throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(keyPath);
		NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return result;
	}
	
	
	/**
	 * Description:
	 * <BR>
	 * find a element by name, the method just return the one element.
	 *
	 * @param element the element
	 * @param name the name
	 * @return the element
	 * @deprecated
	 */
	
	public static Element findElementByName(Element element, String name) {
		if (element == null || name == null || name.length() == 0) {
			return null;
		}		
		NodeList nodeList=element.getElementsByTagName(name);
		if(nodeList.getLength()>0){
			return (Element)nodeList.item(0);
		}
		return null;
	}

	/**
	 * Find element by attr.
	 *
	 * @param element the element
	 * @param attrName the attr name
	 * @param attrVal the attr val
	 * @return the element
	 * @deprecated
	 */
	public static Element findElementByAttr(Element element, String attrName,
			String attrVal) {
		return findElementByAttr(element, attrName, attrVal, true);
	}

	/**
	 * Find element by attr.
	 *
	 * @param element the element
	 * @param attrName the attr name
	 * @param attrVal the attr val
	 * @param dept the dept
	 * @return the element
	 * @deprecated
	 */
	public static Element findElementByAttr(Element element, String attrName,
			String attrVal, boolean dept) {
		if (element == null || attrName == null || attrName.length() == 0
				|| attrVal == null || attrVal.length() == 0) {
			return null;
		}
		String tmpValue = null;
		Element[] childs = getChildElements(element);
		for (int i = 0; i < childs.length; i++) {
			tmpValue = childs[i].getAttribute(attrName);
			if (attrVal.equals(tmpValue)) {
				return childs[i];
			}
		}
		if (dept) {
			for (int i = 0; i < childs.length; i++) {
				Element retn = findElementByAttr(childs[i], attrName, attrVal);
				if (retn != null) {
					return retn;
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the child elements.
	 *
	 * @param element the element
	 * @param childName the child name
	 * @return the child elements
	 * @deprecated
	 */
	public static Element[] getChildElements(Element element, String childName) {
		if (element == null || childName == null || childName.length() == 0) {
			return null;
		}
		List<Element> childs = new ArrayList<Element>();
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				String name=node.getNodeName();
				if (name.equals(childName)) {
					childs.add((Element) node);
				}
			}
		}
		Element[] elements = new Element[childs.size()];
		childs.toArray(elements);
		return elements;
	}
	
	/**
	 * Gets the child nodes.
	 *
	 * @param node the node
	 * @return the child nodes
	 * @deprecated
	 */
	public static Node[] getChildNodes(Node node) {
		if (node == null) {
			return null;
		}
		List<Element> childs = new ArrayList<Element>();
		for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
			childs.add((Element) n);
		}
		Node[] childNodes = new Element[childs.size()];
		childs.toArray(childNodes);
		return childNodes;
	}
	
	/**
	 * Gets the child element.
	 *
	 * @param element the element
	 * @return the child element
	 * @deprecated
	 */
	public static Element getChildElement(Element element) {
		if (element == null) {
			return null;
		}
		Element childs = null;
		for (Node node = element.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			if (node instanceof Element) {
				childs = (Element) node;
				break;
			}
		}
		return childs;
	}


}
