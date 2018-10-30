package com.example.demo.controllers;

import com.example.demo.Bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String login(ModelMap map){
        UserBean us = new UserBean("0","0");
        map.put("us",us);
        return "login";
    }


}
