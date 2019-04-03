package com.iti.model.Dao;
import com.iti.model.entity.Product;
import com.iti.model.request.ShopRequest;
import org.hibernate.Session;
import java.util.ArrayList;
import com.iti.model.entity.Category;

//Eraky Import


//Aya import


//Ashraf Import



public interface ProductDao {
    //Eraky Part

    ArrayList<Product> retrieveProductsByFilters(ShopRequest request,Session session,int start,int pageSize);
    public ArrayList<Product> retrieveProductsByPage(Session session,int start,int pageSize);


    //Aya Part

    Product retriveProductByID(Integer id, Session session);
    Product retriveProductByExample(Product product, Session session);
    ArrayList<Product> retriveProductsByExample(Product product, Session session);
    ArrayList<Product> retriveProductsByAge(int minAge, int maxAge, Session session);
    ArrayList<Product> retriveProductsByPrice(long minPrice, long maxPrice, Session session);
    ArrayList<Product> retriveProductByName(String name, Session session);
    boolean persistProduct(Product product, Session session);
    boolean updateProduct(Product oldProduct, Product newProduct, Session session);
    ArrayList<Product> retriveProducts(Session session);
    ArrayList<Product> retriveProductByCategory(Category category, Session session);
    ArrayList<Product> retrieveNewProducts(Session session);
    ArrayList<Product> retrievePopularProducts(Session session); 
    boolean updateProductQuantity(Product soldedProduct  ,Session session);

    //Ashraf Part

}
