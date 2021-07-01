package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.services.DashboardService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bootcamp-management/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class DashboardAdmin {
    
    @Autowired
    DashboardService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        model.addAttribute("dashboardAdminItem","nav-item active");

        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("jobPage","job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("sitePage","site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("skillPage","skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("userPage","user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("userRolePage","userRole");
        model.addAttribute("rolePage","role");
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("employeePage","employee");
        model.addAttribute("employeeItem","nav-item");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        model.addAttribute("viewRequestPage","/request");
        model.addAttribute("viewRequestItem","nav-item");
                   
        return "dashboard-admin";
    }
    
    @GetMapping("employee-all")
    @ResponseBody
    public ResponseData getEmployeeAll(){
        return new ResponseData(service.employeeAll().getData());
    }
    
    @GetMapping("user-all")
    @ResponseBody
    public ResponseData getUserAll(){
        return new ResponseData(service.userAll().getData());
    }
    
    @GetMapping("class-all")
    @ResponseBody
    public ResponseData getClassAll(){
        return new ResponseData(service.classAll().getData());
    }
    
    @GetMapping("site-all")
    @ResponseBody
    public ResponseData getEmployeeAvail(){
        return new ResponseData(service.siteAll().getData());
    }
    
    @GetMapping("employee-dev")
    @ResponseBody
    public ResponseData getEmployeeDev(){
        return new ResponseData(service.empDevAll().getData());
    }
    
    @GetMapping("employee-trainer")
    @ResponseBody
    public ResponseData getEmployeeTrainer(){
        return new ResponseData(service.empTrainAll().getData());
    }
}