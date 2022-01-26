package fr.diginamic.SpringGestionBanque.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.SpringGestionBanque.exceptions.BanqueNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;

public interface BanqueControllerInterface {

	@GetMapping("all")
	public Iterable<Banque> getBanques();
	
	@GetMapping("{id}")
	public Banque getBanque(@PathVariable("id") Integer pid) throws BanqueNotFoundException;

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBanque(@PathVariable("id") Integer pid) throws BanqueNotFoundException;
	
	@PostMapping
	public Banque addBanque(@RequestBody Banque banque);
	
	@PutMapping("{id}")
	public Banque updateBanque(@PathVariable("id") Integer pid, @RequestBody Banque banque)
			throws BanqueNotFoundException;

	@GetMapping("{id}/banque")
	public List<Client> getMesClients(@PathVariable("id") Integer pid) throws ClientNotFoundException, BanqueNotFoundException;

}
