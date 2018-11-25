package com.example.demo.controllers;

import com.example.demo.Bean.TeacherControlStudentBean;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class teacherControlStudents {
    @RequestMapping("/teacherControlStudents")  //班级管理
    public String StudentList(HttpSession session, ModelMap map){
        List<TeacherControlStudentBean> tcsbs = new ArrayList<>();
        String teacherno = session.getAttribute("userNo").toString();
        try{
            Connection conn = GetConnection.getConn();
            String sql = "{call selectTeacherGrade(?,?)}";
            CallableStatement cst = conn.prepareCall(sql);
            cst.setString(1,teacherno);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();
            ResultSet rs = (ResultSet) cst.getObject(2);
            while(rs.next()){
                TeacherControlStudentBean tcsb = new TeacherControlStudentBean("","","","");
                tcsb.setClassno(rs.getString("classno"));
                tcsb.setClassname(rs.getString("classname"));
                tcsb.setStudentno(rs.getString("studentno"));
                tcsb.setStudentname(rs.getString("studentname"));
                tcsbs.add(tcsb);
            }
            if(tcsbs.size() == 0){
                TeacherControlStudentBean tcsb = new TeacherControlStudentBean("","","","");
                tcsb.setClassno("NULL");
                tcsb.setClassname("NULL");
                tcsb.setStudentno("NULL");
                tcsb.setStudentname("NULL");
                tcsbs.add(tcsb);
            }
            map.put("tcsbs",tcsbs);
            cst.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "teacherGrade";
    }
}
