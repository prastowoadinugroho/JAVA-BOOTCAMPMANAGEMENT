package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.UserRole;
import com.bootcampmanagement.server.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bootcamp-management/userRole")
public class UserRoleController implements EntityNameMessage{
    
    @Autowired
    UserRoleService userRoleService;
    
    @GetMapping("")
    public ResponseListData getAllUserRole() {
        return new ResponseListData(userRoleService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getUserRoleById(@PathVariable Integer id) {
        return new ResponseData(userRoleService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addUserRole(@RequestBody UserRole userRole) {
        return new ResponseMessage(userRoleService.create(userRole),getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateUserRole(@RequestBody UserRole userRole,@PathVariable Integer id) {
        userRole.setId(id);
        return new ResponseMessage(userRoleService.update(userRole, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteUserRole(@PathVariable Integer id) {
        return new ResponseMessage(userRoleService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "User Role";
    }
}
