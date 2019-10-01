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
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.SalleException;
import com.tenor.tsf.dao.services.SalleService;

@RestController
@RequestMapping(value = { "/Salle" })
public class SalleController {

	@Autowired
	private SalleService salleService;

	@GetMapping(value = { "/salles" }, produces = "application/json")
	public ResponseEntity<List<Salle>> getSalles() {
		List<Salle> salles = salleService.findAll();
		if (salles.isEmpty()) {
			return new ResponseEntity<List<Salle>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Salle>>(salleService.findAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) 
			throws SalleException {
		try {
			salleService.createSalle(salle);
		} catch (Exception e) {
			return new ResponseEntity<Salle>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Salle>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Salle> updateSalle(@RequestBody Salle salle, @PathVariable Long id)
			throws SalleException {
		try {
			salleService.updateSalle(salle);
		} catch (Exception e) {
			return new ResponseEntity<Salle>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Salle>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Salle> deleteSalle(@PathVariable Long id) 
			throws SalleException {
		salleService.getById(id);
		try {
			salleService.deleteSalle(id);
		} catch (Exception e) {
			return new ResponseEntity<Salle>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Salle>(HttpStatus.OK);
	}
}
