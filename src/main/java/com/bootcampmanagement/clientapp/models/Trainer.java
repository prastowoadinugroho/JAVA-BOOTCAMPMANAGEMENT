package com.bootcampmanagement.clientapp.models;

public class Trainer {
    private Integer id;
    private Classes classes;
    private Employee employee;

    public Trainer() {
        
    }

    public Trainer(Integer id, Classes classes, Employee employee) {
        this.id = id;
        this.classes = classes;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
