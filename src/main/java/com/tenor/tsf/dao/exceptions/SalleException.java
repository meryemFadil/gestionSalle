package com.tenor.tsf.dao.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SalleException extends GsExceptions{

	
	private static final long serialVersionUID = 1L;
	public final Logger logger = LogManager.getLogger(SalleException.class);
	
	public SalleException(String message) {
		super(message);
		logger.error(message,this);	
	}

}
