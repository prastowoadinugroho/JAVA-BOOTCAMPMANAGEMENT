package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Request;
import com.bootcampmanagement.server.repositories.RequestRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService extends BasicCrudService<Request, Integer> {

    private RequestRepository requestRepo;

    public RequestService(RequestRepository requestRepo) {
        super(requestRepo);
        this.requestRepo = requestRepo;
    }
    
    public int updateStatAcc(Integer id) {
        return requestRepo.updateStatusAcc(id);
    }
    
    public List<Request> getStatusAccDone() {
         return requestRepo.getStatusAcceptedDone();
    }
    public List<Request> getStatusNull() {
         return requestRepo.getStatusNull();
    }
    public List<Request> getStatusRejected() {
         return requestRepo.getStatusRejected();
    }
        
    public Request updateStatNot(Request request) {
        String rejectNote = request.getRejectNote();
        Integer id = request.getId();
        requestRepo.updateStatusNot(rejectNote,id);
        return request;
    }
    
    public List<Request> getStatusNullOrRequest() {
         return requestRepo.getStatusNullOrRejected();
     }
    
    public List<Request> getStatusAcc() {
         return requestRepo.getStatusAccepted();
    }
    
    public int candidateSent(Integer id) {
        Request request = requestRepo.findById(id).get();

        Integer NumSent = request.getCandidateSent()+1;

        if (request.getCandidateSent() == request.getCandidateNeeded()-1) {
            request.setStatus("DONE");
            String doneStat = request.getStatus();
            requestRepo.updateStatus(NumSent, id);
            return requestRepo.updateStatusDone(doneStat, id);
        } else {
            return requestRepo.updateStatus(NumSent, id);
        }
    }
    
    //dashboard
    //total status null
    public int totalStatusNull() {
        return requestRepo.totalStatusNull();
    }
    //total status rejected
    public int totalStatusRejected() {
        return requestRepo.totalStatusRejected();
    }
    //total status undone
    public int totalStatusUndone() {
        return requestRepo.totalStatusUndone();
    }
    //total status done
    public int totalStatusDone() {
        return requestRepo.totalStatusDone();
    }
     //total request
    public int totalRequest() {
        return requestRepo.totalRequest();
    }
    
    public int totalStatusRejectedSkill() {
        return requestRepo.totalStatusRejectedSkill();
    }
    public int totalStatusRejectedSkill1() {
        return requestRepo.totalStatusRejectedSkill1();
    }
    public int totalStatusRejectedSkill2() {
        return requestRepo.totalStatusRejectedSkill2();
    }
    public int totalStatusRejectedSkill3() {
        return requestRepo.totalStatusRejectedSkill3();
    }
    public int totalStatusRejectedSkill4() {
        return requestRepo.totalStatusRejectedSkill4();
    }

    @Override
    public String dataNotFoundMessage() {
        return "Contract data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Contract data already exist";
    }
}