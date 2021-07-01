package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Role;
import com.bootcampmanagement.server.services.RoleService;
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
@RequestMapping("bootcamp-management/role")
public class RoleController implements EntityNameMessage{
    
    @Autowired
    RoleService roleService;
    
    @GetMapping("")
    public ResponseListData getAllRole() {
        return new ResponseListData(roleService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getRoleById(@PathVariable Integer id) {
        return new ResponseData(roleService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addRole(@RequestBody Role role) {
        return new ResponseMessage(roleService.create(role),getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateRole(@RequestBody Role role,@PathVariable Integer id) {
        role.setId(id);
        return new ResponseMessage(roleService.update(role, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteRole(@PathVariable Integer id) {
        return new ResponseMessage(roleService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Role";
    }
}
