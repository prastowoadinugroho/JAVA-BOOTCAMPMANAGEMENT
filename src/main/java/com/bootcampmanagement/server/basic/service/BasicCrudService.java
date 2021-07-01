package com.bootcampmanagement.server.basic.service;

import com.bootcampmanagement.server.basic.ErrorMessage;
import com.bootcampmanagement.server.basic.ThrowMessageInterface;
import com.bootcampmanagement.server.basic.iservice.BasicCrudServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BasicCrudService<E, I> implements BasicCrudServiceInterface<E, I>, 
        ThrowMessageInterface, ErrorMessage {
    
    private final JpaRepository<E, I> repository;

    public BasicCrudService(JpaRepository<E, I> repository) {
        this.repository = repository;
    }

    @Override
    public ResponseStatusException dataAlreadyExist(){
        return new ResponseStatusException(HttpStatus.CONFLICT, dataAlreadyExistMessage());
    }

    @Override
    public ResponseStatusException dataNotFound(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, dataNotFoundMessage());
    }
    
    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E findById(I id) {
        return repository.findById(id).orElseThrow(this::dataNotFound);
    }

    @Override
    public E create(E data) {
       return repository.save(data);
    }

    @Override
    public E update(E data, I id) {
        Optional<E> dataUpdate = repository.findById(id);
        if (!dataUpdate.isPresent()) {
            throw dataNotFound();
        }
        return repository.save(data);
    }

    @Override
    public E delete(I id) {
        E data = repository.findById(id).orElseThrow(this::dataNotFound);
        
        repository.deleteById(id);
        
        return data;
    }
}

    
    
