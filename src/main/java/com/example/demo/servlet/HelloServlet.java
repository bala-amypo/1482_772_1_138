package com.example.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

public class HelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        message = "Hello Tomcat";
    }

    public String getMessage() {
        return message;
    }
}
