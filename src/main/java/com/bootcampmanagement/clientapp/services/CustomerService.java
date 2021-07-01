package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Customer;
import com.bootcampmanagement.clientapp.models.Job;
import com.bootcampmanagement.clientapp.models.Request;
import com.bootcampmanagement.clientapp.models.Site;
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
public class CustomerService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/customer")
    private String url;
    
    public ResponseListData<Customer> findAll(){
        ResponseEntity<ResponseListData<Customer>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Customer>>() {});  
        ResponseListData<Customer> customer = response.getBody();  
    return customer;
    }
    
    public ResponseData<Customer> getCustomer(Integer id){
        ResponseEntity<ResponseData<Customer>> response = restTemplate.exchange
        (url+"/"+id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                new ParameterizedTypeReference<ResponseData<Customer>>() {});
        return response.getBody();
    }
    
    public ResponseMessage save(Customer customer){
        
        Site site = new Site();
        site.setId(customer.getSite().getId());
        
        Customer cst = new Customer();
        cst.setId(customer.getId());
        cst.setName(customer.getName());
        cst.setPosition(customer.getPosition());
        cst.setSite(site);

        ResponseEntity<ResponseMessage> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(cst, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage>() {});        
        return response.getBody();
        
    }
    
    public ResponseMessage deleteCustomer(Integer id){
        ResponseEntity<ResponseMessage<Customer>> response = restTemplate.exchange
        (url +"/"+ id, HttpMethod.DELETE, new HttpEntity(createHeaders()),
                new ParameterizedTypeReference<ResponseMessage<Customer>>() {});        
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
