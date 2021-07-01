package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Contract;
import com.bootcampmanagement.server.services.ContractService;
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
@RequestMapping("bootcamp-management/contract")
public class ContractController implements EntityNameMessage {

    @Autowired
    ContractService contractService;

    @GetMapping("")
    public ResponseListData getAllContract() {
        return new ResponseListData(contractService.findAll());
    }

    @GetMapping("{id}")
    public ResponseData getContractById(@PathVariable Integer id) {
        return new ResponseData(contractService.findById(id));
    }

    @PostMapping("")
    public ResponseMessage addContract(@RequestBody Contract contract) {
        return new ResponseMessage(contractService.create(contract), getEntityName() + " created");
    }

    @PutMapping("{id}")
    public ResponseMessage updateContract(@RequestBody Contract contract, @PathVariable Integer id) {
        contract.setId(id);
        return new ResponseMessage(contractService.update(contract, id), getEntityName() + " updated");
    }

    @DeleteMapping("{id}")
    public ResponseMessage deleteContract(@PathVariable Integer id) {
        return new ResponseMessage(contractService.delete(id), getEntityName() + " deleted");
    }

    @Override
    public String getEntityName() {
        return "Contract";
    }

}
