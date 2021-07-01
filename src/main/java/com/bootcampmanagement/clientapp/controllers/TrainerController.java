package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Classes;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Trainer;
import com.bootcampmanagement.clientapp.services.ClassesService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.EmployeeService;
import com.bootcampmanagement.clientapp.services.TrainerService;
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
@RequestMapping("bootcamp-management/trainer")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class TrainerController {
    
    @Autowired
    TrainerService service;
    
    @Autowired
    EmployeeService serviceEmp;
    
    @Autowired
    ClassesService serviceClass;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String employee(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
                
        model.addAttribute("trainers",service.findAll().getData());
        model.addAttribute("listTrainer",serviceEmp.listTrainer().getData());
        model.addAttribute("listClass",serviceClass.findAll().getData());
        
        model.addAttribute("trainerItem","nav-item active");
        
        model.addAttribute("employeePage","employee");
        model.addAttribute("employeeItem","nav-item");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
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
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        return "trainer";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Trainer> getAllTrainer(){
        List<Trainer> trainer = service.findAll().getData();
        return trainer;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Trainer getById(@PathVariable("id") Integer id){
        Trainer train = service.getTrainerById(id).getData();
        return train;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage saveTrainer(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
       
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter("employee")));
        
        Classes classes = new Classes();
        classes.setId(Integer.parseInt(request.getParameter("class")));
        
        Trainer train = new Trainer(id,classes,employee);
        return service.saveTrainer(train);
        
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteTrainer(@PathVariable("id") Integer id){
        return service.deleteTrainer(id);
    }
}
