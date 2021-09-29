package org.cp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2 )
public abstract class Compte implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codeCompte;
	private Date dateCreation;
	private double solde;
	
	@ManyToOne
	@JoinColumn(name = "CODE_CLIENT")
	private Client client;
	
	@OneToMany(mappedBy="compte", fetch = FetchType.LAZY)
	private Collection<Operation> operations;

}
