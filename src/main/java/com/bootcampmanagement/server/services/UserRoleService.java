package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.UserRole;
import com.bootcampmanagement.server.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends BasicCrudService<UserRole, Integer>{
    
    private UserRoleRepository userRoleRepo;

    public UserRoleService(UserRoleRepository userRoleRepo) {
        super(userRoleRepo);
        this.userRoleRepo = userRoleRepo;
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "User Role data not found";
    }
    
    @Override
    public String dataAlreadyExistMessage() {
        return "User Role data already exist";
    }
    
}