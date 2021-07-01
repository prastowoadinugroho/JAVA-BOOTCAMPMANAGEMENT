package com.bootcampmanagement.clientapp.models;

public class Developer {
    private Integer id;
    private String username;
    private Employee employee;

    public Developer() {
        
    }

    public Developer(Integer id, String username, Employee employee) {
        this.id = id;
        this.username = username;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
}
