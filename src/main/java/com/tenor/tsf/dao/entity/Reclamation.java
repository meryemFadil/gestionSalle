package com.tenor.tsf.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reclamation")
public class Reclamation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	 private Long id;
	 private String message;
	 private Statut statut;
	 private LocalDate date;
     @ManyToOne
	 private Salle salle;
     @ManyToOne
	 private User user;
     
     
	@Override
	public String toString() {
		return "Reclamation [id=" + id + ", message=" + message + ", statut=" + statut + ", date=" + date + ", salle="
				+ salle + ", user=" + user + "]";
	}
	



	
}
