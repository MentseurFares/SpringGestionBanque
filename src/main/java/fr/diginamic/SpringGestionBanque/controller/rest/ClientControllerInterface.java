package fr.diginamic.SpringGestionBanque.controller.rest;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.SpringGestionBanque.exceptions.BanqueNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.CompteNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;

public interface ClientControllerInterface {

	@GetMapping("all")
	public Iterable<Client> getClients();
	
	@GetMapping("{id}")
	public Client getClient(@PathVariable("id") Integer pid) throws ClientNotFoundException;

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException;
	
	@PostMapping
	public Client addClient(@RequestBody Client client);
	
	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Integer pid, @RequestBody Client client)
			throws ClientNotFoundException;

	@GetMapping("{id}/banque")
	public Banque getMaBanque(@PathVariable("id") Integer pid) throws ClientNotFoundException, BanqueNotFoundException;

	@GetMapping("{id}/comptes")
	public Set<Compte> getMesComptes(@PathVariable("id") Integer pid)
			throws ClientNotFoundException, CompteNotFoundException;
}
