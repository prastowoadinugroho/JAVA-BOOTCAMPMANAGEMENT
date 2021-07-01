package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Role;
import com.bootcampmanagement.clientapp.models.Site;
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
public class RoleService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/role")
    private String url;
    
    public ResponseListData<Role> findAll(){
        ResponseEntity<ResponseListData<Role>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Role>>() {});  
        ResponseListData<Role> role = response.getBody();  
    return role;
    }
    
    public ResponseData<Role> getRoleById(Integer id){
        ResponseEntity<ResponseData<Role>> response = restTemplate.exchange
        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                new ParameterizedTypeReference<ResponseData<Role>>() {});
        return response.getBody();
    }
        
    public ResponseMessage saveRole(Role role){
        ResponseEntity<ResponseMessage<Role>> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(role, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage<Role>>() {});        
        return response.getBody();
    }
    
    public ResponseMessage deleteRole(Integer id){
        ResponseEntity<ResponseMessage<Role>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<Role>>() {});        
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
