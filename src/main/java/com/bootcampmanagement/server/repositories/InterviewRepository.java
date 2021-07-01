package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Interview;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    @Query(value = "select * From interview where status ='QUALIFIED'", nativeQuery = true)
    List<Interview> getStatusInterviewQualified();
    
    @Query("select c From Interview c where c.employee.id = :id AND c.status ='QUALIFIED'")
    Interview getInterviewByIdQual(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update interview set status = 'QUALIFIED',start_date = :startDate where id = :id",nativeQuery = true)
    void updateStatusYes(@Param("id") Integer id, @Param("startDate") Date startDate);
    
    @Modifying
    @Query(value = "update interview set status = 'UNQUALIFIED' where id = :id",nativeQuery = true)
    int updateStatusNo(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update employee set job_status = 'Not Available' where id = :id",nativeQuery = true)
    int updateStatEmpNot(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update employee set job_status = 'Available' where id = :id",nativeQuery = true)
    int updateStatEmpAvailable(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update employee set job_status = 'On Site' where id = :id",nativeQuery = true)
    int updateStatEmpSite(@Param("id") Integer id);
    
    @Query(value = "select * From interview where status IS NULL", nativeQuery = true)
    List<Interview> getStatusInterviewNull();
    
    @Query("select c From Interview c where c.employee.name = :name order by c.request desc")
    List<Interview> getInterviewByEmpId(@Param("name") String name);
    
    @Query(value="select * From interview i join employee e on e.id = i.emp_id where e.name = :name order by i.req_id desc limit 1", nativeQuery = true)
    Interview getInterviewByEmpIdLatest(@Param("name") String name);
    
}
