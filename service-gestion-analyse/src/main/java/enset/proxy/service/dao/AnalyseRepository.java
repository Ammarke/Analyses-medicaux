package enset.proxy.service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import enset.proxy.service.entities.Analyse;
import enset.proxy.service.entities.Client;
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RepositoryRestResource
public interface AnalyseRepository extends MongoRepository<Analyse, String>{
	
	public void deleteByClient(Client client);

}
