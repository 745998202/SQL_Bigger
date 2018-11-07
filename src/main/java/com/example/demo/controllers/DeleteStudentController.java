package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class DeleteStudentController {
    public String deletStudent(@RequestParam("studentno") String studentno){
        Connection conn = GetConnection.getConn();
        String sql = "{call deleteStudent(?)}";
        try {
            CallableStatement cst = conn.prepareCall(sql);
            cst.setString(1,studentno);
            cst.execute();
            cst.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return"redirect:/teacherControlStudents";
    }
}
