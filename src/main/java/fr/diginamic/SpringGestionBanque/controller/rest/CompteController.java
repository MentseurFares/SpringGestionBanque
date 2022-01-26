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

import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.CompteNotFoundException;
import fr.diginamic.SpringGestionBanque.exceptions.OperationNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;
import fr.diginamic.SpringGestionBanque.modele.Operation;
import fr.diginamic.SpringGestionBanque.repository.ICrudCompte;

@RestController
@CrossOrigin
@RequestMapping("/api/compte")
public class CompteController implements CompteControllerInterface {
	 
	@Autowired
	ICrudCompte cc;
	
	public CompteController() {
	}
	
	@GetMapping("all")
	public Iterable<Compte> getComptes(){
		return cc.findAll();
	}
	
	@GetMapping("{id}")
	public Compte getCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException{
		if(cc.findById(pid).isEmpty()) {
			throw new CompteNotFoundException("compte non trouvé, id: "+pid);
		}
		return cc.findById(pid).get();
	}	
	
	@PostMapping
	public Compte addCompte(@RequestBody Compte compte) {
		return cc.save(compte);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException{
		getCompte(pid);
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Compte suprrimé, id : "+pid);
	}
	
	@PutMapping("{id}")
	public Compte updateCompte(@PathVariable("id") Integer
			pid, @RequestBody Compte compte) throws CompteNotFoundException{
		// test if the Id(s) are different 
		if(pid != compte.getId())
		{
			String s = "Error pathvariable en l'id : " + pid + " et le compte : "+compte+" !!";
			throw new CompteNotFoundException(s);
		}		
		getCompte(pid);	// 	
		return cc.save(compte);
	}
		
	@GetMapping("{id}/clients")
	public Client getClientByCompte(@PathVariable("id") Integer pid) throws CompteNotFoundException, ClientNotFoundException{
		Compte compte = getCompte(pid);
		System.out.println(compte);
		return cc.getClientByCompte(compte);
	}
	
	@GetMapping("{id}/operations")
	public List<Operation> getMyOperations(@PathVariable("id") Integer pid) throws OperationNotFoundException, CompteNotFoundException{
			Compte compte = getCompte(pid);			
			List<Operation> operations = (List<Operation>) cc.getOperationsByCompte(compte);
			if(operations == null || operations.isEmpty()) {
				throw new OperationNotFoundException("Aucune opération enregistée sur le compte : "+ compte.getNumero());
			}
			return operations;
	}
	
	
}
