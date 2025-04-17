package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmpDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/EmployeeManagement";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}