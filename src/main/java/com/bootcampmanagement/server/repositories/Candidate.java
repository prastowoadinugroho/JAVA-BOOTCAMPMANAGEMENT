package com.bootcampmanagement.server.repositories;

public interface Candidate {
    Integer getEmpId();   
    String getName();   
    String getAddress();   
    String getClasses();   
    String getJob();
    String getStatus();
    String getSkill();
    Integer getReq();
    String getEmail();
    String getPhone();
    String getSent();
}