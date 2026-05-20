package org.newfort.sk.Skloora.Repositorys;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignupRepo {
    
    @Autowired
    private DBPool DBP;

    public String save(String email, String username, String password, String description)throws SQLException {

        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection con = DBP.ds.getConnection()) {

            String sql1 = "INSERT INTO skloora.user_data VALUES(?,?,?)";

            try (PreparedStatement pst = con.prepareStatement(sql1)) {
                pst.setString(1, email);
                pst.setString(2, username);
                pst.setString(3, hash);

                int count = pst.executeUpdate();

                if (count <= 0) {
                    return "failed";
                }
            }

            String sql2 = "INSERT INTO skloora.dev_details VALUES(?,?)";

            try (PreparedStatement pst2 = con.prepareStatement(sql2)) {
                pst2.setString(1, email);
                pst2.setString(2, description);

                int count2 = pst2.executeUpdate();

                if (count2 > 0) {
                    return "success";
                }
            }

            return "failed";

        }

    }

    public boolean check(String email, String username) throws SQLException {

        String sql = "SELECT COUNT(*) FROM skloora.user_data WHERE email = ? OR username = ?";

        try (Connection con = DBP.ds.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, username);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }
}