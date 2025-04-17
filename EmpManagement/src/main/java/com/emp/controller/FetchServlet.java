package com.emp.controller;

import com.emp.model.EmpDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;


@WebServlet("/fetch")
public class FetchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection con = EmpDAO.getConnection();
            String sql = "SELECT * FROM emp";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            response.setContentType("text/html");
            response.getWriter().println("<h2>Employee List</h2>");
            response.getWriter().println("<table border='1'><tr><th>ID</th><th>Name</th><th>Age</th><th>Salary</th></tr>");

            while (rs.next()) {
                response.getWriter().println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                        rs.getString("name") + "</td><td>" +
                        rs.getInt("age") + "</td><td>" +
                        rs.getInt("salary") + "</td></tr>");
            }

            response.getWriter().println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}