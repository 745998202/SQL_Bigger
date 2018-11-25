package com.example.demo.controllers;

import com.example.demo.Bean.studentRegisterBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class studentRegisterController {
    @RequestMapping("/goStudentRegister")
    public String goStudentRegister(ModelMap map){
        int studentNum = 0;
        int[] registStudentNums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        map.put("studentNum",studentNum);
        map.put("registStudentNums",registStudentNums);
        return "studentRegister"   ;
    }

    @RequestMapping("/studentRegister")
    public String studentRegister(@RequestParam("studentNum")int  studentNum, ModelMap map, HttpSession session){
        List<studentRegisterBean> studentList = new ArrayList<>();
        //首先获取批量注册的老师ID
        String teacherNo = session.getAttribute("userNo").toString();
        //查询老师所在的班级号
        try{
            Connection conn = GetConnection.getConn();
            String sql = "SELECT CLASSNO FROM TEACHER WHERE TEACHERNO = '"+teacherNo+"'";//创建SQL语句
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            String studentClassNo = "";
            if(res.next()) {
                studentClassNo = res.getString("CLASSNO");
            }
            pre.close();
            //查询班级总人数
            sql = "SELECT STUDENTNUM FROM GRADE WHERE TEACHERNO = '"+teacherNo+"'";
            pre =  conn.prepareStatement(sql);
            res = pre.executeQuery();
            int havedStudentNum = 0;
            if(res.next()) {
                havedStudentNum = res.getInt("STUDENTNUM");
            }
            for (int i = havedStudentNum+1;i<=havedStudentNum+studentNum;i++){
                String newStudentNo = teacherNo +"_"+String.valueOf(i);
                String studentAccount = teacherNo +"_"+String.valueOf(i);
                String studentPassword = String.valueOf((int)(1+Math.random()*1000));//密码随机生成
                studentRegisterBean newStudent = new studentRegisterBean(studentAccount,studentPassword,newStudentNo);
                String insertSql = "{call REGISTENEWSTUDENT(?,?)}";
                CallableStatement cs = conn.prepareCall(insertSql);
                cs.setString(1,newStudentNo);
                cs.setString(2,studentClassNo);
                cs.execute();
                insertSql = "{call REGISTENEWSTUDENTACCOUNT(?,?,?)}";
                cs = conn.prepareCall(insertSql);
                cs.setString(1,studentAccount);
                cs.setString(2,studentPassword);
                cs.setString(3,newStudentNo);
                cs.execute();
                studentList.add(newStudent);
            }
            String insertSql = "{call REGISTESTUDENTNUM(?,?)}";
            CallableStatement cs = conn.prepareCall(insertSql);
            cs.setInt(1,studentNum);
            cs.setString(2,studentClassNo);
            cs.execute();
            cs.close();
            pre.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        map.put("studentList",studentList);
        return "studentRegisteResult";
    }
}
