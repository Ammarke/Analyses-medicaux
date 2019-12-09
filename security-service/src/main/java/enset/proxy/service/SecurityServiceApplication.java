package enset.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import enset.proxy.service.dao.AppRoleRepository;
import enset.proxy.service.dao.AppUserRepository;
import enset.proxy.service.entities.AppRole;
import enset.proxy.service.entities.AppUser;
import enset.proxy.service.service.AccountService;

@SpringBootApplication
public class SecurityServiceApplication {

	@Autowired
	AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start() {
		return args->{
			
		};
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
