package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.User;
import com.bootcampmanagement.server.services.UserService;
import javax.mail.MessagingException;
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
@RequestMapping("bootcamp-management/user")
public class UserController implements EntityNameMessage {
    
    @Autowired
    UserService userService;
    
    @GetMapping("")
    public ResponseListData getAll(){
        return new ResponseListData(userService.getAll());
    }
    
    @GetMapping("{username}")
    public ResponseData getById(@PathVariable("username") String username){
        return new ResponseData(userService.findByUserName(username));
    }
    
    @PostMapping("")
    public ResponseMessage addUser(@RequestBody User user) throws MessagingException{
        return new ResponseMessage(userService.addUser(user), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage editUsername(@RequestBody User user,@PathVariable Integer id){
        user.setId(id);
        return new ResponseMessage(userService.editUsername(user,id), getEntityName() + "name updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteUser(@PathVariable("id") Integer id){
        return new ResponseMessage(userService.deleteUser(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "User";
    }
    
}
