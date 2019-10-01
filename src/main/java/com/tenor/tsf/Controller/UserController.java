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
import com.tenor.tsf.dao.exceptions.UserException;
import com.tenor.tsf.dao.services.UserService;

@RestController
@RequestMapping(value = { "/User" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = { "/users" }, produces = "application/json")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) 
			throws UserException {
		try {
			userService.createUser(user);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id)
			throws UserException {
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) throws 
						UserException {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
