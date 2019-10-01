package com.tenor.tsf.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.dao.entity.Reservation;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	Reservation reservation = new Reservation();
	LocalDate date = LocalDate.of(2019, 10, 24);
	LocalTime heureDeb = LocalTime.of(19, 00);
	LocalTime heureFin = LocalTime.of(20, 00);
	Salle salle = new Salle();
	User user = new User();

	@Test
	public void testGetReservations() throws Exception {
		ResultActions rs = mockMvc.perform(get("/Reservation/reservations"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateReservation() throws JsonProcessingException, Exception {
		salle.setId(20L);
		user.setId(12L);
		reservation.setNom("reservation 1");
		reservation.setMessage("msg");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		ResultActions rs = mockMvc.perform(post("/Reservation/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test   //Id user not found
	public void testKoCreateReservation() throws JsonProcessingException, Exception {
		salle.setId(20L);
		user.setId(1L);
		reservation.setNom("reservation 1");
		reservation.setMessage("msg");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		ResultActions rs = mockMvc.perform(post("/Reservation/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isNotImplemented());
		rs.andDo(print());
	}

	@Test
	public void testUpdateReservation() throws JsonProcessingException, Exception {
		salle.setId(20L);
		user.setId(12L);
		reservation.setId(1L);
		reservation.setNom("reservation");
		reservation.setMessage("message");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		ResultActions rs = mockMvc.perform(put("/Reservation/update/{id}", "1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test  //Id reservation not found
	public void testKoUpdateReservation() throws JsonProcessingException, Exception {
		salle.setId(1L);
		user.setId(5L);
		reservation.setId(130L);
		reservation.setNom("reservation");
		reservation.setMessage("message");
		reservation.setDate(date);
		reservation.setHeureDeb(heureDeb);
		reservation.setHeureFin(heureFin);
		reservation.setSalle(salle);
		reservation.setUtilisateur(user);
		ResultActions rs = mockMvc.perform(put("/Reservation/update/{id}", "130")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteReservation() throws Exception {
		reservation.setId(1L);
		ResultActions rs = mockMvc.perform(delete("/Reservation/delete/{id}", "1"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test   //Id reservation not found
	public void testKoDeleteReservation() throws Exception {
		reservation.setId(150L);
		ResultActions rs = mockMvc.perform(delete("/Reservation/delete/{id}", "150"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
