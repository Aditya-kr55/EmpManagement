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
import java.sql.*;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
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
            String sql = "UPDATE emp SET name=?, age=?, salary=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setInt(3, salary);
            pst.setInt(4, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                out.println("<h3 style='color:green;'>Employee Updated Successfully!</h3>");
            } else {
                out.println("<h3 style='color:red;'>Update Failed! Employee not found.</h3>");
            }

            // Include index.html
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }
}