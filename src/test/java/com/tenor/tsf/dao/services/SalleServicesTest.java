package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SalleServicesTest {
	
	Salle salle = new Salle();
	Salle salle1 = new Salle();
	@Autowired
	SalleService salleService;
	
	@Test
	public void testfindAll() throws NoContentException {
		salleService.findAll();
	}
	
	@Test
	public void testCreateSalle() throws FieldNullException {
		salle.setLibelle("libelle 1");
		salle.setCapacite(30L);
		salle1 = salleService.createSalle(salle);
		assertEquals(salle, salle1);
	}
	
	//Exception Libelle null
	@Test(expected=FieldNullException.class)
	public void testCreateExceptionLibelle() throws FieldNullException {
		salle.setCapacite(30L);
		salle1 = salleService.createSalle(salle);
		assertEquals(salle, salle1);
	}
	
	//Exception capacite null
		@Test(expected=FieldNullException.class)
		public void testCreateExceptionCapacite() throws FieldNullException {
			salle.setLibelle("libelle");
			salle1 = salleService.createSalle(salle);
			assertEquals(salle, salle1);
		}

	@Test
	public void testUpdateSalle() throws FieldNullException, NotFoundException {
		salle.setId(20L);
		salle.setLibelle("libele 10");
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception Id not found
	@Test(expected=NotFoundException.class)
	public void testUpdateExceptionId() throws FieldNullException, NotFoundException {
		salle.setId(500L);
		salle.setLibelle("libele 10");
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception libelle null
	@Test(expected=FieldNullException.class)
	public void testUpdateExceptionLibelle() throws FieldNullException, NotFoundException {
		salle.setId(20L);
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception capacite null
	@Test(expected=FieldNullException.class)
	public void testUpdateExceptionCapacite() throws FieldNullException, NotFoundException {
		salle.setId(20L);
		salle.setLibelle("libele 10");
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}

	@Test
	public void testDeleteSalle() throws NotFoundException {
		salle.setId(27L);
		Long id = salle.getId();
		salleService.deleteSalle(id);
		Optional<Salle> output = salleService.getById(id);
		assertEquals(Optional.empty(),output);
	}
	
	//Exception Id not found
	@Test(expected=NotFoundException.class)
	public void testDeleteExceptionId() throws NotFoundException {
		salle.setId(5L);
		Long id = salle.getId();
		salleService.deleteSalle(id);
		Optional<Salle> output = salleService.getById(id);
		assertEquals(Optional.empty(),output);
	}

}
