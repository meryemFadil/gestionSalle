package com.tenor.tsf.dao.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadRequestException extends GsExceptions {

	
	private static final long serialVersionUID = 1L;
	public final Logger logger = LogManager.getLogger(BadRequestException.class);

	public BadRequestException(String message) {
		super(message);
		logger.error(message,this);	

	}

}
