package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
//从任何页面跳转到主页面
@Controller
public class MainController {
    @RequestMapping("/main")
    public String goMain(HttpSession session){
        String userNo = session.getAttribute("userNo").toString();
        int type = Integer.parseInt(session.getAttribute("userType").toString());
        if(type == 1){
            return "teacherindex";
        }else{
            return "studentindex";
        }
    }
}
