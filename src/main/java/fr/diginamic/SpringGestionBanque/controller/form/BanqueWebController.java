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

import fr.diginamic.SpringGestionBanque.controller.rest.BanqueController;
import fr.diginamic.SpringGestionBanque.exceptions.BanqueNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Banque;
import fr.diginamic.SpringGestionBanque.modele.Client;

@Controller
@RequestMapping("/banque")
public class BanqueWebController {

	@Autowired
	BanqueController cb;
	
	
	public BanqueWebController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/banques")
	public String findall(Model model) {
		List<Banque> banques = (List<Banque>) cb.getBanques();
		model.addAttribute("banques", banques);
		model.addAttribute("titre", "Liste des banques");
		return "banques/Liste"; 
	}

	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("banqueForm", new Banque());
		model.addAttribute("titre", "Ajout banque");
		return "banques/add";
	}

	@PostMapping("/add")
	public String addBanque(Model model,@Valid @ModelAttribute("banqueForm") Banque banque) {
		cb.addBanque(banque);
		return "redirect:/banque/banques";
		
	}
	
	@GetMapping("/{id}")
	public String findOne(@PathVariable("id") Integer pid, Model model) throws Exception{
		Banque banque = cb.getBanque(pid);
		List<Client> clients = cb.getMesClients(pid);
		model.addAttribute("banque",  banque) ;
		model.addAttribute("clients", clients);
		model.addAttribute("titre", "Liste des banques");
		return "banques/banque";
	}
	
	@GetMapping("/update/{id}")
	public String updateBanqueForm(@PathVariable Integer id, Model model) throws BanqueNotFoundException {
		model.addAttribute("banqueForm", cb.getBanque(id));
		model.addAttribute("titre","Modification d'une banque");
		return "banques/update";
	}
	
	@PostMapping("/update")
	public String updateBanque(Model model, @Valid @ModelAttribute("banqueForm") Banque banque) {
		cb.addBanque(banque);
		return "redirect:/banque/banques";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		cb.deleteBanque(pid);
		return "redirect:/banque/banques";
	}
}
