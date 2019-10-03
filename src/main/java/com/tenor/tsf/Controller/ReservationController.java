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
import com.tenor.tsf.dao.exceptions.BadRequestException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.ReservationService;

@RestController
@RequestMapping(value = { "/Reservation" })
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping(value = { "/reservations" }, produces = "application/json")
	public ResponseEntity<List<Reservation>> getReservations() throws NoContentException {
		List<Reservation> reservations = reservationService.findAll();
		if (reservations.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation)
			throws BadRequestException, NotFoundException {
			Reservation result = reservationService.createReservation(reservation);
		return new ResponseEntity<Reservation>(result,HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation)
			throws NotFoundException, BadRequestException {
		Reservation result = reservationService.updateReservation(reservation);
		return new ResponseEntity<Reservation>(result,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id)
			throws NotFoundException {
			reservationService.deleteReservation(id);
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}
}
