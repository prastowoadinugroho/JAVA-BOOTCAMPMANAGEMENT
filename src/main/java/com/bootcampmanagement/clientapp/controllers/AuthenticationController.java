package com.bootcampmanagement.clientapp.controllers;

import com.bootcampmanagement.clientapp.basic.models.AuthenticationRequest;
import com.bootcampmanagement.clientapp.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class AuthenticationController {
    
    @Autowired
    AuthenticationService service;

    @GetMapping("")
    public String index(Model model){
        return "index";
    }
    
    @GetMapping("login")
    public String loginPage(Model model){
        return "login";
    }
    
    @PostMapping("check-login")
    public String login(AuthenticationRequest request) throws JsonProcessingException {
        try {
            service.login(request);
            return "redirect:/bootcamp-management";
        } catch (HttpClientErrorException e) {
            int response = e.getRawStatusCode();
            switch (response) {
                case 401:
                    return "redirect:/login?error";
                default:
                    return "redirect:/login?error";
            }
        }

    }
    
    @PostMapping("logout")
    public String logout(){
        service.logout();
        return "redirect:/";
    }
    
}
