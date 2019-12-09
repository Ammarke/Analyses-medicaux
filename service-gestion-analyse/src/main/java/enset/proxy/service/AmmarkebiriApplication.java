package enset.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import enset.proxy.service.dao.AnalyseRepository;
import enset.proxy.service.dao.ClientRepository;
import enset.proxy.service.entities.Analyse;
import enset.proxy.service.entities.Client;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class AmmarkebiriApplication implements CommandLineRunner{

	 	@Autowired
	    RepositoryRestConfiguration configuration;
	 	@Autowired 
	 	ClientRepository clientRepository;
	 	@Autowired
	 	AnalyseRepository analyseRepository;
	public static void main(String[] args) {
		SpringApplication.run(AmmarkebiriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 configuration.exposeIdsFor(Analyse.class,Client.class);
		 
		 
	}

}
