package org.newfort.sk.Skloora.Repositorys;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Model.MSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MSGViewRepo {

    @Autowired
    private DBPool DBP;

    @Autowired
    private CookieRepo repo;

    public List<MSG> getAllMSG(String token) throws SQLException {
        List<MSG> list = new ArrayList<>();

        Connection con = DBP.ds.getConnection();

        String email = repo.TokenToEmail(token);

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.msg WHERE getter = ? LIMIT 10");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            MSG msg = new MSG();

            String sender = rs.getString("sender");
            String text = rs.getString("text");

            msg.setSender(sender);
            msg.setText(text);

            list.add(msg);

        }

        rs.close();
        pst.close();
        con.close();


        return list;
    }

}
