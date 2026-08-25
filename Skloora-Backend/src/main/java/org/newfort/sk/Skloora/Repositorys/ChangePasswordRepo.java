package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Utility.AESDecryptor;
import org.newfort.sk.Skloora.Utility.AESEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ChangePasswordRepo {

    @Autowired
    private DBPool pool;

    @Autowired
    private AESEncryptor encryptor;

    @Autowired
    private AESDecryptor decryptor;

    public String Authentication(String token, String email) throws Exception {

        String sql = """
                SELECT email
                FROM skloora.user_data
                WHERE login_token = ?
                """;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, token);

            try (ResultSet rs = pst.executeQuery()) {

                if (!rs.next()) {
                    return "Something went wrong";
                }

                String dbEmail = rs.getString("email");

                if (dbEmail == null || !dbEmail.equals(email)) {
                    return "not your email";
                }


                SecureRandom random = new SecureRandom();

                String otp = String.valueOf(
                        100000 + random.nextInt(900000)
                );


                String encryptedOtp = encryptor.encrypt(otp);

                String sql1 = """
                        INSERT INTO skloora.changepasswordpayloads
                        (email, payload, status, verifiedornot)
                        VALUES (?, ?, ?, ?)
                        """;

                try (
                        PreparedStatement pst1 =
                                con.prepareStatement(sql1)
                ) {

                    pst1.setString(1, dbEmail);
                    pst1.setString(2, encryptedOtp);
                    pst1.setString(3, "Pending");
                    pst1.setString(4, "not");

                    int count = pst1.executeUpdate();

                    if (count == 0) {
                        return "Something went wrong";


                    }
                }

                String sql2 = """
                        INSERT INTO skloora.pendingmails
                        VALUES (?, ?, ?)
                        """;

                try (
                        PreparedStatement pst2 =
                                con.prepareStatement(sql2)
                ) {

                    pst2.setString(1, dbEmail);
                    pst2.setString(2, "ChangePassword");
                    pst2.setString(3, "Pending");

                    int count = pst2.executeUpdate();

                    if (count == 0) {

                        con.close();
                        pst.close();
                        pst2.close();
                        rs.close();

                        return "Something went wrong";
                    }
                }

                return "success";
            }
        }
    }


    public String ChangePassword(
            String otp,
            String token,
            String newpassword
    ) throws Exception {


        String emailSql = """
                SELECT email
                FROM skloora.user_data
                WHERE login_token = ?
                """;

        String email;

        try (
                Connection con = pool.ds.getConnection();
                PreparedStatement pst = con.prepareStatement(emailSql)
        ) {

            pst.setString(1, token);

            try (ResultSet rs = pst.executeQuery()) {

                if (!rs.next()) {
                    return "Invalid token";
                }

                email = rs.getString("email");

                if (email == null || email.isBlank()) {
                    return "Invalid token";
                }
            }

            String otpSql = """
                    SELECT payload
                    FROM skloora.changepasswordpayloads
                    WHERE email = ?
                    AND verifiedornot = ?
                    """;

            try (
                    PreparedStatement pst2 =
                            con.prepareStatement(otpSql)
            ) {

                pst2.setString(1, email);
                pst2.setString(2, "not");

                try (ResultSet rs2 = pst2.executeQuery()) {

                    while (rs2.next()) {

                        String encryptedPayload =
                                rs2.getString("payload");

                        if (encryptedPayload == null) {
                            continue;
                        }

                        String decryptedOtp =
                                decryptor.decrypt(encryptedPayload);

                        if (decryptedOtp == null) {
                            continue;
                        }

                        decryptedOtp = decryptedOtp.trim();

                        System.out.println(
                                "Email from token: [" + email + "]"
                        );

                        System.out.println(
                                "Database OTP: [" + decryptedOtp + "]"
                        );

                        System.out.println(
                                "Entered OTP: [" + otp + "]"
                        );

                        if (decryptedOtp.equals(otp.trim())) {

                            String updatePasswordSql = """
                                    UPDATE skloora.user_data
                                    SET password = ?
                                    WHERE email = ?
                                    """;

                            try (
                                    PreparedStatement pst3 =
                                            con.prepareStatement(
                                                    updatePasswordSql
                                            )
                            ) {

                                pst3.setString(1, newpassword);
                                pst3.setString(2, email);

                                int count =
                                        pst3.executeUpdate();

                                if (count == 0) {
                                    return "Password update failed";
                                }
                            }


                            String updateOtpSql = """
                                    UPDATE skloora.changepasswordpayloads
                                    SET verifiedornot = ?
                                    WHERE email = ?
                                    AND payload = ?
                                    AND verifiedornot = ?
                                    """;

                            try (
                                    PreparedStatement pst4 =
                                            con.prepareStatement(
                                                    updateOtpSql
                                            )
                            ) {

                                pst4.setString(1, "yes");
                                pst4.setString(2, email);
                                pst4.setString(3, encryptedPayload);
                                pst4.setString(4, "not");

                                pst4.executeUpdate();
                            }

                            return "Success";
                        }
                    }
                }
            }
        }

        return "Invalid OTP";
    }
}