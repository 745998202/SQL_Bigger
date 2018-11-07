package com.example.demo.Bean;

public class WorkListBean {
    private int workId;
    private String workTitle;
    private String workDescribe;
    private String workBody;
    private String workType;
    private String fileAddress;
    private String teacherNo;

    public void setWorkId(int workId){
        this.workId = workId;
    }
    public int getWorkId() {
        return workId;
    }
    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkDescribe(String workDescribe) {
        this.workDescribe = workDescribe;
    }

    public String getWorkDescribe() {
        return workDescribe;
    }

    public void setWorkBody(String workBody) {
        this.workBody = workBody;
    }

    public String getWorkBody() {
        return workBody;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getFileAddress() {
        return fileAddress;
    }
    public WorkListBean(int workId,String workTitle,String workDescribe,String workBody,String workType,String fileAddress,String teacherNo){
        this.workId = workId;
        this.workTitle = workTitle;
        this.workDescribe = workDescribe;
        this.workBody = workBody;
        this.workType = workType;
        this.fileAddress = fileAddress;
        this.teacherNo = teacherNo;
    }

}
