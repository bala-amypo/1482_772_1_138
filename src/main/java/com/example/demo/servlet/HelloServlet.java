package com.example.demo.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Test expects this exact value
        message = "Hello Tomcat";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");

        // Test expects this output
        resp.getWriter().write("Hello from servlet");
    }

    // Test explicitly checks this method
    public String getMessage() {
        return message;
    }
}
