package org.cp.controller;

import org.cp.entities.Compte;
import org.cp.entities.Operation;
import org.cp.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	
	@Autowired
	private IBanqueService banqueService;
	
	@RequestMapping("/")
	public String index() {
		return "comptes";
		
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model,String codeCompte, 
			@RequestParam(name="page", defaultValue = "0")int page,
			@RequestParam(name="size", defaultValue = "4")int size
			) {
		
		model.addAttribute("codeCompte",codeCompte);
		try {
			Compte cp = banqueService.consulterCompte(codeCompte);
			Page<Operation> pageOperation = banqueService.listOperation(codeCompte, page, size);
			
			model.addAttribute("listeOperations",pageOperation.getContent());
			int[] pages = new int[pageOperation.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("compte",cp);
			
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		
		return "comptes";
		
	}
	
	@RequestMapping(value="/saveOperation" , method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant, String codeCompte2) {
		
		try {
			
			if(typeOperation.equals("VERS")) {
				banqueService.verser(codeCompte, montant);
			}else if(typeOperation.equals("RET")) {
				banqueService.retirer(codeCompte, montant);
			}else if(typeOperation.equals("VIR")) {
				banqueService.virement(codeCompte, codeCompte2, montant);
			}
			
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	} 
	

}
