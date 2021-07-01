package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Interview;
import com.bootcampmanagement.server.repositories.InterviewRepository;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InterviewService extends BasicCrudService<Interview, Integer>{
    
    private InterviewRepository interviewRepo;

    public InterviewService(InterviewRepository interviewRepo) {
        super(interviewRepo);
        this.interviewRepo = interviewRepo;
    }
    
    public Interview updateStatY(Interview interview) {
        Date startDate = interview.getStartDate();
        Integer id = interview.getId();
        interviewRepo.updateStatusYes(id,startDate);
        return interview;
    }
    public int updateEmployeeSite(Integer id) {
         return interviewRepo.updateStatEmpSite(id);
    }
     
    public int updateStatN(Integer id) {
         return interviewRepo.updateStatusNo(id);
    }
          
    public int updateStatEmpAvailable(Integer id) {
         return interviewRepo.updateStatEmpAvailable(id);
    }
    
    public int updateStatEmpNo(Integer id) {
         return interviewRepo.updateStatEmpNot(id);
    }
    
    public List<Interview> getStatusInterviewNull() {
        return interviewRepo.getStatusInterviewNull();
    }
    
    public List<Interview> getEmpQualified() {
        return interviewRepo.getStatusInterviewQualified();
    }
    
    public List<Interview> getInterviewByEmpId(String name) {
        return interviewRepo.getInterviewByEmpId(name);
    }
     
    public Interview getInterviewByEmpIdLatest(String name) {
        return interviewRepo.getInterviewByEmpIdLatest(name);
    }
    
    public Interview getInterviewEmpQualified(Integer id) {
        return interviewRepo.getInterviewByIdQual(id);
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "Interview data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Interview data already exist";
    }
}