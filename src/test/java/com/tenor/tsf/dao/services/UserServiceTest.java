package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Departement;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	User user = new User();
	User user1 = new User();
	@Autowired
	UserService userService;
	Departement departement = new Departement();

	@Test
	public void testfindAll() throws NoContentException {
		userService.findAll();
	}

	@Test
	public void testCreateUser() throws FieldNullException, NotFoundException {
		departement.setId(21L);
		user.setNom("nom 1");
		user.setPrenom("prenom 1");
		user.setEmail("email 1");
		user.setLogin("login 1");
		user.setPassword("password 1");
		user.setDepartement(departement);
		user1 = userService.createUser(user);
		assertEquals(user1, user);
	}

	// Exception Id departement not found
	@Test(expected = NotFoundException.class)
	public void testCreateExceptionIdDept() throws FieldNullException, NotFoundException {
		departement.setId(99L);
		user.setNom("nom 1");
		user.setPrenom("prenom 1");
		user.setEmail("email 1");
		user.setLogin("login 1");
		user.setPassword("password 1");
		user.setDepartement(departement);
		user1 = userService.createUser(user);
		assertEquals(user1, user);
	}

	// Exception Nom null
	@Test(expected = FieldNullException.class)
	public void testCreateExceptionNomNull() throws FieldNullException, NotFoundException {
		departement.setId(21L);
		user.setPrenom("prenom 1");
		user.setEmail("email 1");
		user.setLogin("login 1");
		user.setPassword("password 1");
		user.setDepartement(departement);
		user1 = userService.createUser(user);
		assertEquals(user1, user);
	}

	// Exception Prenom null
	@Test(expected = FieldNullException.class)
	public void testCreateExceptionPrenomNull() throws FieldNullException, NotFoundException {
		departement.setId(21L);
		user.setNom("nom 1");
		user.setEmail("email 1");
		user.setLogin("login 1");
		user.setPassword("password 1");
		user.setDepartement(departement);
		user1 = (User) userService.createUser(user);
		assertEquals(user1.getNom(), user.getNom());
	}

	@Test
	public void testUpdateUser() throws NotFoundException, FieldNullException {
		departement.setId(21L);
		user.setId(11L);
		user.setNom("fadil");
		user.setPrenom("meryem");
		user.setEmail("email");
		user.setLogin("login");
		user.setPassword("password");
		user.setDepartement(departement);
		userService.updateUser(user);
		user1 = userService.getByUser(user);
		assertEquals(user1.getId(), user.getId());
	}

	//Exception id departement not found
	@Test(expected = NotFoundException.class)
	public void testUpdateExceptionIdDept() throws NotFoundException, FieldNullException {
		departement.setId(55L);
		user.setId(11L);
		user.setNom("fadil");
		user.setPrenom("meryem");
		user.setEmail("email ");
		user.setLogin("login");
		user.setPassword("password");
		user.setDepartement(departement);
		userService.updateUser(user);
		user1 = userService.getByUser(user);
		assertEquals(user1.getId(), user.getId());
	}
	
	//Exception id User not found
		@Test(expected = NotFoundException.class)
		public void testUpdateExceptionIdUser() throws NotFoundException, FieldNullException {
			departement.setId(21L);
			user.setId(66L);
			user.setNom("fadil");
			user.setPrenom("meryem");
			user.setEmail("email ");
			user.setLogin("login");
			user.setPassword("password");
			user.setDepartement(departement);
			userService.updateUser(user);
			user1 = userService.getByUser(user);
			assertEquals(user1.getId(), user.getId());
		}

	@Test
	public void testDeleteUser() throws NotFoundException {
		user.setId(17L);
		Long id = user.getId();
		userService.deleteUser(id);
		Optional<User> output = userService.getById(id);
		assertEquals(Optional.empty(), output);
	}

	//Exception Id not found
	@Test(expected = NotFoundException.class)
	public void nottestDeleteUser() throws NotFoundException {
		user.setId(5L);
		Long id = user.getId();
		userService.deleteUser(id);
		Optional<User> output = userService.getById(id);
		assertEquals(Optional.empty(), output);
	}

}
