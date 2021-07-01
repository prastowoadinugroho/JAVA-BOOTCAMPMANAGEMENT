package com.bootcampmanagement.clientapp.models;

public class Customer {
    private Integer id;
    private String name;
    private String position;
    private Site site;

    public Customer() {
        
    }

    public Customer(Integer id, String name, String position, Site site) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.site = site;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
    
}
