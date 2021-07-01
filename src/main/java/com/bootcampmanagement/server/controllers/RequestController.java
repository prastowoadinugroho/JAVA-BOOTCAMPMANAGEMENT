package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Request;
import com.bootcampmanagement.server.services.RequestService;
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
@RequestMapping("bootcamp-management/request")
public class RequestController implements EntityNameMessage {

    @Autowired
    RequestService requestService;

    @GetMapping("")
    public ResponseListData getAllRequest() {
        return new ResponseListData(requestService.findAll());
    }

    @GetMapping("{id}")
    public ResponseData getRequestById(@PathVariable Integer id) {
        return new ResponseData(requestService.findById(id));
    }

    @GetMapping("status/acceptedDone")
    public ResponseListData getStatusAcceptedDone() {
        return new ResponseListData(requestService.getStatusAccDone());
    }
    
    @GetMapping("status/null")
    public ResponseListData getStatusNull() {
        return new ResponseListData(requestService.getStatusNull());
    }
    
    @GetMapping("status/rejected")
    public ResponseListData getStatusReject() {
        return new ResponseListData(requestService.getStatusRejected());
    }
    
    @GetMapping("statAcc/{id}")
    public ResponseMessage getStatusY(@PathVariable Integer id) {
        requestService.updateStatAcc(id);
        return new ResponseMessage(requestService.findById(id), getEntityName() + " updated") ;
    }
    
    @PostMapping("statNo")
    public ResponseMessage getStatusN(@RequestBody Request request) {
        return new ResponseMessage(requestService.updateStatNot(request), getEntityName() + " updated") ;
    }
    
    @GetMapping("status")
    public ResponseListData getStatusRejected() {
        return new ResponseListData(requestService.getStatusNullOrRequest());
    }
    
    @GetMapping("status/accepted")
    public ResponseListData getStatusAccepted() {
        return new ResponseListData(requestService.getStatusAcc());
    }

    @PostMapping("")
    public ResponseMessage addRequest(@RequestBody Request request) {
        return new ResponseMessage(requestService.create(request), getEntityName() + " created");
    }

    @PutMapping("{id}")
    public ResponseMessage updateRequest(@RequestBody Request request, @PathVariable Integer id) {
        request.setId(id);
        return new ResponseMessage(requestService.update(request, id), getEntityName() + " updated");
    }

    @DeleteMapping("{id}")
    public ResponseMessage deleteRequest(@PathVariable Integer id) {
        return new ResponseMessage(requestService.delete(id), getEntityName() + " deleted");
    }
    
    @GetMapping("statDone/{id}")
    public ResponseMessage getStatusDone(@PathVariable Integer id) {
        requestService.candidateSent(id);        
        return new ResponseMessage(requestService.findById(id), getEntityName() + " updated") ;
    }
    
    //dashboard
    //total status null
    @GetMapping("totalStatusNull")
    public ResponseData totalStatusNull() {
        return new ResponseData(requestService.totalStatusNull());
    }
    //total status rejected
    @GetMapping("totalStatusRejected")
    public ResponseData totalStatusRejected() {
        return new ResponseData(requestService.totalStatusRejected());
    }
    //total status undone
    @GetMapping("totalStatusUndone")
    public ResponseData totalStatusUndone() {
        return new ResponseData(requestService.totalStatusUndone());
    }
    //total status done
    @GetMapping("totalStatusDone")
    public ResponseData totalStatusDone() {
        return new ResponseData(requestService.totalStatusDone());
    }
     //total request
    @GetMapping("totalRequest")
    public ResponseData totalRequest() {
        return new ResponseData(requestService.totalRequest());
    }
    
    @GetMapping("totalStatusRejectedSkill")
    public ResponseData totalStatusRejectedSkill() {
        return new ResponseData(requestService.totalStatusRejectedSkill());
    }
    @GetMapping("totalStatusRejectedSkill1")
    public ResponseData totalStatusRejectedSkill1() {
        return new ResponseData(requestService.totalStatusRejectedSkill1());
    }
    @GetMapping("totalStatusRejectedSkill2")
    public ResponseData totalStatusRejectedSkill2() {
        return new ResponseData(requestService.totalStatusRejectedSkill2());
    }
    @GetMapping("totalStatusRejectedSkill3")
    public ResponseData totalStatusRejectedSkill3() {
        return new ResponseData(requestService.totalStatusRejectedSkill3());
    }
    @GetMapping("totalStatusRejectedSkill4")
    public ResponseData totalStatusRejectedSkill4() {
        return new ResponseData(requestService.totalStatusRejectedSkill4());
    }

    @Override
    public String getEntityName() {
        return "Request";
    }

}
