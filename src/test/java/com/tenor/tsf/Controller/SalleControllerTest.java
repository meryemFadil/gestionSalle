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
import com.tenor.tsf.dao.entity.Salle;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SalleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Salle salle = new Salle();
	
	@Test
	public void testGetSalles() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Salle/salles"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateSalle() throws JsonProcessingException, Exception {
		    salle.setLibelle("Salle 2");
		    salle.setCapacite(150L);
		    ResultActions rs = mockMvc.perform( post("/Salle/create")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(salle)))
					.andExpect(status().isCreated());
			rs.andDo(print());
	}
	
	@Test   //Field libelle null
	public void testKoCreateSalle() throws JsonProcessingException, Exception {
		    salle.setCapacite(150L);
		    ResultActions rs = mockMvc.perform( post("/Salle/create")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(salle)))
					.andExpect(status().isNotImplemented());
			rs.andDo(print());
	}

	@Test
	public void testUpdateSalle() throws JsonProcessingException, Exception {
		salle.setId(20L);
		salle.setLibelle("Salle 3");
	    salle.setCapacite(50L);
		ResultActions rs = mockMvc.perform( put("/Salle/update/{id}","20")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(salle)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test  //Id room not found
	public void testKoUpdateSalle() throws JsonProcessingException, Exception {
		salle.setId(400L);
		salle.setLibelle("Salle 3");
	    salle.setCapacite(50L);
		ResultActions rs = mockMvc.perform( put("/Salle/update/{id}","400")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(salle)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteSalle() throws Exception {
		salle.setId(21L);
		ResultActions rs = mockMvc.perform( delete("/Salle/delete/{id}","21"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test   //Id room not found
	public void testKoDeleteSalle() throws Exception {
		salle.setId(400L);
		ResultActions rs = mockMvc.perform( delete("/Salle/delete/{id}","400"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
