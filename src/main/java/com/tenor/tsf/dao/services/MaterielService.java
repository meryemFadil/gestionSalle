package com.tenor.tsf.dao.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.MaterielRepository;
import com.tenor.tsf.Repository.SalleRepository;
import com.tenor.tsf.dao.entity.Materiel;
import com.tenor.tsf.dao.exceptions.MaterielException;

@Service
public class MaterielService {

	@Autowired
	MaterielRepository materielRepository;
	@Autowired
	SalleRepository salleRepository;
	public final Logger logger = LogManager.getLogger(MaterielService.class);

	public List<Materiel> findAll() {
		return (List<Materiel>) materielRepository.findAll();
	}

	public Materiel createMateriel(Materiel materiel) throws MaterielException {
		Validate.notNull(materiel, "Materiel cannot be null!");
		if (materiel.getLibelle() == null || materiel.getLibelle().isEmpty()) {
			throw new MaterielException
			("The field libelle cannot be null!");
		} else if (materiel.getCategorie() == null || materiel.getCategorie().isEmpty()) {
			throw new MaterielException
			("the field categorie cannot be null!");
		} else if (!salleRepository.findById(materiel.getSalle().getId()).isPresent()) {
			throw new MaterielException("Salle not found!");
		} else {
			return materielRepository.save(materiel);
		}
	}

	public Materiel updateMateriel(Materiel materiel) throws MaterielException {
		Validate.notNull(materiel, "Le materiel ne doit pas etre null!");
		logger.info("Update Id:  " + materiel);
		if (materiel.getLibelle() == null || materiel.getLibelle().isEmpty()) {
			throw new MaterielException
			("The field libelle cannot be null!");
		} else if (materiel.getCategorie() == null || materiel.getCategorie().isEmpty()) {
			throw new MaterielException
			("the field categorie cannot be null!");
		} else if (!materielRepository.findById(materiel.getId()).isPresent()) {
			throw new MaterielException("Materiel not found !");
		} else if (!salleRepository.findById(materiel.getId()).isPresent()) {
			throw new MaterielException("Salle not found!");
		} else {
			return materielRepository.save(materiel);
		}
	}

	public void deleteMateriel(Long id) throws MaterielException {
		Validate.notNull(id, "The id cannot be null!");
		logger.info("Delete Id:  " + id);
		if (!materielRepository.findById(id).isPresent()) {
			throw new MaterielException("Materiel not found!");
		} else {
			materielRepository.deleteById(id);
		}
	}

	public Optional<Materiel> getById(Long id) {
		return materielRepository.findById(id);
	}

	public Materiel getByMateriel(Materiel materiel) {
		materiel = materielRepository.findById(materiel.getId()).get();
		return materiel;
	}
}
