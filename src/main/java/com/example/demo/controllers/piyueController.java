package com.example.demo.controllers;

import com.example.demo.Bean.WorkListBean;
import com.example.demo.Bean.studentPiyueBean;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class piyueController {
    @RequestMapping("/piyueBegin")
    public String piyueBegin(HttpSession session , ModelMap map){
        List<WorkListBean> workList = new ArrayList<>();//任务列表
        String teacherNo = session.getAttribute("userNo").toString();
        try {
            Connection conn = GetConnection.getConn();
            String sql = "{call getWorkList(?,?)}";
            CallableStatement csmt= conn.prepareCall(sql);
            csmt.setString(1,teacherNo);
            csmt.registerOutParameter(2, OracleTypes.CURSOR);
            csmt.executeUpdate();
            ResultSet rs = (ResultSet) csmt.getObject(2);
            int workId = 0;
            while(rs.next()) {
                WorkListBean work = new WorkListBean(0,"","","","","","");
                work.setWorkId(++workId);
                work.setWorkTitle(rs.getString("workTitle"));
                work.setWorkDescribe(rs.getString("workDescribe"));
                work.setWorkBody(rs.getString("workBody"));
                work.setWorkType(rs.getString("workType"));
                work.setFileAddress(rs.getString("fileAddress"));
                work.setTeacherNo(rs.getString("teacherno"));
                workList.add(work);
            }
            if(workList.size()==0){
                WorkListBean work = new WorkListBean(0,"","","","","","");
                work.setWorkId(++workId);
                work.setWorkTitle("NULL");
                work.setWorkDescribe("NULL");
                work.setWorkBody("NULL");
                work.setWorkType("NULL");
                work.setFileAddress("NULL");
                work.setTeacherNo("NULL");
                workList.add(work);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        map.put("workList",workList);
        return "piyueBegin";
    }
    @RequestMapping("/piyue")
    public String piyue(@RequestParam("Title") String Title,HttpSession session,ModelMap map){
        List<studentPiyueBean> piyueList = new ArrayList<>();
        session.setAttribute("Title",Title);
        String userNo = session.getAttribute("userNo").toString();
        try{
            Connection conn = GetConnection.getConn();
            String sql = "SELECT STUDENTNO , FILEADDRESS FROM WORKSCORE WHERE WORKTITLE = '"+Title+"' AND TEACHERNO = '"+userNo+"' AND STATE = 1";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            while(res.next()){
                String student = res.getString("STUDENTNO");
                String address = res.getString("FILEADDRESS");
                studentPiyueBean piyue = new studentPiyueBean(student,address);
                piyueList.add(piyue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        map.put("piyueList",piyueList);
        return "piyue";
    }
    @RequestMapping("/piyueTijiao")
    public String piyueTijiao(@RequestParam("studentno") String studentno,@RequestParam("num") int num,HttpSession session,ModelMap map){
        String userNo = session.getAttribute("userNo").toString();
        String Title = session.getAttribute("Title").toString();
        List<studentPiyueBean> piyueList = new ArrayList<>();
        try{
            Connection conn = GetConnection.getConn();
            String sql = "UPDATE WORKSCORE SET SCORE = "+String.valueOf(num)+"WHERE STUDENTNO = '"+studentno+"' AND WORKTITLE = '"+Title+"'";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.execute();
            sql = "UPDATE WORKSCORE SET STATE = 2 WHERE STUDENTNO = '"+studentno+"' AND WORKTITLE = '"+Title+"'";
            pre = conn.prepareStatement(sql);
            pre.execute();
            sql = "SELECT STUDENTNO , FILEADDRESS FROM WORKSCORE WHERE WORKTITLE = '"+Title+"' AND TEACHERNO = '"+userNo+"' AND STATE = 1";
            pre = conn.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            while(res.next()){
                String student = res.getString("STUDENTNO");
                String address = res.getString("FILEADDRESS");
                studentPiyueBean piyue = new studentPiyueBean(student,address);
                piyueList.add(piyue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        map.put("piyueList",piyueList);
        return "piyue";
    }
}
