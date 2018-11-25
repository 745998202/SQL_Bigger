package com.example.demo.controllers;

import com.example.demo.Bean.WorkListBean;
import com.example.demo.Bean.scoreBean;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {
    @RequestMapping("/ViewBegin")
    public String  ViewBegin(ModelMap map, HttpSession session){
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
        return "ViewBegin";
    }

    @RequestMapping("View")
    public String View(@RequestParam("Title") String Title,HttpSession session,ModelMap map){
        String userNo = session.getAttribute("userNo").toString();
        List<scoreBean> studentScoreList = new ArrayList<>();
        try{
            Connection conn =GetConnection.getConn();
            String sql = "{call SEARCHSCORE(?,?,?)}";
            CallableStatement csmt = conn.prepareCall(sql);
            csmt.setString(1,Title);
            csmt.setString(2,userNo);
            csmt.registerOutParameter(3, OracleTypes.CURSOR);
            csmt.executeUpdate();
            ResultSet res = (ResultSet)csmt.getObject(3);
            while(res.next()){
                String student = res.getString(1);
                int score = res.getInt(2);
                scoreBean bean = new scoreBean();
                bean.setNumber(score);
                bean.setStudent(student);
                studentScoreList.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        map.put("studentScoreList",studentScoreList);
        return "View";
    }
}
