package com.example.demo.controllers;

import com.example.demo.Bean.WorkBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class UploadControl {
    @RequestMapping("/upload")
    public String reupload(ModelMap map){
        WorkBean work = new WorkBean("","","","","","");
        map.put("work",work);
        return "uploadfile";
    }

    @RequestMapping(value="/upfile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                      @ModelAttribute WorkBean work,
                      HttpServletRequest request,
                      HttpSession session) throws Exception{
        System.out.println("任务主题： = "+ work.getWorkTitle());
        System.out.println("任务描述： = "+ work.getWorkDescribe());
        System.out.println("任务主体： = "+ work.getWorkBody());
        System.out.println("任务类型: = "+ work.getWorkType());
        if(!file.isEmpty()){
            String path = "D://upload/";
            String fileName = file.getOriginalFilename();
            String fileAddress = fileName;
            File filepath = new File(path,fileName);
            if(!filepath.getParentFile().exists()){
                filepath.mkdirs();
            }
            file.transferTo(new File(path+File.separator+fileName));
            String userNo = session.getAttribute("userNo").toString();
            try {
                Connection conn = GetConnection.getConn();
                String sql = "{call insertWork(?,?,?,?,?,?)}";
                CallableStatement cst = conn.prepareCall(sql);
                cst.setString(1, work.getWorkTitle());
                cst.setString(2, work.getWorkDescribe());
                cst.setString(3, work.getWorkBody());
                cst.setString(4, work.getWorkType());
                cst.setString(5, fileAddress);
                cst.setString(6, userNo);
                cst.execute();
                cst.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return "uploadSuccess";
        }
        else{
            return "uploadFailed";
        }

    }
}
