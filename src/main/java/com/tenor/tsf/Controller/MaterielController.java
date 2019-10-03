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
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;
import com.tenor.tsf.dao.services.MaterielService;

@RestController
@RequestMapping(value = { "/Materiel" })
public class MaterielController {

	@Autowired
	private MaterielService materielService;

	@GetMapping(value = { "/materiels" }, produces = "application/json")
	public ResponseEntity<List<Materiel>> getMateriels() throws NoContentException {
		List<Materiel> materiels = materielService.findAll();
		if (materiels.isEmpty()) {
			return new ResponseEntity<List<Materiel>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Materiel>>(materiels, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel)
			throws NotFoundException, FieldNullException {
			Materiel result = materielService.createMateriel(materiel);
		return new ResponseEntity<Materiel>(result,HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Materiel> updateMateriel(@RequestBody Materiel materiel)
			throws FieldNullException, NotFoundException {
			Materiel result = materielService.updateMateriel(materiel);
		return new ResponseEntity<Materiel>(result,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Materiel> deleteMateriel(@PathVariable Long id) 
			throws NotFoundException {
			materielService.deleteMateriel(id);
		return new ResponseEntity<Materiel>(HttpStatus.OK);
	}

}
