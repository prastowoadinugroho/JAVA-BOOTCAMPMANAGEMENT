package com.bootcampmanagement.clientapp.models;

public class Candidate {
    private Integer empId;
    private Integer req;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String classes;
    private String job;
    private String status;
    private String skill;
    private String sent;

    public Candidate() {
        
    }

    public Candidate(Integer empId, Integer req, String name, String email, String phone, String address, String classes, String job, String status, String skill, String sent) {
        this.empId = empId;
        this.req = req;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.classes = classes;
        this.job = job;
        this.status = status;
        this.skill = skill;
        this.sent = sent;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getReq() {
        return req;
    }

    public void setReq(Integer req) {
        this.req = req;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    
}
