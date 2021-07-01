package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Role;
import com.bootcampmanagement.clientapp.models.User;
import com.bootcampmanagement.clientapp.models.UserRole;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.RoleService;
import com.bootcampmanagement.clientapp.services.UserRoleService;
import com.bootcampmanagement.clientapp.services.UserService;
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
@RequestMapping("bootcamp-management/userRole")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserRoleController {
    
    @Autowired
    private UserRoleService service;
    
    @Autowired
    private UserService serviceUser;
    
    @Autowired
    private RoleService serviceRole;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("users",serviceUser.findAll().getData());
        model.addAttribute("roles",serviceRole.findAll().getData());
        
        model.addAttribute("userItem","nav-item active");
        
        model.addAttribute("userPage","user");
        model.addAttribute("rolePage","role");
        
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
        
        return "user-role";
        
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<UserRole> list(){
        List<UserRole> user = service.findAll().getData();
        return user;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public UserRole getUserRoleById(@PathVariable("id") Integer id){
        UserRole userRole = service.getUserRoleById(id).getData();
        return userRole;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage save(HttpServletRequest request) throws ParseException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("user")));
        
        Role role = new Role();
        role.setId(Integer.parseInt(request.getParameter("role")));
                
        UserRole userRole = new UserRole(id,role,user);
        return service.save(userRole);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteRole(@PathVariable("id") Integer id){
        return service.delete(id);
    }
    
}
