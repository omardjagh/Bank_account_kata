package org.cp.entities;


import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double decouvert;

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client,
			Collection<Operation> operations, double decouvert) {
		super(codeCompte, dateCreation, solde, client, operations);
		this.decouvert = decouvert;
	}

	
	
	

}
