/**
 * 
 */
package com.solactive.tick.advice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.exception.InvalidTickException;

/**
 * @author Jeena A V
 *
 */

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidTickException.class)
	public ResponseEntity<String> handleException(InvalidTickException ex) {
		return new ResponseEntity<String>(TickConstant.INVALID_TICKS, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleException(IOException ex) {
		return new ResponseEntity<String>(TickConstant.EXPORT_ERROR, HttpStatus.FORBIDDEN);
	}

}
