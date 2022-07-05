/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservice.simple.controllers;

import dbservice.simple.dto.LogServiceRequest;
import dbservice.simple.dto.ProductRequest;
import dbservice.simple.entities.Product;
import dbservice.simple.services.ProductService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;
    
    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }
    
    @GetMapping("/finds")
    public List<Product> finds(){
        return productService.finds();
    }
    
//    @GetMapping("/findallbyid")
//    public List<Product> findAllById(@RequestBody ProductRequest productrequest) {
//        return productService.findAllById(productrequest.getProductId());
//    }
    
    @PostMapping("/findallbyidanddate")
    public List<Product> findAllByIdandDate(@RequestBody ProductRequest productrequest) {
        
        //call logs
        String logs = getDateTime() + ": database process started#";
        logs += getDateTime() + ": call database#";
        
        logs += getDateTime() + ": database process success#";
        LogServiceRequest logServiceRequest = new LogServiceRequest("Database", logs);
        System.out.println(logs);

        String logsResult = restTemplate.postForObject("http://localhost:2004/logservice/postlog", 
            logServiceRequest, String.class);
        
        System.out.println(logsResult);
        
       return productService.findAllByIdandDate(productrequest.getProductId(), productrequest.getTransDate());
        
    }
    
    @GetMapping("/getallsumstock")
    public List<Product> getAllSumStock(){
//        List<Product> product = productService.getAllSumStock();
//        return product;
        return productService.getAllSumStock();
    }
    
    @GetMapping("/getallsumstock1")
    public List<Product> getAllSumStock1(){
        return productService.getAllSumStock1();
    }
    
}
