package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Skill;
import com.bootcampmanagement.server.services.SkillService;
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
@RequestMapping("bootcamp-management/skill")
public class SkillController implements EntityNameMessage{
    
    @Autowired
    SkillService skillService;
    
    @GetMapping("")
    public ResponseListData getAllSkill() {
        return new ResponseListData(skillService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getSkillById(@PathVariable Integer id) {
        return new ResponseData(skillService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addSkill(@RequestBody Skill skill) {
        return new ResponseMessage(skillService.create(skill),getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateSkill(@RequestBody Skill skill,@PathVariable Integer id) {
        skill.setId(id);
        return new ResponseMessage(skillService.update(skill, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteSkill(@PathVariable Integer id) {
        return new ResponseMessage(skillService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Skill";
    }
}
