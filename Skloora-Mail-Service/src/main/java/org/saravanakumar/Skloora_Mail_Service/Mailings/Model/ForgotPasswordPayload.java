package org.saravanakumar.Skloora_Mail_Service.Mailings.Model;

import lombok.Data;

@Data
public class ForgotPasswordPayload {

    private String email;
    private String payload;
    private String status;
}
