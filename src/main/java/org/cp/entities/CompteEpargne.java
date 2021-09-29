package org.cp.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double taux;
	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client,
			Collection<Operation> operations, double taux) {
		super(codeCompte, dateCreation, solde, client, operations);
		this.taux = taux;
	}
	
	
	
	

}
