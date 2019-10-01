package com.tenor.tsf.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tenor.tsf.dao.entity.Reservation;
import com.tenor.tsf.dao.exceptions.ReservationException;
import com.tenor.tsf.dao.services.ReservationService;

@RestController
@RequestMapping(value = { "/Reservation" })
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping(value = { "/reservations" }, produces = "application/json")
	public ResponseEntity<List<Reservation>> getReservations() throws ReservationException {
		List<Reservation> reservations = reservationService.findAll();
		if (reservations.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Reservation>>(reservationService.findAll(),
					HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation)
			throws ReservationException {
		try {
			reservationService.createReservation(reservation);
		} catch (Exception e) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Reservation>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Reservation> updateReservation
	(@RequestBody Reservation reservation, @PathVariable Long id)
			throws ReservationException {
		try {
			reservationService.updateReservation(reservation);
		} catch (Exception e) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id)
			throws ReservationException {
		reservationService.getById(id);
		try {
			reservationService.deleteReservation(id);
		} catch (Exception e) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}
}
