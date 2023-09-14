package com.zfinance.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zfinance.dto.response.ErrorResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), ex.getParams()));
	}

	@ExceptionHandler(DataNotFoundException.class)
	protected ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
		logger.error(ex);
		return ResponseEntity.badRequest().body(new ErrorResponse("error_invalidRequestParams"));
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex,
			WebRequest request) throws Exception {
		logger.error(ex);
		return ResponseEntity.badRequest().body(new ErrorResponse("error_invalidRequestParams"));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		logger.error(ex);
		if (ex.getCause() instanceof SQLIntegrityConstraintViolationException) {
			return ResponseEntity.badRequest().body(new ErrorResponse("error_invalidRequestParams"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResponse("error_general"));
		}
	}

	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ResponseEntity<Object> handleDataAccessResourceFailureException(final DataAccessResourceFailureException ex,
			final WebRequest request) {
		logger.error(ex);
		return ResponseEntity.badRequest().body(new ErrorResponse("error_databaseConnectionFailed"));
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error(ex);
		return ResponseEntity.internalServerError().body(new ErrorResponse("error_general"));
	}
}
