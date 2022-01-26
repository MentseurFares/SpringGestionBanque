package fr.diginamic.SpringGestionBanque.modele;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Virement extends Operation{
	
	@Column(name="BENEFICIAIRE")
	private String beneficiaire;

	public Virement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Virement(String beneficiaire) {
		super();
		this.beneficiaire = beneficiaire;
	}
	
	public Virement(Date date, Double montant, String motif, String beneficiaire) {
		super(date, montant, motif);
		this.beneficiaire = beneficiaire;
	}

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	@Override
	public String toString() {
		return "Virement [beneficiaire=" + beneficiaire + "]";
	}

}
