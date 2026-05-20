package org.newfort.sk.Skloora.Services;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.ForgotPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private JavaMailSender sender;


    @Autowired
    private ForgotPasswordRepo fsp;

    public String Forgot(String email) throws SQLException
    {
        String result = fsp.Forgot(email);

        if(result == "Failed to change the password")
        {
            return "Failed to change the password";
        }
        else
        {
            SimpleMailMessage message = new SimpleMailMessage();
            
            message.setTo(email);
            message.setSubject("Forgot password recovery");
            message.setText(result);

            sender.send(message);

            return "Check your mail";
        }
   
    }

}
