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
import com.tenor.tsf.dao.exceptions.BadRequestException;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.ReclamationService;

@RestController
@RequestMapping(value = { "/Reclamation" })
public class reclamationController {

	@Autowired
	private ReclamationService reclamationService;

	@GetMapping(value = { "/reclamations" }, produces = "application/json")
	public ResponseEntity<List<Reclamation>> getReclamations() throws NoContentException {
		
			List<Reclamation> reclamations = reclamationService.findAll();
		if(reclamations.isEmpty()) {
			return new ResponseEntity<List<Reclamation>>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Reclamation>>(reclamations, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation)
			throws FieldNullException, NotFoundException, BadRequestException {
			Reclamation result = reclamationService.createReclamation(reclamation);
		return new ResponseEntity<Reclamation>(result,HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Reclamation> updateReclamation(@RequestBody Reclamation reclamation)
			throws NotFoundException, FieldNullException, BadRequestException {
		Reclamation result = reclamationService.updateReclamation(reclamation);
		return new ResponseEntity<Reclamation>(result,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reclamation> deleteReclamation(@PathVariable Long id)
			throws NotFoundException {
		reclamationService.deleteReclamation(id);
		return new ResponseEntity<Reclamation>(HttpStatus.OK);
	}
}
