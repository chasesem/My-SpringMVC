package com.ben.web.tool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.ben.web.servlet.DispatcherServlet;

public class ApplicationContext {
	private static Map<String, Object> map = new HashMap<String, Object>();

	public void build(String filePath) {
		try {
			XMLOperator xo = new XMLOperator("",
					DispatcherServlet.class.getResourceAsStream(filePath));
			int count = xo.getNodeCount("beans/bean");
			for (int i = 0; i < count; i++) {
				String clzName = xo.getAttributeValue("beans/bean", i, "class");
				Class clz = Class.forName(clzName);
				Object obj = clz.newInstance();
				String xoName = "";
				if ((xoName = xo.getAttributeValue("beans/bean", i, "name")) != null) {
					map.put(xoName, obj);
				} else if((xoName = xo.getAttributeValue("beans/bean", i, "class"))!=null){
					map.put(clzName, obj);
				}
				//else if((xoName = xo.getAttributeValue("bean/property", i, "name"))!=null){
					//System.out.println(xoName);
				//}
			}
			int num = xo.getNodeCount("beans/bean/property");
			for (int i = 0 ; i < num ;i++){
				String xoName = xo.getAttributeValue("beans/bean/property", i, "name");
				String xoValue = xo.getAttributeValue("beans/bean/property", i, "value");
				map.put(xoName, xoValue);
			}
			
			//String xoName = xo.getAttributeValue("bean/property", i, "name");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object getBean(String key) {
		return map.get(key);
	}

	public Set<String> getKeys() {
		return map.keySet();

	}
}
