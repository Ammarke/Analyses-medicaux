package enset.proxy.service.saop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import enset.proxy.service.dao.ClientRepository;
import enset.proxy.service.entities.Client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@WebService
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @WebMethod
    public Collection<Client> getClientByName(@WebParam(name = "name") String name) {
        return clientRepository.findByNom(name);
    }
}