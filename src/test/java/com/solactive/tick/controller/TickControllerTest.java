/**
 * 
 */
package com.solactive.tick.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solactive.tick.model.Tick;

/**
 * @author Jeena A V
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TickControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TickController tickController;

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
		StringBuilder ticks = new StringBuilder();
		ticks.append("TIMESTAMP=1673257904390|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ").append("\n")
				.append("TIMESTAMP=1673257904390|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=IBM.N").append("\n")
				.append("TIMESTAMP=1673257904390|PRICE=|CLOSE_PRICE=7.5|CURRENCY=EUR|RIC=AAPL.OQ");

		mockMvc.perform(post("/solactive/ticks").contentType(MediaType.TEXT_PLAIN_VALUE).content(ticks.toString()));

		mockMvc.perform(get("/solactive/ticks/AAPL.OQ")).andExpect(status().isOk());
	}

}
