package com.bootcampmanagement.clientapp.models;

public class CandidateSkill {
    private String name;
    private String level;
    private String skill;
    private String description;
    private Integer id;
    private Integer emp;
    private Integer angka;

    public CandidateSkill() {
    
    }

    public CandidateSkill(String name, String level, String skill, String description, Integer id, Integer emp, Integer angka) {
        this.name = name;
        this.level = level;
        this.skill = skill;
        this.description = description;
        this.id = id;
        this.emp = emp;
        this.angka = angka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmp() {
        return emp;
    }

    public void setEmp(Integer emp) {
        this.emp = emp;
    }

    public Integer getAngka() {
        return angka;
    }

    public void setAngka(Integer angka) {
        this.angka = angka;
    }

   
}
