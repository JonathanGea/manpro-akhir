/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservice.simple.services;

import dbservice.simple.dto.ProductRequest;
import dbservice.simple.entities.Product;
import dbservice.simple.repos.ProductRepo;
import dbservice.simple.utils.HibernateUtil;
import java.util.Date;
import java.util.List;
//import org.hibernate.Query;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;
    
    public List<Product> finds(){
        return productRepo.findAll();
    }
    
//    public List<Product> findAllById(String productId) {
//        return productRepo.findAllById(productId);
//    }
    
    public List<Product> findAllByIdandDate(String productId, Date transDate) {
        return productRepo.findAllByIdandDate(productId, transDate);
    }
    
    public List<Product> getAllSumStock() {
        return productRepo.getAllSumStock();
    }
    
    
    public List<Product> getAllSumStock1(){
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        
        String hql = "SELECT p.productId, p.transDate, SUM(p.stock) FROM Product p GROUP BY p.productId";
        Query query = session.createQuery(hql);
        List<Product> result = query.list();
        
        session.close();
        sessionFactory.close();
        return result;
        
    }
    
}
