package com.ben.web.tool;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Use XPath to catch node,and then can operate it.
 * 
 * 
 * 
 */
public class XMLOperator {
  private Document xml;
  private Element root;
  private XPath xpathHandle = XPathFactory.newInstance().newXPath();
  private String filePath;

  public XMLOperator(String filePath) throws ParserConfigurationException, SAXException,
      IOException {
    this.filePath = filePath;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // factory.setNamespaceAware(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    this.xml = builder.parse(filePath);
    this.root = this.xml.getDocumentElement();
  }

  public XMLOperator(String filePath, InputStream inputStream) throws ParserConfigurationException,
      SAXException, IOException {
    this.filePath = filePath;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // factory.setNamespaceAware(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    this.xml = builder.parse(inputStream);
    this.root = this.xml.getDocumentElement();
  }

  public boolean isExist(String xpath) throws XPathExpressionException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    return ((NodeList) result).getLength() != 0;
  }

  /**
   * get node value by special XPath and index to catch only node
   * 
   * @param xpath
   * @param index
   * @return
   * @throws XPathExpressionException
   */
  public String getValue(String xpath, int index) throws XPathExpressionException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    return ((NodeList) result).item(index).getTextContent();
  }

  /**
   * set node value by special XPath and index to catch only node
   * 
   * @param xpath
   * @param index
   * @param newValue
   * @throws XPathExpressionException
   * @throws TransformerException
   */
  public void setValue(String xpath, int index, String newValue) throws XPathExpressionException,
      TransformerException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    ((NodeList) result).item(index).setTextContent(newValue);
    saveXML();
  }

  /**
   * get the number of node by special XPath to catch all node
   * 
   * @param xpath
   * @return
   * @throws XPathExpressionException
   */
  public int getNodeCount(String xpath) throws XPathExpressionException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    return ((NodeList) result).getLength();
  }

  /**
   * get the attribute of special node by special XPath and index to catch only node
   * 
   * @param xpath
   * @param index
   * @param attributeName
   * @return
   * @throws XPathExpressionException
   */
  public String getAttributeValue(String xpath, int index, String attributeName)
      throws XPathExpressionException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    if (((NodeList) result).item(index).getAttributes().getNamedItem(attributeName) != null) {
      return ((NodeList) result).item(index).getAttributes().getNamedItem(attributeName)
          .getNodeValue();
    } else {
      return null;
    }
  }

  public boolean isExistAttributeValue(String xpath, int index, String attributeName)
      throws XPathExpressionException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    return ((NodeList) result).item(index).getAttributes().getNamedItem(attributeName) != null;
  }

  /**
   * set the attribute of special node by special XPath and index to catch only node
   * 
   * @param xpath
   * @param index
   * @param attributeName
   * @param newValue
   * @throws XPathExpressionException
   * @throws TransformerException
   */
  public void setAttributeValue(String xpath, int index, String attributeName, String newValue)
      throws XPathExpressionException, TransformerException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    ((NodeList) result).item(index).getAttributes().getNamedItem(attributeName)
        .setTextContent(newValue);
    saveXML();
  }

  /**
   * add new node to root
   * 
   * @param nodeName
   * @param nodeValue
   * @throws TransformerException
   */
  public void addNodeToRoot(String nodeName, String nodeValue) throws TransformerException {
    Element newNode = xml.createElement(nodeName);
    newNode.setTextContent(nodeValue);
    root.appendChild(newNode);
    saveXML();
  }

  /**
   * add new attribute to special node by special XPath and index to catch only node
   * 
   * @param xpath
   * @param index
   * @param attributeName
   * @param newValue
   * @throws XPathExpressionException
   * @throws TransformerException
   */
  public void addAttribute(String xpath, int index, String attributeName, String newValue)
      throws XPathExpressionException, TransformerException {
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    Attr newAttr = xml.createAttribute(attributeName);
    newAttr.setValue(newValue);
    ((NodeList) result).item(index).getAttributes().setNamedItem(newAttr);
    saveXML();
  }

  /**
   * add node to special node
   * 
   * @param xpath
   * @param index
   * @param nodeName
   * @param nodeValue
   * @throws XPathExpressionException
   * @throws TransformerException
   */
  public void addNode(String xpath, int index, String nodeName, String nodeValue)
      throws XPathExpressionException, TransformerException {
    Element newNode = xml.createElement(nodeName);
    newNode.setTextContent(nodeValue);
    Object result = xpathHandle.compile(xpath).evaluate(xml, XPathConstants.NODESET);
    ((NodeList) result).item(index).appendChild(newNode);
    saveXML();
  }

  /**
   * save XML to file
   * 
   * @param filePath
   * @throws TransformerException
   */
  public void saveXML() throws TransformerException {

    DOMSource source = new DOMSource(xml);
    StreamResult result = new StreamResult(filePath);
    TransformerFactory tff = TransformerFactory.newInstance();
    Transformer tf = tff.newTransformer();
    tf.transform(source, result);
  }

  public void saveXML(String filePath) throws TransformerException {
    DOMSource source = new DOMSource(xml);
    StreamResult result = new StreamResult(filePath);
    TransformerFactory tff = TransformerFactory.newInstance();
    Transformer tf = tff.newTransformer();
    tf.transform(source, result);
  }

  /**
   * create a XML file and add a root node to this file
   * 
   * @param savePath
   * @param rootNodeName
   * @throws ParserConfigurationException
   * @throws TransformerException
   */
  public static void createXMLFile(String savePath, String rootNodeName)
      throws ParserConfigurationException, TransformerException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.newDocument();
    document.appendChild(document.createElement(rootNodeName));
    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer();

    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(savePath);
    transformer.transform(source, result);
  }

}
