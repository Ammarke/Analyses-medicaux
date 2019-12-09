package enset.proxy.service.saop;

import java.util.Collection;

import enset.proxy.service.entities.Client;

public interface ClientService {
	public Collection<Client> getClientByName(String name);

}
