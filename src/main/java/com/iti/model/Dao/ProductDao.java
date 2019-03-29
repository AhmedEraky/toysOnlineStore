package com.iti.model.Dao;
import com.iti.model.entity.Product;
import org.hibernate.Session;
import java.util.ArrayList;

public interface ProductDao {
    Product retriveProductByID(Integer id, Session session);
    Product retriveProductByExample(Product product, Session session);
    ArrayList<Product> retriveProductsByExample(Product product, Session session);
    ArrayList<Product> retriveProductsByAge(int minAge, int maxAge, Session session);
    ArrayList<Product> retriveProductsByPrice(long minPrice, long maxPrice, Session session);
    ArrayList<Product> retriveProductByName(String name, Session session);
    boolean persistProduct(Product product, Session session);
    boolean updateProduct(Product oldProduct, Product newProduct, Session session);
}
