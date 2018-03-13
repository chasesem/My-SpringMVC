package com.ben.web.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.ben.web.tool.XMLOperator;

public class XMLOperatorTest {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		XMLOperator xo = new XMLOperator("",XMLOperatorTest.class.getResourceAsStream("/spring-mvc.xml"));
		System.out.println(xo.getAttributeValue("beans/bean/property", 0,"value"));
		System.out.println(xo.getAttributeValue("beans/bean", 0, "class"));
		System.out.println(xo.getNodeCount("beans/bean"));
	}
}
