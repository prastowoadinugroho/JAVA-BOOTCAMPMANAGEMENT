package com.bootcampmanagement.clientapp.models;

import java.util.Date;


public class Request {
    private Integer id;
    private Date interviewDate;
    private Integer candidateNeeded;
    private String status;
    private Date statusDate;
    private Customer customer;
    private Skill skill;
    private String rejectNote;

    public Request() {
        
    }

    public Request(Integer id, Date interviewDate, Integer candidateNeeded, String status, Date statusDate, Customer customer, Skill skill, String rejectNote) {
        this.id = id;
        this.interviewDate = interviewDate;
        this.candidateNeeded = candidateNeeded;
        this.status = status;
        this.statusDate = statusDate;
        this.customer = customer;
        this.skill = skill;
        this.rejectNote = rejectNote;
    }

    public Request(Date interviewDate, Integer candidateNeeded, String status, Customer customer, Skill skill) {
        this.interviewDate = interviewDate;
        this.candidateNeeded = candidateNeeded;
        this.customer = customer;
        this.skill = skill;
    }

    public Request(Integer id, String rejectNote) {
        this.id = id;
        this.rejectNote = rejectNote;
    }

    public Request(Integer id, Date interviewDate, Integer candidateNeeded, String status, Customer customer, Skill skill) {
        this.id = id;
        this.interviewDate = interviewDate;
        this.candidateNeeded = candidateNeeded;
        this.customer = customer;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Integer getCandidateNeeded() {
        return candidateNeeded;
    }

    public void setCandidateNeeded(Integer candidateNeeded) {
        this.candidateNeeded = candidateNeeded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    public String getRejectNote() {
        return rejectNote;
    }

    public void setRejectNote(String rejectNote) {
        this.rejectNote = rejectNote;
    }
  
}
