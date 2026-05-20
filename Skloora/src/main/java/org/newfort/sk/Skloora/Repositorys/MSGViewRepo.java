package org.newfort.sk.Skloora.Repositorys;

import java.util.*;
import java.sql.*;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Getters_Setters.MSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MSGViewRepo
{

    @Autowired
    private DBPool DBP;
    
    public List<MSG> getAllMSG(String email)throws SQLException
    {
        List<MSG> list = new ArrayList<>();
        
        Connection con = DBP.ds.getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.msg WHERE getter = ? LIMIT 10");
        pst.setString(1, email);

        ResultSet rs = pst.executeQuery();

        while(rs.next())
        {

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
