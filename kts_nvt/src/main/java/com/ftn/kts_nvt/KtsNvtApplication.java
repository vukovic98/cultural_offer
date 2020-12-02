package com.ftn.kts_nvt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KtsNvtApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtsNvtApplication.class, args);
	}

}
