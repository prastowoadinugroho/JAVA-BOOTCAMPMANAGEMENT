package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Job;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.JobService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bootcamp-management/job")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class JobController {
        
    @Autowired
    private JobService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("jobs", service.findAll().getData());
        model.addAttribute("jobItem","nav-item active");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("employeeItem","nav-item");
        model.addAttribute("employeePage","employee");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("sitePage","site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("skillPage","skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("userPage","user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "job";
    }
    
    @GetMapping("job")
    @ResponseBody
    public List<Job> getAllJob(){
        List<Job> job = service.findAll().getData();
        return job;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Job getJobById(@PathVariable("id") Integer id){
        Job job = service.getJobById(id).getData();
        return job;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage saveJob(HttpServletRequest request){
        String name = request.getParameter("name");
        Job job = new Job(0,name);
        return service.saveJob(job);
    }
    
    @PostMapping("edit")
    @ResponseBody
    public ResponseMessage editJob(HttpServletRequest request){
        String ids = request.getParameter("id");
        Integer id = Integer.parseInt(ids);
        String name = request.getParameter("name");
        Job job = new Job(id,name);
        return service.saveJob(job);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteJob(@PathVariable("id") Integer id){
        return service.deleteJob(id);
    }
    
}
