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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = EmpDAO.getConnection();
            String sql = "DELETE FROM emp WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                out.println("<h3 style='color:green;'>Employee Deleted Successfully!</h3>");
            } else {
                out.println("<h3 style='color:red;'>Delete Failed! Employee not found.</h3>");
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