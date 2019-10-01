package com.tenor.tsf.dao.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "materiel")
public class Materiel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String libelle;
	 private String categorie;
     @ManyToOne
	 private Salle salle;
	 
	 
	@Override
	public String toString() {
		return "Materiel [id=" + id + ", libelle=" + libelle + ", categorie=" + categorie + ", salle=" + salle + "]";
	}
	 
	
	


}
