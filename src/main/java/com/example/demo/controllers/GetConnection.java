package com.example.demo.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    public static Connection getConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/SMP";
        String username = "root";
        String password = "Yaomengjie2";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn =  DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
