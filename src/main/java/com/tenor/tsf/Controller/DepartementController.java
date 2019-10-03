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
import com.tenor.tsf.dao.entity.Departement;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.DepartementService;

@RestController
@RequestMapping(value = { "/Departement" })
public class DepartementController {

	@Autowired
	private DepartementService departementService;

	@GetMapping(value = { "/departements" }, produces = "application/json")
	public ResponseEntity<List<Departement>> getDepartements() throws NoContentException {
		List<Departement> departements = departementService.findAll();
		if (departements.isEmpty()) {
			return new ResponseEntity<List<Departement>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Departement>>(departements, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement)
			throws FieldNullException {
		
		Departement result = departementService.createDepartement(departement);
		return new ResponseEntity<Departement>(result, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Departement> updateDepartement(@RequestBody Departement departement)
			throws FieldNullException, NotFoundException {
		
			Departement result = departementService.updateDepartement(departement);
		return new ResponseEntity<Departement>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Departement> deleteDepartement(@PathVariable Long id)
			throws NotFoundException {
			departementService.deleteDepartement(id);
		return new ResponseEntity<Departement>(HttpStatus.OK);
	}

}
