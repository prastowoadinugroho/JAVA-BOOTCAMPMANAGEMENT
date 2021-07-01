package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Contract;
import com.bootcampmanagement.server.repositories.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BasicCrudService<Contract, Integer> {

    private ContractRepository contractRepo;

    public ContractService(ContractRepository contractRepo) {
        super(contractRepo);
        this.contractRepo = contractRepo;
    }

    @Override
    public String dataNotFoundMessage() {
        return "Contract data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Contract data already exist";
    }
}