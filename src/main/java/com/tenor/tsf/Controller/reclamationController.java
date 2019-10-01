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
import com.tenor.tsf.dao.entity.Reclamation;
import com.tenor.tsf.dao.exceptions.ReclamationException;
import com.tenor.tsf.dao.services.ReclamationService;

@RestController
@RequestMapping(value = { "/Reclamation" })
public class reclamationController {

	@Autowired
	private ReclamationService reclamationService;

	@GetMapping(value = { "/reclamations" }, produces = "application/json")
	public ResponseEntity<List<Reclamation>> getReclamations() {
		try {
			reclamationService.findAll();
		}catch(Exception e) {
			return new ResponseEntity<List<Reclamation>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Reclamation>>(reclamationService.findAll(), 
				HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation)
			throws ReclamationException {
		try {
			reclamationService.createReclamation(reclamation);
		} catch (Exception e) {
			return new ResponseEntity<Reclamation>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Reclamation>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Reclamation> updateReclamation
	(@RequestBody Reclamation reclamation, @PathVariable Long id)
			throws ReclamationException {
		try {
			reclamationService.updateReclamation(reclamation);
		} catch (Exception e) {
			return new ResponseEntity<Reclamation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reclamation>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reclamation> deleteReclamation(@PathVariable Long id)
			throws ReclamationException {
		reclamationService.getById(id);
		try {
			reclamationService.deleteReclamation(id);
		} catch (Exception e) {
			return new ResponseEntity<Reclamation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reclamation>(HttpStatus.OK);
	}
}
