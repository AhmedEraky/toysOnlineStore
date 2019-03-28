package com.iti.controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DoubleTagHandler extends SimpleTagSupport {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void doTag() throws JspException, IOException {
        int doubleNumber=Integer.parseInt(number);
        doubleNumber*=2;
        getJspContext().getOut().write("<p>"+doubleNumber+"</p>");
    }
}
