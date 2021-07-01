package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.EmpSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmpSkill, Integer>{
      @Query("SELECT u.employee.name as name, "
            + "u.employee.jobStatus as status, "
            + "u.employee.email as email, "
            + "u.employee.phoneNumber as phone, "
            + "u.employee.id as empId, "
            + "u.employee.address as address, "
            + "u.employee.classes.name as classes, "
            + "u.skill.name as skill, "
            + "u.employee.job.name as job, "
            + "r.id as req, "
            + "r.candidateSent as sent "
            + "FROM EmpSkill u  JOIN Request r ON r.skill = u.skill "
            + "WHERE u.employee.job = 1 AND u.employee.jobStatus = 'available' AND u.skill.name = :skillName AND r.id = :reqAngka")
    List<Candidate> getAllCandidate(@Param("skillName") String skill,@Param("reqAngka") Integer req);
    
    @Query("SELECT u.employee.name as name, "
            + "u.employee.jobStatus as status, "
            + "u.employee.email as email, "
            + "u.employee.phoneNumber as phone, "
            + "u.employee.id as empId, "
            + "u.employee.address as address, "
            + "u.employee.classes.name as classes, "
            + "u.skill.name as skill, "
            + "u.employee.job.name as job, "
            + "r.id as req "
            + "FROM EmpSkill u  JOIN Request r ON r.skill = u.skill "
            + "WHERE u.employee.job = 1 AND u.employee.jobStatus = 'available' AND u.employee.id =:emp AND u.skill.name = :skillName AND r.id = :reqAngka")
    List<Candidate> getAllCandidateId(@Param("skillName") String skill,@Param("reqAngka") Integer req,@Param("emp") Integer emp);
    
    @Query("SELECT u.employee.name as name, "           
            + "u.skill.name as skill, "
            + "u.level as level, "
            + "u.desc as description "
            + "FROM EmpSkill u "
            + "WHERE u.employee.id = :angka ")
    List<SkillSet> getAllSkill(@Param("angka") Integer angka);
    
    @Query("SELECT u.employee.name as name, "           
            + "u.skill.name as skill, "
            + "u.level as level, "
            + "u.desc as description, "
            + "u.skill.id as id, "
            + "u.employee.id as emp, "
            + "u.id as angka "
            + "FROM EmpSkill u "
            + "WHERE u.employee.name = :name ")
    List<SkillSet> getSkillByName(@Param("name") String name);

    @Query("SELECT r.candidateSent as sent, r.candidateNeeded as need FROM Request r WHERE r.id = :angka")
    List<Sent> getSent(@Param("angka") Integer angka);
    
    
}
