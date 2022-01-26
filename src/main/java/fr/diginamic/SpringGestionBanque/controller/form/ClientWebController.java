package fr.diginamic.SpringGestionBanque.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.SpringGestionBanque.controller.rest.ClientController;
import fr.diginamic.SpringGestionBanque.exceptions.ClientNotFoundException;
import fr.diginamic.SpringGestionBanque.modele.Client;
import fr.diginamic.SpringGestionBanque.repository.ICrudBanque;
import fr.diginamic.SpringGestionBanque.repository.ICrudClient;

@Controller
@RequestMapping("/client")
public class ClientWebController {

	@Autowired
	ClientController cr;
	@Autowired
	ICrudBanque crudBanque;

	public ClientWebController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/clients")
	public String findall(Model model) {
		List<Client> clients = (List<Client>) cr.getClients();
		model.addAttribute("clients", clients);
		model.addAttribute("titre", "Liste des clients");
		return "clients/Liste"; 
	}

	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("clientForm", new Client());
		
		model.addAttribute("crudBanque", crudBanque);
		model.addAttribute("titre", "Ajout client");
		return "clients/add";
	}

	@PostMapping("/add")
	public String addClient(Model model,@Valid @ModelAttribute("clientForm") Client client) {
		cr.addClient(client);
		return "redirect:/client/clients";
		
	}
	
	@GetMapping("/{id}")
	public String finOne(@PathVariable("id") Integer pid, Model model) throws Exception{
		System.out.println("findOne Client ");
		model.addAttribute("client",  cr.getClient(pid)) ;
		model.addAttribute("titre", "Liste des clients");
		return "clients/client";
	}
	
	@GetMapping("/update/{id}")
	public String updateClientForm(@PathVariable Integer id, Model model) throws ClientNotFoundException {
		model.addAttribute("clientForm", cr.getClient(id));
		model.addAttribute("titre","Modification d'un client");
		return "clients/update";
	}
	
	@PostMapping("/update")
	public String updateClient(Model model, @Valid @ModelAttribute("clientForm") Client client) {
		cr.addClient(client);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		cr.deleteClient(pid);
		return "redirect:/client/clients";
	}
}
