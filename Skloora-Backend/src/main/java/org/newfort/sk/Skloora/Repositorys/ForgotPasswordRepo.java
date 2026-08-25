package org.newfort.sk.Skloora.Repositorys;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Utility.AESEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class ForgotPasswordRepo {

    @Autowired
    private DBPool DBP;

    @Autowired
    private AESEncryptor encryptor;

    public String Forgot(String email) throws SQLException, Exception {
        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.user_data WHERE email = ?");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

        rs.next();

        String password = UUID.randomUUID().toString();

        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        PreparedStatement pst2 = con.prepareStatement("UPDATE skloora.user_data SET password = ? WHERE email = ?");
        pst2.setString(1, hash);
        pst2.setString(2, email);

        int count = pst2.executeUpdate();

        PreparedStatement pst3 = con.prepareStatement("INSERT INTO skloora.pendingmails VALUES(?,?,?)");
        pst3.setString(1, email);
        pst3.setString(2, "ForgotPassword");
        pst3.setString(3, "Pending");

        int count3 = pst3.executeUpdate();

        PreparedStatement pst4 = con.prepareStatement("INSERT INTO skloora.forgotpasswordpayloads VALUES(?,?,?)");
        pst4.setString(1, email);
        pst4.setString(2, encryptor.encrypt(password));
        pst4.setString(3, "Pending");

        int count4 = pst4.executeUpdate();

        if (count > 0 && count3 > 0 && count4 > 0) {
            rs.close();
            pst.close();
            pst2.close();
            pst3.close();
            pst4.close();
            con.close();

            return "Success";
        } else {
            rs.close();
            pst.close();
            pst2.close();
            con.close();

            return "Failed to change the password";
        }


    }

}
