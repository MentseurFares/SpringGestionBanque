package fr.diginamic.SpringGestionBanque.controller.form;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.SpringGestionBanque.controller.rest.CompteController;
import fr.diginamic.SpringGestionBanque.exceptions.CompteNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.modele.Compte;
import fr.diginamic.SpringGestionBanque.modele.Operation;

@Controller
@RequestMapping("/compte")
public class CompteWebController {

	@Autowired
	CompteController cc;
	
	
	public CompteWebController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/comptes")
	public String findall(Model model) {
		List<Compte> comptes = (List<Compte>) cc.getComptes();
		model.addAttribute("comptes", comptes);
		model.addAttribute("titre", "Liste des comptes");
		return "comptes/Liste"; 
	}

	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("compteForm", new Compte());
		model.addAttribute("titre", "Ajout compte");
		return "comptes/add";
	}

	@PostMapping("/add")
	public String addCompte(Model model,@Valid @ModelAttribute("compteForm") Compte compte) {
		cc.addCompte(compte);
		return "redirect:/compte/comptes";
		
	}
	
	@GetMapping("/{id}")
	public String findOne(@PathVariable("id") Integer pid, Model model) throws Exception{
		Compte compte = cc.getCompte(pid);
		Client client = cc.getClientByCompte(pid);
		List<Operation> operations = cc.getMyOperations(pid);
		System.err.println(compte);
		System.err.println(client);
		System.out.println(operations);
		model.addAttribute("compte",  compte) ;
		model.addAttribute("client", client);
		model.addAttribute("operations", operations);
		model.addAttribute("titre", "Liste des comptes");
		return "comptes/compte";
	}
	
	@GetMapping("/update/{id}")
	public String updateCompteForm(@PathVariable Integer id, Model model) throws CompteNotFoundException {
		model.addAttribute("compteForm", cc.getCompte(id));
		model.addAttribute("titre","Modification d'un compte");
		return "comptes/update";
	}
	
	@PostMapping("/update")
	public String updateCompte(Model model, @Valid @ModelAttribute("compteForm") Compte compte) {
		cc.addCompte(compte);
		return "redirect:/compte/comptes";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		cc.deleteCompte(pid);
		return "redirect:/compte/comptes";
	}
}
