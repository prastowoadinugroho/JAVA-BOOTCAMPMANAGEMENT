package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
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
public class UserRoleService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/userRole")
    private String url;
    
    public ResponseListData<UserRole> findAll(){
        ResponseEntity<ResponseListData<UserRole>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<UserRole>>() {});  
        ResponseListData<UserRole> userRole = response.getBody();  
    return userRole;
    }
    
    public ResponseData<UserRole> getUserRoleById(Integer id){
        ResponseEntity<ResponseData<UserRole>> response = restTemplate.exchange
        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                new ParameterizedTypeReference<ResponseData<UserRole>>() {});
        return response.getBody();
    }
      
    public ResponseMessage save(UserRole userRole){
        User user = new User();
        user.setId(userRole.getUser().getId());
        
        Role role = new Role();
        role.setId(userRole.getRole().getId());
        
        UserRole usr = new UserRole();
        usr.setId(userRole.getId());
        usr.setRole(role);
        usr.setUser(user);
        
        ResponseEntity<ResponseMessage<UserRole>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(usr, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<UserRole>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage delete(Integer id){
        ResponseEntity<ResponseMessage<UserRole>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<UserRole>>() {});        
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
