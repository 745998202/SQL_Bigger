package com.example.demo.Bean;

public class studentPiyueBean {
    private String studentno;
    private String fileaddress;

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setFileaddress(String fileaddress) {
        this.fileaddress = fileaddress;
    }

    public String getFileaddress() {
        return fileaddress;
    }

    public studentPiyueBean(String studentno,String fileaddress){
        this.studentno = studentno;
        this.fileaddress = fileaddress;
    }
}
