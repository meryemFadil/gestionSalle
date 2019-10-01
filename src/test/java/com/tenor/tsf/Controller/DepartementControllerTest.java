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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.dao.entity.Departement;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	Departement departement = new Departement();

	@Test
	public void testGetDepartements() throws Exception {

		ResultActions rs = mockMvc.perform(get("/Departement/departements")).
				andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateDepartement() throws Exception {
		departement.setNom("name");
		ResultActions rs = mockMvc.perform(post("/Departement/create").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(departement))).
				andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test  //Name of departement null
	public void testKoCreateDepartement() throws Exception {
		ResultActions rs = mockMvc.perform(post("/Departement/create").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(departement))).
				andExpect(status().isNotImplemented());
		rs.andDo(print());

	}

	@Test
	public void testUpdateDepartement() throws Exception {
		departement.setNom("depUp");
		departement.setId(24L);
		ResultActions rs = mockMvc.perform(put("/Departement/update/{id}", "1").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(departement))).
				andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test  //Id of departement not found
	public void testKoUpdateDepartement() throws Exception {
		departement.setNom("depUp");
		departement.setId(50L);
		ResultActions rs = mockMvc.perform(put("/Departement/update/{id}", "50").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(departement))).
				andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteDepartement() throws Exception {
		departement.setId(25L);
		ResultActions rs = mockMvc.perform(delete("/Departement/delete/{id}", "25")).
				andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test  //Id of departement not found
	public void testKoDeleteDepartement() throws Exception {
		departement.setId(155L);
		ResultActions rs = mockMvc.perform(delete("/Departement/delete/{id}", "155")).
				andExpect(status().isNotFound());
		rs.andDo(print());
	}
}
