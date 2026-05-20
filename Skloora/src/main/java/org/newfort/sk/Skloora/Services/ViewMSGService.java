package org.newfort.sk.Skloora.Services;

import java.sql.SQLException;
import java.util.List;

import org.newfort.sk.Skloora.Getters_Setters.MSG;
import org.newfort.sk.Skloora.Repositorys.MSGViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewMSGService 
{

    @Autowired
    private MSGViewRepo MVR;


    public List<MSG> getAllMSG(String email) throws SQLException
    {

        return MVR.getAllMSG(email);
        
    }

}
