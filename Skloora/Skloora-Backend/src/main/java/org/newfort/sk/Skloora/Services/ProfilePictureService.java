package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Repository.CookieRepo;
import org.newfort.sk.Skloora.Repository.ProfilePictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class ProfilePictureService {

    @Autowired
    private ProfilePictureRepo repo;

    @Autowired
    private CookieRepo Crepo;

    public String profilePicture(String token, MultipartFile file) throws SQLException, IOException {

        String email = Crepo.TokenToEmail(token);

        if(repo.SavePic(email, file))
        {
            return "Successfully updated your profile";
        }else{
            return "Something went wrong";
        }
    }
}
