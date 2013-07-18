package com.example.XMLParserAndroid.XMLObject;

import com.example.XMLParserAndroid.XMLModel.XMLModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLObject {

    private ArrayList<HashMap<String, String>> itemContentList = new ArrayList<HashMap<String, String>>();

    public ArrayList<HashMap<String, String>> readXml(String xmlUrl) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlUrl);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName(XMLModel.ITEM);
        for (int itemCount = 0; itemCount < nodeList.getLength(); itemCount++) {
            Node node = nodeList.item(itemCount);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String link = element.getElementsByTagName(XMLModel.LINK).item(0).getTextContent();
                String  title = element.getElementsByTagName(XMLModel.TITLE).item(0).getTextContent();
                String  time = element.getElementsByTagName(XMLModel.TIME).item(0).getTextContent();

                HashMap<String, String> itemContent = new HashMap<String, String>();
                itemContent.put(XMLModel.LINK, link);
                itemContent.put(XMLModel.TITLE, title);
                itemContent.put(XMLModel.TIME, time);
                itemContentList.add(itemContent);
            }
        }
        return itemContentList;
    }

}
