package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
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
public class SkillService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/skill")
    private String url;
    
    public ResponseListData<Skill> findAll(){
        ResponseEntity<ResponseListData<Skill>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Skill>>() {});  
        ResponseListData<Skill> skill = response.getBody();  
    return skill;
    }
    
    public ResponseData<Skill> getSkillById(Integer id){
        ResponseEntity<ResponseData<Skill>> response = restTemplate.exchange
        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                new ParameterizedTypeReference<ResponseData<Skill>>() {});
        return response.getBody();
    }
        
    public ResponseMessage saveSkill(Skill skill){
        ResponseEntity<ResponseMessage<Skill>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(skill, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<Skill>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage deleteSkill(Integer id){
        ResponseEntity<ResponseMessage<Skill>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<Skill>>() {});        
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
