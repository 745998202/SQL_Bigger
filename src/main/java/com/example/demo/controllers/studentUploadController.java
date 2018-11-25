package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class studentUploadController {
    @RequestMapping("/getStudentUpload")
    public String getStudentUpload(ModelMap map, HttpSession session){
        String userNo = session.getAttribute("userNo").toString();
        String sql = "SELECT WORKTITLE FROM WORKSCORE WHERE STATE = 0 AND STUDENTNO = '"+userNo+"'";
        ArrayList workList = new ArrayList<String>();
        try {
            Connection conn = GetConnection.getConn();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet res = pre.executeQuery();
            while(res.next()){
                String worktitle = res.getString(1);
                workList.add(worktitle);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        String workTitle = "";
        map.put("workTitle",workTitle);
        map.put("workList",workList);
        return "studentUploadWork";
    }
    @RequestMapping("/studentUpload")
    public String studentUpload(@RequestParam("workTitle") String workTitle, @RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
        //这个函数实现的功能：
        //1. 将文件存入到指定路径中
        //2. 将文件路径存储到对应的workTitle中
        //3. 将workTitle的状态由0更改为1，即设置为已经提交的状态
        String userNo = session.getAttribute("userNo").toString();
        if (!file.isEmpty()) {
            String path = "D://upload/";
            String fileName = file.getOriginalFilename();
            String fileAddress = fileName;
            File filepath = new File(path, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.mkdirs();
            }
            file.transferTo(new File(path + File.separator + fileName));
            try {
                Connection conn = GetConnection.getConn();
                String sql = "UPDATE WORKSCORE SET fileAddress = '" + fileName + "' WHERE WORKTITLE = '" + workTitle + "' AND STUDENTNO = '" + userNo + "'";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.execute();
                String sql2 = "UPDATE WORKSCORE SET STATE = 1 WHERE WORKTITLE = '" + workTitle + "' AND STUDENTNO = '" + userNo + "'";
                pre = conn.prepareStatement(sql2);
                pre.execute();
                pre.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "studentUploadSuccess";
        } else {
            return "uploadFailed";
        }
    }
}
