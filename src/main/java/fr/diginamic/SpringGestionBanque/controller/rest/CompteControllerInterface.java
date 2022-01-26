package fr.diginamic.SpringGestionBanque.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.CompteNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.OperationNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;
import fr.diginamic.SpringGestionBanque.modele.Operation;

public interface CompteControllerInterface {

	@GetMapping("all")
	public Iterable<Compte> getComptes();
	
	@GetMapping("{id}")
	public Compte getCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException;

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException;
	
	@PostMapping
	public Compte addCompte(@RequestBody Compte compte);
	
	@PutMapping("{id}")
	public Compte updateCompte(@PathVariable("id") Integer pid, @RequestBody Compte compte)
			throws CompteNotFoundException;

	@GetMapping("{id}/client")
	public Client getClientByCompte(@PathVariable("id") Integer pid) throws ClientNotFoundException, CompteNotFoundException;
	
	@GetMapping("{id}/operations")
	public Iterable<Operation> getMyOperations(@PathVariable("id") Integer pid) throws CompteNotFoundException, OperationNotFoundException;
	
}
