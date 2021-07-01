package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Job;
import com.bootcampmanagement.server.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService extends BasicCrudService<Job, Integer>{

    private JobRepository jobRepo;

    public JobService(JobRepository jobRepo) {
        super(jobRepo);
        this.jobRepo = jobRepo;
    }
   
    @Override
    public String dataNotFoundMessage() {
        return "Job data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Job data already exist";
    }
}
