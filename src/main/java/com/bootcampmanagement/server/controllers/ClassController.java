package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Classes;
import com.bootcampmanagement.server.services.ClassService;
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
@RequestMapping("bootcamp-management/class")
public class ClassController implements EntityNameMessage {
    
    @Autowired
    ClassService classService;
    
    @GetMapping("")
    public ResponseListData getAllClass(){
        return new ResponseListData(classService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getClassById(@PathVariable Integer id){
        return new ResponseData(classService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addClass(@RequestBody Classes classes){
        return new ResponseMessage(classService.create(classes), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateClass(@RequestBody Classes classes,@PathVariable("id") Integer id){
        classes.setId(id);
        return new ResponseMessage(classService.update(classes,id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteClass(@PathVariable("id") Integer id){
        return new ResponseMessage(classService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Class";
    }
    
}
