package com.tenor.tsf.dao.services;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tenor.tsf.dao.entity.Reclamation;
import com.tenor.tsf.dao.entity.Salle;
import com.tenor.tsf.dao.entity.Statut;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.BadRequestException;
import com.tenor.tsf.dao.exceptions.FieldNullException;
import com.tenor.tsf.dao.exceptions.NoContentException;
import com.tenor.tsf.dao.exceptions.NotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReclamationServicesTest {

	Reclamation reclamation = new Reclamation();
	Reclamation reclamation1 = new Reclamation();
	@Autowired
	ReclamationService reclamService;
	Salle salle = new Salle();
	User user = new User();
	Statut statut = Statut.INPROGRESS;
	LocalDate date = LocalDate.now();

	@Test
	public void testfindAll() throws NoContentException {
		reclamService.findAll();
	}

	@Test
	public void testCreateReclamation() throws BadRequestException, FieldNullException, NotFoundException {
		user.setId(12L);
		salle.setId(25L);
		reclamation.setMessage("msg");
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	// Exception message null
	@Test(expected = FieldNullException.class)
	public void testCreateExceptionMsgNull() throws BadRequestException, FieldNullException, NotFoundException {
		user.setId(20L);
		salle.setId(12L);
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	// Exception statut null
	@Test(expected = FieldNullException.class)
	public void testCreateExceptionStatutNull() throws BadRequestException, FieldNullException, NotFoundException {
		user.setId(20L);
		salle.setId(12L);
		reclamation.setMessage("message");
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	// Exception date
	@Test(expected = BadRequestException.class)
	public void testCreateExceptionDate() throws BadRequestException, FieldNullException, NotFoundException {
		LocalDate date1 = LocalDate.of(2019, 7, 16);
		user.setId(20L);
		salle.setId(12L);
		reclamation.setMessage("message");
		reclamation.setStatut(statut);
		reclamation.setDate(date1);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	// Exception Id User not found
	@Test(expected = NotFoundException.class)
	public void testCreateExceptionIdUser() throws BadRequestException, FieldNullException, NotFoundException {
		user.setId(300L);
		salle.setId(12L);
		reclamation.setMessage("message");
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	// Exception Id salle not found
	@Test(expected = NotFoundException.class)
	public void testCreateExceptionIdSalle() throws BadRequestException, FieldNullException, NotFoundException {
		user.setId(20L);
		salle.setId(392L);
		reclamation.setMessage("message");
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		Reclamation reclamation1 = reclamService.createReclamation(reclamation);
		assertEquals(reclamation, reclamation1);
	}

	@Test
	public void testUpdateReclamation() throws BadRequestException, NotFoundException, FieldNullException {
		user.setId(20L);
		salle.setId(12L);
		reclamation.setId(7L);
		reclamation.setMessage("message");
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamService.updateReclamation(reclamation);
		reclamation1 = reclamService.getByReclamation(reclamation);
		assertEquals(reclamation1.getId(), reclamation.getId());
	}

	// Exception Id Reclamation not found
	@Test(expected = NotFoundException.class)
	public void testUpdateExceptionIdReclam() throws BadRequestException, NotFoundException, FieldNullException {
		user.setId(20L);
		salle.setId(12L);
		reclamation.setId(77L);
		reclamation.setMessage("message 1");
		reclamation.setStatut(statut);
		reclamation.setDate(date);
		reclamation.setSalle(salle);
		reclamation.setUser(user);
		reclamService.updateReclamation(reclamation);
		reclamation1 = reclamService.getByReclamation(reclamation);
		assertEquals(reclamation1.getId(), reclamation.getId());
	}

	@Test
	public void testDeleteReclamation() throws NotFoundException {
		reclamation.setId(6L);
		Long id = reclamation.getId();
		reclamService.deleteReclamation(id);
		Optional<Reclamation> output = reclamService.getById(id);
		assertEquals(Optional.empty(), output);
	}

	// Exception Id not found
	@Test(expected = NotFoundException.class)
	public void testDeleteExceptionId() throws NotFoundException {
		reclamation.setId(521L);
		Long id = reclamation.getId();
		reclamService.deleteReclamation(id);
		Optional<Reclamation> output = reclamService.getById(id);
		assertEquals(Optional.empty(), output);
	}

}
