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
@RequestMapping("bootcamp-management/rm")
@PreAuthorize("hasAnyRole('ROLE_RM')")
public class DashboardController {
    
    @Autowired
    DashboardService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        model.addAttribute("dashboardItem","nav-item active");
        
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
               
        return "dashboard";
    }
    
    @GetMapping("employee-avail")
    @ResponseBody
    public ResponseData getEmployeeAvail(){
        return new ResponseData(service.employeeAvail().getData());
    }
    
    @GetMapping("employee-notavail")
    @ResponseBody
    public ResponseData getEmployeeNotAvail(){
        return new ResponseData(service.employeeNotAvail().getData());
    }
    @GetMapping("employee-onsite")
    @ResponseBody
    public ResponseData getEmployeeOnSite(){
        return new ResponseData(service.employeeOnSite().getData());
    }
    
    @GetMapping("employee-java")
    @ResponseBody
    public ResponseData getEmployeeJava(){
        return new ResponseData(service.employeeSkillJava().getData());
    }
    
    @GetMapping("employee-python")
    @ResponseBody
    public ResponseData getEmployeePython(){
        return new ResponseData(service.employeeSkillPython().getData());
    }
    
    @GetMapping("employee-php")
    @ResponseBody
    public ResponseData getEmployeePhp(){
        return new ResponseData(service.employeeSkillPhp().getData());
    }
    
    @GetMapping("employee-react")
    @ResponseBody
    public ResponseData getEmployeeReact(){
        return new ResponseData(service.employeeSkillReact().getData());
    }
    
    @GetMapping("employee-net")
    @ResponseBody
    public ResponseData getEmployeeNet(){
        return new ResponseData(service.employeeSkillNet().getData());
    }
    
    @GetMapping("request-all")
    @ResponseBody
    public ResponseData getRequest(){
        return new ResponseData(service.allRequest().getData());
    }
    
    @GetMapping("request-null")
    @ResponseBody
    public ResponseData getRequestNull(){
        return new ResponseData(service.needActionRequest().getData());
    }
    
    @GetMapping("request-undone")
    @ResponseBody
    public ResponseData getRequestUndone(){
        return new ResponseData(service.undoneRequest().getData());
    }
    
    @GetMapping("request-done")
    @ResponseBody
    public ResponseData getRequestDone(){
        return new ResponseData(service.doneRequest().getData());
    }
    
    @GetMapping("request-rejected")
    @ResponseBody
    public ResponseData getRequestReject(){
        return new ResponseData(service.rejectedRequest().getData());
    }
    
    @GetMapping("reject-java")
    @ResponseBody
    public ResponseData getRejectJava(){
        return new ResponseData(service.rejectSkillJava().getData());
    }
    
    @GetMapping("reject-python")
    @ResponseBody
    public ResponseData getRejectPython(){
        return new ResponseData(service.rejectSkillPython().getData());
    }
    @GetMapping("reject-php")
    @ResponseBody
    public ResponseData getRejectPhp(){
        return new ResponseData(service.rejectSkillPhp().getData());
    }
    @GetMapping("reject-react")
    @ResponseBody
    public ResponseData getRejectReact(){
        return new ResponseData(service.rejectSkillReact().getData());
    }
    @GetMapping("reject-net")
    @ResponseBody
    public ResponseData getRejectNet(){
        return new ResponseData(service.rejectSkillNet().getData());
    }
    
}

