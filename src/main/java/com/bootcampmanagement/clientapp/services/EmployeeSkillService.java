package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Candidate;
import com.bootcampmanagement.clientapp.models.CandidateSkill;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.EmployeeSkill;
import com.bootcampmanagement.clientapp.models.Sent;
import com.bootcampmanagement.clientapp.models.Skill;
import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeSkillService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/empSkill")
    private String url;
       
    public ResponseData<EmployeeSkill> empSkillId(Integer id){
        ResponseEntity<ResponseData<EmployeeSkill>> response = restTemplate.exchange
            (url + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<EmployeeSkill>>() {});  
        ResponseData<EmployeeSkill> empSkill = response.getBody();  
    return empSkill;
    }
    
    public ResponseMessage save(EmployeeSkill empSkill){
        Employee employee = new Employee();
        employee.setId(empSkill.getEmployee().getId());
        
        Skill skill = new Skill();
        skill.setId(empSkill.getSkill().getId());
        
        EmployeeSkill emp = new EmployeeSkill();
        emp.setId(empSkill.getId());
        emp.setLevel(empSkill.getLevel());
        emp.setDesc(empSkill.getDesc());
        emp.setEmployee(employee);
        emp.setSkill(skill);
        
        ResponseEntity<ResponseMessage<EmployeeSkill>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(emp, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<EmployeeSkill>>() {});        
        return response.getBody();
    }
    
    public ResponseListData<Candidate> listCandidate(String skill, Integer id){
        ResponseEntity<ResponseListData<Candidate>> response = restTemplate.exchange
            (url + "/candidate/" + skill + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Candidate>>() {});  
        ResponseListData<Candidate> cadidates = response.getBody();  
    return cadidates;
    }
    
    public ResponseListData<Candidate> detailCandidate(String skill, Integer id, Integer emp){
        ResponseEntity<ResponseListData<Candidate>> response = restTemplate.exchange
            (url + "/candidateId/" + skill + "/" + id + "/" + emp, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Candidate>>() {});  
        ResponseListData<Candidate> cadidates = response.getBody();  
    return cadidates;
    }
    
    public ResponseListData<Sent> sentCount(Integer id){
        ResponseEntity<ResponseListData<Sent>> response = restTemplate.exchange
            (url + "/sent/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Sent>>() {});  
        ResponseListData<Sent> sent = response.getBody();  
        return sent;
    }
    
    public ResponseListData<CandidateSkill> candidateSkill(Integer id){
        ResponseEntity<ResponseListData<CandidateSkill>> response = restTemplate.exchange
            (url + "/skillSet/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<CandidateSkill>>() {});  
        ResponseListData<CandidateSkill> skill = response.getBody();  
    return skill;
    }
    
    HttpHeaders createHeaders(){
    return new HttpHeaders() {{
        Authentication auths = SecurityContextHolder.getContext().getAuthentication();
        String auth = auths.getName()+ ":" + auths.getCredentials().toString();
        byte[] encodedAuth = Base64.encodeBase64( 
            auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );
        set( "Authorization", authHeader );
        set("Content-Type", "application/json");
    }};
    }
}
