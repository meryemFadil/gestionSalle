package com.tenor.tsf.dao.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaterielException extends GsExceptions {

	private static final long serialVersionUID = 1L;
	public final Logger logger = LogManager.getLogger(MaterielException.class);
	
	public MaterielException(String message) {
		super(message);
		logger.error(message,this);	
		
	}

}
