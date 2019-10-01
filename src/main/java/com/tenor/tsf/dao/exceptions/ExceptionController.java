package com.tenor.tsf.dao.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler({DepartementException.class})
	public final ResponseEntity<Object> DepartementException(DepartementException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({MaterielException.class})
	public final ResponseEntity<Object> MaterielException(MaterielException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ReclamationException.class})
	public final ResponseEntity<Object> ReclamationException(ReclamationException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ReservationException.class})
	public final ResponseEntity<Object> ReservationException(ReservationException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({SalleException.class})
	public final ResponseEntity<Object> SalleException(SalleException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({UserException.class})
	public final ResponseEntity<Object> UserException(UserException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
}
