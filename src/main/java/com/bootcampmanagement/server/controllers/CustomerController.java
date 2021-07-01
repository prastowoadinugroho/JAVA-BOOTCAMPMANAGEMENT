package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Customer;
import com.bootcampmanagement.server.services.CustomerService;
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
@RequestMapping("bootcamp-management/customer")
public class CustomerController implements EntityNameMessage{
    
    @Autowired
    CustomerService customerService;
    
    @GetMapping("")
    public ResponseListData getAllCustomer() {
        return new ResponseListData(customerService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getCustomerById(@PathVariable Integer id) {
        return new ResponseData(customerService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addCustomer(@RequestBody Customer customer) {
        return new ResponseMessage(customerService.create(customer), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        customer.setId(id);
        return new ResponseMessage(customerService.update(customer, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteCustomer(@PathVariable Integer id) {
        return new ResponseMessage(customerService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Customer";
    }
    
}

