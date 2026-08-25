package org.saravanakumar.Skloora_Mail_Service.Mailings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SklooraMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SklooraMailServiceApplication.class, args);
	}

}
