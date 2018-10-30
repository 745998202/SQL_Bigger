package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UploadControl {
    @RequestMapping("/upload")
    public String reupload(Model model){

        return "uploadfile";
    }

    @RequestMapping(value="/upfile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFile(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) throws Exception{
        System.out.println();
        if(!file.isEmpty()){
            String path = "D://upload/";
            System.out.println("path ="+path);
            String fileName = file.getOriginalFilename();
            System.out.println("fileName-->" + fileName);
            File filepath = new File(path,fileName);
            if(!filepath.getParentFile().exists()){
                filepath.mkdirs();
            }
            file.transferTo(new File(path+File.separator+fileName));
            return "file success";
        }
        else{
            return "file fails";
        }

    }
}
