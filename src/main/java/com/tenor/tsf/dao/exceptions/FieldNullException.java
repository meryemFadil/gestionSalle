package com.tenor.tsf.dao.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FieldNullException extends GsExceptions {

	private static final long serialVersionUID = 1L;
	public final Logger logger = LogManager.getLogger(FieldNullException.class);
	
	public FieldNullException(String message) {
		super(message);
		logger.error(message,this);	
	}
}
