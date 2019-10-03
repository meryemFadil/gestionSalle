package com.tenor.tsf.dao.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.DepartementRepository;
import com.tenor.tsf.dao.entity.Departement;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@Service
public class DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	public final Logger logger = LogManager.getLogger(DepartementService.class);

	public List<Departement> findAll() throws NoContentException {
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement createDepartement(Departement departement) 
			throws FieldNullException {
		Validate.notNull(departement, "Departement cannot be null!");
		logger.info("Create:  " + departement);
		if (departement.getNom() == null || departement.getNom().isEmpty()) {
			throw new FieldNullException("Name of departement cannot be null");
		} else {
			return departementRepository.save(departement);
		}
	}

	public Departement updateDepartement(Departement departement) 
			throws FieldNullException, NotFoundException {
		Validate.notNull(departement, "Departement cannot be null!");
		logger.info("Update:  " + departement);
		Optional<Departement> departementFindById = departementRepository.findById(departement.getId());
		if (departement.getNom() == null || departement.getNom().isEmpty()) {
			throw new FieldNullException("name of departement cannot be null");
		} else if (!departementFindById.isPresent()) {
			throw new NotFoundException("Departement not found!");
		} else {
			return departementRepository.save(departement);
		}
	}

	public void deleteDepartement(Long id) throws NotFoundException {
		Validate.notNull(id, "Id cannot be null!");
		logger.info("Delete Id:  " + id);
		Optional<Departement> departementFindById = departementRepository.findById(id);
		if (!departementFindById.isPresent()) {
			throw new NotFoundException("Departement not found!");
		} else {
			departementRepository.deleteById(id);
		}
	}

	public Optional<Departement> getById(Long id) {
		return departementRepository.findById(id);
	}

	public Departement getByDept(Departement departement) {
		departement = departementRepository.findById(departement.getId()).get();
		return departement;
	}

}
