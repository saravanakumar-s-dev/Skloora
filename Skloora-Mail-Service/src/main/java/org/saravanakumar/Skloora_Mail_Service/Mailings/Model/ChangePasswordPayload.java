package org.saravanakumar.Skloora_Mail_Service.Mailings.Model;

import lombok.Data;

@Data
public class ChangePasswordPayload {


    private String email;
    private String encryptedOtp;

}
