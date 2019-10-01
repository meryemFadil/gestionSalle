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
import com.tenor.tsf.dao.entity.Materiel;
import com.tenor.tsf.dao.exceptions.MaterielException;
import com.tenor.tsf.dao.services.MaterielService;

@RestController
@RequestMapping(value = { "/Materiel" })
public class MaterielController {

	@Autowired
	private MaterielService materielService;

	@GetMapping(value = { "/materiels" }, produces = "application/json")
	public ResponseEntity<List<Materiel>> getMateriels() {
		List<Materiel> materiels = materielService.findAll();
		if (materiels.isEmpty()) {
			return new ResponseEntity<List<Materiel>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Materiel>>(materielService.findAll(), HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel)
			throws MaterielException {
		try {
			materielService.createMateriel(materiel);
		} catch (Exception e) {
			return new ResponseEntity<Materiel>(HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<Materiel>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Materiel> updateMateriel
	(@RequestBody Materiel materiel, @PathVariable Long id)
			throws MaterielException {
		try {
			materielService.updateMateriel(materiel);
		} catch (Exception e) {
			return new ResponseEntity<Materiel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Materiel>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Materiel> deleteMateriel(@PathVariable Long id) 
			throws MaterielException {
		materielService.getById(id);
		try {
			materielService.deleteMateriel(id);
		} catch (Exception e) {
			return new ResponseEntity<Materiel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Materiel>(HttpStatus.OK);
	}

}
