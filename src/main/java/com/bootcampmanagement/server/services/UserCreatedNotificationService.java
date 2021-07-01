package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.entities.User;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedNotificationService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    public UserCreatedNotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Employee employee, User user) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
              
        // From
        helper.setFrom(email);
        // To
        helper.setTo(employee.getEmail());
        // Subject
        helper.setSubject("User Account Information");
        // Body
        message.setText("<p>Dear " + employee.getName() + ",</p>"
                + "<p> We want to inform you that you receive this email because Admin Has Created an Account for you. Please Read the detail Information below:</p><br>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Account Detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Username&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:" + user.getUsername() + "</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: bo07c@mp</p><br>"                
                +"<p>Terima Kasih & Best Regard</p>"
                +"<p>Bootcamp Management</p>"
                +"<p>PT. Mitra Integrasi Informatika - member of METRODATA</p>"
                +"<p>APL Tower 18th Fl., Jl. Letjend. S. Parman kav. 28, Jakarta 11470</p>"
                +"<p>Phone 021-29345777 ext 3307 | Fax (62-21) 29345 700 | Website www.mii.co.id</p>"
                
                ,"UTF-8","html");

        javaMailSender.send(message);
    }
    
}
