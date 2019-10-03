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
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.BadRequestException;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
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

	public List<Reclamation> findAll() throws NoContentException {
		return (List<Reclamation>) reclamationRepository.findAll();
	}

	public Reclamation createReclamation(Reclamation reclamation) 
			throws BadRequestException, FieldNullException, NotFoundException {
		Validate.notNull(reclamation, "Reclamation cannot be null!");
		log.info("Create: "+reclamation);
		Optional<Salle> salleFindById = salleRepository.findById(reclamation.getSalle().getId());
		Optional<User> userFindById = userRepository.findById(reclamation.getUser().getId());
		if (reclamation.getMessage() == null) {
			throw new FieldNullException("The message field can not be null!");
		} else if (reclamation.getStatut() == null) {
			throw new FieldNullException("the field statut can not be null!");
		} else if (!reclamation.getDate().equals(date)) {
			throw new BadRequestException("The date of the reclamation must be today!");
		} else if (!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		} else if (!userFindById.isPresent()) {
			throw new NotFoundException("User not found!");
		} else {
			return reclamationRepository.save(reclamation);
		}
	}

	public Reclamation updateReclamation(Reclamation reclamation) 
			throws BadRequestException, NotFoundException, FieldNullException {
		Validate.notNull(reclamation, "Reclamation cannot be null!");
		log.info("Update: "+reclamation);
		Optional<Salle> salleFindById = salleRepository.findById(reclamation.getSalle().getId());
		Optional<User> userFindById = userRepository.findById(reclamation.getUser().getId());
		Optional<Reclamation> reclamationFindById = reclamationRepository.findById(reclamation.getId());
		if (reclamation.getMessage() == null) {
			throw new FieldNullException("the field statut can not be null!");
		} else if (reclamation.getStatut() == null) {
			throw new FieldNullException("the field statut can not be null!");
		} else if (!reclamation.getDate().equals(date)) {
			throw new BadRequestException("The date of the reclamation must be today!");
		} else if (!salleFindById.isPresent()) {
			throw new NotFoundException("Salle not found!");
		} else if (!userFindById.isPresent()) {
			throw new NotFoundException("User not found!");
		} else if (!reclamationFindById.isPresent()) {
			throw new NotFoundException("reclamation not found!");
		} else {
			return reclamationRepository.save(reclamation);
		}
	}

	public void deleteReclamation(Long id) throws NotFoundException {
		Validate.notNull(id, "The id cannot be null!");
		log.info("delete: "+id);
		Optional<Reclamation> reclamationFindById = reclamationRepository.findById(id);
		if (!reclamationFindById.isPresent()) {
			throw new NotFoundException("Reclamation not found!");
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
