package com.example.demo.Bean;

public class UserBean {
    private String account;
    private String password;

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount(){
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public UserBean(String account, String password){
        this.account = account;
        this.password = password;
    }
}
