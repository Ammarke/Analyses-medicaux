package enset.proxy.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import enset.proxy.service.dao.AppRoleRepository;
import enset.proxy.service.dao.AppUserRepository;
import enset.proxy.service.entities.AppRole;
import enset.proxy.service.entities.AppUser;

@Service
@Transactional
public class AccountServiceImp implements AccountService{

	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleRepository appRoleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public AppUser saveUser(String userName, String password, String rePassword) {
		AppUser user = appUserRepository.findByUsername(userName);
		if(user!=null) throw new RuntimeException("user already exists");
		if(!password.equals(rePassword)) throw new RuntimeException("Please confirm your password");
		
		AppUser appUser = new AppUser();
		appUser.setUsername(userName);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setActived(true);
		appUserRepository.save(appUser);
		addRoleToUser(userName, "USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole appRole) {
		return appRoleRepository.save(appRole);
		
	}

	@Override
	public AppUser loadUserByUserName(String userName) {
		return appUserRepository.findByUsername(userName);
		
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(userName);
		AppRole appRole  = appRoleRepository.findByRoleName(roleName);
		appUser.getRoles().add(appRole);
		
	}

}
