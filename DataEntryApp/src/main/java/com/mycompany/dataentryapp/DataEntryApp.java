/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dataentryapp;

import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
public class DataEntryApp {

    public static void main(String[] args) {
        
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Data");
            document.appendChild(root);

            String input = "P001;2022-05-31#P002;2022-05-31#P003;2022-04-30";
            StringTokenizer tkn = new StringTokenizer(input, "#");
            
            while (tkn.hasMoreTokens()) {
                //System.out.println(tkn.nextToken());
                String cols = tkn.nextToken();
                String[] arrCols = cols.split(";");

                Element product = document.createElement("Product");
                Element productIdTag = document.createElement("ProductId");
                productIdTag.appendChild(document.createTextNode(arrCols[0]));
                product.appendChild(productIdTag);

                Element dateTag = document.createElement("Date");
                dateTag.appendChild(document.createTextNode(arrCols[1]));
                product.appendChild(dateTag);
                
                Element stockTag = document.createElement("Stock");
                stockTag.appendChild(document.createTextNode(""));
                product.appendChild(stockTag);

                root.appendChild(product);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(System.out);

            transformer.transform(domSource, result);
            System.out.println(result.toString());

            //System.out.println("Done creating XML File");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
