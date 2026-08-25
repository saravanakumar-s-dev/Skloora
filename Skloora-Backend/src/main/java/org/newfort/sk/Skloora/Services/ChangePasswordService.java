package org.newfort.sk.Skloora.Services;

import org.mindrot.jbcrypt.BCrypt;
import org.newfort.sk.Skloora.Repositorys.ChangePasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private ChangePasswordRepo repo;

    public String Authentication(String token, String email) throws Exception {
        return repo.Authentication(token, email);
    }

    public String ChangePassword(String otp, String token, String newpassword) throws Exception {

        String hashed = BCrypt.hashpw(newpassword, BCrypt.gensalt());

        return repo.ChangePassword(otp, token, hashed);
    }
}
