package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Repositorys.ForgotPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {


    @Autowired
    private ForgotPasswordRepo fsp;

    public String Forgot(String email) throws Exception {
        String result = fsp.Forgot(email);

        if (result == "Failed to change the password") {
            return "Failed to change the password";
        } else {
            return "Check your mail. \n If you have not received any mail plz wait for few minutes.";
        }

    }

}
