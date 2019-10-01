package com.tenor.tsf.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "salle")
public class Salle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private Long capacite;
	 private String libelle;
	@OneToMany
    private final List<Materiel> materiels = new ArrayList<Materiel>();
	
	
	@Override
	public String toString() {
		return "Salle [id=" + id + ", capacite=" + capacite + ", libelle=" + libelle + ", materiels=" + materiels + "]";
	}

	

	
}
