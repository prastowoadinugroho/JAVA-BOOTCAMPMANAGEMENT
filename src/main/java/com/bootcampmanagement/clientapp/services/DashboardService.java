package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.ResponseData;
import com.bootcampmanagement.clientapp.models.Dashboard;
import com.bootcampmanagement.clientapp.models.Developer;
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
public class DashboardService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/bootcamp-management/employee")
    private String url;
//    
    @Value("http://localhost:8091/bootcamp-management/request")
    private String url2;
    
    @Value("http://localhost:8091/bootcamp-management/dashboardAdmin")
    private String url3;
    
    public ResponseData<Dashboard> rejectSkillJava(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejectedSkill", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> rejectSkillPython(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejectedSkill1", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> rejectSkillPhp(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejectedSkill2", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> rejectSkillReact(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejectedSkill3", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> rejectSkillNet(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejectedSkill4", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> employeeAvail(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/totalEmpAvai", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> employeeNotAvail(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/totalEmpNotAvai", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> employeeOnSite(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/totalEmpOnSite", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> employeeSkillJava(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/java", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> employeeSkillPython(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/python", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> employeeSkillReact(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/react", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> employeeSkillPhp(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/php", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> employeeSkillNet(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url + "/.net", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> allRequest(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalRequest", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> needActionRequest(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusNull", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> undoneRequest(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusUndone", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> doneRequest(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusDone", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    public ResponseData<Dashboard> rejectedRequest(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url2 + "/totalStatusRejected", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> employeeAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalEmp", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> classAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalClass", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> userAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalUser", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> siteAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalSite", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> empDevAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalEmpDev", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
        return user;
    }
    
    public ResponseData<Dashboard> empTrainAll(){
        ResponseEntity<ResponseData<Dashboard>> response = restTemplate.exchange
            (url3 + "/totalEmpTrainer", HttpMethod.GET, new HttpEntity(createHeaders()), 
                    new ParameterizedTypeReference<ResponseData<Dashboard>>() {});  
        ResponseData<Dashboard> user = response.getBody();  
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
