package fr.diginamic.SpringGestionBanque.modele;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	@Column(name="dateNaissance")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name ="BANQUE_ID")	
	private Banque banque;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "CLIENT_COMPTE", joinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "COMPTE_ID", referencedColumnName = ""))
	private Set<Compte> comptes;
	@Embedded
	private Adresse adresse;

	public Client() {
		comptes = new HashSet<>();
	}

	public Client(String nom, String prenom, Date dateNaissance) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public Client(String nom, String prenom, Date dateNaissance, Banque banque) {
		this(nom, prenom, dateNaissance);
		this.banque = banque;
	}

	public Client(String nom, String prenom, Date dateNaissance, Banque banque, Adresse adresse) {
		this(nom, prenom, dateNaissance, banque);
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	
	public void addCompte(Compte compte) {
		this.comptes.add(compte);
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + "]";
	}



}
