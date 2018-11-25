package com.example.demo.controllers;

import com.example.demo.Bean.RegisterBean;
import com.example.demo.Bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RegisterController {
    @RequestMapping("/register")
    public String reRegister(ModelMap map){
        RegisterBean regBean = new RegisterBean("","","");
        map.put("registerBean",regBean);
        return "register";
    }

    @RequestMapping("/registerPost")
    public String registerPost(RegisterBean regBean,ModelMap map){
        String name = regBean.getName();
        String classname = regBean.getClassname();
        String password = regBean.getPassword();
        System.out.println(name);
        System.out.println(classname);
        System.out.println(password);
        UserBean us = new UserBean("0","0");
        map.put("us",us);
        return "login";
    }
}
