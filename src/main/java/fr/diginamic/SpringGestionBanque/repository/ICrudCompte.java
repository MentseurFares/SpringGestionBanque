package fr.diginamic.SpringGestionBanque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.SpringGestionBanque.modele.AssuranceVie;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;
import fr.diginamic.SpringGestionBanque.modele.CompteCourant;
import fr.diginamic.SpringGestionBanque.modele.LivretA;
import fr.diginamic.SpringGestionBanque.modele.Operation;

public interface ICrudCompte extends CrudRepository<Compte, Integer> {

	@Query("select c from Client c where :cpt MEMBER OF c.comptes")
	public Client getClientByCompte(Compte cpt);
	
	@Query("select o from Operation o where compte = :cpt")
	public Iterable<Operation> getOperationsByCompte(Compte cpt);
	
	@Query("select c from CompteCourant c ")
	 public Iterable<Compte> getAllComptes();
	
	@Query("select c from CompteCourant c ")
	 public Iterable<CompteCourant> getAllComptesCourant();
	
	@Query("select a from AssuranceVie a ")
	 public Iterable<AssuranceVie> getAllAssuranceVies();
	
	@Query("select l from LivretA l ")
	 public Iterable<LivretA> getAllLivretA();
	
	
	
}
