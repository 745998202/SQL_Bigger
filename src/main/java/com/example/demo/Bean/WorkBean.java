package com.example.demo.Bean;

public class WorkBean {
    private String workTitle;
    private String workDescribe;
    private String fileAddress;
    private String workBody;
    private String workType;
    private String teacherno;

    public void setWorkTitle(String workTitle){ this.workTitle = workTitle; }
    public String getWorkTitle(){
        return workTitle;
    }

    public void setWorkDescribe(String workDescribe){
        this.workDescribe= workDescribe;
    }
    public String getWorkDescribe() {
        return workDescribe;
    }

    public void setFileAddress(String fileAddress){ this.fileAddress = fileAddress; }
    public String getFileAddress(){ return fileAddress; }

    public void setWorkBody(String workBody) { this.workBody = workBody; }
    public String getWorkBody() { return workBody; }

    public void setWorkType(String workType) { this.workType = workType; }
    public String getWorkType(){return workType;}

    public void setTeacherno(String teacherno) { this.teacherno = teacherno; }
    public String getTeacherno() { return teacherno; }

    public WorkBean(String workTitle,String workDescribe,String workBody,String workType,String teacherno,String fileAddress){
        {
            this.workTitle = workTitle;
            this.workDescribe = workDescribe;
            this.workBody = workBody;
            this.workType = workType;
            this.teacherno = teacherno;
            this.fileAddress = fileAddress;
        }
    }
}
