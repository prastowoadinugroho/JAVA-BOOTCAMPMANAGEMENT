
package com.bootcampmanagement.server.services;


import com.bootcampmanagement.server.entities.Employee;
import com.bootcampmanagement.server.entities.Request;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;
    
    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    public void sendEmail(Employee employee, Request request) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
                
        // From
        helper.setFrom(email);
        // To
        helper.setTo(employee.getEmail());
        // Subject
        helper.setSubject("Interview Information Schedule");
        // Body
        message.setText("<p>Dear " +employee.getName()+ ",</p> "
                + "<p> We want to inform you that you receive this email because you have been selected by your "
                + "Relation Manager to attend an Interview agenda. Please Read the detail Information below:</p><br>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Interview Schdule on&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " +request.getInterviewDate()+ "</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Interview Time at&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: 09.00-12.00 WIB</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Company Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + request.getCustomer().getSite().getName() + "</p>"                        
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Company Address &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + request.getCustomer().getSite().getAddress() + "</p>"
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Interviewer name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " +request.getCustomer().getName()+"</p><br><br>"
                +"<p>Terima Kasih & Best Regard</p>"
                +"<p>Bootcamp Management</p>"
                +"<p>PT. Mitra Integrasi Informatika - member of METRODATA</p>"
                +"<p>APL Tower 18th Fl., Jl. Letjend. S. Parman kav. 28, Jakarta 11470</p>"
                +"<p>Phone 021-29345777 ext 3307 | Fax (62-21) 29345 700 | Website www.mii.co.id</p>"
                
                ,"UTF-8","html");
        
       
        
        
        javaMailSender.send(message);
    }
    
}
