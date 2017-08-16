package by.galkina.parser.handler;


import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XmlParser {
    private static final Logger LOGGER = Logger.getLogger(XmlParser.class);
    private static XmlParser instance;
    private Node mainNode;
    private Document document;
    private TreeSet<String> names;
    private String path;

    private XmlParser(){
        names = new TreeSet<>();
    }

    public static XmlParser getInstance(){
        if(instance==null){
            instance = new XmlParser();
        }
        return instance;
    }

    public List<Map> parseXml(String pathToFile){
        path = pathToFile;
        List<Map> list = new ArrayList<>();
        try{
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(pathToFile);
            Node root = document.getDocumentElement();
            NodeList books = root.getChildNodes();
            mainNode = books.item(1);
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                Map<String, String> map = new TreeMap<>();
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            names.add(bookProp.getNodeName());
                            map.put(bookProp.getNodeName(), bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                }
                list.add(map);
            }
        }catch (ParserConfigurationException | IOException | SAXException ex) {
            LOGGER.error("Exception in xml parsing: " + ex.getMessage());
        }
        return list;
    }

    public void addElement(List<String> content)throws TransformerFactoryConfigurationError {
        Node root = document.getDocumentElement();
        Element mainElement = document.createElement(mainNode.getNodeName());
        ArrayList<String> list = new ArrayList<>(names);
        for(int i=0;i<list.size();i++){
            Element el = document.createElement(list.get(i));
            el.setTextContent(content.get(i));
            mainElement.appendChild(el);
        }
        root.appendChild(mainElement);
        LOGGER.info("New element was added.");
        writeDocument(document);
    }

    public void removeElement(int number){
        Node root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        root.removeChild(nodes.item(number));
        LOGGER.info("Element was removed.");
        writeDocument(document);
    }

    private void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            LOGGER.error("Exception in rewriting file: " + e.getMessage());
        }
    }

    public Set<String> getNames() {
        return names;
    }
}
