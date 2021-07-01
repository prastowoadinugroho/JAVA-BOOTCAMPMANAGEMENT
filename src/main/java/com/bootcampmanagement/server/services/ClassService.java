package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Classes;
import com.bootcampmanagement.server.repositories.ClassRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassService extends BasicCrudService<Classes, Integer> {
    
    private ClassRepository classRepo;

    public ClassService(ClassRepository classRepo) {
        super(classRepo);
        this.classRepo = classRepo;
    }

    @Override
    public String dataNotFoundMessage() {
        return "Class data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Class data already exist";
    }
}