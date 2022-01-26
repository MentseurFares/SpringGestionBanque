package fr.diginamic.SpringGestionBanque.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="COMPTE")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String numero;
	protected Double solde;


	public Compte() {
	}

	public Compte(String numero, Double  solde) {
		this();
		this.numero = numero;
		this.solde = solde;
	}
	
	
	public void addClient(Client client) {
		client.getComptes().add(this);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getType()+ " numero=" + numero + ", solde=" + solde ;
	}

	public void addOperation(Operation operation) {
		operation.setCompte(this);
	}
	
	public String getType() {
		return "Autre Compte";	
	}
}
