package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Interview;
import com.bootcampmanagement.clientapp.models.Request;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.InterviewService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("bootcamp-management/interview")
@PreAuthorize("hasAnyRole('ROLE_RM')")
public class InterviewController {
    
    @Autowired
    InterviewService service;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("interviewItem","nav-item active");
        
        model.addAttribute("viewRequestItem","nav-item");
        model.addAttribute("viewRequestPage","/bootcamp-management/request");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "interview";
    }
    
    @GetMapping("list")
    @ResponseBody
    public List<Interview> listInterview(){
        List<Interview> interview = service.findAll().getData();
        return interview;
    }
    
    @PostMapping("save")
    @ResponseBody
    public ResponseMessage saveInvite(HttpServletRequest request){
        
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter("emp")));
        
        Request req = new Request();
        req.setId(Integer.parseInt(request.getParameter("req")));
        
        Interview interview = new Interview(employee,req);
        return service.save(interview);  
    }
    
    @PostMapping("add/qualified")
    @ResponseBody
    public ResponseMessage qualified(HttpServletRequest request) throws ParseException {
        Integer id = Integer.parseInt(request.getParameter("idInter"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(request.getParameter("startDate"));
        Interview inter = new Interview(id,startDate);
        return service.qualifiedInterview(inter);
    }
    
    @GetMapping("list/{id}")
    @ResponseBody
    public Interview interviewById(@PathVariable Integer id){
        Interview inter = service.findById(id).getData();
        return inter;
    }
    
    @GetMapping("unqualified/{id}")
    @ResponseBody
    public ResponseMessage unqualified(@PathVariable Integer id){
        return new ResponseMessage(service.unqualifiedInterview(id));
    }
    
    
}
