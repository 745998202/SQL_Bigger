package com.example.demo.controllers;

import com.example.demo.Bean.scoreBean;
import com.example.demo.Bean.studentScoreBean;
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
public class myScoreController {
    @RequestMapping("myScore")
    public String myScore(HttpSession session, ModelMap map){
        String userNo = session.getAttribute("userNo").toString();
        List<studentScoreBean> studentScoreList = new ArrayList<>();
        try{
            Connection conn =GetConnection.getConn();
            String sql = "{call SEARCHSTUDENTSCORE(?,?)}";
            CallableStatement csmt = conn.prepareCall(sql);
            csmt.setString(1,userNo);
            csmt.registerOutParameter(2, OracleTypes.CURSOR);
            csmt.executeUpdate();
            ResultSet res = (ResultSet)csmt.getObject(2);
            while(res.next()){
                String work = res.getString(1);
                int score = res.getInt(2);
                studentScoreBean bean = new studentScoreBean();
                bean.setNumber(score);
                bean.setTitle(work);
                studentScoreList.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        map.put("studentScoreList",studentScoreList);
        return "myScore";
    }
}
