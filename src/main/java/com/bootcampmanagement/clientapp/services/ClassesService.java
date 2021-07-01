package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Classes;
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
public class ClassesService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/class")
    private String url;
    
    public ResponseListData<Classes> findAll(){
        ResponseEntity<ResponseListData<Classes>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Classes>>() {});  
        ResponseListData<Classes> classes = response.getBody();  
    return classes;
    }
    
    public ResponseData<Classes> getClassById(Integer id){
        ResponseEntity<ResponseData<Classes>> response = restTemplate.exchange(url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), new ParameterizedTypeReference<ResponseData<Classes>>() {});
        return response.getBody();
    }
        
    public ResponseMessage saveClasses(Classes classes){
        ResponseEntity<ResponseMessage<Classes>> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(classes, createHeaders()), new ParameterizedTypeReference<ResponseMessage<Classes>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage deleteClass(Integer id){
        ResponseEntity<ResponseMessage<Classes>> response = restTemplate.exchange(url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()), new ParameterizedTypeReference<ResponseMessage<Classes>>() {});        
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

