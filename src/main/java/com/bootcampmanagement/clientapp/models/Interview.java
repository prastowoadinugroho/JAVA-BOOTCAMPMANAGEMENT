package com.bootcampmanagement.clientapp.models;

import java.util.Date;

public class Interview {
    private Integer id;
    private String status;
    private Employee employee;
    private Request request;
    private Date startDate;

    public Interview() {
        
    }

    public Interview(Integer id, String status, Employee employee, Request request, Date startDate) {
        this.id = id;
        this.status = status;
        this.employee = employee;
        this.request = request;
        this.startDate = startDate;
    }

    public Interview(Employee employee, Request req) {
        this.request = req;
        this.employee = employee;
    }

    public Interview(Integer id, Date startDate) {
        this.id = id;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 
}
