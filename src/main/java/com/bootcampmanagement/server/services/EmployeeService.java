package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.repositories.EmployeeRepository;
import com.bootcampmanagement.server.repositories.JobTrainer;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bootcampmanagement.server.repositories.Trainer;

@Service
public class EmployeeService extends BasicCrudService<Employee, Integer>{
    
    EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        super(employeeRepo);
        this.employeeRepo = employeeRepo;
    }
    
    public List<JobTrainer> getJobTrainer() {
        return employeeRepo.getJobTrainer();
    }
    
     //dashboard RM
    public int totalEmpAvailable() {
        return employeeRepo.totalEmpAvai();
    }
    
    public int totalEmpAvailableBySkill() {
        return employeeRepo.totalEmpAvaiBySkill();
    }
    
    public int totalEmpAvailableBySkill1() {
        return employeeRepo.totalEmpAvaiBySkill1();
    }
    
    public int totalEmpAvailableBySkill2() {
        return employeeRepo.totalEmpAvaiBySkill2();
    }
    public int totalEmpAvailableBySkill3() {
        return employeeRepo.totalEmpAvaiBySkill3();
    }
    public int totalEmpAvailableBySkill4() {
        return employeeRepo.totalEmpAvaiBySkill4();
    }
    //end of dashboard
    
    public int totalEmpNotAvailable() {
        return employeeRepo.totalEmpNotAvai();
    }

    public int totalEmpOnSite() {
        return employeeRepo.totalEmpOnSite();
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "Employee data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Employee data already exist";
    }
}