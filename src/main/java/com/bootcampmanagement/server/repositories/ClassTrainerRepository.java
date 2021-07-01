package com.bootcampmanagement.server.repositories;
import com.bootcampmanagement.server.entities.ClassTrainer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTrainerRepository extends JpaRepository<ClassTrainer, Integer> {
    @Query("SELECT u.name as name, "
            + "u.job.name as job, "
            + "u.classes.id as classes "
            + "FROM Employee u "
            + "WHERE u.job.id = 1 AND u.classes.id = :jobangka")
    List<Trainer> getAllTrainer(@Param("jobangka") Integer angka);
}
