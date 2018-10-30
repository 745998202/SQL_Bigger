package com.example.demo.Bean;

public class WorkBean {
    private String workTitle;
    private String workDescribe;
    private String fileAddress;
    private String classno;
    public void setWorkTitle(String workTitle){
        this.workTitle = workTitle;
    }

    public String getWorkTitle(){
        return workTitle;
    }
    public void setWorkDescribe(String workDescribe){
        this.workDescribe= workDescribe;
    }

    public String getWorkDescribe() {
        return workDescribe;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }
    public String getClassno(){
        return classno;
    }

    public void setFileAddress(String fileAddress){
        this.fileAddress = fileAddress;
    }
    public String getFileAddress(){
        return fileAddress;
    }

}
