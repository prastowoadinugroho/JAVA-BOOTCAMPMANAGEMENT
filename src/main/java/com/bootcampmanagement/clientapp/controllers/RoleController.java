package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Role;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.RoleService;
import java.text.ParseException;
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
@RequestMapping("bootcamp-management/role")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {
    @Autowired
    private RoleService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("userItem","nav-item active");
        
        model.addAttribute("userPage","user");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("employeeItem","nav-item");
        model.addAttribute("employeePage","employee");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("jobPage","job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("sitePage","site");
        model.addAttribute("siteItem","nav-item");
        
        model.addAttribute("skillPage","skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "role";
        
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Role> listRole(){
        List<Role> rl = service.findAll().getData();
        return rl;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Role getSiteById(@PathVariable("id") Integer id){
        Role role = service.getRoleById(id).getData();
        return role;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage save(HttpServletRequest request) throws ParseException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        
        Role role = new Role(id,name);
        return service.saveRole(role);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage delete(@PathVariable("id") Integer id){
        return service.deleteRole(id);
    }
    
}
