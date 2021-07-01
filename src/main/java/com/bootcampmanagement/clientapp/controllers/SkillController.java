package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Skill;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.SkillService;
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
@RequestMapping("bootcamp-management/skill")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")

public class SkillController {
    @Autowired
    private SkillService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("skills", service.findAll().getData());
        model.addAttribute("skillItem","nav-item active");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("employeeItem","nav-item");
        model.addAttribute("employeePage","employee");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("jobPage","job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("sitePage","site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("userPage","user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        
        return "skill";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Skill> getAllSite(){
        List<Skill> skill = service.findAll().getData();
        return skill;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Skill getSkillById(@PathVariable("id") Integer id){
        Skill skill = service.getSkillById(id).getData();
        return skill;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage saveSkill(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Skill skill = new Skill(id,name);
        return service.saveSkill(skill);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteSkill(@PathVariable("id") Integer id){
        return service.deleteSkill(id);
    }
}
