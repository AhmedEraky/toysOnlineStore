package com.iti.controller.filter.handler;

import com.iti.model.entity.Store;
import com.iti.service.StoreService;
import com.iti.service.impl.StoreServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductHandler implements Handler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        StoreService service=new StoreServiceImpl();
        
        List<Store> stores=service.getStores();
            List<String> storesResponse = new ArrayList<String>();

            for(Store store:stores){
                storesResponse.add(store.getName());
            }
        request.getSession().setAttribute("stores",storesResponse);/////////// on request
         filterChain.doFilter(request,response);
    }
}
