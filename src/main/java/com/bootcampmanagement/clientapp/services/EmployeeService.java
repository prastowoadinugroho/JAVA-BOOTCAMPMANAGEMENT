package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Classes;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Job;
import com.bootcampmanagement.clientapp.models.JobTrainer;
import com.bootcampmanagement.clientapp.models.Role;
import com.bootcampmanagement.clientapp.models.User;
import com.bootcampmanagement.clientapp.models.UserRole;
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
public class EmployeeService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/employee")
    private String url;
    
    public ResponseListData<Employee> findAll(){
        ResponseEntity<ResponseListData<Employee>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Employee>>() {});  
        ResponseListData<Employee> employee = response.getBody();  
    return employee;
    }
    
    public ResponseData<Employee> detail(Integer id){
        ResponseEntity<ResponseData<Employee>> response = restTemplate.exchange
            (url + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Employee>>() {});  
        ResponseData<Employee> employee = response.getBody();  
    return employee;
    }
    
    public ResponseMessage save(Employee employee){
        Classes classes = new Classes();
        classes.setId(employee.getClasses().getId());
        
        Job job = new Job();
        job.setId(employee.getJob().getId());
        
        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        emp.setAddress(employee.getAddress());
        emp.setPhoneNumber(employee.getPhoneNumber());
        emp.setJobStatus(employee.getJobStatus());
        emp.setClasses(classes);
        emp.setJob(job);
        
        ResponseEntity<ResponseMessage<Employee>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(emp, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<Employee>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage delete(Integer id){
        ResponseEntity<ResponseMessage<Employee>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<Employee>>() {});        
        return response.getBody();
    }
    
    public ResponseListData<JobTrainer> listTrainer(){
        ResponseEntity<ResponseListData<JobTrainer>> response = restTemplate.exchange
            (url + "/jobTrainer", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<JobTrainer>>() {});  
        ResponseListData<JobTrainer> jobTrain = response.getBody();  
    return jobTrain;
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
