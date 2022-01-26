package fr.diginamic.SpringGestionBanque.controller.rest;

import java.util.List;

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
import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.repository.ICrudBanque;

@RestController
@CrossOrigin
@RequestMapping("/api/banque")
public class BanqueController implements BanqueControllerInterface {
	 
	@Autowired
	ICrudBanque cb;
	
	public BanqueController() {
	}
	
	@GetMapping("all")
	public Iterable<Banque> getBanques(){
		return cb.findAll();
	}
	
	@GetMapping("{id}")
	public Banque getBanque(@PathVariable("id") Integer pid) throws BanqueNotFoundException{
		if(cb.findById(pid).isEmpty()) {
			throw new BanqueNotFoundException("banque non trouvé, id: "+pid);
		}
		return cb.findById(pid).get();
	}	
	
	@PostMapping
	public Banque addBanque(@RequestBody Banque banque) {
		return cb.save(banque);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBanque(@PathVariable("id") Integer pid) throws BanqueNotFoundException{
		getBanque(pid);
		cb.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Banque suprrimé, id : "+pid);
	}
	
	@PutMapping("{id}")
	public Banque updateBanque(@PathVariable("id") Integer
			pid, @RequestBody Banque banque) throws BanqueNotFoundException{
		// test if the Id(s) are different 
		if(pid != banque.getId())
		{
			String s = "Error pathvariable en l'id : " + pid + " et le banque : "+banque+" !!";
			throw new BanqueNotFoundException(s);
		}		
		getBanque(pid);	// 	
		return cb.save(banque);
	}
		
	@GetMapping("{id}/clients")
	public List<Client> getMesClients(@PathVariable("id") Integer pid) throws BanqueNotFoundException, ClientNotFoundException{
		Banque banque = getBanque(pid);
		System.out.println(banque);
		List<Client> clients = (List<Client>) cb.getClientsByBankId(banque);
		System.out.println(clients);
		if(clients == null || clients.isEmpty()) {
			throw new ClientNotFoundException("Aucun client rattaché à la banque : " +banque.getNom());
		}
		return clients;
	}
	
}
