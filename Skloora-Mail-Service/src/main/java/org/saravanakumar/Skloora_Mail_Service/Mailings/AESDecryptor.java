package org.saravanakumar.Skloora_Mail_Service.Mailings;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AESDecryptor {

    private static final String SECRET_KEY =
            "12345678901234567890123456789012";

    public static String decrypt(String encryptedData) throws Exception {

        SecretKeySpec key = new SecretKeySpec(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                "AES"
        );

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decoded =
                Base64.getDecoder().decode(encryptedData);

        return new String(
                cipher.doFinal(decoded),
                StandardCharsets.UTF_8
        );
    }
}
