package org.cp;

import java.util.Date;

import org.cp.dao.ClientRepository;
import org.cp.dao.CompteRepository;
import org.cp.dao.OperationRepository;
import org.cp.entities.Client;
import org.cp.entities.Compte;
import org.cp.entities.CompteCourant;
import org.cp.entities.CompteEpargne;
import org.cp.entities.Retrait;
import org.cp.entities.Versement;
import org.cp.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IBanqueService banqueService;
	

	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = clientRepository.save(new Client(null, "Khaled", "khaled@gmail.com", null));
		Client c2 = clientRepository.save(new Client(null, "Omar", "Omar@gmail.com", null));
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1, null, 500));
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 3000, c2, null, 5.5));
		
		operationRepository.save(new Versement(null, new Date(), 2500, cp1));
		operationRepository.save(new Versement(null, new Date(), 3000, cp1));
		operationRepository.save(new Versement(null, new Date(), 6000, cp1));
		operationRepository.save(new Retrait(null, new Date(), 8000, cp1));
		
		
		operationRepository.save(new Versement(null, new Date(), 5000, cp2));
		operationRepository.save(new Versement(null, new Date(), 9000, cp2));
		operationRepository.save(new Versement(null, new Date(), 6000, cp2));
		operationRepository.save(new Retrait(null, new Date(), 8000, cp2));
		
		banqueService.verser("c1", 1011);
	}

}
