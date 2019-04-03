package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Product;
import com.iti.model.request.ShopRequest;
import com.iti.model.response.ShopResponse;
import com.iti.service.ShopService;
import org.hibernate.Session;

import java.util.ArrayList;

public class ShopServiceImpl implements ShopService {
    @Override
    public ArrayList<ShopResponse> shopData(ShopRequest shopRequest) {
        ProductDao productDao=new ProductDaoImplementation();
        Session session= HibernateUtils.getSession();
        ArrayList<Product> products=productDao.retrieveProductsByFilters(shopRequest,session,0,10);
        ArrayList<ShopResponse> shopResponse=new ArrayList<>();
        for (Product product:products){
            ShopResponse response=new ShopResponse();
            response.setName(product.getName());
            response.setImagePath(product.getImagePath());
            response.setPrice("$"+product.getPrice());
            response.setImagePath(product.getImagePath());
            shopResponse.add(response);
        }
        return shopResponse;
    }
}
