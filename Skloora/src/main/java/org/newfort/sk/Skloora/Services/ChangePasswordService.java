package org.newfort.sk.Skloora.Services;

import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.ChangePasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private ChangePasswordRepo cpr;

    @Autowired
    private JavaMailSender sender;

    public String ChangePassword(String email, String password) throws SQLException
    {
        if(cpr.ChangePassword(email, password))
        {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("Security Alert!");
            message.setText("We have found that a your password was changed suspiciously. If it was you ignore this mail if not immediately change your password!");

            sender.send(message);

            return "Successfully changed the password";
            
        }
        else
        {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("Security Alert!");
            message.setText("We have found that a your password was suspiciously tried to change but failed due to a system error. If it was you ignore this mail if not immediately change your password!");

            sender.send(message);

            return "Failed to change tje password";
        }
    }

}
