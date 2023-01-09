/**
 * 
 */
package com.solactive.tick.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.exception.InvalidTickException;
import com.solactive.tick.model.Tick;

/**
 * @author Jeena A V
 *
 */

@Component
public class TickvalueValidator {
	private static final Logger logger = LoggerFactory.getLogger(TickvalueValidator.class);

	/**
	 * validate the request
	 * 
	 * @param tick
	 * @param currentTimeStamp
	 * @throws InvalidTickException
	 */

	public void validate(Tick tick, long currentTimeStamp){

		if (tick.getTimeStamp() > currentTimeStamp) {
			logger.error(TickConstant.FUTURE_TIMESTAMP);
			throw new InvalidTickException(TickConstant.FUTURE_TIMESTAMP);
		}

		if (tick.getPrice() < 0) {
			logger.error(TickConstant.PRICE_LESS_THAN_ZERO);
			throw new InvalidTickException(TickConstant.PRICE_LESS_THAN_ZERO);
		}

		if (tick.getRic().isBlank()) {
			logger.error(TickConstant.RIC_EMPTY);
			throw new InvalidTickException(TickConstant.RIC_EMPTY);
		}
	}
}
