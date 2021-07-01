package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.EmployeeSkill;
import com.bootcampmanagement.clientapp.models.Skill;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.EmployeeSkillService;
import com.bootcampmanagement.clientapp.services.SkillService;
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
@RequestMapping("bootcamp-management/developer")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class DeveloperController {
    
    @Autowired
    DeveloperService service;
    
    @Autowired
    EmployeeSkillService serviceEmp;
    
    @Autowired
    SkillService serviceSkill;
    
    @GetMapping("information")
    public String information(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",service.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        model.addAttribute("users",service.historyInterview(auth.getName()).getData());
        model.addAttribute("results",service.resultInterview(auth.getName()).getData());
        
        model.addAttribute("informationItem","nav-item active");
        model.addAttribute("skillPage","skill");
        
        model.addAttribute("dashboardPage","/bootcamp-management");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("userSkillPage","skill");
        model.addAttribute("userSkillItem","nav-item");
        
        return "developer-info";
    }
    
    @GetMapping("skill")
    public String skill(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",service.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        model.addAttribute("skills",service.skill(auth.getName()).getData());
        model.addAttribute("skillemp",serviceSkill.findAll().getData());
        
        model.addAttribute("userSkillItem","nav-item active");
        
        model.addAttribute("informationItem","nav-item");
        model.addAttribute("informationPage","information");
        model.addAttribute("historyPage","interview-history");
        
        model.addAttribute("dashboardPage","/bootcamp-management");
        model.addAttribute("dashboardItem","nav-item");

        return "developer-skill";
    }
    
    @GetMapping("list/{id}")
    @ResponseBody
    public EmployeeSkill getAllEmployee(@PathVariable Integer id){
        EmployeeSkill employee = serviceEmp.empSkillId(id).getData();
        return employee;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage saveSkill(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String level = request.getParameter("level");
        String desc = request.getParameter("desc");
       
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter("empId")));
        
        Skill skill = new Skill();
        skill.setId(Integer.parseInt(request.getParameter("skillId")));
        
        EmployeeSkill emp = new EmployeeSkill(id,level,employee,skill,desc);
        return serviceEmp.save(emp);
        
    }
}
