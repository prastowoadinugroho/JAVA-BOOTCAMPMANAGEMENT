package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.EmpSkill;
import com.bootcampmanagement.server.services.EmployeeSkillService;
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
@RequestMapping("bootcamp-management/empSkill")
public class EmployeeSkillController implements EntityNameMessage{
    
    @Autowired
    EmployeeSkillService empSkillService;
    
    @GetMapping("")
    public ResponseListData getAllEmpSkill() {
        return new ResponseListData(empSkillService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getEmpSkillById(@PathVariable Integer id) {
        return new ResponseData(empSkillService.findById(id));
    }
    
    @GetMapping("sent/{id}")
    public ResponseData getSent(@PathVariable Integer id) {
        return new ResponseData(empSkillService.getSent(id));
    }
    
    @GetMapping("candidate/{skill}/{req}")
    public ResponseListData getAllTrainer(@PathVariable("skill") String skill,@PathVariable("req") Integer req) {
        return new ResponseListData(empSkillService.getTrainer(skill,req));
    }
    
    @GetMapping("candidateId/{skill}/{req}/{emp}")
    public ResponseListData getAllTrainerById(@PathVariable("skill") String skill,@PathVariable("req") Integer req,@PathVariable("emp") Integer emp) {
        return new ResponseListData(empSkillService.getTrainerById(skill,req,emp));
    }
    
    @GetMapping("skillSet/{id}")
    public ResponseListData getAllSkill(@PathVariable Integer id) {
        return new ResponseListData(empSkillService.getSkill(id));
    }
    @GetMapping("skillSetName/{name}")
    public ResponseListData getAllSkillName(@PathVariable String name) {
        return new ResponseListData(empSkillService.getSkillByName(name));
    }
    
//    @GetMapping("skillName/{name}")
//    public ResponseData getAllSkillId(@PathVariable String name) {
//        return new ResponseData(empSkillService.getSkillName(name));
//    }
    
    @PostMapping("")
    public ResponseMessage addEmpSkill(@RequestBody EmpSkill empSkill) {
        return new ResponseMessage(empSkillService.create(empSkill), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateEmpSkill(@RequestBody EmpSkill empSkill, @PathVariable Integer id) {
        empSkill.setId(id);
        return new ResponseMessage(empSkillService.update(empSkill, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteEmpSkill(@PathVariable Integer id) {
        return new ResponseMessage(empSkillService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Employee Skill";
    }

}
