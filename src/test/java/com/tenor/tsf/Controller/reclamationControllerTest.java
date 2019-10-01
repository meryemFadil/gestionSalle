package com.tenor.tsf.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
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
import com.tenor.tsf.dao.entity.Reclamation;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.Statut;
import com.tenor.tsf.dao.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class reclamationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Reclamation reclamation = new Reclamation();
	private User user = new User();
	private Salle salle = new Salle();
	private LocalDate date = LocalDate.now();
	
	@Test
	public void testGetReclamations() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Reclamation/reclamations"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateReclamation() throws JsonProcessingException, Exception {
		salle.setId(20L);
		user.setId(12L);
		reclamation.setMessage("message 1");
		reclamation.setStatut(Statut.INPROGRESS);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamation.setDate(date);
		 ResultActions rs = mockMvc.perform( post("/Reclamation/create")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(reclamation)))
					.andExpect(status().isCreated());
			rs.andDo(print());
	}
	
	@Test  //Field message is null
	public void testKoCreateReclamation() throws JsonProcessingException, Exception {
		salle.setId(1L);
		user.setId(3L);
		reclamation.setStatut(Statut.INPROGRESS);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamation.setDate(date);
		 ResultActions rs = mockMvc.perform( post("/Reclamation/create")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(reclamation)))
					.andExpect(status().isNotImplemented());
			rs.andDo(print());
	}

	@Test
	public void testUpdateReclamation() throws JsonProcessingException, Exception {
		salle.setId(20L);
		user.setId(12L);
		reclamation.setId(2L);
		reclamation.setMessage("message 2");
		reclamation.setStatut(Statut.DONE);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamation.setDate(date);
		ResultActions rs = mockMvc.perform( put("/Reclamation/update/{id}","2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reclamation)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test  //Id of reclamation not found
	public void testKoUpdateReclamation() throws JsonProcessingException, Exception {
		salle.setId(1L);
		user.setId(3L);
		reclamation.setId(166L);
		reclamation.setMessage("message 2");
		reclamation.setStatut(Statut.DONE);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamation.setDate(date);
		ResultActions rs = mockMvc.perform( put("/Reclamation/update/{id}","166")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reclamation)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteReclamation() throws Exception {
		reclamation.setId(2L);
		ResultActions rs = mockMvc.perform( delete("/Reclamation/delete/{id}","2"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test  //Id of reclamation not found
	public void testKoDeleteReclamation() throws Exception {
		reclamation.setId(160L);
		ResultActions rs = mockMvc.perform( delete("/Reclamation/delete/{id}","160"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
