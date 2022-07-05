/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlserviceapp_c.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmlserviceapp_c.simple.utils.XmlParser;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/xmldataentry")
public class XmlDataEntryController {
    
    @Autowired
    private XmlParser xmlParser;
    
    @PostMapping("/processxml")
    public String processXml(@RequestBody String input){        
        return xmlParser.convertToXml(input);
    }

}
