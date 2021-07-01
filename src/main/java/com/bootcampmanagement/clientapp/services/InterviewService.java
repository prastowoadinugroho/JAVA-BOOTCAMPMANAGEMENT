package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Interview;
import com.bootcampmanagement.clientapp.models.Request;
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
public class InterviewService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/interview")
    private String url;
    
    public ResponseListData<Interview> findAll(){
        ResponseEntity<ResponseListData<Interview>> response = restTemplate.exchange
            (url + "/list", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Interview>>() {});  
        ResponseListData<Interview> interview = response.getBody();  
    return interview;
    }
    
    public ResponseListData<Interview> employeeQualified(){
        ResponseEntity<ResponseListData<Interview>> response = restTemplate.exchange
            (url + "/employee/qualified", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Interview>>() {});  
        ResponseListData<Interview> interview = response.getBody();  
    return interview;
    }
    
    public ResponseData<Interview> employeeQualifiedById(Integer id){
        ResponseEntity<ResponseData<Interview>> response = restTemplate.exchange
            (url + "/empQualified/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Interview>>() {});  
        ResponseData<Interview> interview = response.getBody();  
    return interview;
    }
    
    public ResponseData<Interview> findById(Integer id){
        ResponseEntity<ResponseData<Interview>> response = restTemplate.exchange
            (url + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Interview>>() {});  
        ResponseData<Interview> interview = response.getBody();  
    return interview;
    }
    
    public ResponseMessage save(Interview interview){
        Employee employee = new Employee();
        employee.setId(interview.getEmployee().getId());
        
        Request request = new Request();
        request.setId(interview.getRequest().getId());
        
        Interview inter = new Interview();
        inter.setId(interview.getId());
        inter.setStatus(interview.getStatus());
        inter.setEmployee(employee);
        inter.setRequest(request);
        
        ResponseEntity<ResponseMessage<Interview>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(inter, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<Interview>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage<Interview> qualifiedInterview(Interview interview){
        ResponseEntity<ResponseMessage<Interview>> response = restTemplate.exchange
            (url + "/statY", HttpMethod.POST, new HttpEntity(interview,createHeaders()), 
                    new ParameterizedTypeReference<ResponseMessage<Interview>>() {});  
        ResponseMessage<Interview> inter = response.getBody();  
        return inter;
    }
    
    public ResponseMessage<Interview> unqualifiedInterview(Integer id){
        ResponseEntity<ResponseMessage<Interview>> response = restTemplate.exchange
            (url + "/statN/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseMessage<Interview>>() {});  
        ResponseMessage<Interview> interview = response.getBody();  
        return interview;
    }
    
//    public ResponseData<Skill> getSkillById(Integer id){
//        ResponseEntity<ResponseData<Skill>> response = restTemplate.exchange
//        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
//                new ParameterizedTypeReference<ResponseData<Skill>>() {});
//        return response.getBody();
//    }
//        
//    public ResponseMessage saveSkill(Skill skill){
//        ResponseEntity<ResponseMessage<Skill>> response = restTemplate.exchange
//        (url, HttpMethod.POST, new HttpEntity(skill, createHeaders()), 
//                new ParameterizedTypeReference<ResponseMessage<Skill>>() {});        
//        return response.getBody();
//    }
//    
//    public ResponseMessage deleteSkill(Integer id){
//        ResponseEntity<ResponseMessage<Skill>> response = restTemplate.exchange
//        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
//                new ParameterizedTypeReference<ResponseMessage<Skill>>() {});        
//        return response.getBody();
//    }
    
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
