package com.tenor.tsf.dao.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.ReclamationRepository;
import com.tenor.tsf.Repository.SalleRepository;
import com.tenor.tsf.Repository.UserRepository;
import com.tenor.tsf.dao.entity.Reclamation;
import com.tenor.tsf.dao.exceptions.ReclamationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ReclamationService {

	@Autowired
	ReclamationRepository reclamationRepository;
	@Autowired
	SalleRepository salleRepository;
	@Autowired
	UserRepository userRepository;
	LocalDate date = LocalDate.now();

	public List<Reclamation> findAll() {
		return (List<Reclamation>) reclamationRepository.findAll();
	}

	public Reclamation createReclamation(Reclamation reclamation) 
			throws ReclamationException {
		Validate.notNull(reclamation, "Reclamation cannot be null!");
		log.info("Create: "+reclamation);
		if (reclamation.getMessage() == null) {
			throw new ReclamationException("The message field can not be null!");
		} else if (reclamation.getStatut() == null) {
			throw new ReclamationException("the field statut can not be null!");
		} else if (!reclamation.getDate().equals(date)) {
			throw new ReclamationException("The date of the reclamation must be today!");
		} else if (!salleRepository.findById(reclamation.getSalle().getId()).isPresent()) {
			throw new ReclamationException("Salle not found!");
		} else if (!userRepository.findById(reclamation.getUser().getId()).isPresent()) {
			throw new ReclamationException("User not found!");
		} else {
			return reclamationRepository.save(reclamation);
		}
	}

	public Reclamation updateReclamation(Reclamation reclamation) 
			throws ReclamationException {
		Validate.notNull(reclamation, "Reclamation cannot be null!");
		log.info("Update: "+reclamation);
		if (reclamation.getMessage() == null) {
			throw new ReclamationException("the field statut can not be null!");
		} else if (reclamation.getStatut() == null) {
			throw new ReclamationException("the field statut can not be null!");
		} else if (!reclamation.getDate().equals(date)) {
			throw new ReclamationException("The date of the reclamation must be today!");
		} else if (!salleRepository.findById(reclamation.getSalle().getId()).isPresent()) {
			throw new ReclamationException("Salle not found!");
		} else if (!userRepository.findById(reclamation.getUser().getId()).isPresent()) {
			throw new ReclamationException("User not found!");
		} else if (!reclamationRepository.findById(reclamation.getId()).isPresent()) {
			throw new ReclamationException("reclamation not found!");
		} else {
			return reclamationRepository.save(reclamation);
		}
	}

	public void deleteReclamation(Long id) throws ReclamationException {
		Validate.notNull(id, "The id cannot be null!");
		log.info("delete: "+id);
		if (!reclamationRepository.findById(id).isPresent()) {
			throw new ReclamationException("Reclamation not found!");
		} else {
			reclamationRepository.deleteById(id);
		}
	}

	public Optional<Reclamation> getById(Long id) {
		log.info("getById: "+id);
		return reclamationRepository.findById(id);
	}

	public Reclamation getByReclamation(Reclamation reclamation) {
		log.info("getByReclamtion: "+reclamation);
		reclamation = reclamationRepository.findById(reclamation.getId()).get();
		return reclamation;
	}
}
