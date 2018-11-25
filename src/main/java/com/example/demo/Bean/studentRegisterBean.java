package com.example.demo.Bean;

public class studentRegisterBean {
    String account;
    String password;
    String no;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount(){
        return this.account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public studentRegisterBean(String account, String password, String no){
        this.account = account;
        this.password = password;
        this.no = no;
    }
}
