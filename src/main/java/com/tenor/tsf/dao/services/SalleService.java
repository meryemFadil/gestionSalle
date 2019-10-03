package com.tenor.tsf.dao.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.SalleRepository;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SalleService {

	@Autowired
	SalleRepository salleRepository;

	public List<Salle> findAll() throws NoContentException {
		return (List<Salle>) salleRepository.findAll();
	}

	public Salle createSalle(Salle salle) throws FieldNullException {
		Validate.notNull(salle, "Salle cannot be null!");
		log.info("Create: "+salle);
		if (salle.getLibelle() == null) {
			throw new FieldNullException("The field libelle cannot be null!");
		} else if(salle.getCapacite()==null) {
			throw new FieldNullException("the field capacite cannot be null!");
		}else {
			return salleRepository.save(salle);
		}
	}

	public Salle updateSalle(Salle salle) throws FieldNullException, NotFoundException {
		Validate.notNull(salle, "Salle cannot be null!");
		log.info("Update: "+salle);
		Optional<Salle> salleFindById = salleRepository.findById(salle.getId());
		if (salle.getLibelle() == null) {
			throw new FieldNullException("The field libelle cannot be null!");
		} else if(salle.getCapacite()==null) {
			throw new FieldNullException("the field capacite cannot be null!");
		}else if (!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		} else {
			return salleRepository.save(salle);
		}
	}

	public void deleteSalle(Long id) throws NotFoundException {
		Validate.notNull(id, "Id cannot be null!");
		log.info("Delete: "+id);
		Optional<Salle> salleFindById = salleRepository.findById(id);
		if(!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		}else {
			salleRepository.deleteById(id);
		}
	}

	public Optional<Salle> getById(Long id) {
		log.info("getById: "+id);
		return salleRepository.findById(id);
	}

	public Salle getBySalle(Salle salle) {
		log.info("getBySalle: "+salle);
		salle = salleRepository.findById(salle.getId()).get();
		return salle;
	}

}
