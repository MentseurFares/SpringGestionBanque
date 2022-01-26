package fr.diginamic.SpringGestionBanque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.SpringGestionBanque.modele.Client;

public interface ICrudClient extends CrudRepository<Client, Integer> {

	@Query("select c from Client c where c.nom= :nom")
	Optional<Client> findClientByName(String nom);
}
