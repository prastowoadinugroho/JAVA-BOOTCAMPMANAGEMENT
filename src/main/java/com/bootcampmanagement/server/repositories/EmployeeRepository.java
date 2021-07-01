package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT u.id as id, "
            + "u.name as name, "
            + "u.job.name as job "
            + "FROM Employee u "
            + "WHERE u.job.id = 2")
    List<JobTrainer> getJobTrainer();
    
     //dashboard RM
    //developer available
    @Query("SELECT count(e) as total FROM Employee e WHERE e.jobStatus = 'Available' AND e.job.name = 'DEVELOPER'")
    int totalEmpAvai();
    
    //developer java available
    @Query(value="select COUNT(*) as total from employee e join emp_skill es on es.emp_id=e.id where e.job_status = 'available' and e.job_id = 1 and es.skill_id = 1", nativeQuery = true)
    int totalEmpAvaiBySkill();
    
    //developer phyton available
    @Query(value="select COUNT(*) as total from employee e join emp_skill es on es.emp_id=e.id where e.job_status = 'available' and e.job_id = 1 and es.skill_id = 2", nativeQuery = true)
    int totalEmpAvaiBySkill1();
    
    //developer php available
    @Query(value="select COUNT(*) as total from employee e join emp_skill es on es.emp_id=e.id where e.job_status = 'available' and e.job_id = 1 and es.skill_id = 3", nativeQuery = true)
    int totalEmpAvaiBySkill2();
    //developer phyton available
    @Query(value="select COUNT(*) as total from employee e join emp_skill es on es.emp_id=e.id where e.job_status = 'available' and e.job_id = 1 and es.skill_id = 4", nativeQuery = true)
    int totalEmpAvaiBySkill3();
    //developer phyton available
    @Query(value="select COUNT(*) as total from employee e join emp_skill es on es.emp_id=e.id where e.job_status = 'available' and e.job_id = 1 and es.skill_id = 5", nativeQuery = true)
    int totalEmpAvaiBySkill4();
    
    //end of dashboard
    
     //total pegawai
    @Query(value="SELECT count(*) FROM Employee",nativeQuery = true)
    int totalEmp();
    
    //total developer 
    @Query("SELECT count(e) as total FROM Employee e WHERE e.job.name = 'DEVELOPER'")
    int totalEmpDeveloper();

    //total trainer
    @Query("SELECT count(e) as total FROM Employee e WHERE e.job.name = 'TRAINER'")
    int totalEmpTrainer();
    
    //developer not available
    @Query("SELECT count(e) as total FROM Employee e WHERE e.jobStatus = 'Not Available' AND e.job.name = 'DEVELOPER'")
    int totalEmpNotAvai();

    //developer on site
    @Query("SELECT count(e) as total FROM Employee e WHERE e.jobStatus = 'On Site' AND e.job.name = 'DEVELOPER'")
    int totalEmpOnSite();
}
