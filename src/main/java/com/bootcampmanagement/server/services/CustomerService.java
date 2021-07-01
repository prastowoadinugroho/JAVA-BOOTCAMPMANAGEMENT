package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Customer;
import com.bootcampmanagement.server.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BasicCrudService<Customer, Integer> {

    private CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        super(customerRepo);
        this.customerRepo = customerRepo;
    }

    @Override
    public String dataNotFoundMessage() {
        return "Customer data not found";
    }

    @Override
    public String dataAlreadyExistMessage() {
        return "Customer data already exist";
    }
}