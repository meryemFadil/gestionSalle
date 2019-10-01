package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.exceptions.SalleException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SalleServicesTest {
	
	Salle salle = new Salle();
	Salle salle1 = new Salle();
	@Autowired
	SalleService salleService;
	
	@Test
	public void testfindAll() {
		salleService.findAll();
	}
	
	@Test
	public void testCreateSalle() throws SalleException {
		salle.setLibelle("libelle 1");
		salle.setCapacite(30L);
		salle1 = salleService.createSalle(salle);
		assertEquals(salle, salle1);
	}
	
	//Exception Libelle null
	@Test(expected=SalleException.class)
	public void testCreateExceptionLibelle() throws SalleException {
		salle.setCapacite(30L);
		salle1 = salleService.createSalle(salle);
		assertEquals(salle, salle1);
	}
	
	//Exception capacite null
		@Test(expected=SalleException.class)
		public void testCreateExceptionCapacite() throws SalleException {
			salle.setLibelle("libelle");
			salle1 = salleService.createSalle(salle);
			assertEquals(salle, salle1);
		}

	@Test
	public void testUpdateSalle() throws SalleException {
		salle.setId(20L);
		salle.setLibelle("libele 10");
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception Id not found
	@Test(expected=SalleException.class)
	public void testUpdateExceptionId() throws SalleException {
		salle.setId(500L);
		salle.setLibelle("libele 10");
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception libelle null
	@Test(expected=SalleException.class)
	public void testUpdateExceptionLibelle() throws SalleException {
		salle.setId(20L);
		salle.setCapacite(100L);
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}
	
	//Exception capacite null
	@Test(expected=SalleException.class)
	public void testUpdateExceptionCapacite() throws SalleException {
		salle.setId(20L);
		salle.setLibelle("libele 10");
		salleService.updateSalle(salle);
		salle1 = salleService.getBySalle(salle);
		assertEquals(salle1.getId(), salle.getId());
	}

	@Test
	public void testDeleteSalle() throws SalleException {
		salle.setId(27L);
		Long id = salle.getId();
		salleService.deleteSalle(id);
		Optional<Salle> output = salleService.getById(id);
		assertEquals(Optional.empty(),output);
	}
	
	//Exception Id not found
	@Test(expected=SalleException.class)
	public void testDeleteExceptionId() throws SalleException {
		salle.setId(5L);
		Long id = salle.getId();
		salleService.deleteSalle(id);
		Optional<Salle> output = salleService.getById(id);
		assertEquals(Optional.empty(),output);
	}

}
