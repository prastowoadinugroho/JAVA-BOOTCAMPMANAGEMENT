package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bootcamp-management")
public class HomeController {
    
    @Autowired
    DeveloperService service;
    
    @GetMapping("")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",service.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        model.addAttribute("dashboardItem","nav-item active");
        model.addAttribute("dashboardAdminItem","nav-item active");

        model.addAttribute("classPage","bootcamp-management/class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("jobPage","bootcamp-management/job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("sitePage","bootcamp-management/site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("skillPage","bootcamp-management/skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("userPage","bootcamp-management/user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("userRolePage","bootcamp-management/userRole");
        model.addAttribute("rolePage","role");
        
        model.addAttribute("customerPage","bootcamp-management/customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("employeePage","bootcamp-management/employee");
        model.addAttribute("employeeItem","nav-item");
        
        model.addAttribute("trainerPage","bootcamp-management/trainer");
        model.addAttribute("trainerItem","nav-item");
        
        model.addAttribute("viewRequestPage","/bootcamp-management/request");
        model.addAttribute("viewRequestItem","nav-item");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        model.addAttribute("informationPage","/bootcamp-management/developer/information");
        model.addAttribute("informationItem","nav-item");
        
        model.addAttribute("userSkillPage","/bootcamp-management/developer/skill");
        model.addAttribute("userSkillItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
               
        return "home";
        
    }
}
