/**
 * 
 */
package com.solactive.tick.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Jeena A V
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecureTickControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
    private MockMvc mvc;
    
    
    @BeforeEach
    public void setup()
    {
    	mvc=MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }
	
	@WithMockUser("/mockuser")
	@Test
	public void secureTickControllerTest() throws Exception
	{
		this.mvc.perform(get("/solactive/ticks/**").with(csrf()).
				contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk());
		
	}
	
	

}
