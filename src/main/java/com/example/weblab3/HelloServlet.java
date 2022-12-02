package com.example.weblab3;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    //check if the user is logged in and if so, display the page with the user's name
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Get a writer pointer
        // to display the successful result
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "admin";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            out.println("Connection successful!");
        } catch (SQLException e) {
            out.println("Connection failed!"+e.getMessage());
            e.printStackTrace();
        }
        out.println("</body></html>");
    }
    //if the user is not logged in, redirect to the login page
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }
    public void destroy() {
    }
}