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
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@Service
public class MaterielService {

	@Autowired
	MaterielRepository materielRepository;
	@Autowired
	SalleRepository salleRepository;
	public final Logger logger = LogManager.getLogger(MaterielService.class);

	public List<Materiel> findAll() throws NoContentException {
		return (List<Materiel>) materielRepository.findAll();
	}

	public Materiel createMateriel(Materiel materiel) throws NotFoundException, FieldNullException {
		Validate.notNull(materiel, "Materiel cannot be null!");
		Optional<Salle> salleFindById = salleRepository.findById(materiel.getSalle().getId());
		if (materiel.getLibelle() == null || materiel.getLibelle().isEmpty()) {
			throw new FieldNullException("The field libelle cannot be null!");
		} else if (materiel.getCategorie() == null || materiel.getCategorie().isEmpty()) {
			throw new FieldNullException("the field categorie cannot be null!");
		} else if (!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		} else {
			return materielRepository.save(materiel);
		}
	}

	public Materiel updateMateriel(Materiel materiel) throws FieldNullException, NotFoundException {
		Validate.notNull(materiel, "Le materiel ne doit pas etre null!");
		logger.info("Update Id:  " + materiel);
		Optional<Materiel> materielFindById = materielRepository.findById(materiel.getId());
		Optional<Salle> salleFindById = salleRepository.findById(materiel.getId());
		if (materiel.getLibelle() == null || materiel.getLibelle().isEmpty()) {
			throw new FieldNullException("The field libelle cannot be null!");
		} else if (materiel.getCategorie() == null || materiel.getCategorie().isEmpty()) {
			throw new FieldNullException("the field categorie cannot be null!");
		} else if (!materielFindById.isPresent()) {
			throw new NotFoundException("Materiel not found !");
		} else if (!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		} else {
			return materielRepository.save(materiel);
		}
	}

	public void deleteMateriel(Long id) throws NotFoundException {
		Validate.notNull(id, "The id cannot be null!");
		logger.info("Delete Id:  " + id);
		Optional<Materiel> materielFindById = materielRepository.findById(id);
		if (!materielFindById.isPresent()) {
			throw new NotFoundException("Materiel not found!");
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
