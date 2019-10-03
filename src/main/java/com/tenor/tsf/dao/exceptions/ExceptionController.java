package com.tenor.tsf.dao.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler({FieldNullException.class})
	public final ResponseEntity<Object> FieldNullException(FieldNullException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NotFoundException.class})
	public final ResponseEntity<Object> NotFoundException(NotFoundException e){
		Error error = new Error(e.getMessage(),HttpStatus.NOT_FOUND,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({BadRequestException.class})
	public final ResponseEntity<Object> BadRequestException(BadRequestException e){
		Error error = new Error(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NoContentException.class})
	public final ResponseEntity<Object> NoContentException(NoContentException e){
		Error error = new Error(e.getMessage(),HttpStatus.NO_CONTENT,LocalDateTime.now(),
				e.getLocalizedMessage());
		return new ResponseEntity<Object>(error,HttpStatus.NO_CONTENT);
	}
}
