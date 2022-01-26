package fr.diginamic.SpringGestionBanque.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.SpringGestionBanque.modele.Virement;

public interface ICrudVirement extends CrudRepository<Virement, Integer> {
}
