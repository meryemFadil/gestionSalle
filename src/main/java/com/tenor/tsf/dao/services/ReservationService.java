package com.tenor.tsf.dao.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.ReservationRepository;
import com.tenor.tsf.Repository.SalleRepository;
import com.tenor.tsf.Repository.UserRepository;
import com.tenor.tsf.dao.entity.Reservation;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.BadRequestException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	SalleRepository salleRepository;
	@Autowired
	UserRepository userRepository;
	LocalDate date = LocalDate.now();
	LocalTime heureDeb = LocalTime.now();
	LocalTime heureFin;

	public List<Reservation> findAll() throws NoContentException {
		return (List<Reservation>) reservationRepository.findAll();
	}

	public Reservation createReservation(Reservation reservation) 
			throws BadRequestException, NotFoundException {
		Validate.notNull(reservation, "Reservation could not be null!");
		log.info("Create: "+reservation);
		Optional<Salle> salleFindById = salleRepository.findById(reservation.getSalle().getId());
		Optional<User> userFindById = userRepository.findById(reservation.getUtilisateur().getId());
		if (reservation.getDate().isBefore(date)) {
			throw new BadRequestException("The date must be greater than now!");
		} else if (reservation.getHeureDeb().isBefore(heureDeb)) {
			throw new BadRequestException("The start time must be higher than now!");
		} else if (reservation.getHeureFin().isBefore(reservation.getHeureDeb())) {
			throw new BadRequestException("The end time must be greater than the start time!");
		} else if (reservation.getHeureDeb() == reservation.getHeureFin()) {
			throw new BadRequestException( "The start time should not be equal to the end time!");
		} else if (!salleDispo(reservation)) {
			throw new BadRequestException("This room is not available!");
		} else if (!salleFindById.isPresent()) {
			throw new NotFoundException("room not found!");
		} else if (!userFindById.isPresent()) {
			throw new NotFoundException("User not found!");
		} else {
			return reservationRepository.save(reservation);
		}
	}

	public Reservation updateReservation(Reservation reservation) 
			throws NotFoundException, BadRequestException {
		Validate.notNull(reservation, "Reservation could not be null");
		log.info("Update: "+reservation);
		Optional<Reservation> reservationFindById = reservationRepository.findById(reservation.getId());
		if (!reservationFindById.isPresent()) {
			throw new NotFoundException("Reservation not found!");
		} else if (!salleDispo(reservation)) {
			throw new BadRequestException("This room is not available!");
		} else {
			return reservationRepository.save(reservation);
		}
	}

	public void deleteReservation(long id) throws NotFoundException {
		Validate.notNull(id,"Id cannot be null");
		log.info("Delete: "+id);
		Optional<Reservation> reservationFindById = reservationRepository.findById(id);
		if (!reservationFindById.isPresent()) {
			throw new NotFoundException("Reservation not found!");
		} else {
			reservationRepository.deleteById(id);
		}
	}

	public Optional<Reservation> getById(Long id) {
		Validate.notNull(id,"id cannot be null");
		log.info("getById: "+id);
		return reservationRepository.findById(id);
	}

	public Reservation getByReservation(Reservation reservation) {
		Validate.notNull(reservation,"Reservation cannot be null!");
		log.info("getByReservation: "+reservation);
		reservation = reservationRepository.findById(reservation.getId()).get();
		return reservation;
	}

	public boolean salleDispo(Reservation reservation) {
		Validate.notNull(reservation,"Reservation cannot be null");
		log.info("SalleDispo: "+reservation);
		boolean dispo = false;
		if(reservationRepository.findAll()==null) {
			log.info("This room is available!");
			dispo=true;
		}else {
			for (Reservation reserv : reservationRepository.findAll()) {
				if (reservation.getSalle().getId() == reserv.getSalle().getId()
						&& reservation.getDate() == reserv.getDate()
						&& reservation.getHeureDeb() == reserv.getHeureDeb()
						&& reservation.getHeureFin() == reserv.getHeureFin()) {
					log.info("This room is not available!");
					dispo = false;
					log.debug(dispo);
				} else {
					log.info("This room is available!");
					dispo = true;
					log.debug(dispo);
				}
			}
		}
		return dispo;
	}
}
