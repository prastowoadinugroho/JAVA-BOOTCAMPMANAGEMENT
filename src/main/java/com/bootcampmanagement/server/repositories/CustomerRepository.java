package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
