package com.nc.trial.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.nc.trial.uc.app", "com.nc.trial.uc.rest", "com.nc.trial.uc.services" })
public class UrlCompactorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlCompactorServiceApplication.class, args);
	}
}
