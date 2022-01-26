package fr.diginamic.SpringGestionBanque.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.SpringGestionBanque.modele.Operation;

public interface ICrudOperation extends CrudRepository<Operation, Integer> {
}
