package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.services.DashboardAdminService;
import com.bootcampmanagement.server.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bootcamp-management/dashboardAdmin")
public class DashboardAdminController {

    @Autowired
    DashboardAdminService dashAdmService;

    @GetMapping("totalEmp")
    public ResponseData getTotalEmp() {
        return new ResponseData(dashAdmService.totalEmp());
    }

    @GetMapping("totalClass")
    public ResponseData getTotalClass() {
        return new ResponseData(dashAdmService.totalClass());
    }

    @GetMapping("totalUser")
    public ResponseData getTotalUser() {
        return new ResponseData(dashAdmService.totalUser());
    }
    
    @GetMapping("totalSite")
    public ResponseData getTotalSite() {
        return new ResponseData(dashAdmService.totalSite());
    }
    
    @GetMapping("totalEmpDev")
    public ResponseData getTotalEmpDeveloper() {
        return new ResponseData(dashAdmService.totalEmpDeveloper());
    }
    @GetMapping("totalEmpTrainer")
    public ResponseData getTotalEmpTrainer() {
        return new ResponseData(dashAdmService.totalEmpTrainer());
    }

}