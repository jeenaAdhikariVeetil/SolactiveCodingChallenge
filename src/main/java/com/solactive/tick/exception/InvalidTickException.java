/**
 * 
 */
package com.solactive.tick.exception;

/**
 * @author Jeena A V
 *
 */
public class InvalidTickException extends RuntimeException {

	private static final long serialVersionUID = 1307997365679671541L;

	public InvalidTickException(String message) {
		super(message);
	}

	public InvalidTickException(Throwable cause) {
		super(cause);
	}

	public InvalidTickException(String message, Throwable cause) {
		super(message, cause);
	}
}
