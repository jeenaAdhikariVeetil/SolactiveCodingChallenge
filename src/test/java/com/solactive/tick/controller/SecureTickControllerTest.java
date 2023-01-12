/**
 * 
 */
package com.solactive.tick.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import com.solactive.tick.service.TickService;
import com.solactive.tick.validator.TickvalueValidator;

/**
 * @author Jeena A V
 *
 */
@WebMvcTest(TickController.class)
public class SecureTickControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TickvalueValidator tickvalueValidator;

	@MockBean
	private TickService tickService;

	@Test
	public void secureTickControllerTest() throws Exception {

		this.mockMvc.perform(get("/solactive/ticks/**").contentType(MediaType.APPLICATION_JSON)
				.with(csrf()).with(SecurityMockMvcRequestPostProcessors.user("admin"))).
				andExpect(status().isOk());
	}

	@Test
	public void shouldRejectCreatingReviewsWhenUserIsAnonymous() throws Exception {
		this.mockMvc.perform(
				get("/solactive/ticks/**").contentType(MediaType.APPLICATION_JSON)
				.content("AAP.Q").with(csrf()))
				.andExpect(status().isUnauthorized());
	}

}
