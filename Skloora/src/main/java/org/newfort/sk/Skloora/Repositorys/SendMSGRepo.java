package org.newfort.sk.Skloora.Repositorys;

import java.sql.*;

import org.newfort.sk.Skloora.DBPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SendMSGRepo {

    @Autowired
    private DBPool DBP;

    public boolean SendMSG(String sender, String getter, String text) throws SQLException {

        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("INSERT INTO skloora.msg VALUES(?,?,?)");
        pst.setString(1, sender);
        pst.setString(2, getter);
        pst.setString(3, text);

        int count = pst.executeUpdate();

        pst.close();
        con.close();
        
        return count > 0;

    }

}
