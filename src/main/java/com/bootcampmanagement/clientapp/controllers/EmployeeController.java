package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.models.Classes;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Job;
import com.bootcampmanagement.clientapp.services.ClassesService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.EmployeeService;
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
@RequestMapping("bootcamp-management/employee")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")

public class EmployeeController {
    
    @Autowired
    EmployeeService service;
    
    @Autowired
    ClassesService serviceClass;
    
    @Autowired
    JobService serviceJob;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String employee(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
                
        model.addAttribute("employees",service.findAll().getData());
        model.addAttribute("employeeItem","nav-item active");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
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
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "employee";
    }
    
    @GetMapping("add")
    public String employeeAdd(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("classes",serviceClass.findAll().getData());
        model.addAttribute("jobs",serviceJob.findAll().getData()); 
        
        model.addAttribute("employeeItem","nav-item active");
        model.addAttribute("employeePage","../employee");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
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
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "employee-add";
    }
    
    @GetMapping("{id}/edit")
    public String employeeEdit(@PathVariable("id") Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("employee",service.detail(id).getData());
        model.addAttribute("classes",serviceClass.findAll().getData());
        model.addAttribute("jobs",serviceJob.findAll().getData()); 
        
        model.addAttribute("employeeItem","nav-item active");
        model.addAttribute("employeePage","../../employee");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("classPage","../../class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("rolePage","../../role");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("jobPage","../../job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("sitePage","../../site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("skillPage","../../skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("userPage","../../user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("customerPage","../../customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("trainerPage","../../trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "employee-edit";
    }
    
    @PostMapping("save")
    public String save(HttpServletRequest request){ 
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String jobStatus = request.getParameter("jobStatus");
        
        Classes classes = new Classes();
        classes.setId(Integer.parseInt(request.getParameter("classes")));
        
        Job job = new Job();
        job.setId(Integer.parseInt(request.getParameter("job")));
        
        
        Employee employee = new Employee(id, name, email,address,phoneNumber,jobStatus,classes,job);
        service.save(employee);
        
        return "redirect:/bootcamp-management/employee";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Employee> getAllEmployee(){
        List<Employee> employee = service.findAll().getData();
        return employee;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Employee getById(@PathVariable Integer id){
        Employee employee = service.detail(id).getData();
        return employee;
    }
    
}
