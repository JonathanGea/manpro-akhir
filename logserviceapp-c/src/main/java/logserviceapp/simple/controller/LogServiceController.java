/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logserviceapp.simple.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import logserviceapp.simple.dto.LogServiceRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/logservice")
public class LogServiceController {
    
    private String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

    @PostMapping("/postlog")
    public String postLog(@RequestBody LogServiceRequest request) {
        
        FileWriter fw = null;
        String result = "";
        
        try {            
            String folder = "C:\\Users\\user\\Documents\\NetBeansProjects\\SimpleMicroservices\\Logs\\";
            folder = folder + request.getLogId() + "\\";

            String logs = request.getLogs();
            StringTokenizer tkn = new StringTokenizer(logs, "#");
            List<String> listLogs = new ArrayList<String>();

            while (tkn.hasMoreTokens()) {
                listLogs.add(tkn.nextToken());
            }   //tulis ke file

            String uniqueId = generateUniqueId();
            String fileName = folder + uniqueId + ".txt";
            fw = new FileWriter(new File(fileName));
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (String str : listLogs) {
                bw.write(str);
                bw.newLine();
            }
            
            bw.close();
            fw.close();
            
            result = "1" + "Success:" + uniqueId;
        } catch (Exception ex) {
            result = "0" + ex.getMessage();
        } 
        
        return result;
    }
}
