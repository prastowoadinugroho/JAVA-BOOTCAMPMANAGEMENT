package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Job;
import com.bootcampmanagement.server.services.JobService;
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
@RequestMapping("bootcamp-management/job")
public class JobController implements EntityNameMessage{
    
    @Autowired
    JobService jobService;
    
    @GetMapping("")
    public ResponseListData getAllJob() {
        return new ResponseListData(jobService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData GetJobById(@PathVariable Integer id) {
        return new ResponseData(jobService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addJob(@RequestBody Job job) {
        return new ResponseMessage(jobService.create(job), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateJob(@RequestBody Job job, @PathVariable Integer id) {
        job.setId(id);
        return new ResponseMessage(jobService.update(job, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteJob(@PathVariable Integer id) {
        return new ResponseMessage(jobService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Job";
    }
    
}
