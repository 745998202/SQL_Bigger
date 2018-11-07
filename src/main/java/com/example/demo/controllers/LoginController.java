package com.example.demo.controllers;

import com.example.demo.Bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.*;

@Controller
public class LoginController {
    //获取数据库连接

    @PostMapping("/index")
    public String login(@ModelAttribute UserBean us, WebRequest webRequest, HttpSession session){
        Connection conn = GetConnection.getConn();//获取数据库连接
        String sql = "select type from account where account = '"+us.getAccount() +"' and password = '"+ us.getPassword()+"'";
        int type = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();//执行SQL语句，返回结果给ResultSet
            if(resultSet.next()){
                type = resultSet.getInt("type");
                session.setAttribute("userType",type);
            }
            if(type == 1){//老师
                sql = "select teacherno from account where account = '"+us.getAccount() +"' and password = '"+ us.getPassword()+"'";
                pre = conn.prepareStatement(sql);
                resultSet = pre.executeQuery();
                String userNo;
                if(resultSet.next()){
                    userNo = resultSet.getString("teacherno");
                    session.setAttribute("userNo",userNo);
                }
            }
            if(type == 2){
                sql = "select studentno from account where account = '"+us.getAccount() +"' and password = '"+ us.getPassword()+"'";
                pre = conn.prepareStatement(sql);
                resultSet = pre.executeQuery();
                String userNo;
                if(resultSet.next()){
                    userNo = resultSet.getString("studentno");
                    session.setAttribute("userNo",userNo);
                }
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
