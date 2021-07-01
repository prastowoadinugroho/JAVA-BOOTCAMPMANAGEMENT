package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.models.CandidateSkill;
import com.bootcampmanagement.clientapp.models.Developer;
import com.bootcampmanagement.clientapp.models.Interview;
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
public class DeveloperService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/user")
    private String url;
    
    @Value("http://localhost:8091/bootcamp-management/interview")
    private String url2;
    
    @Value("http://localhost:8091/bootcamp-management/empSkill")
    private String url3;
    
    public ResponseData<Developer> detailUser(String username){
        ResponseEntity<ResponseData<Developer>> response = restTemplate.exchange
            (url + "/" + username, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Developer>>() {});  
        ResponseData<Developer> user = response.getBody();  
        return user;
    }
    
    public ResponseListData<Interview> historyInterview(String username){
        ResponseEntity<ResponseListData<Interview>> response = restTemplate.exchange
            (url2 + "/emp/" + username, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Interview>>() {});  
        ResponseListData<Interview> user = response.getBody();  
        return user;
    }
    
    public ResponseListData<CandidateSkill> skill(String username){
        ResponseEntity<ResponseListData<CandidateSkill>> response = restTemplate.exchange
            (url3 + "/skillSetName/" + username, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<CandidateSkill>>() {});  
        ResponseListData<CandidateSkill> skills = response.getBody();  
        return skills;
    }
    
    public ResponseData<Interview> resultInterview(String username){
        ResponseEntity<ResponseData<Interview>> response = restTemplate.exchange
            (url2 + "/empLatest/" + username, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Interview>>() {});  
        ResponseData<Interview> user = response.getBody();  
        return user;
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
