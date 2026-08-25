 package org.saravanakumar.Skloora_Mail_Service.Mailings;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

 @Service
 public class MailingService {

     @Autowired
     protected JavaMailSender sender;

     @Value("${spring.mail.host}")
     private String host;

     @Value("${spring.mail.port}")
     private int port;

     @PostConstruct
     public void init() {
         System.out.println("Mail Host = " + host);
         System.out.println("Mail Port = " + port);
     }

     public void Signup(String email) {

         SimpleMailMessage message = new SimpleMailMessage();

         message.setTo(email);

         message.setSubject("Signup alert!");

         message.setText("""
Welcome to Skloora!

We're delighted to let you know that your Skloora account has been created successfully.

You can now connect with others, share your ideas, and explore everything Skloora has to offer.

Thank you for choosing Skloora. We're excited to have you as part of our community!

- The Skloora Team
""");

         sender.send(message);
     }

     public void Login(String email)
     {

         SimpleMailMessage message = new SimpleMailMessage();

         message.setTo(email);

         message.setSubject("Security Alert!");

         message.setText("""
A new login to your Skloora account has been detected.

If this was you, no action is required.

If you do not recognize this activity, please change your password immediately and review your account security.

Thank you for helping us keep your account secure.

- The Skloora Security Team
""");

         sender.send(message);

     }


     public void ChangePassword(String email)
     {

         SimpleMailMessage message = new SimpleMailMessage();

         message.setSubject("Password Changed Successfully");

         message.setTo(email);

         message.setText("""
Your Skloora account password has been changed successfully.

If you made this change, no further action is required.

If you did not change your password, your account may be at risk. Please reset your password immediately and contact the Skloora Support Team.

Thank you for helping us keep your account secure.

- The Skloora Security Team
""");

         sender.send(message);

     }

     public void ForgotPassword(String email, String payload)
     {
         SimpleMailMessage msg = new SimpleMailMessage();

         msg.setTo(email);
         msg.setSubject("Password Recovery");

         msg.setText("""
Hello,

We received a request to reset the password for your Skloora account.

Your new temporary password is:

%s

Please use this password to sign in to your account. For your security, we strongly recommend changing your password immediately after logging in.

If you did not request this password reset, please contact the Skloora Support Team immediately.

Thank you for choosing Skloora.

- The Skloora Security Team
""".formatted(payload));

         sender.send(msg);
     }

     public void ChangePasswordOTP(String email, String otp)
     {
         SimpleMailMessage message = new SimpleMailMessage();

         message.setTo(email);
         message.setSubject("Skloora - Change Password OTP");

         message.setText(
                 "Your Skloora password change OTP is: " + otp +
                         "\n\nThis OTP is valid for a limited time." +
                         "\n\nIf you did not request a password change, please ignore this email."
         );

         sender.send(message);
     }

 }