package com.iti.controller.filter.handler;

import com.iti.model.request.ShopRequest;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ShopHandler implements Handler {
    ShopRequest shopRequest;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        shopRequest=createShopRequest(request);

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
