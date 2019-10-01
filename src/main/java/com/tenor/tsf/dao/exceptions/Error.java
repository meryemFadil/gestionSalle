package com.tenor.tsf.dao.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

	private String msg;
	private HttpStatus status;
	private LocalDateTime dateTime;
	private String logRef;
}
