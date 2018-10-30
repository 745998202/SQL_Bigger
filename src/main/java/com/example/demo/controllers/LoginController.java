package com.example.demo.controllers;

import com.example.demo.Bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
public class LoginController {
    //获取数据库连接
    private static Connection getConn() {
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
    public void testConnectSQL(){
        Connection conn = getConn();//获取数据库连接
        String sql = "exec SQLCREATETABLE";
        try {
            CallableStatement cst=conn.prepareCall(sql);
            ResultSet rs = cst.executeQuery();
            cst.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @PostMapping("/index")
    public String login(@ModelAttribute UserBean us,WebRequest webRequest){

        Connection conn = getConn();//获取数据库连接
        String sql = "select type from account where account = '"+us.getAccount() +"' and password = '"+ us.getPassword()+"'";
        int type = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();//执行SQL语句，返回结果给ResultSet
            if(resultSet.next()){
                type = resultSet.getInt("type");
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(type == 0)
            return "login";
        if(type == 1)
            return "teacherindex";
        if(type ==2)
            return "studentindex";
        return "login";
    }

}
