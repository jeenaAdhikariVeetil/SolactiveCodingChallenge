/**
 * 
 */
package com.solactive.tick.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.exception.InvalidTickException;
import com.solactive.tick.model.Tick;
import com.solactive.tick.model.TickReponse;
import com.solactive.tick.service.impl.TickServiceImpl;
import com.solactive.tick.validator.TickvalueValidator;

/**
 * @author Jeena A V
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TickControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	TickServiceImpl tickServiceImpl;

	@InjectMocks
	TickController tickController;

	@Test
	void contexLoads() throws Exception {
		assertThat(tickController).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void consumeTicksTest() throws Exception {

		StringBuilder ticks = new StringBuilder();
		ticks.append("TIMESTAMP=1673257904390|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ").append("\n")
				.append("TIMESTAMP=1673257904390|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=IBM.N").append("\n")
				.append("TIMESTAMP=1673257904390|PRICE=|CLOSE_PRICE=7.5|CURRENCY=EUR|RIC=AAPL.OQ");

		mockMvc.perform(post("/solactive/ticks").contentType(MediaType.TEXT_PLAIN_VALUE).content(ticks.toString()))
				.andDo(print()).andExpect(status().isCreated());

	}

	@Test
	public void lookupTicksTest() throws Exception {

		List<Tick> tickList = Stream.of(new Tick(1673257904390L, 5.24, 0, "EUR", "AAPL.OQ"),
				new Tick(1673257904390L, 7.25, 0, "EUR", "AAPL.OQ")).collect(Collectors.toList());
		TickReponse tickReponse = new TickReponse(tickList);
		when(tickServiceImpl.lookupTicks("AAPL.OQ")).thenReturn(tickReponse);
		ResponseEntity<TickReponse> outputTicks = tickController.lookupTicks("AAPL.OQ");
		assertThat(outputTicks.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test()
	public void invalidTickTest() {
		final long currentTimeStamp = System.currentTimeMillis();
		Tick tick = new Tick(1673257904390L, -5.24, 0, "EUR", "AAPL.OQ");
		TickvalueValidator tickvalueValidator = new TickvalueValidator();
		InvalidTickException invalidTickException = Assertions.assertThrows(InvalidTickException.class, () -> {
			tickvalueValidator.validate(tick, currentTimeStamp);
		});
		String expectedMessage = TickConstant.PRICE_LESS_THAN_ZERO;
		String actualMessage = invalidTickException.getMessage();

		Assertions.assertTrue(actualMessage.contains(expectedMessage));

	}

}
