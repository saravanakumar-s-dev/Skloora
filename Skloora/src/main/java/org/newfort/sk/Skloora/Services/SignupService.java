package org.newfort.sk.Skloora.Services;

import java.io.IOException;
import java.sql.SQLException;

import org.newfort.sk.Skloora.Repositorys.SignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService 
{

    @Autowired
    private SignupRepo SR;

    public String SignupServ(String email, String username, String password, String des)throws SQLException, IOException
    {

        if(SR.check(email, username))
        {
            return "user already exists";
        }
        else
        {
           String result =  SR.save(email, username, password, des);

           if(result.equalsIgnoreCase("Success"))
           {
            return "Success";
           }
           else
           {
            return "Failed";
           }
        }

    }

}
