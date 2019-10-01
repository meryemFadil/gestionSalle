package com.tenor.tsf.dao.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenor.tsf.Repository.DepartementRepository;
import com.tenor.tsf.Repository.UserRepository;
import com.tenor.tsf.dao.entity.User;
import com.tenor.tsf.dao.exceptions.UserException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartementRepository departementRepository;

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User createUser(User user) throws UserException {
		Validate.notNull(user, "User cannot be null!");
		log.info("Create: "+user);
		if (user.getNom() == null) {
			throw new UserException("field lastname cannot be null!");
		} else if (user.getPrenom() == null) {
			throw new UserException("field name cannot be null!");
		} else if (user.getEmail() == null) {
			throw new UserException("the field email cannot be null!");
		} else if (user.getLogin() == null) {
			throw new UserException("the field login cannot be null!");
		} else if (user.getPassword() == null) {
			throw new UserException("the field password cannot be null!");
		} else if (!departementRepository.findById(user.getDepartement().getId()).isPresent()) {
			throw new UserException("Departement not found");
		} else {
			return userRepository.save(user);
		}
	}

	public User updateUser(User user) throws UserException {
		Validate.notNull(user, "User cannot be null!");
		log.info("update: "+user);
		if (user.getNom() == null) {
			throw new UserException("field lastname cannot be null!");
		} else if (user.getPrenom() == null) {
			throw new UserException("field name cannot be null!");
		} else if (user.getEmail() == null) {
			throw new UserException("the field email cannot be null!");
		} else if (user.getLogin() == null) {
			throw new UserException("the field login cannot be null!");
		} else if (user.getPassword() == null) {
			throw new UserException("the field password cannot be null!");
		} else if (!userRepository.findById(user.getId()).isPresent()) {
			throw new UserException("User not found!");
		} else if (!departementRepository.findById(user.getDepartement().getId()).isPresent()) {
			throw new UserException("Departement not found");
		} else {
			return userRepository.save(user);
		}
	}

	public void deleteUser(Long id) throws UserException {
		Validate.notNull(id, "L'id cannot be null");
		log.info("Delete: "+id);
		if (!userRepository.findById(id).isPresent()) {
			throw new UserException("User not found!");
		} else {
			userRepository.deleteById(id);
		}
	}

	public Optional<User> getById(Long id) {
		log.info("getById: "+id);
		return userRepository.findById(id);
	}

	public User getByUser(User user) {
		log.info("getByUser: "+user);
		user = userRepository.findById(user.getId()).get();
		return user;
	}

}
