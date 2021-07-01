package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Classes;
import com.bootcampmanagement.clientapp.services.ClassesService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
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
@RequestMapping("bootcamp-management/class")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class ClassesController {
    
    @Autowired
    private ClassesService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String getAllClass(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("classes", service.findAll().getData());
        model.addAttribute("classItem","nav-item active");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("employeePage","employee");
        model.addAttribute("employeeItem","nav-item");
        
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

        return "class";
    }
    
    @GetMapping("emp")
    @ResponseBody
    public List<Classes> country(){
        List<Classes> classes = service.findAll().getData();
        return classes;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Classes countryById(@PathVariable("id") Integer id){
        Classes classes = service.getClassById(id).getData();
        return classes;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage save(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Classes classes = new Classes(id,name, type);
        return service.saveClasses(classes);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage classDelete(@PathVariable("id") Integer id){
        return service.deleteClass(id);
    }
    
}
