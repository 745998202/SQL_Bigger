package com.example.demo.Bean;

public class RegisterBean {
    private String name;
    private String classname;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public RegisterBean(String name,String classname,String password){
        this.name = name;
        this.classname = classname;
        this.password = password;
    }
}
