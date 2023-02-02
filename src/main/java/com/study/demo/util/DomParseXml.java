package com.study.demo.util;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * 解析xml文件方式一
 * dom文件采集
 * 优点：
 *
 * 缺点：
 *
 * @author caonan
 */
public class DomParseXml {
    public static void parseXml(String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //文件地址
        File file = new File(filePath);
        Document doc = db.parse(file);
        // 获取根节点
        Element root = doc.getDocumentElement();
        System.out.println(root.getNodeName());
        // xpath的使用
        XPath xPath = XPathFactory.newInstance().newXPath();
        String experssion = "/SResult/VulnFound/vuln-list/vuln";
        NodeList nodeList = (NodeList)xPath.compile(experssion).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (Node.ELEMENT_NODE == item.getNodeType()){
                NamedNodeMap attributes = item.getAttributes();
                System.out.println(attributes);
            }
        }


    }

    public static void main(String[] args) throws Exception {
        DomParseXml.parseXml("E:\\tj.xml");
    }
}
