package enset.proxy.service.dao;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import enset.proxy.service.entities.Client;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RepositoryRestResource
public interface ClientRepository extends MongoRepository<Client, String>{
	
	public Collection<Client> findByNom(String name);

}
