package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
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
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/user")
    private String url;
    
    public ResponseListData<User> findAll(){
        ResponseEntity<ResponseListData<User>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<User>>() {});  
        ResponseListData<User> user = response.getBody();  
    return user;
    }
    
    public ResponseMessage save(User user){
        ResponseEntity<ResponseMessage> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(user, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage>() {});        
        return response.getBody();
    }
    
    public ResponseMessage deleteUser(Integer id){
        ResponseEntity<ResponseMessage<User>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<User>>() {});        
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
