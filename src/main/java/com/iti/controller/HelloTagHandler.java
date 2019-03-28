package com.iti.controller;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class HelloTagHandler extends SimpleTagSupport {


    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("inside a good place");
        JspWriter out=getJspContext().getOut();
        out.write("<font color='red'>Eraky</font>");
    }
}
