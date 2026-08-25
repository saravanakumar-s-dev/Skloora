package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Model.Developers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DevelopersRepo {

    @Autowired
    private DBPool DBP;

    @Autowired
    private CookieRepo CookieRepo;

    public List<Developers> getAllDevs(String token) throws SQLException {

        List<Developers> list = new ArrayList<>();

        Connection con = DBP.ds.getConnection();

        try {

            String email = CookieRepo.TokenToEmail(token);

            // Fetch all other developers
            PreparedStatement pst2 = con.prepareStatement("""
                    SELECT
                        u.username,
                        u.email,
                        d.des,
                        m.msg_id
                    FROM skloora.user_data u
                    INNER JOIN skloora.dev_details d
                        ON u.email = d.email
                    INNER JOIN skloora.msg_id m
                        ON u.email = m.email
                    WHERE u.email <> ?
                    """);

            pst2.setString(1, email);

            ResultSet rs2 = pst2.executeQuery();

            while (rs2.next()) {

                Developers d = new Developers();

                d.setDev_name(rs2.getString("username"));
                d.setDev_email(rs2.getString("email"));
                d.setDev_des(rs2.getString("des"));
                d.setMsg_id(rs2.getString("msg_id"));

                list.add(d);
            }

            rs2.close();
            pst2.close();
            con.close();

            return list;

        } finally {
            if (!con.isClosed()) {
                con.close();
            }
        }
    }

    public List<Developers> searchDev(String query, String token) throws SQLException {

        List<Developers> list = new ArrayList<>();

        String sql = """
                SELECT
                    u.username,
                    u.email,
                    d.des,
                    m.msg_id
                FROM skloora.user_data u
                INNER JOIN skloora.dev_details d
                    ON u.email = d.email
                INNER JOIN skloora.msg_id m
                    ON u.email = m.email
                WHERE u.email <> ?
                  AND (
                        u.username ILIKE ?
                        OR d.des ILIKE ?
                        OR d.email ILIKE ?
                      )
                """;

        String search = "%" + query + "%";

        try (Connection con = DBP.ds.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, CookieRepo.TokenToEmail(token));
            pst.setString(2, search);
            pst.setString(3, search);
            pst.setString(4, search);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Developers d = new Developers();

                d.setDev_name(
                        rs.getString("username")
                );

                d.setDev_email(
                        rs.getString("email")
                );

                d.setDev_des(
                        rs.getString("des")
                );

                d.setMsg_id(
                        rs.getString("msg_id")
                );

                list.add(d);

            }

            return list;
        }

    }

}
