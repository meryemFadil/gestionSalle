package com.tenor.tsf.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import com.tenor.tsf.dao.entity.Materiel;
import com.tenor.tsf.dao.entity.Salle;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MaterielControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Salle salle = new Salle();
	private Materiel materiel = new Materiel();

	@Test
	public void testGetMateriels() throws Exception {
		ResultActions rs = mockMvc.perform(get("/Materiel/materiels")).
				andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateMateriel() throws JsonProcessingException, Exception {
		salle.setId(20L);
		materiel.setLibelle("datacheur");
		materiel.setCategorie("datacheur");
		materiel.setSalle(salle);
		ResultActions rs = mockMvc.perform(post("/Materiel/create").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(materiel))).
				andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test  //Id of salle not found
	public void testKoCreateMateriel() throws JsonProcessingException, Exception {
		salle.setId(4L);
		materiel.setCategorie("datacheur");
		materiel.setSalle(salle);
		ResultActions rs = mockMvc.perform(post("/Materiel/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(materiel)))
				.andExpect(status().isNotImplemented());
		rs.andDo(print());
	}

	@Test
	public void testUpdateMateriel() throws JsonProcessingException, Exception {
		salle.setId(22L);
		materiel.setId(2L);
		materiel.setLibelle("datacheur updated");
		materiel.setCategorie("datacheur");
		materiel.setSalle(salle);
		ResultActions rs = mockMvc.perform(put("/Materiel/update/{id}", "2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(materiel)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test  //Id of materiel not found
	public void testKoUpdateMateriel() throws JsonProcessingException, Exception {
		salle.setId(1L);
		materiel.setId(50L);
		materiel.setLibelle("datacheur updated");
		materiel.setCategorie("datacheur");
		materiel.setSalle(salle);
		ResultActions rs = mockMvc.perform(put("/Materiel/update/{id}", "50")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(materiel)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteMateriel() throws Exception {
		materiel.setId(22L);
		ResultActions rs = mockMvc.perform(delete("/Materiel/delete/{id}", "22"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test //Id of materiel not found
	public void testKoDeleteMateriel() throws Exception {
		materiel.setId(160L);
		ResultActions rs = mockMvc.perform(delete("/Materiel/delete/{id}", "160"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
