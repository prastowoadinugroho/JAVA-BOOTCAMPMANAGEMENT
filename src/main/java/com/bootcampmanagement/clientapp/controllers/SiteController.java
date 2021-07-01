package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Site;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.SiteService;
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
@RequestMapping("bootcamp-management/site")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class SiteController {
    @Autowired
    private SiteService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("dashboardAdminPage","/bootcamp-management/admin");
        model.addAttribute("dashboardAdminItem","nav-item");
        
        model.addAttribute("sites", service.findAll().getData());
        model.addAttribute("siteItem","nav-item active");
 
        model.addAttribute("employeeItem","nav-item");
        model.addAttribute("employeePage","employee");
        
        model.addAttribute("rolePage","role");
        model.addAttribute("userRolePage","userRole");
        
        model.addAttribute("classPage","class");
        model.addAttribute("classItem","nav-item");
        
        model.addAttribute("jobPage","job");
        model.addAttribute("jobItem","nav-item");
        
        model.addAttribute("skillPage","skill");
        model.addAttribute("skillItem","nav-item");
        
        model.addAttribute("userPage","user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("customerPage","customer");
        model.addAttribute("customerItem","nav-item");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "site";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Site> getAllSite(){
        List<Site> site = service.findAll().getData();
        return site;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Site getSiteById(@PathVariable("id") Integer id){
        Site site = service.getSiteById(id).getData();
        return site;
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseMessage saveSite(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Site site = new Site(id,name,address);
        return service.saveSite(site);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteSite(@PathVariable("id") Integer id){
        return service.deleteSite(id);
    }
}
