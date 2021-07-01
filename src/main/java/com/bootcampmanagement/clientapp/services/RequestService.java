package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.basic.models.ResponseListData;
import com.bootcampmanagement.clientapp.basic.models.ResponseMessage;
import com.bootcampmanagement.clientapp.models.Customer;
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
public class RequestService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/request")
    private String url;
    
    public ResponseListData<Request> findAll(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseListData<Request> RequestNull(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url + "/status/null", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseListData<Request> RequestDone(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url + "/status/acceptedDone", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    public ResponseListData<Request> requestRejected(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url + "/status/rejected", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseData<Request> findById(Integer id){
        ResponseEntity<ResponseData<Request>> response = restTemplate.exchange
            (url + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Request>>() {});  
        ResponseData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseMessage save(Request request){
        
        Customer customer = new Customer();
        customer.setId(request.getCustomer().getId());
        
        Skill skill = new Skill();
        skill.setId(request.getSkill().getId());
        
        Request req = new Request();
        req.setId(request.getId());
        req.setInterviewDate(request.getInterviewDate());
        req.setCandidateNeeded(request.getCandidateNeeded());
        req.setCustomer(customer);
        req.setSkill(skill);
 
        ResponseEntity<ResponseMessage> response = restTemplate.exchange
        (url, HttpMethod.POST, new HttpEntity(req, createHeaders()), 
                new ParameterizedTypeReference<ResponseMessage>() {});        
        return response.getBody();
        
    }
    
    public ResponseData<Request> detailRequest(Integer id){
        ResponseEntity<ResponseData<Request>> response = restTemplate.exchange
            (url + "/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Request>>() {});  
        ResponseData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseMessage<Request> statDone(Integer id){
        ResponseEntity<ResponseMessage<Request>> response = restTemplate.exchange
            (url + "/statDone/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseMessage<Request>>() {});  
        ResponseMessage<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseListData<Request> listRequest(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url + "/status", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    public ResponseListData<Request> listAcceptRequest(){
        ResponseEntity<ResponseListData<Request>> response = restTemplate.exchange
            (url + "/status/accepted", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseListData<Request>>() {});  
        ResponseListData<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseMessage<Request> acceptRequest(Integer id){
        ResponseEntity<ResponseMessage<Request>> response = restTemplate.exchange
            (url + "/statAcc/" + id, HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseMessage<Request>>() {});  
        ResponseMessage<Request> requests = response.getBody();  
        return requests;
    }
    
    public ResponseMessage<Request> rejectRequest(Request request){
        ResponseEntity<ResponseMessage<Request>> response = restTemplate.exchange
            (url + "/statNo", HttpMethod.POST, new HttpEntity(request,createHeaders()), 
                    new ParameterizedTypeReference<ResponseMessage<Request>>() {});  
        ResponseMessage<Request> requests = response.getBody();  
        return requests;
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
