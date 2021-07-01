package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Role;
import com.bootcampmanagement.server.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BasicCrudService<Role, Integer>{
    
    private RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        super(roleRepo);
        this.roleRepo = roleRepo;
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "Role data not found";
    }
    
    @Override
    public String dataAlreadyExistMessage() {
        return "Role data already exist";
    }
    
}