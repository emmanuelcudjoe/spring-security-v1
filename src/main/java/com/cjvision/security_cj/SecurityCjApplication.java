package com.cjvision.security_cj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
)
public class SecurityCjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCjApplication.class, args);
	}

}
