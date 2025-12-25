package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello Tomcat";
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.getWriter().print("Hello from servlet");
    }

    public String getMessage() {
        return message;
    }
}
