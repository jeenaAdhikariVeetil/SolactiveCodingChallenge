/**
 * 
 */
package com.solactive.tick.service;

import java.io.IOException;

import com.solactive.tick.model.Tick;
import com.solactive.tick.model.TickReponse;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Jeena A V
 *
 */
public interface TickService {

	/**
	 * 
	 * @param tick
	 * @return
	 */

	public void insertTickvalues(Tick tick);

	/**
	 * @param ric
	 * @return tickValues for specific ric
	 */

	public TickReponse lookupTicks(String ric);

	/**
	 * @param ric
	 * 
	 * @param httpServletResponse
	 * 
	 * @return export tickValues for specific ric
	 */
	public void exportToCSV(String ric,HttpServletResponse httpServletResponse) throws IOException;
	

}
