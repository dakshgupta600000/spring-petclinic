package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PetClinicApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("org.springframework.cloud.bindings.boot.enable", "true");
        System.setProperty("spring.profiles.active", "postgresql,spring-data-jpa");
		SpringApplication.run(PetClinicApplication.class, args);
	}
}
