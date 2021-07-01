package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.repositories.ClassRepository;
import com.bootcampmanagement.server.repositories.EmployeeRepository;
import com.bootcampmanagement.server.repositories.SiteRepository;
import com.bootcampmanagement.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardAdminService {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    ClassRepository classRepo;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    SiteRepository siteRepo;
    
    public int totalEmp() {
        return empRepo.totalEmp();
    }

    public int totalClass() {
        return classRepo.totalClass();
    }

    public int totalUser() {
        return userRepo.totalUser();
    }
    
    public int totalSite() {
        return siteRepo.totalSite();
    }
    
    public int totalEmpDeveloper() {
        return empRepo.totalEmpDeveloper();
    }

    public int totalEmpTrainer() {
        return empRepo.totalEmpTrainer();
    }
    
}
