package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.entities.User;
import com.bootcampmanagement.server.entities.MyUserDetail;
import com.bootcampmanagement.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new MyUserDetail(user);
    }
    
}
