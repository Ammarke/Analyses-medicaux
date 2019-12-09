package enset.proxy.service.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import enset.proxy.service.dao.AnalyseRepository;
import enset.proxy.service.dao.ClientRepository;
import enset.proxy.service.entities.Analyse;
import enset.proxy.service.entities.Client;

@CrossOrigin
@RestController
public class Controller  implements ControllerInterface{

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AnalyseRepository analyseRepository;
	
	private String UPLOAD_DIR = "photos";
	@PostMapping(value = "clients/add")
	public boolean addClient(
							@RequestParam("photo") MultipartFile file,
							@RequestParam("nom") String nom,
							@RequestParam("prenom") String prenom,
							@RequestParam("code") String code,
							HttpServletRequest request) {
		
		String fileName=file.getOriginalFilename();
		String path = "/Users/mbp/Desktop/controle-ammar-kebiri/src/assets"+ File.separator + fileName;
		File convertFile = new File(path);
		
		Client client = new Client(code,nom,prenom,fileName,new ArrayList());
		try (FileOutputStream fout = new FileOutputStream(convertFile)){
			fout.write(file.getBytes());
			clientRepository.save(client);
			
		} catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
			  return false;
		}
		return true;
	}
	
	@DeleteMapping(value = "clients/remove/{code}")
	public boolean removeClient(@PathVariable String code) {
		Optional<Client> optional = clientRepository.findById(code);
		if(optional.isPresent()) {
			Client client = optional.get();
			try {
				analyseRepository.deleteByClient(client);
				clientRepository.delete(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return true;
		}
		return false;
	}
	
	@PostMapping(value = "analyses/add")
	public boolean addAnalyse(@RequestBody Analyse analyse) {
		Optional<Client> optional = clientRepository.findById(analyse.getClient().getCode());
		if(optional.isPresent()) {
			Client client = analyse.getClient();
			client.getAnalyses().add(analyse);
			analyseRepository.save(analyse);
			clientRepository.save(client);
			return true;
		}
		else new RuntimeException("client don't find");
		return false;
	}

}
