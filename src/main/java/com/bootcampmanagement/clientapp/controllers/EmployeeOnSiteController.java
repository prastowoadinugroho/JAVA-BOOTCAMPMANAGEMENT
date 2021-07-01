package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.models.Interview;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.InterviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bootcamp-management/employee-site")
@PreAuthorize("hasAnyRole('ROLE_RM')")
public class EmployeeOnSiteController {
    @Autowired
    InterviewService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        model.addAttribute("employeeOnSiteItem","nav-item active");
        
        model.addAttribute("viewRequestItem","nav-item");
        model.addAttribute("viewRequestPage","/bootcamp-management/request");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        return "employee-onSite";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Interview> listEmployee(){
        List<Interview> interview = service.employeeQualified().getData();
        return interview;
    }
    
    @GetMapping("list/{id}")
    @ResponseBody
    public Interview listEmployee(@PathVariable Integer id){
        Interview inter = service.employeeQualifiedById(id).getData();
        return inter;
    }
    
}
