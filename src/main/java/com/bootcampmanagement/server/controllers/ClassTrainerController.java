package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.ClassTrainer;
import com.bootcampmanagement.server.repositories.Trainer;
import com.bootcampmanagement.server.services.ClassTrainerService;
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
@RequestMapping("bootcamp-management/class-trainer")
public class ClassTrainerController implements EntityNameMessage {

    @Autowired
    ClassTrainerService classTrainerService;

    @GetMapping("")
    public ResponseListData getAllClassTrainer() {
        return new ResponseListData(classTrainerService.findAll());
    }
    
    @GetMapping("trainer/{angka}")
    public ResponseListData getAll(@PathVariable Integer angka) {
        return new ResponseListData(classTrainerService.getTrainer(angka));
    }

    @GetMapping("{id}")
    public ResponseData getClassTrainerById(@PathVariable Integer id) {
        return new ResponseData(classTrainerService.findById(id));
    }

    @PostMapping("")
    public ResponseMessage addClassTrainer(@RequestBody ClassTrainer classTrainer) {
        return new ResponseMessage(classTrainerService.create(classTrainer), getEntityName() + " created");
    }

    @PutMapping("{id}")
    public ResponseMessage updateClassTrainer(@RequestBody ClassTrainer classTrainer, @PathVariable Integer id) {
        classTrainer.setId(id);
        return new ResponseMessage(classTrainerService.update(classTrainer, id), getEntityName() + " updated");
    }

    @DeleteMapping("{id}")
    public ResponseMessage deleteClassTrainer(@PathVariable Integer id) {
        return new ResponseMessage(classTrainerService.delete(id), getEntityName() + " deleted");
    }

    @Override
    public String getEntityName() {
        return "Class Trainer";
    }

}
