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
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.UserService;

@RestController
@RequestMapping(value = { "/User" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = { "/users" }, produces = "application/json")
	public ResponseEntity<List<User>> getUsers() throws NoContentException {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) throws FieldNullException, NotFoundException {
		User result = userService.createUser(user);
		return new ResponseEntity<User>(result, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws NotFoundException, FieldNullException {
		User result = userService.updateUser(user);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) throws NotFoundException {
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
