/**
 * 
 */
package com.solactive.tick.exception;

/**
 * @author Jeena A V
 *
 */
public class NoTicksAvailableException extends RuntimeException {

	private static final long serialVersionUID = -5751035735640047988L;

	public NoTicksAvailableException(String message) {
		super(message);
	}

	public NoTicksAvailableException(Throwable cause) {
		super(cause);
	}

	public NoTicksAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
