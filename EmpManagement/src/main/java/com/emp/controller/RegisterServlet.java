package com.emp.controller;

import com.emp.model.EmpDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int salary = Integer.parseInt(request.getParameter("salary"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = EmpDAO.getConnection();
            String sql = "INSERT INTO emp (id, name, age, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.setInt(4, salary);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                out.println("<h3 style='color:green;'>Employee Registered Successfully!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            } else {
                out.println("<h3 style='color:red;'>Registration Failed!</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }
}