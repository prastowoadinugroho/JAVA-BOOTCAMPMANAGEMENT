package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.entities.Request;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserHiredNotificationService {
    
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    public UserHiredNotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    public void sendEmail(Employee employee, Request request) throws MessagingException{
//        SimpleMailMessage smm = new SimpleMailMessage();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
                
        // From
        helper.setFrom(email);
        // To
        helper.setTo(employee.getEmail());
        // Subject
        helper.setSubject("Passed On Interview");
        // Body
        message.setText("<p>Dear " + employee.getName() + ",</p>"
                + "<p> we want to inform you regarding the result of your Interview test that you have at:" + request.getCustomer().getSite().getName() +"</p>"
                + "<p> The result of your Interview is:</p><br>"                        
                + "<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QUALIFIED&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1><br>"
                + "<p>We Want to Congratulate you and wish you Good Luck in your next Journey.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>"
                              
                +"<p>Terima Kasih & Best Regard</p>"
                +"<p>Bootcamp Management</p>"
                +"<p>PT. Mitra Integrasi Informatika - member of METRODATA</p>"
                +"<p>APL Tower 18th Fl., Jl. Letjend. S. Parman kav. 28, Jakarta 11470</p>"
                +"<p>Phone 021-29345777 ext 3307 | Fax (62-21) 29345 700 | Website www.mii.co.id</p>"
                
                ,"UTF-8","html");
        
       
        
        
        javaMailSender.send(message);
    }
}
