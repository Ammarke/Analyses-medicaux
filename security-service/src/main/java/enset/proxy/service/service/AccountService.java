package enset.proxy.service.service;

import enset.proxy.service.entities.AppRole;
import enset.proxy.service.entities.AppUser;

public interface AccountService {

	public AppUser saveUser(String userName,String password,String rePassword);
	public AppRole saveRole(AppRole appRole);
	public AppUser loadUserByUserName(String userName);
	public void addRoleToUser(String userName,String roleName);
}
