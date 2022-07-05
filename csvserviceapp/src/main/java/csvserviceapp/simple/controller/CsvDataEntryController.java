/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvserviceapp.simple.controller;

import csvserviceapp.simple.dto.CsvRequest;
import csvserviceapp.simple.dto.CsvResponse;
import csvserviceapp.simple.dto.LogServiceRequest;
import csvserviceapp.simple.dto.ProductResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/csvdataentry")
public class CsvDataEntryController {
    
    @Autowired
    private RestTemplate restTemplate;

    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

    @PostMapping("/convertxml")
    public CsvResponse convertXML(@RequestBody CsvRequest input) {
        
        CsvResponse resp = new CsvResponse();
        resp.setId(input.getId());

        String logs = getDateTime() + ": csv process started#";
        logs += getDateTime() + ": Call xml parser#";
        
        String xmlConvert = restTemplate.postForObject("http://localhost:2002/xmldataentry/processxml",
                input.getContent(), String.class);
        logs += getDateTime() + ": read xml result#";

        //contact xml converter service
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String xmlResult = "";

        if (xmlConvert.toCharArray()[0] == '1') {
            xmlResult = xmlConvert.substring(1, xmlConvert.length() - 1);
            logs += getDateTime() + ": xml process success#";
        } else {
            xmlResult = "There is an error while convert the xml: " + xmlConvert.substring(1, xmlConvert.length() - 1);
            logs += getDateTime() + ": xml process fail#";
        }
        
        
        
        //call dbservice-app to get the stock by productid and date
        String ProductId = "P001";
        String transDate = "2022-05-31";
        ProductResponse resproduct = new ProductResponse();
        resproduct.setId(input.getId());
        resproduct.setProductId(ProductId);
        resproduct.setTransDate(transDate);
        
        String dbservice = restTemplate.postForObject("http://localhost:2003/products/findallbyidanddate", 
                resproduct, String.class);
        //String dbservice = restTemplate.getForObject("http://localhost:2003/products/finds", String.class);
        System.out.println(dbservice);
        
        
        
        resp.setXmlResult(xmlResult);
        logs += getDateTime() + ": csv process ended";

        //call logs
        LogServiceRequest logServiceRequest = new LogServiceRequest("Csv", logs);

        String logsResult = restTemplate.postForObject("http://localhost:2004/logservice/postlog", 
                logServiceRequest, String.class);

        return resp;
    }
    
}
