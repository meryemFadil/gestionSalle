package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Materiel;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.MaterielException;
import com.tenor.tsf.dao.exceptions.SalleException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MaterielServiceTest {

	Materiel materiel = new Materiel();
	Materiel materiel1 = new Materiel();
	@Autowired
	MaterielService materielService;
	@Autowired
	SalleService salleService;
	Salle salle = new Salle();

	@Test
	public void testfindAll() {
		materielService.findAll();
	}

	@Test
	public void testcreateMateriel() throws MaterielException {
		salle.setId(22L);
		materiel.setLibelle("libelle 1");
		materiel.setCategorie("categorie 1");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getLibelle(), materiel1.getLibelle());
	}

	// Exception libelle null
	@Test(expected = MaterielException.class)
	public void testcreateExceptionLibelle() throws MaterielException {
		salle.setId(22L);
		materiel.setCategorie("categorie 1");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getCategorie(), materiel1.getCategorie());
	}

	// Exception categorie null
	@Test(expected = MaterielException.class)
	public void testcreateExceptionCategorie() throws MaterielException {
		salle.setId(22L);
		materiel.setLibelle("libelle");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getLibelle(), materiel1.getLibelle());
	}

	@Test
	public void testUpdateMateriel() throws MaterielException {
		salle.setId(20L);
		materiel.setId(2L);
		materiel.setLibelle("libelle 4");
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception salle Id not found
	@Test(expected = MaterielException.class)
	public void nottestUpdateExceptionIdSalle()
			throws MaterielException, SalleException {
		salle.setId(123L);
		materiel.setId(2L);
		materiel.setLibelle("libelle 4");
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception materiel Id not found
	@Test(expected = MaterielException.class)
	public void testUpdateExceptionIdMateriel()
			throws MaterielException, SalleException {
		salle.setId(20L);
		materiel.setId(193L);
		materiel.setLibelle("libelle 4");
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception Libelle null
	@Test(expected = MaterielException.class)
	public void testUpdateExceptionLibelleNull()
			throws MaterielException, SalleException {
		salle.setId(20L);
		materiel.setId(2L);
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception categorie null
	@Test(expected = MaterielException.class)
	public void testUpdateExceptionCategorieNull()
			throws MaterielException, SalleException {
		salle.setId(20L);
		materiel.setId(2L);
		materiel.setLibelle("libelle");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	@Test
	public void testDeleteMateriel() throws MaterielException {
		materiel.setId(2L);
		Long id = materiel.getId();
		materielService.deleteMateriel(id);
		Optional<Materiel> output = materielService.getById(id);
		assertEquals(Optional.empty(), output);
	}

	//Exception Id not found
	@Test(expected = MaterielException.class)
	public void testDeleteExceptionId() throws MaterielException {
		materiel.setId(60L);
		Long id = materiel.getId();
		materielService.deleteMateriel(id);
		Optional<Materiel> output = materielService.getById(id);
		assertEquals(Optional.empty(), output);
	}

}
