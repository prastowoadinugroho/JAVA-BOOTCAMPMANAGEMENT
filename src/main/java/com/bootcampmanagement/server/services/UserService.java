package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.entities.User;
import com.bootcampmanagement.server.repositories.UserRepository;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    UserService us;
    
    @Autowired
    UserCreatedNotificationService userCreatedService;
    
    @Autowired
    EmployeeService empService;
       
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> getAll(){
        return userRepo.findAll();
    }
    
    public User findById(Integer id){
        return userRepo.findById(id).get();
    }
    
    public User findByUserName(String username){
        return userRepo.findByUsername(username).get();
    }
    
    
    public User addUser(User user) throws MessagingException{
        Integer id = user.getId();
        String username = user.getUsername();
        String password = passwordEncoder.encode("bo07c@mp");
        userRepo.addUser(id,username, password);
        User users = us.findById(id);
        
        Employee employee = empService.findById(id);
        userCreatedService.sendEmail(employee,users);
        return user;
    }
    
    public User editUsername(User user, Integer id) {
        String username = user.getUsername();
        userRepo.editUsername(username, id);
        
        return user;
    }
    
    public User deleteUser(Integer id){
        User user = userRepo.findById(id).get();
        userRepo.deleteById(id);
        return user;
    }

}
