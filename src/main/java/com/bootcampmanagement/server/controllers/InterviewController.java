package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.entities.Interview;
import com.bootcampmanagement.server.entities.Request;
import com.bootcampmanagement.server.services.EmployeeService;
import com.bootcampmanagement.server.services.InterviewService;
import com.bootcampmanagement.server.services.NotificationService;
import com.bootcampmanagement.server.services.RequestService;
import com.bootcampmanagement.server.services.UserHiredNotificationService;
import com.bootcampmanagement.server.services.UserNotHiredNotificationService;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bootcamp-management/interview")
public class InterviewController implements EntityNameMessage{
    
    @Autowired
    InterviewService interviewService;
    
    @Autowired
    NotificationService notifService;
    
    @Autowired
    UserHiredNotificationService notifHiredService;
    
    @Autowired
    UserNotHiredNotificationService notifNotHiredService;
    
    @Autowired
    EmployeeService empService;
    
    @Autowired
    RequestService reqService;
    
    @GetMapping("")
    public ResponseListData getAllInterview() {
        return new ResponseListData(interviewService.findAll());
    }
    
    @GetMapping("employee/qualified")
    public ResponseListData getAllEmp() {
        return new ResponseListData(interviewService.getEmpQualified());
    }
    
    @GetMapping("empQualified/{id}")
    public ResponseData getInterviewByEmpIdLatest(@PathVariable Integer id) {
        return new ResponseData(interviewService.getInterviewEmpQualified(id));
    }
    
    @GetMapping("{id}")
    public ResponseData getInterviewById(@PathVariable Integer id) {
        return new ResponseData(interviewService.findById(id));
    }
    
    @GetMapping("list")
    public ResponseListData getList() {
        return new ResponseListData(interviewService.getStatusInterviewNull());
    }
    
    @GetMapping("emp/{name}")
    public ResponseListData getInterviewByEmpId(@PathVariable String name) {
        return new ResponseListData(interviewService.getInterviewByEmpId(name));
    }
    @GetMapping("empLatest/{name}")
    public ResponseData getInterviewByEmpIdLatest(@PathVariable String name) {
        return new ResponseData(interviewService.getInterviewByEmpIdLatest(name));
    }
    
    @PostMapping("statY")
    public ResponseMessage getTrainerY(@RequestBody Interview interview) throws MessagingException{
        interviewService.updateStatY(interview);
        Interview inter = interviewService.findById(interview.getId());
        Integer requestId = inter.getRequest().getId();
        Integer employeeId = inter.getEmployee().getId();
        Employee employee = empService.findById(employeeId);
        Request request = reqService.findById(requestId);
        interviewService.updateEmployeeSite(employeeId);
        notifHiredService.sendEmail(employee,request);
        return new ResponseMessage(interview, getEntityName() + " updated") ;
    }
    
    @GetMapping("statN/{id}")
    public ResponseMessage getTrainerN(@PathVariable Integer id) throws MessagingException {
        interviewService.updateStatN(id);
        Interview inter = interviewService.findById(id);
        Integer requestId = inter.getRequest().getId();
        Integer employeeId = inter.getEmployee().getId();
        Employee employee = empService.findById(employeeId);
        Request request = reqService.findById(requestId);
        interviewService.updateStatEmpAvailable(employeeId);
        notifNotHiredService.sendEmail(employee, request);
        return new ResponseMessage(inter, getEntityName() + " updated");
    }
    
    @PostMapping("")
    public ResponseMessage addInterview(@RequestBody Interview interview)  throws MessagingException{
        interviewService.create(interview);
        int empId = interview.getEmployee().getId();
        int reqId = interview.getRequest().getId();
        Employee employee = empService.findById(empId);
        Integer id = employee.getId();
        Request request = reqService.findById(reqId);
        notifService.sendEmail(employee, request);
        interviewService.updateStatEmpNo(id);
        reqService.candidateSent(reqId); 
        return new ResponseMessage(interview, getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateInterview(@RequestBody Interview interview, @PathVariable Integer id) {
        interview.setId(id);
        return new ResponseMessage(interviewService.update(interview, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteInterview(@PathVariable Integer id) {
        return new ResponseMessage(interviewService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Interview";
    }
    
}
