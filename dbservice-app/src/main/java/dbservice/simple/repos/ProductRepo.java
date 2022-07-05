/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservice.simple.repos;

import dbservice.simple.dto.ProductRequest;
import dbservice.simple.entities.Product;
import java.util.Date;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
    
    @Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.transDate = :transDate")
    public List<Product> findAllByIdandDate(
            @PathParam("productId") String productId,
            @PathParam("transDate") Date transDate);
    
    @Query("SELECT p.productId, p.transDate, SUM(p.stock) FROM Product p GROUP BY p.productId")
    public List<Product> getAllSumStock();
    
    
    //@Query(hql)
    //List<Product> test1 = query.list();
    
    //String hql = "SELECT p.productId, p.transDate, SUM(p.stock) FROM Product p GROUP BY p.productId";
}
