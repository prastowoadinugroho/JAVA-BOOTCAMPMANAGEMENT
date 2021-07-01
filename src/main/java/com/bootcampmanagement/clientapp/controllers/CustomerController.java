package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Customer;
import com.bootcampmanagement.clientapp.models.Site;
import com.bootcampmanagement.clientapp.services.CustomerService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.SiteService;
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
@RequestMapping("bootcamp-management/customer")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class CustomerController {
    
    @Autowired
    private CustomerService service;
    
    @Autowired
    private SiteService siteService;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("customers", service.findAll().getData());
        model.addAttribute("sites", siteService.findAll().getData());
        
        model.addAttribute("customerItem","nav-item active");
        
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
        
        model.addAttribute("userPage","user");
        model.addAttribute("userItem","nav-item");
        
        model.addAttribute("userRolePage","userRole");
        model.addAttribute("rolePage","role");
        
        model.addAttribute("trainerPage","trainer");
        model.addAttribute("trainerItem","nav-item");
        
        return "customer";
        
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Customer> listRequest(){
        List<Customer> customer = service.findAll().getData();
        return customer;
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Customer detail(@PathVariable("id") Integer id){
        Customer customer = service.getCustomer(id).getData();
        return customer;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage save(HttpServletRequest request) throws ParseException{ 
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
       
        Site site = new Site();
        site.setId(Integer.parseInt(request.getParameter("site")));
        
        Customer customer = new Customer(id,name,position,site);
        return service.save(customer);
    }
    
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseMessage deleteCustomer(@PathVariable("id") Integer id){
        return service.deleteCustomer(id);
    }
    
    
}
