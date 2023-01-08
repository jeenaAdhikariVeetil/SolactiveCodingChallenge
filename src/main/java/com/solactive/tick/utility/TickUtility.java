/**
 * 
 */
package com.solactive.tick.utility;

import com.solactive.tick.model.Tick;

/**
 * @author Jeena A V
 *
 */
public class TickUtility {

	public static Tick getTickValues(String tick) {

		String[] tickValues = tick.split("\\|");
		long timeStamp = tickValues[0].split("=").length > 1 ? Long.parseLong(tickValues[0].split("=")[1]) : 0L;
		double price = tickValues[1].split("=").length > 1 ? Double.parseDouble(tickValues[1].split("=")[1]) : 0;
		double closePrice = tickValues[2].split("=").length > 1 ? Double.parseDouble(tickValues[2].split("=")[1]) : 0;
		String currency = tickValues[3].split("=").length > 1 ? tickValues[3].split("=")[1] : "";
		String ric = tickValues[4].split("=").length > 1 ? tickValues[4].split("=")[1] : "";
		return new Tick(timeStamp, price, closePrice, currency, ric);
	}

}
