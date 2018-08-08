package com.joh.phms.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.joh.phms.domain.model.JsonResponse;
import com.joh.phms.domain.model.ValidationErrorD;
import com.joh.phms.exception.ItemNotAvaiableException;

@ControllerAdvice
public class RestErrorHandler {

	private static final Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ValidationErrorD processValidationError(MethodArgumentNotValidException ex) {
		System.out.println("processValidationError=>fired");
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		ValidationErrorD dto = new ValidationErrorD();
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = messageSource.getMessage(fieldError, null);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	@ExceptionHandler(ItemNotAvaiableException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponse itemNotAvaiableExceptionHandler(ItemNotAvaiableException ex) {
		logger.info("itemNotAvaiableExceptionHandler=>fired");

		JsonResponse jsonResponse = new JsonResponse();

		jsonResponse.setMessage(ex.getMessage());

		return jsonResponse;
	}
}