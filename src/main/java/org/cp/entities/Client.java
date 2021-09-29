package org.cp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long code;
	private String nom;
	private String email;
	@OneToMany(mappedBy="client", fetch= FetchType.LAZY)
	private Collection<Compte> comptes;

}
