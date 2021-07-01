/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Skill;
import com.bootcampmanagement.server.repositories.SkillRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillService extends BasicCrudService<Skill, Integer>{
    
    private SkillRepository skillRepo;
    
    public SkillService(SkillRepository skillRepo) {
        super(skillRepo);
        this.skillRepo = skillRepo;
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "Skill data not found";
    }
    
    @Override
    public String dataAlreadyExistMessage() {
        return " Skill data already exist";
    }
}