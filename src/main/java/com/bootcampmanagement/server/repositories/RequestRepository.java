package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Request;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Modifying
    @Query(value = "update request set status = 'UNDONE', status_date = CURRENT_DATE() where id = :id",nativeQuery = true)
    int updateStatusAcc(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update request set status = 'REJECTED', reject_note = :rejectNote, status_date = CURRENT_DATE() where id = :id", nativeQuery = true)
    void updateStatusNot(@Param("rejectNote") String rejectNote, @Param("id") Integer id);
    
    @Query(value = "select * From request where status = 'DONE'",nativeQuery = true)
    List<Request> getStatusAcceptedDone();
    @Query(value = "select * From request where status IS NULL",nativeQuery = true)
    List<Request> getStatusNull();
    @Query(value = "select * From request where status = 'REJECTED'",nativeQuery = true)
    List<Request> getStatusRejected();
    
    @Query(value = "select * From request where status IS NULL OR status = 'REJECTED' OR status = 'DONE'",nativeQuery = true)
    List<Request> getStatusNullOrRejected();
    
    @Query(value = "select * From request where status = 'UNDONE'",nativeQuery = true)
    List<Request> getStatusAccepted();
    
    @Modifying
    @Query(value = "update request set candidate_sent = :sent where id = :id", nativeQuery = true)
    int updateStatus(@Param("sent") Integer sent, @Param("id") Integer id);

    @Modifying
    @Query(value = "update request set status = :stat where id = :id", nativeQuery = true)
    int updateStatusDone(@Param("stat") String stat, @Param("id") Integer id);
    
    //dashboard
    //total status null
    @Query(value = "select count(*) From request where status IS NULL", nativeQuery = true)
    int totalStatusNull();
    //total status rejected
    @Query(value = "select count(*) From request where status ='REJECTED'", nativeQuery = true)
    int totalStatusRejected();
    //total status undone
    @Query(value = "select count(*) From request where status ='UNDONE'", nativeQuery = true)
    int totalStatusUndone();
    //total status done
    @Query(value = "select count(*) From request where status ='DONE'", nativeQuery = true)
    int totalStatusDone();
    //total request
    @Query(value = "select count(*) From request", nativeQuery = true)
    int totalRequest();
    //end
    
    //pie reject by skill
    @Query(value = "select sum(candidate_needed) From request where status ='REJECTED' AND skill_id = 1", nativeQuery = true)
    int totalStatusRejectedSkill();
    @Query(value = "select sum(candidate_needed) From request where status ='REJECTED' AND skill_id = 2", nativeQuery = true)
    int totalStatusRejectedSkill1();
    @Query(value = "select sum(candidate_needed) From request where status ='REJECTED' AND skill_id = 3", nativeQuery = true)
    int totalStatusRejectedSkill2();
    @Query(value = "select sum(candidate_needed) From request where status ='REJECTED' AND skill_id = 4", nativeQuery = true)
    int totalStatusRejectedSkill3();
    @Query(value = "select sum(candidate_needed) From request where status ='REJECTED' AND skill_id = 5", nativeQuery = true)
    int totalStatusRejectedSkill4();
}
