package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Reservation;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.ReservationException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservationServicesTest {

	Reservation reservation = new Reservation();
	Reservation reservation1 = new Reservation();
	@Autowired
	ReservationService reservationService;
	Salle salle = new Salle();
	User user = new User();
	LocalDate date = LocalDate.of(2019, 10, 28);
	LocalTime heureDeb = LocalTime.of(18, 00);
	LocalTime heureFin = LocalTime.of(20, 00);

	@Test
	public void testfindAll() throws ReservationException {
		reservationService.findAll();
	}

	@Test
	public void testCreateReservation() throws ReservationException {
		salle.setId(20L);
		user.setId(12L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservation1 = reservationService.createReservation(reservation);
		assertEquals(reservation1, reservation);
	}

	// Exception Date
	@Test(expected = ReservationException.class)
	public void testCreateExceptionDate() throws ReservationException {
		LocalDate date1 = LocalDate.of(2019, 9, 22);
		salle.setId(20L);
		user.setId(12L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date1);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservation1 = reservationService.createReservation(reservation);
		assertEquals(reservation1, reservation);
	}

	// Exception heure de fin est moins de heure de début
	@Test(expected = ReservationException.class)
	public void testCreateExceptionheure() throws ReservationException {
		LocalTime heureDeb1 = LocalTime.of(22, 00);
		LocalTime heureFin1 = LocalTime.of(18, 00);
		salle.setId(20L);
		user.setId(12L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb1);
		reservation.setHeureFin(heureFin1);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservation1 = reservationService.createReservation(reservation);
		assertEquals(reservation1, reservation);
	}

	// Exception Id Salle not found
	@Test(expected = ReservationException.class)
	public void testCreateExceptionIdSalle() throws ReservationException {
		salle.setId(100L);
		user.setId(12L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservation1 = reservationService.createReservation(reservation);
		assertEquals(reservation1, reservation);
	}

	// Exception Id User not found
	@Test(expected = ReservationException.class)
	public void testCreateExceptionIdUser() throws ReservationException {
		salle.setId(20L);
		user.setId(100L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservation1 = reservationService.createReservation(reservation);
		assertEquals(reservation1, reservation);
	}

	@Test
	public void testUpdateReservation() throws ReservationException {
		salle.setId(20L);
		user.setId(12L);
		reservation.setId(4L);
		reservation.setNom("reservation 1");
		reservation.setMessage("message");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservationService.updateReservation(reservation);
		reservation1 = reservationService.getByReservation(reservation);
		assertEquals(reservation1.getId(), reservation.getId());
	}
	
	//Exception Id Reservation not found
	@Test(expected = ReservationException.class)
	public void nottestUpdateReservation() throws ReservationException {
		salle.setId(20L);
		user.setId(12L);
		reservation.setId(100L);
		reservation.setNom("nom 1");
		reservation.setMessage("message 1");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		reservationService.updateReservation(reservation);
		reservation1 = reservationService.getByReservation(reservation);
		assertEquals(reservation1.getId(), reservation.getId());
	}

	@Test
	public void testDeleteReservation() throws ReservationException {
		reservation.setId(3L);
		Long id = reservation.getId();
		reservationService.deleteReservation(id);
		Optional<Reservation> output = reservationService.getById(id);
		assertEquals(Optional.empty(), output);
	}

	// Exception Id not found
	@Test(expected = ReservationException.class)
	public void testDeleteExceptionId() throws ReservationException {
		reservation.setId(56L);
		Long id = reservation.getId();
		reservationService.deleteReservation(id);
		Optional<Reservation> output = reservationService.getById(id);
		assertEquals(Optional.empty(), output);
	}
}
