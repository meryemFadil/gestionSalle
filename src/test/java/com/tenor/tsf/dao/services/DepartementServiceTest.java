package com.tenor.tsf.dao.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Departement;
import com.tenor.tsf.dao.exceptions.DepartementException;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class DepartementServiceTest {
	
	Departement departement = new Departement();
	Departement dept1 = new Departement();
	@Autowired
	DepartementService departementService ;
	
	@Test
	public void testfindAll() {
		departementService.findAll();
	}

	@Test
	public void testCreatedepartement() throws DepartementException {
		departement.setNom("Departement 1");
		log.info("Test Create : " +departement);
		 Departement departement1 = departementService.createDepartement(departement);
		log.info(departement1);
		 assertEquals(departement, departement1);
	}
	
	//Exception name of departement null
	@Test(expected=DepartementException.class)
	public void testCreateExceptionNomNull() throws DepartementException {
		log.info("Test Ko Create : "+departement);
		departement.setNom("");
		departementService.createDepartement(departement);
		Departement output = departementService.getByDept(departement);
		log.info("Test Ko Create: "+output);
		assertEquals(output, departement);
	}

	@Test
	public void testUpdateDepartement() throws DepartementException {
		departement.setId(21L);
		departement.setNom("Departement 2");
		log.info("Test Update: "+departement);
		 Departement departement1 = departementService.updateDepartement(departement);
		log.info("Test Update: "+departement1);
		assertEquals(departement.getId(),departement1.getId());
	}
	
	//Exception Id not found
	@Test(expected=DepartementException.class)
	public void testUpdateExceptionId() throws DepartementException {
		departement.setId(55L);
		departement.setNom("Departement 2");
		log.info("Test Ko Update:"+departement);
		 Departement departement1 = departementService.updateDepartement(departement);
		log.info("Test Ko Update: "+departement1);
		assertNotEquals(departement.getId(),departement1.getId());
	}

	@Test
	public void testDeleteDepartement() throws DepartementException {
		departement.setId(22L);
		Long id = departement.getId();
		departementService.deleteDepartement(id);
		Optional<Departement> output = departementService.getById(id);
		assertEquals(Optional.empty(),output);
	}
	
	//Exception Id not found
	@Test(expected=DepartementException.class)
	public void testDeleteExceptionId() throws DepartementException {
		departement.setId(5L);
		Long id = departement.getId();
		departementService.deleteDepartement(id);
		Optional<Departement> output = departementService.getById(id);
		assertEquals(Optional.empty(),output);
	}

}
