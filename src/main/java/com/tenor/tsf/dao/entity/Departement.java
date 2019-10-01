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

@Entity
@Data
@Table(name = "departement")
public class Departement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String nom;
	@OneToMany
    private final List<User> users = new ArrayList<User>();
	
	
	@Override
	public String toString() {
		return "Departement [id=" + id + ", nom=" + nom + ", users=" + users + "]";
	}

}