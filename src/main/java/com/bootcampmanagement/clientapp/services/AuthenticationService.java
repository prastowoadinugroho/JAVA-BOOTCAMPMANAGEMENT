package com.bootcampmanagement.clientapp.services;

import com.bootcampmanagement.clientapp.basic.models.LogoutResponse;
import com.bootcampmanagement.clientapp.basic.models.AuthenticationRequest;
import com.bootcampmanagement.clientapp.basic.models.AuthenticationToken;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService { 
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:8091/login")
    private String url;
    
    @Value("http://localhost:8091/logout")
    private String urlLogout;
    
    public AuthenticationToken login(AuthenticationRequest request){
        ResponseEntity<AuthenticationToken> req = 
        restTemplate.postForEntity(url, request, AuthenticationToken.class);
        
        AuthenticationToken auts = req.getBody();
        List<GrantedAuthority> authorities = new ArrayList<>();
       
        auts.getAuthorities().forEach(action -> {
            authorities.add(new SimpleGrantedAuthority(action));
        });
            
        if(req.getStatusCodeValue() == 200){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            request.getUsername(),request.getPassword(),authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return req.getBody();
    }
    
    public LogoutResponse logout(){
        ResponseEntity<LogoutResponse> req = 
        restTemplate.exchange(urlLogout, HttpMethod.POST, 
                new HttpEntity<>(createHeaders()), 
                new ParameterizedTypeReference<LogoutResponse>() {});
        SecurityContextHolder.getContext().setAuthentication(null);
        return req.getBody();
    }

    HttpHeaders createHeaders(){
    return new HttpHeaders() {{
        Authentication auths = SecurityContextHolder.getContext().getAuthentication();
        String auth = auths.getName()+ ":" + auths.getCredentials().toString();    
        byte[] encodedAuth = Base64.encodeBase64( 
           auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        set( "Authorization", authHeader );
    }};
    }
}
