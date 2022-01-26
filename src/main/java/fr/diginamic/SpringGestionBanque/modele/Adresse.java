package fr.diginamic.SpringGestionBanque.modele;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	private Integer numero;
	private String rue;
	private Integer codePostal;
	private String ville;

	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adresse(Integer numero, String rue, Integer codePostal, String ville) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return numero + " " + rue + ", " + codePostal + " " + ville ;
	}
}
