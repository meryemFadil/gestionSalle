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
import com.tenor.tsf.dao.entity.Departement;
import com.tenor.tsf.dao.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Departement departement = new Departement();
	private User user = new User();
	
	@Test
	public void testGetUsers() throws Exception {
		ResultActions rs = mockMvc.perform(get("/User/users")).
				andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreateUser() throws JsonProcessingException, Exception {
		departement.setId(21L);
		user.setNom("fadil");
		user.setPrenom("meryem");
		user.setEmail("email");
		user.setLogin("login");
		user.setPassword("pass");
		user.setDepartement(departement);
		ResultActions rs = mockMvc.perform(post("/User/create").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(user))).
				andExpect(status().isCreated());
		rs.andDo(print());
	}
	
	@Test   //field name null
	public void testKoCreateUser() throws JsonProcessingException, Exception {
		departement.setId(4L);
		user.setPrenom("meryem");
		user.setEmail("email");
		user.setLogin("login");
		user.setPassword("pass");
		user.setDepartement(departement);
		ResultActions rs = mockMvc.perform(post("/User/create").
				contentType("application/json")
				.content(objectMapper.writeValueAsString(user))).
				andExpect(status().isNotImplemented());
		rs.andDo(print());
	}

	@Test
	public void testUpdateUser() throws JsonProcessingException, Exception {
		departement.setId(22L);
		user.setId(11L);
		user.setNom("fadil");
		user.setPrenom("meryem");
		user.setEmail("email");
		user.setLogin("login");
		user.setPassword("pass");
		user.setDepartement(departement);
		ResultActions rs = mockMvc.perform( put("/User/update/{id}","11")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test  //Id user not found
	public void testKoUpdateUser() throws JsonProcessingException, Exception {
		departement.setId(4L);
		user.setId(30L);
		user.setNom("fadil");
		user.setPrenom("meryem");
		user.setEmail("email");
		user.setLogin("login");
		user.setPassword("pass");
		user.setDepartement(departement);
		ResultActions rs = mockMvc.perform( put("/User/update/{id}","30")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteUser() throws Exception {
		user.setId(12L);
		ResultActions rs = mockMvc.perform( delete("/User/delete/{id}","12"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
	@Test  //Id user not found
	public void testKoDeleteUser() throws Exception {
		user.setId(35L);
		ResultActions rs = mockMvc.perform( delete("/User/delete/{id}","35"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
