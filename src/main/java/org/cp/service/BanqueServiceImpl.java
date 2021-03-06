package org.cp.service;

import java.util.Date;

import org.cp.dao.CompteRepository;
import org.cp.dao.OperationRepository;
import org.cp.entities.Compte;
import org.cp.entities.CompteCourant;
import org.cp.entities.Operation;
import org.cp.entities.Retrait;
import org.cp.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService{
	
	@Autowired
	private CompteRepository compteRepository;
	
	
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = compteRepository.findById(codeCpte).orElse(null);
		if(cp ==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(null, new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		double facilitesCaisse=0;
		if(cp instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait(null, new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException("Impossible de faire un virement sur le meme compte");
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		return operationRepository.listOperation(codeCpte, firstPageWithTwoElements);
	}

}
