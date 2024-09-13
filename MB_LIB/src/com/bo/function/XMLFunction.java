/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import cjm.component.cb.map.ToMap;
import cjm.component.cb.xml.ToXML;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class XMLFunction {

    private static Logger log = Logger.getLogger(XMLFunction.class);

    public static HashMap convertXMLtoHashmap(String xmltext) {
        HashMap result = new HashMap();
        try {
            InputSource is = new InputSource(new StringReader(xmltext));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(is);
            Element rootElement = document.getRootElement();
            List<Element> content = rootElement.getChildren();
//            System.out.println("contect size : " + content.size());
            for (int temp = 0; temp < content.size(); temp++) {
                Element data = content.get(temp);
                System.out.println(data.getName() + " - " + data.getValue());
                result.put(data.getName(), data.getValue());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static HashMap convertXMLtoHashmap2(String xmltext) {
        HashMap result = new HashMap();
        try {
            InputSource is = new InputSource(new StringReader(xmltext));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(is);
            Element rootElement = document.getRootElement();
            ToMap tomap = new ToMap();
            HashMap retHashmap = (HashMap) tomap.convertToMap(xmltext);
            result = (HashMap) retHashmap.get(rootElement.getName());
            retHashmap = null;
            tomap = null;
            rootElement = null;
            document = null;
            saxBuilder = null;
            is = null;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static String convertHashmapToXML(HashMap map, String rootelement) {
        Element root = new Element(rootelement);
        Document doc = new Document(root);
        Iterator iter = map.keySet().iterator();
        String item;
        while (iter.hasNext()) {
            item = iter.next().toString();
            Element node = new Element(item);
            node.setText(map.get(item).toString());
            doc.getRootElement().addContent(node);

        }
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getCompactFormat());
//        return xmlOutput.outputString(doc);
        return xmlOutput.outputString(doc).substring(40);
    }

    public static String convertHashmapToXML2(HashMap map, String rootelement) {
        HashMap root = new HashMap();
        root.put(rootelement, map);
        ToXML toxml = new ToXML();
        return String.valueOf(toxml.convertToXML(root, true));
    }
//    
//
//    public static void main(String[] args) throws JDOMException, IOException {
//        HashMap root = new HashMap();
//        HashMap a = new HashMap();
//        a.put("nama", "herry");
//        a.put("alamat", "kebagusan");
//        root.put("verdana", a);
//        HashMap data = new HashMap();
//        data.put("tgl_lahir", "16 september 1986");
//        data.put("alamat", "jagakarsa");
//        data.put("tempat_lahir", "jakarta");
//        a.put("data", data);
////        System.out.println(XMLFunction.convertHashmapToXML(a, "verdana"));
//
//        String xmltext = "<verdana><nama>herry</nama><data><tempat_lahir>jakarta</tempat_lahir><tgl_lahir>16 september 1986</tgl_lahir><alamat>jagakarsa</alamat></data><alamat>kebagusan</alamat></verdana>";
//        System.out.println(XMLFunction.convertXMLtoHashmap2(xmltext));
//        System.out.println(XMLFunction.convertXMLtoHashmap(xmltext));
//    }

}
