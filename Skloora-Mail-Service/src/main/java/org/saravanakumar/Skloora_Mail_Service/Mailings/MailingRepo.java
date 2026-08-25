package org.saravanakumar.Skloora_Mail_Service.Mailings;

import org.saravanakumar.Skloora_Mail_Service.Mailings.DBPool;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.ChangePasswordPayload;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.ForgotPasswordPayload;
import org.saravanakumar.Skloora_Mail_Service.Mailings.Model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MailingRepo {

    @Autowired
    private DBPool pool;

    public void Update(String operation, String email) throws SQLException {

        String sql = """
                UPDATE skloora.pendingmails
                SET status = 'Sent'
                WHERE email = ?
                  AND operationdone = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, email);
            pst.setString(2, operation);

            int count = pst.executeUpdate();

            if (count > 0) {
                System.out.println(
                        "Mailing Done To " + email
                                + " -> " + LocalDateTime.now()
                );
            } else {
                System.out.println("Failed to update " + email);
            }
        }
    }

    public void ForgotPasswordUpdate(String email)throws SQLException
        {

             String sql = """
                UPDATE skloora.forgotpasswordpayloads
                SET status = 'Sent'
                WHERE email = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, email);

            int count = pst.executeUpdate();

            if (count > 0) {
                System.out.println(
                        "Mailing Done To " + email
                                + " -> " + LocalDateTime.now()
                );
            } else {
                System.out.println("Failed to update " + email);
            }
        }

        }
    

    public List<Mail> Signup() throws SQLException {

        List<Mail> mailList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM skloora.pendingmails
                WHERE operationdone = ?
                  AND status = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, "Signup");
            pst.setString(2, "Pending");

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    Mail mail = new Mail();

                    mail.setEmail(rs.getString("email"));
                    mail.setOpertationdone(
                            rs.getString("operationdone")
                    );
                    mail.setStatus(
                            rs.getString("status")
                    );

                    // Add the mail object to the List
                    mailList.add(mail);
                }
            }
        }

        return mailList;
    }

    public  List<Mail> Login() throws SQLException
    {
        List<Mail> mailList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM skloora.pendingmails
                WHERE operationdone = ?
                  AND status = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, "Login");
            pst.setString(2, "Pending");

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    Mail mail = new Mail();

                    mail.setEmail(rs.getString("email"));
                    mail.setOpertationdone(
                            rs.getString("operationdone")
                    );
                    mail.setStatus(
                            rs.getString("status")
                    );

                    // Add the mail object to the List
                    mailList.add(mail);
                }
            }
        }

        return mailList;
    }

    public  List<Mail> ChangePassword() throws Exception
    {
        List<Mail> mailList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM skloora.pendingmails
                WHERE operationdone = ?
                  AND status = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, "PasswordChange");
            pst.setString(2, "Pending");

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    Mail mail = new Mail();

                    mail.setEmail(rs.getString("email"));
                    mail.setOpertationdone(
                            rs.getString("operationdone")
                    );
                    mail.setStatus(
                            rs.getString("status")
                    );

                    // Add the mail object to the List
                    mailList.add(mail);
                }
            }
        }

        return mailList;
    }

    public List<ChangePasswordPayload> ChangePasswordOTP() throws SQLException {

        List<ChangePasswordPayload> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM skloora.changepasswordpayloads
            WHERE status = ?
            """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, "Pending");

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    ChangePasswordPayload payload =
                            new ChangePasswordPayload();

                    payload.setEmail(
                            rs.getString("email")
                    );

                    payload.setEncryptedOtp(
                            rs.getString("payload")
                    );

                    list.add(payload);
                }
            }
        }

        return list;
    }

    public void ChangePasswordOTPUpdate(String email)
            throws SQLException {

        String sql = """
            UPDATE skloora.changepasswordpayloads
            SET status = 'Sent'
            WHERE email = ?
              AND status = 'Pending'
            """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, email);

            int count = pst.executeUpdate();

            if (count > 0) {

                System.out.println(
                        "Change Password OTP marked as Sent for "
                                + email
                );

            } else {

                System.out.println(
                        "Failed to update OTP status for "
                                + email
                );
            }
        }
    }

    public List<ForgotPasswordPayload> ForgotPassword()throws SQLException
    {

        List<ForgotPasswordPayload> list = new ArrayList<>();

        try(Connection con = pool.ds.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.forgotpasswordpayloads WHERE status = ?")){

            pst.setString(1, "Pending");

            try(ResultSet rs = pst.executeQuery())
            {
                while(rs.next())
                {
                    ForgotPasswordPayload payload = new ForgotPasswordPayload();

                    payload.setEmail(rs.getString(1));
                    payload.setPayload(rs.getString(2));
                    payload.setStatus(rs.getString(3));

                    list.add(payload);
                }
            }

            return list;

        }


    }


}