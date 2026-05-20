package org.newfort.sk.Skloora.Services;

import java.sql.SQLException;
import java.util.List;

import org.newfort.sk.Skloora.Getters_Setters.Developers;
import org.newfort.sk.Skloora.Repositorys.DevelopersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopersService 
{

    @Autowired
    private DevelopersRepo DR;

    public List<Developers> getAllDevs(String email) throws SQLException
    {

        return DR.getAllDevs(email);
        
    }

}
