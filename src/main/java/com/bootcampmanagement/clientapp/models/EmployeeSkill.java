package com.bootcampmanagement.clientapp.models;

public class EmployeeSkill {
    private Integer id;
    private String level;
    private Employee employee;
    private Skill skill;
    private String desc;

    public EmployeeSkill() {
    
    }

    public EmployeeSkill(Integer id, String level, Employee employee, Skill skill, String desc) {
        this.id = id;
        this.level = level;
        this.employee = employee;
        this.skill = skill;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    
}
