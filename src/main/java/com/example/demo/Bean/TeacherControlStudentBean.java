package com.example.demo.Bean;

public class TeacherControlStudentBean {
    private String classno;
    private String classname;
    private String studentno;
    private String studentname;
    public void setClassno(String classno){
        this.classno = classno;
    }
    public String getClassno(){
        return classno;
    }
    public void setClassname(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    public String getStudentno(){
        return studentno;
    }
    public void setStudentname(String studentname){
        this.studentname = studentname;
    }
    public String getStudentname(){
        return studentname;
    }
    public TeacherControlStudentBean(String classno,String classname,String studentno,String studentname){
        this.classno = classno;
        this.classname = classname;
        this.studentno =studentno;
        this.studentname = studentname;
    }
}
