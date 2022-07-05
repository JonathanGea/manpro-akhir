/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlserviceapp_c.simple.utils;

import java.io.StringWriter;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
public class XmlParser {
    
    public String convertToXml(String csvText) {
        
        String xmlString = "";
        
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Data");
            document.appendChild(root);

            String input = csvText;
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
           
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            
            xmlString = "1" + writer.toString();
        } catch (Exception pce) {
            xmlString = "0" + pce.getMessage();
        } 
        
        return xmlString;
    }
}
