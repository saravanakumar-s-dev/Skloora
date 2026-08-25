package org.saravanakumar.Skloora_Mail_Service.Mailings;

import org.saravanakumar.Skloora_Mail_Service.Mailings.AESDecryptor;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.ChangePasswordPayload;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.ForgotPasswordPayload;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.Mail;
import org.saravanakumar.Skloora_Mail_Service.Mailings.MailingRepo;
import org.saravanakumar.Skloora_Mail_Service.Mailings.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class MailingExecutor {

    @Autowired
    AESDecryptor decryptor;

    private final MailingService mailingService;
    private final MailingRepo mailingRepo;

    public MailingExecutor(
            MailingService mailingService,
            MailingRepo mailingRepo
    ) {
        this.mailingService = mailingService;
        this.mailingRepo = mailingRepo;
    }

    @Scheduled(fixedRate = 25000)
    public void SignupMailExecute() {

        System.out.println("Signup Mailing executor started...");

        try {

            // Get all pending Signup emails
            List<Mail> mailList = mailingRepo.Signup();

            System.out.println(
                    "Signup Pending signup mails: " + mailList.size()
            );

            // Send email to each person
            for (Mail mail : mailList) {

                String email = mail.getEmail();

                try {

                    // Send email
                    mailingService.Signup(email);

                    // If sending succeeds, mark as Sent
                    mailingRepo.Update(
                            mail.getOpertationdone(),
                            email
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Failed to send Signup mail to: " + email
                    );

                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to fetch Signup pending mails"
            );

            e.printStackTrace();
        }

        System.out.println(
                "Signup Mailing executor completed..."
        );
    }

    @Scheduled(fixedRate = 25000)
    public void Login() {

        System.out.println("Login Mailing executor started...");

        try {

            // Get all pending Login emails
            List<Mail> mailList = mailingRepo.Login();

            System.out.println(
                    "Pending login mails: " + mailList.size()
            );

            // Send email to each person
            for (Mail mail : mailList) {

                String email = mail.getEmail();

                try {

                    // Send login alert email
                    mailingService.Login(email);

                    // Mark as sent
                    mailingRepo.Update(
                            mail.getOpertationdone(),
                            email
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Failed to send login mail to: " + email
                    );

                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to fetch pending login mails"
            );

            e.printStackTrace();
        }

        System.out.println(
                "Login Mailing executor completed..."
        );
    }

    @Scheduled(fixedRate = 10000)
    public void ChangePassword() throws Exception {

        System.out.println("Password Change executor started");

        try {

            List<Mail> mailList = mailingRepo.ChangePassword();

            System.out.println(
                    "Pending ChangePassword mails: " + mailList.size()
            );

            for (Mail mail : mailList) {

                String email = mail.getEmail();

                try {

                    mailingService.ChangePassword(email);

                    mailingRepo.Update(
                            mail.getOpertationdone(),
                            email
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Failed to send ChangePassword mail to: " + email
                    );

                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {

            System.out.println("Failed to fetch pending ChangePassword mails");
            e.printStackTrace();
        }

        System.out.println("ChangePassword Mailing executor completed...");
    }

    @Scheduled(fixedRate = 10000)
public void ForgotPassword() throws Exception {

    System.out.println("Forgot password executor started");

    try {

        List<ForgotPasswordPayload> list = mailingRepo.ForgotPassword();

        System.out.println(
                "Pending ForgotPassword mails: " + list.size()
        );

        for (ForgotPasswordPayload payload : list) {

            String data = decryptor.decrypt(payload.getPayload());
            String email = payload.getEmail();

            try {

                mailingService.ForgotPassword(email, data);

                // Mark this request as Sent
                mailingRepo.ForgotPasswordUpdate(email);

                System.out.println("Forgot password mail sent to " + email);

            } catch (Exception e) {

                System.out.println(
                        "Failed to send forgot password mail to: " + email
                );

                e.printStackTrace();
            }
        }

    } catch (Exception e) {

        System.out.println("Failed to process forgot password mails");
        e.printStackTrace();
    }

    System.out.println("Forgot password executor completed");
}

    @Scheduled(fixedRate = 10000)
    public void ChangePasswordOTP() {

        System.out.println(
                "Change Password OTP executor started"
        );

        try {

            List<ChangePasswordPayload> list =
                    mailingRepo.ChangePasswordOTP();

            System.out.println(
                    "Pending ChangePassword OTP mails: "
                            + list.size()
            );

            for (ChangePasswordPayload payload : list) {

                String email = payload.getEmail();

                try {

                    String otp = decryptor.decrypt(
                            payload.getEncryptedOtp()
                    );

                    mailingService.ChangePasswordOTP(
                            email,
                            otp
                    );

                    mailingRepo.ChangePasswordOTPUpdate(
                            email
                    );

                    System.out.println(
                            "Change password OTP sent to "
                                    + email
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Failed to send ChangePassword OTP to "
                                    + email
                    );

                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to process ChangePassword OTP mails"
            );

            e.printStackTrace();
        }

        System.out.println(
                "Change Password OTP executor completed"
        );
    }
}
