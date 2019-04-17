package com.iti.controller.filter.handler;

import com.iti.model.entity.Product;
import com.iti.model.request.ShopRequest;
import com.iti.model.response.ShopResponse;
import com.iti.service.ShopService;
import com.iti.service.UploadProductService;
import com.iti.service.impl.ShopServiceImpl;
import com.iti.service.impl.UploadProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ShopHandler implements Handler {
    ShopRequest shopRequest;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        shopRequest=createShopRequest(request);
        ShopService shopService=new ShopServiceImpl();
        String pageNumber=request.getParameter("pageNo");
        if(pageNumber==null){
            pageNumber="1";
        }
        ArrayList<ShopResponse> shopResponse=shopService.shopData(shopRequest,Integer.parseInt(pageNumber));
        request.setAttribute("shopProduct",shopResponse);
        request.setAttribute("size",shopService.getProductCount(shopRequest));
        filterChain.doFilter(request,response);
    }

    private ShopRequest createShopRequest(HttpServletRequest request) {
        ShopRequest shopRequest=new ShopRequest();
        try {
            BeanUtils.populate(shopRequest,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return shopRequest;
    }
}
