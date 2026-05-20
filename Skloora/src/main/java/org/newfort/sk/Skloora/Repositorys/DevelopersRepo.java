package org.newfort.sk.Skloora.Repositorys;

import java.sql.*;
import java.util.*;

import org.newfort.sk.Skloora.DBPool;
import org.newfort.sk.Skloora.Getters_Setters.Developers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DevelopersRepo 
{
    @Autowired
    private DBPool DBP;

    

    public  List<Developers> getAllDevs(String email)throws SQLException
    {

        List<Developers> list = new ArrayList<>();

        Connection con = DBP.ds.getConnection();
        
        PreparedStatement pst = con.prepareStatement("SELECT * FROM skloora.dev_details WHERE email != ?");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        PreparedStatement pst2 = con.prepareStatement("SELECT * FROM skloora.user_data WHERE email != ?");
        pst2.setString(1, email);
        ResultSet rs2 = pst2.executeQuery();

        while(rs.next() && rs2.next())
        {
            Developers D = new Developers();

            String dev_email = rs.getString("email");
            String dev_des = rs.getString("des");
            String dev_name = rs2.getString("username");

            D.setDev_name(dev_name);
            D.setDev_email(dev_email);
            D.setDev_des(dev_des);

             list.add(D);

        }

        rs.close();
        rs2.close();
        pst.close();
        pst2.close();
        con.close();

        return list;
    }


}
