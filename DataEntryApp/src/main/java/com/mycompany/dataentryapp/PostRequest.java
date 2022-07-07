/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dataentryapp;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
public class PostRequest implements Serializable {
    
    private String productId;
    private Date transDate;

    public PostRequest() {
    }

    public PostRequest(String productId, Date transDate) {
        this.productId = productId;
        this.transDate = transDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }
    
}
