package com.joh.phms.controller;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.joh.phms.exception.CusDataIntegrityViolationException;
import com.joh.phms.exception.ItemExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ ItemExistsException.class })
	public String handleItemExistsException(HttpServletRequest request, ItemExistsException ex, Model model) {
		logger.info("handleItemExistsException->fired");
		logger.info("handleItemExistsException Occured:: URL=" + request.getRequestURL());
		model.addAttribute("message", ex.getMessage());
		return "itemExistsException";

	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public String handleSQLException(HttpServletRequest request, Exception ex, Model model) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());

		if (ex instanceof CusDataIntegrityViolationException) {
			CusDataIntegrityViolationException cex = (CusDataIntegrityViolationException) ex;
			logger.info("cex=" + cex);
			model.addAttribute("message", cex.getCustomMessage());
		}
		ex.printStackTrace();

		return "dataIntigrityException";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ EntityNotFoundException.class })
	public String handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
		logger.info("handleEntityNotFoundException occured:: URL=" + request.getRequestURL());
		logger.info("Entity refernece=" + ex.getMessage());

		return "entityNotFoundException";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ NoResultException.class })
	public String handleNoResultException(HttpServletRequest request, Exception ex) {
		logger.info("NoResultException occured:: URL=" + request.getRequestURL());
		return "noResultException";
	}

	// @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler({ Exception.class })
	// public String handleInternalServerError(HttpServletRequest request, Exception
	// ex) {
	// logger.info("internalServerError occured:: URL=" + request.getRequestURL());
	// return "internalServerError";
	// }
}