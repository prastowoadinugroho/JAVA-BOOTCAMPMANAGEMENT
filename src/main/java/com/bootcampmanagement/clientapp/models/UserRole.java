package com.bootcampmanagement.clientapp.models;

public class UserRole {
    private Integer id;
    private Role role;
    private User user;

    public UserRole() {
    
    }

    public UserRole(Integer id, Role role, User user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
}
