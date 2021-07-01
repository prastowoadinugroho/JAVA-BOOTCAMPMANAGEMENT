package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.EmpSkill;
import com.bootcampmanagement.server.repositories.Candidate;
import com.bootcampmanagement.server.repositories.EmployeeSkillRepository;
import com.bootcampmanagement.server.repositories.Sent;
import com.bootcampmanagement.server.repositories.SkillSet;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillService extends BasicCrudService<EmpSkill, Integer>{
    
    private EmployeeSkillRepository empSkillRepo;

    public EmployeeSkillService(EmployeeSkillRepository empSkillRepo) {
        super(empSkillRepo);
        this.empSkillRepo = empSkillRepo;
    }
    
    public List<Candidate> getTrainer(String skill,Integer req) {
        return empSkillRepo.getAllCandidate(skill,req);
    }
    
    public List<Candidate> getTrainerById(String skill,Integer req,Integer emp) {
        return empSkillRepo.getAllCandidateId(skill,req,emp);
    }
    
    public List<SkillSet> getSkill(Integer id) {
        return empSkillRepo.getAllSkill(id);
    }
    
    public List<SkillSet> getSkillByName(String name) {
        return empSkillRepo.getSkillByName(name);
    }
    
//    public SkillSet getSkillName(String name) {
//        return empSkillRepo.getSkillName(name);
//    }
    
    public List<Sent> getSent(Integer angka) {
        return empSkillRepo.getSent(angka);
    }
        
    @Override
    public String dataNotFoundMessage() {
        return "Employee Skill data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "employee Skill data already exist";
    }
}