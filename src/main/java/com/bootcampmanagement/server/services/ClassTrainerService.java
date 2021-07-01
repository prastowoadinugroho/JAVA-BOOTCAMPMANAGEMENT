package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.ClassTrainer;
import com.bootcampmanagement.server.repositories.ClassTrainerRepository;
import com.bootcampmanagement.server.repositories.Trainer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClassTrainerService extends BasicCrudService<ClassTrainer, Integer> {

    private ClassTrainerRepository classTrainerRepo;

    public ClassTrainerService(ClassTrainerRepository classTrainerRepo) {
        super(classTrainerRepo);
        this.classTrainerRepo = classTrainerRepo;
    }
    
    public List<Trainer> getTrainer(Integer angka){
        return classTrainerRepo.getAllTrainer(angka);
    }

    @Override
    public String dataNotFoundMessage() {
        return "Class Trainer data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Class Trainer data already exist";
    }
    

    
}
