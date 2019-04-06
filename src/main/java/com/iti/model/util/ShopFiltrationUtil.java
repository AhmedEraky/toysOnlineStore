package com.iti.model.util;

import com.iti.model.entity.Category;
import com.iti.model.request.ShopRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ShopFiltrationUtil {
    public void ShopFilter(Criteria criteria, ShopRequest request){
        //catgory Filtration
        filterByCategory(criteria,request);
        //Discount Filtration

        filterByDiscount(criteria,request);
        //Price filter

        if(request.getPriceRangeInput()!=null) {
            filterByPrice(criteria,request);
        }

        //Search Filter
        Criterion searchCriterion=Restrictions.sqlRestriction("(1=0)");
        if(request.getSearch()!=null){
            filterByname(criteria,request);
        }

    }

    private void filterByname(Criteria criteria, ShopRequest request) {
        Criterion searchCriterion=Restrictions.like("name","%"+request.getSearch()+"%");
        criteria.add(searchCriterion);

    }

    private void filterByCategory(Criteria criteria, ShopRequest request) {
        Criterion actionFiguresCriterion=Restrictions.sqlRestriction("(1=0)");
        Criterion carsAndPlanesCriterion=Restrictions.sqlRestriction("(1=0)");
        Criterion constructionCriterion=Restrictions.sqlRestriction("(1=0)");
        Criterion dollsCriterion=Restrictions.sqlRestriction("(1=0)");
        Criterion puzzlesCriterion=Restrictions.sqlRestriction("(1=0)");
        int count=0;
        if(request.getActionFigures()!=null){
            count++;
            actionFiguresCriterion=Restrictions.eq("category.name","Action Figures");
        }
        if (request.getCarsAndPlanes()!=null){
            count++;
            carsAndPlanesCriterion=Restrictions.eq("category.name","Cars And Planes");
        }
        if (request.getConstruction()!=null){
            count++;
            constructionCriterion=Restrictions.eq("category.name","Construction");
        }
        if (request.getDolls()!=null){
            count++;
            dollsCriterion=Restrictions.eq("category.name","Dolls");
        }
        if (request.getPuzzles()!=null){
            count++;
            puzzlesCriterion=Restrictions.eq("category.name","Puzzles");
        }
        if(count>0)
            criteria.add(Restrictions.or(actionFiguresCriterion,carsAndPlanesCriterion,constructionCriterion,dollsCriterion,puzzlesCriterion));

    }

    private void filterByDiscount(Criteria criteria, ShopRequest request) {
        Criterion discount5Criterion=Restrictions.sqlRestriction("(1=0)");
        Criterion discount10Criterion=Restrictions.sqlRestriction("(1=0)");
        Criterion discount20Criterion=Restrictions.sqlRestriction("(1=0)");
        Criterion discount30Criterion=Restrictions.sqlRestriction("(1=0)");
        Criterion discount50Criterion=Restrictions.sqlRestriction("(1=0)");
        Criterion discount60Criterion=Restrictions.sqlRestriction("(1=0)");
        int count=0;
        if (request.getDiscount5Option()!=null){
            count++;
            discount5Criterion=Restrictions.ge("discountPercentage",5);
        }

        if (request.getDiscount10Option()!=null){
            count++;
            discount10Criterion=Restrictions.ge("discountPercentage",10);
        }

        if (request.getDiscount20Option()!=null){
            count++;
            discount20Criterion=Restrictions.ge("discountPercentage",20);
        }

        if (request.getDiscount30Option()!=null){
            count++;
            discount30Criterion=Restrictions.ge("discountPercentage",30);
        }

        if (request.getDiscount50Option()!=null){
            count++;
            discount50Criterion=Restrictions.ge("discountPercentage",50);
        }

        if (request.getDiscount60Option()!=null){
            count++;
            discount60Criterion=Restrictions.ge("discountPercentage",60);
        }
        if(count>0)
            criteria.add(Restrictions.or(discount5Criterion,discount10Criterion,discount20Criterion,discount30Criterion,discount50Criterion,discount60Criterion));

    }

    private void filterByPrice(Criteria criteria, ShopRequest request) {
        String minPrice="",maxPrice="";
        int i = 1;
        while (request.getPriceRangeInput().charAt(i) != ' ') {
            minPrice += request.getPriceRangeInput().charAt(i);
            i++;
        }
        while (request.getPriceRangeInput().charAt(i) != '$')
            i++;

        i++;
        for (; i < request.getPriceRangeInput().length(); i++) {
            maxPrice += request.getPriceRangeInput().charAt(i);
        }
        criteria.add(Restrictions.ge("price", Double.parseDouble(minPrice))).add(Restrictions.le("price", Double.parseDouble(maxPrice)));

    }


}
