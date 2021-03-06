package com.ftn.kts_nvt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication()
@EnableAsync(proxyTargetClass = true)
@EnableSwagger2
public class KtsNvtApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtsNvtApplication.class, args);
	}

}
