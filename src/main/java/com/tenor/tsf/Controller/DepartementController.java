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
import com.tenor.tsf.dao.exceptions.DepartementException;
import com.tenor.tsf.dao.services.DepartementService;

@RestController
@RequestMapping(value = { "/Departement" })
public class DepartementController {

	@Autowired
	private DepartementService departementService;

	@GetMapping(value = { "/departements" }, produces = "application/json")
	public ResponseEntity<List<Departement>> getDepartements() {
		List<Departement> departements = departementService.findAll();
		if (departements.isEmpty()) {
			return new ResponseEntity<List<Departement>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Departement>>(departementService.findAll(),
					HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement)
			throws DepartementException {
		try {
			departementService.createDepartement(departement);
		} catch (Exception e) {
			return new ResponseEntity<Departement>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Departement>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Departement> updateDepartement
	(@RequestBody Departement departement, @PathVariable Long id)
			throws DepartementException {
		try {
			departementService.updateDepartement(departement);
		} catch (Exception e) {
			return new ResponseEntity<Departement>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departement>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Departement> deleteDepartement(@PathVariable Long id)
			throws DepartementException {
		departementService.getById(id);
		try {
			departementService.deleteDepartement(id);
		} catch (Exception e) {
			return new ResponseEntity<Departement>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departement>(HttpStatus.OK);
	}

}
