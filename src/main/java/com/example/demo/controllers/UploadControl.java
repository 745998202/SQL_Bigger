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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                sql = "select STUDENTNO from STUDENT , TEACHER where student.CLASSNO = TEACHER.CLASSNO and TEACHER.teacherno = '"+userNo+"'";
                PreparedStatement pre = conn.prepareStatement(sql);
                ResultSet res = pre.executeQuery();
               ArrayList student_list = new ArrayList<String>();
                while(res.next()){
                    String new_student_no = res.getString("STUDENTNO");
                    student_list.add(new_student_no);
                }

                for(int i = 0 ;i <student_list.size();i++){//循环将任务插入任务得分列表
                    sql = "{call insertworkscore(?,?,?,?,?)}";
                    cst = conn.prepareCall(sql);
                    cst.setString(1,(String)student_list.get(i));
                    cst.setString(2,userNo);
                    cst.setInt(3,0);
                    cst.setString(4,work.getWorkTitle());
                    cst.setInt(5,0);//初始为未提交状态
                    cst.execute();
                    cst.close();
                }
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
