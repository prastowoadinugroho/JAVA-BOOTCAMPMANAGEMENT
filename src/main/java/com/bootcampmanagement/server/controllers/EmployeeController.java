package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.services.EmployeeService;
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
@RequestMapping("bootcamp-management/employee")
public class EmployeeController implements EntityNameMessage {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public ResponseListData getAllEmployee() {
        return new ResponseListData(employeeService.findAll());
    }   

    @GetMapping("{id}")
    public ResponseData getEmployeeById(@PathVariable Integer id) {
        return new ResponseData(employeeService.findById(id));
    }
    
    @GetMapping("jobTrainer")
    public ResponseListData getAllJobTrainer() {
        return new ResponseListData(employeeService.getJobTrainer());
    }

    @PostMapping("")
    public ResponseMessage addEmployee(@RequestBody Employee employee) {
        return new ResponseMessage(employeeService.create(employee), getEntityName() + " created");
    }

    @PutMapping("{id}")
    public ResponseMessage updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
        employee.setId(id);
        return new ResponseMessage(employeeService.update(employee, id), getEntityName() + " updated");
    }

    @DeleteMapping("{id}")
    public ResponseMessage deleteEmployee(@PathVariable Integer id) {
        return new ResponseMessage(employeeService.delete(id), getEntityName() + " deleted");
    }
    
     //dashboard RM
    @GetMapping("totalEmpAvai")
    public ResponseData getTotalEmpAvailable() {
        return new ResponseData(employeeService.totalEmpAvailable());
    }
    
    @GetMapping("java")
    public ResponseData getTotalEmpAvailableBySkill() {
        return new ResponseData(employeeService.totalEmpAvailableBySkill());
    }
    @GetMapping("python")
    public ResponseData getTotalEmpAvailableBySkill1() {
        return new ResponseData(employeeService.totalEmpAvailableBySkill1());
    }
    
    @GetMapping("php")
    public ResponseData getTotalEmpAvailableBySkill2() {
        return new ResponseData(employeeService.totalEmpAvailableBySkill2());
    }
    
    @GetMapping("react")
    public ResponseData getTotalEmpAvailableBySkill3() {
        return new ResponseData(employeeService.totalEmpAvailableBySkill3());
    }
    
    @GetMapping(".net")
    public ResponseData getTotalEmpAvailableBySkill4() {
        return new ResponseData(employeeService.totalEmpAvailableBySkill4());
    }
    
    @GetMapping("totalEmpNotAvai")
    public ResponseData getTotalEmpNotAvailable() {
        return new ResponseData(employeeService.totalEmpNotAvailable());
    }

    @GetMapping("totalEmpOnSite")
    public ResponseData getTotalEmpOnSite() {
        return new ResponseData(employeeService.totalEmpOnSite());
    }
    //end of dashboard

    @Override
    public String getEntityName() {
        return "Employee";
    }

}
