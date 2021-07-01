package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Customer;
import com.bootcampmanagement.clientapp.models.Request;
import com.bootcampmanagement.clientapp.models.Skill;
import com.bootcampmanagement.clientapp.services.CustomerService;
import com.bootcampmanagement.clientapp.services.DeveloperService;
import com.bootcampmanagement.clientapp.services.EmployeeSkillService;
import com.bootcampmanagement.clientapp.services.RequestService;
import com.bootcampmanagement.clientapp.services.SkillService;
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
@RequestMapping("bootcamp-management/request")
@PreAuthorize("hasAnyRole('ROLE_RM')")
public class RequestController {
    @Autowired
    private RequestService service;
    
    @Autowired
    private EmployeeSkillService serviceEmpSkil;
        
    @Autowired
    private CustomerService serviceCustomer;
    
    @Autowired
    private SkillService skillService;
    
    @Autowired
    DeveloperService serviceDev;
    
    @GetMapping("")
    public String view(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("requests", service.listRequest().getData());
        model.addAttribute("customers", serviceCustomer.findAll().getData());
        model.addAttribute("skills", skillService.findAll().getData());
        
        model.addAttribute("viewRequestItem","nav-item active");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "request";
    }
    @GetMapping("done")
    public String viewDone(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("requests", service.listRequest().getData());
        model.addAttribute("customers", serviceCustomer.findAll().getData());
        model.addAttribute("skills", skillService.findAll().getData());
        
        model.addAttribute("viewRequestItem","nav-item active");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "request-done";
    }
    @GetMapping("reject-list")
    public String viewReject(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("requests", service.listRequest().getData());
        model.addAttribute("customers", serviceCustomer.findAll().getData());
        model.addAttribute("skills", skillService.findAll().getData());
        
        model.addAttribute("viewRequestItem","nav-item active");
        
        model.addAttribute("requestAcc","nav-item");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "request-rejected";
    }
    
    @GetMapping("list-accept")
    public String request(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("requestAcc","nav-item active");
        
        model.addAttribute("viewRequestPage","/bootcamp-management/request");
        model.addAttribute("viewRequestItem","nav-item");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "request-accepted";
    }
    
    @GetMapping("candidate/{skill}/{id}")
    public String listCandidate(@PathVariable String skill, @PathVariable Integer id,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",serviceDev.detailUser(auth.getName()).getData());
        model.addAttribute("name",auth.getName());
        
        model.addAttribute("candidates", serviceEmpSkil.listCandidate(skill,id).getData());
        model.addAttribute("send", serviceEmpSkil.sentCount(id).getData().get(0));
        
        model.addAttribute("requestAcc","nav-item active");
        model.addAttribute("requestAccPage","/bootcamp-management/request/list-accept");
        
        model.addAttribute("interviewPage","/bootcamp-management/interview");
        model.addAttribute("interviewItem","nav-item");

        model.addAttribute("viewRequestPage","/bootcamp-management/request");
        model.addAttribute("viewRequestItem","nav-item");
        
        model.addAttribute("dashboardPage","/bootcamp-management/rm");
        model.addAttribute("dashboardItem","nav-item");
        
        model.addAttribute("employeeOnSiteItem","nav-item");
        model.addAttribute("employeeOnSitePage","/bootcamp-management/employee-site");
        
        return "list-candidate";
    }
       
    @GetMapping("coba/{id}")
    public String cobacoba(@PathVariable Integer id, Model model){
        model.addAttribute("listSkill", serviceEmpSkil.candidateSkill(id).getData());
        return "list-candidate";
    }
    
    @GetMapping("accept/{id}")
    @ResponseBody
    public ResponseMessage acceptReq(@PathVariable("id") Integer id){
        return service.acceptRequest(id);
    }
    
    @GetMapping("candidate/skill/{id}")
    @ResponseBody
    public ResponseListData skill(@PathVariable("id") Integer id){
        return new ResponseListData(serviceEmpSkil.candidateSkill(id).getData());
    }
    
    @GetMapping("detail/{skill}/{id}/{empId}")
    @ResponseBody
    public ResponseListData coba(@PathVariable String skill,@PathVariable Integer id,@PathVariable Integer empId){
        return new ResponseListData(serviceEmpSkil.detailCandidate(skill,id,empId).getData());
    }
    
    @PostMapping("reject")
    @ResponseBody
    public ResponseMessage rejectReq(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("reqId"));
        String rejectNote = request.getParameter("reject");
        Request req = new Request(id,rejectNote);
        return service.rejectRequest(req);
    }
    
    @GetMapping("req")
    @ResponseBody
    public List<Request> listRequest(){
        List<Request> req = service.listRequest().getData();
        return req;
    }
    
    @GetMapping("null")
    @ResponseBody
    public List<Request> listNull(){
        List<Request> req = service.RequestNull().getData();
        return req;
    }
    @GetMapping("req-done")
    @ResponseBody
    public List<Request> listDone(){
        List<Request> req = service.RequestDone().getData();
        return req;
    }
    @GetMapping("req-reject")
    @ResponseBody
    public List<Request> listReject(){
        List<Request> req = service.requestRejected().getData();
        return req;
    }
    
    @GetMapping("stat-done/{id}")
    @ResponseBody
    public ResponseMessage statDone(@PathVariable Integer id){
        return new ResponseMessage(service.statDone(id));
    }
    
    @GetMapping("accept")
    @ResponseBody
    public List<Request> listRequestAccept(){
        List<Request> req = service.listAcceptRequest().getData();
        return req;
    }
    
    @GetMapping("list/{id}")
    @ResponseBody
    public Request requestById(@PathVariable Integer id){
        Request req = service.findById(id).getData();
        return req;
    }
    
    @PostMapping("add")
    @ResponseBody
    public ResponseMessage save(HttpServletRequest request) throws ParseException{ 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date interviewDate = sdf.parse(request.getParameter("interviewDate"));
        
        Integer candidateNeeded = Integer.parseInt(request.getParameter("candidateNeeded"));
        String status = request.getParameter("status");
       
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(request.getParameter("customer")));
        
        Skill skill = new Skill();
        skill.setId(Integer.parseInt(request.getParameter("skill")));
        
        Request req = new Request(interviewDate,candidateNeeded,status,customer,skill);
        return service.save(req);
    }
    
    @PostMapping("edit")
    @ResponseBody
    public ResponseMessage edit(HttpServletRequest request) throws ParseException{
        Integer id = Integer.parseInt(request.getParameter("idReqEdit"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date interviewDate = sdf.parse(request.getParameter("interviewDateReqEdit"));
        
        Integer candidateNeeded = Integer.parseInt(request.getParameter("candidateNeededReqEdit"));
        String status = request.getParameter("status");
       
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(request.getParameter("customerReqEdit")));
        
        Skill skill = new Skill();
        skill.setId(Integer.parseInt(request.getParameter("skillReqEdit")));
        
        Request req = new Request(id,interviewDate,candidateNeeded,status,customer,skill);
        return service.save(req);
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Request detail(@PathVariable("id") Integer id){
        Request req = service.detailRequest(id).getData();
        return req;
    }
    
}
