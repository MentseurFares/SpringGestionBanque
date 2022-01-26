package fr.diginamic.SpringGestionBanque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;

public interface ICrudBanque extends CrudRepository<Banque, Integer> {
	
	
	@Query("select c from Client c where c.banque = :banque order by c.id asc")
	 public Iterable<Client> getClientsByBankId(Banque banque);
}
