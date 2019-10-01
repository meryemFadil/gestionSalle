package com.tenor.tsf.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String nom;
	 private String message;
	 private LocalDate date;
	 private LocalTime heureDeb;
	 private LocalTime heureFin;
    @ManyToOne
	 private User utilisateur;
     @ManyToOne
	 private Salle salle;
	 
	 
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", nom=" + nom + ", message=" + message + ", date=" + date + ", heureDeb="
				+ heureDeb + ", heureFin=" + heureFin + ", utilisateur=" + utilisateur + ", salle=" + salle + "]";
	}
	
	
}
