package com.bootcampmanagement.server.controllers;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.MyUserDetail;
import com.bootcampmanagement.server.services.MyUserDetailService;
import com.bootcampmanagement.server.basic.models.AuthenticationRequest;
import com.bootcampmanagement.server.basic.models.AuthenticationToken;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;
    
    @Autowired
    private MyUserDetailService service;
    
    @PostMapping("login")
    public AuthenticationToken login (HttpServletRequest request,
    @RequestBody AuthenticationRequest authenticationRequest) {
        MyUserDetail user = (MyUserDetail) service.loadUserByUsername(authenticationRequest.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authentication = authManager.authenticate(token);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> authorities = new ArrayList<>();

        for (GrantedAuthority authority : user.getAuthorities()) {
                authorities.add(authority.toString());
        }
        
        return new AuthenticationToken(user.getUsername(),authorities);
    } 
    
    @PostMapping("logout")
    public ResponseMessage logout (HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseMessage(null, "logout success");
    }
    
}
