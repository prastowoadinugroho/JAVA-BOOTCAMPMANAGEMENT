package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Integer> {
     //Total Class
    @Query(value="SELECT count(*) FROM class",nativeQuery = true)
    int totalClass();
    
}
