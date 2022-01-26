package fr.diginamic.SpringGestionBanque.controller.rest;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.SpringGestionBanque.exceptions.BanqueNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.CompteNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;
import fr.diginamic.SpringGestionBanque.repository.ICrudClient;

@RestController
@CrossOrigin
@RequestMapping("/api/client")
public class ClientController implements ClientControllerInterface {
	 
	@Autowired
	ICrudClient cc;
	
	public ClientController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("all")
	public Iterable<Client> getClients(){
		return cc.findAll();
	}
	
	@GetMapping("{id}")
	public Client getClient(@PathVariable("id") Integer pid) throws ClientNotFoundException{
		if(cc.findById(pid).isEmpty()) {
			throw new ClientNotFoundException("client non trouvé, id: "+pid);
		}
		return cc.findById(pid).get();
	}	
	
	@PostMapping
	public Client addClient(@RequestBody Client client) {
		return cc.save(client);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException{
		getClient(pid);
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Client suprrimé, id : "+pid);
	}
	
	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Integer
			pid, @RequestBody Client client) throws ClientNotFoundException{
		// test if the Id(s) are different 
		if(pid != client.getId())
		{
			String s = "Error pathvariable en l'id : " + pid + " et le client : "+client+" !!";
			throw new ClientNotFoundException(s);
		}		
		getClient(pid);	// to test if the clien to update, exists in our DB 	
		return cc.save(client);
	}
		
	@GetMapping("{id}/banque")
	public Banque getMaBanque(@PathVariable("id") Integer pid) throws ClientNotFoundException, BanqueNotFoundException{
		Optional<Client> optionalClient = cc.findById(pid);
		if(optionalClient.isEmpty()) {
			throw new ClientNotFoundException("client non trouvé, id: "+pid);
		}
		Client client = optionalClient.get();
		if(client.getBanque() == null) {
			System.out.println("pas de banque");
			throw new BanqueNotFoundException("client : " +client +" n'a pas de banque déclaré");
		}
		return client.getBanque();
	}
	
	@GetMapping("{id}/comptes")
	public Set<Compte> getMesComptes(@PathVariable("id") Integer pid) throws ClientNotFoundException, CompteNotFoundException{		
		Client client = getClient(pid);
		if(client.getComptes() == null) {
			throw new CompteNotFoundException("client : " +client +" n'a pas de compte déclaré");
		}
		return client.getComptes();
	}
}
