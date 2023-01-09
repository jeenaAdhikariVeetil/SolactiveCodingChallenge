package com.solactive.tick.controller;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solactive.tick.model.Tick;
import com.solactive.tick.model.TickReponse;
import com.solactive.tick.service.TickService;
import com.solactive.tick.utility.TickUtility;
import com.solactive.tick.validator.TickvalueValidator;
import com.sun.net.httpserver.Authenticator.Success;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Jeena A V
 *
 */

@RestController
@RequestMapping("/solactive")
public class TickController {

	@Autowired
	private TickvalueValidator tickvalueValidator;

	@Autowired
	private TickService tickService;

	/**
	 * 
	 * @param tick
	 * @return HTTP status 201 when tick is consumed and HTTP status 204 when tick
	 *         is invalid
	 */

	@PostMapping(path = "/ticks", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Success> consumeTicks(@RequestBody String ticks) {

		final long currentTimeStamp = System.currentTimeMillis();
		Stream<String> lines = ticks.lines();

		lines.parallel().map(line -> new String(line)).forEach(line -> {

			Tick tick = TickUtility.getTickValues(line);
			tickvalueValidator.validate(tick, currentTimeStamp);
			tickService.insertTickvalues(tick);

		});

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param ric
	 * @return HTTP status 200 when tick values are retried
	 */

	@GetMapping("/ticks/{ric}")
	public ResponseEntity<TickReponse> lookupTicks(@PathVariable String ric) {
		return new ResponseEntity<TickReponse>(tickService.lookupTicks(ric), HttpStatus.OK);
	}

	/**
	 * 
	 * @param tick
	 * 
	 * @param httpServletResponse
	 * 
	 * @return HTTP status 403 when there are errors during export
	 */

	@GetMapping("/ticks/csv/{ric}")
	public void exportToCSV(@PathVariable String ric, HttpServletResponse httpServletResponse) throws IOException {
		tickService.exportToCSV(ric, httpServletResponse);
	}
}
