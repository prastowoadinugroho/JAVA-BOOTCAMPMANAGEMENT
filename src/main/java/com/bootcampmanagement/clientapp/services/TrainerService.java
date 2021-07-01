package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Classes;
import com.bootcampmanagement.clientapp.models.Customer;
import com.bootcampmanagement.clientapp.models.Employee;
import com.bootcampmanagement.clientapp.models.Request;
import com.bootcampmanagement.clientapp.models.Skill;
import com.bootcampmanagement.clientapp.models.Trainer;
import com.bootcampmanagement.clientapp.models.User;
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
public class TrainerService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/class-trainer")
    private String url;
    
    public ResponseListData<Trainer> findAll(){
        ResponseEntity<ResponseListData<Trainer>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Trainer>>() {});  
        ResponseListData<Trainer> trainer = response.getBody();  
    return trainer;
    }
    
    public ResponseData<Trainer> getTrainerById(Integer id){
        ResponseEntity<ResponseData<Trainer>> response = restTemplate.exchange
        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                new ParameterizedTypeReference<ResponseData<Trainer>>() {});
        return response.getBody();
    }
      
    
    public ResponseMessage saveTrainer(Trainer trainer){
        Employee employee = new Employee();
        employee.setId(trainer.getEmployee().getId());
        
        Classes classes = new Classes();
        classes.setId(trainer.getClasses().getId());
        
        Trainer train = new Trainer();
        train.setId(trainer.getId());
        train.setClasses(classes);
        train.setEmployee(employee);
        
        ResponseEntity<ResponseMessage<Trainer>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(train, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<Trainer>>() {});        
        return response.getBody();
        
    }
    
    public ResponseMessage deleteTrainer(Integer id){
        ResponseEntity<ResponseMessage<Trainer>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<Trainer>>() {});        
        return response.getBody();
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
