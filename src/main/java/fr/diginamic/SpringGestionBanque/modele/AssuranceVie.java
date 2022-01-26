package fr.diginamic.SpringGestionBanque.modele;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AssuranceVie")
public class AssuranceVie extends Compte {

	private LocalDate dateFin;
	private Double taux;

	public AssuranceVie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssuranceVie(LocalDate dateFin, Double taux) {
		super();
		this.dateFin = dateFin;
		this.taux = taux;
	}
	
	public AssuranceVie(String numero, Double  solde, LocalDate dateFin, Double taux) {
		super(numero, solde);
		this.dateFin =dateFin;
		this.taux = taux;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return super.toString() +"\n dateFin=" + dateFin + ", taux=" + taux;
	}
	
	@Override
	public String getType() {
		return "Assurance vie";	
	}

}
