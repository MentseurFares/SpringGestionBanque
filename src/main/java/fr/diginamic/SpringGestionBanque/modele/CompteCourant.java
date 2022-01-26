package fr.diginamic.SpringGestionBanque.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="compteCourant")
public class CompteCourant extends Compte{
	
	@Override
	public String getType() {
		return "Compte courant";	
	}

}
