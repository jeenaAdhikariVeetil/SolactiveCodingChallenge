package com.solactive.tick.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solactive.tick.service.impl.TickServiceImpl;
import com.solactive.tick.utility.TickUtility;
import com.solactive.tick.validator.TickvalueValidator;

@RestController
@RequestMapping("/solactive")
public class TickController {

	@Autowired
	private TickvalueValidator tickvalueValidator;

	@Autowired
	private TickServiceImpl tickServiceImpl;

	@PostMapping(path = "/ticks", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> consumeTicks(@RequestBody String tickValue) {
		Scanner scanner = new Scanner(tickValue);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// validate the tick data

			// insert the tick data into in memory storage
			tickServiceImpl.insertTickvalues(TickUtility.getTickValues(line));

		}
		scanner.close();

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
