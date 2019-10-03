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
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.SalleService;

@RestController
@RequestMapping(value = { "/Salle" })
public class SalleController {

	@Autowired
	private SalleService salleService;

	@GetMapping(value = { "/salles" }, produces = "application/json")
	public ResponseEntity<List<Salle>> getSalles() throws NoContentException {
		List<Salle> salles = salleService.findAll();
		if (salles.isEmpty()) {
			return new ResponseEntity<List<Salle>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Salle>>(salles, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) 
			throws FieldNullException {
			Salle result = salleService.createSalle(salle);
		return new ResponseEntity<Salle>(result,HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Salle> updateSalle(@RequestBody Salle salle)
			throws FieldNullException, NotFoundException {
			Salle result = salleService.updateSalle(salle);
		return new ResponseEntity<Salle>(result,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Salle> deleteSalle(@PathVariable Long id) 
			throws NotFoundException {
			salleService.deleteSalle(id);
		return new ResponseEntity<Salle>(HttpStatus.OK);
	}
}
