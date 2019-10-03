package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.Repository.SalleRepository;
import com.tenor.tsf.dao.entity.Materiel;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MaterielServiceTest {

	private Materiel materiel = new Materiel();
	private Materiel materiel1 = new Materiel();
	@Autowired
	private MaterielService materielService;
	private Salle salle = new Salle();
	@Mock
	private SalleRepository salleRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testfindAll() throws NoContentException {
		materielService.findAll();
	}

	@Test
	public void testcreateMateriel() throws NotFoundException, FieldNullException {
		final Salle salle = new Salle();
		salle.setId(1L);
		salle.setCapacite(50L);
		salle.setLibelle("libelle");
		when(salleRepository.findById(1L).get()).thenReturn(salle);
		materiel.setLibelle("libelle 1");
		materiel.setCategorie("categorie 1");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getLibelle(), materiel1.getLibelle());
	}

	// Exception libelle null
	@Test(expected = FieldNullException.class)
	public void testcreateExceptionLibelle() throws NotFoundException, FieldNullException {
		salle.setId(1L);
		materiel.setCategorie("categorie 1");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getCategorie(), materiel1.getCategorie());
	}

	// Exception categorie null
	@Test(expected = FieldNullException.class)
	public void testcreateExceptionCategorie() throws NotFoundException, FieldNullException {
		salle.setId(1L);
		materiel.setLibelle("libelle");
		materiel.setSalle(salle);
		materiel1 = materielService.createMateriel(materiel);
		assertEquals(materiel.getLibelle(), materiel1.getLibelle());
	}

	@Test
	public void testUpdateMateriel() throws FieldNullException, NotFoundException {
		salle.setId(1L);
		materiel.setId(2L);
		materiel.setLibelle("libelle 4");
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception salle Id not found
	@Test(expected = NotFoundException.class)
	public void nottestUpdateExceptionIdSalle()
			throws FieldNullException, NotFoundException {
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
	@Test(expected = NotFoundException.class)
	public void testUpdateExceptionIdMateriel()
			throws FieldNullException, NotFoundException {
		salle.setId(1L);
		materiel.setId(193L);
		materiel.setLibelle("libelle 4");
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception Libelle null
	@Test(expected = FieldNullException.class)
	public void testUpdateExceptionLibelleNull()
			throws FieldNullException, NotFoundException {
		salle.setId(1L);
		materiel.setId(2L);
		materiel.setCategorie("categorie 4");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	// Exception categorie null
	@Test(expected = FieldNullException.class)
	public void testUpdateExceptionCategorieNull()
			throws FieldNullException, NotFoundException {
		salle.setId(1L);
		materiel.setId(2L);
		materiel.setLibelle("libelle");
		materiel.setSalle(salle);
		materielService.updateMateriel(materiel);
		materiel1 = materielService.getByMateriel(materiel);
		assertEquals(materiel.getId(), materiel1.getId());
	}

	@Test
	public void testDeleteMateriel() throws NotFoundException {
		materiel.setId(2L);
		Long id = materiel.getId();
		materielService.deleteMateriel(id);
		Optional<Materiel> output = materielService.getById(id);
		assertEquals(Optional.empty(), output);
	}

	//Exception Id not found
	@Test(expected = NotFoundException.class)
	public void testDeleteExceptionId() throws NotFoundException {
		materiel.setId(60L);
		Long id = materiel.getId();
		materielService.deleteMateriel(id);
		Optional<Materiel> output = materielService.getById(id);
		assertEquals(Optional.empty(), output);
	}

}
