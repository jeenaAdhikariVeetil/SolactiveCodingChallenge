/**
 * 
 */
package com.solactive.tick.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Jeena A V
 *
 */
@Configuration
@EnableWebSecurity
public class TickSecuirtyConfig {

	@Value("${api.username}")
	String username;

	@Value("${api.password}")
	String password;
	
	@Value("${api.role}")
	String role;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.requestMatchers("/swagger-ui/**","/solactive/csv/ticks/**", "/solactive/ticks").permitAll()
			.requestMatchers("/solactive/ticks/**").hasRole("ADMIN")
			.anyRequest().authenticated().and().formLogin();
			
		http.csrf().disable();

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user =
			 User
				.withUsername(username)
				.password(encoder.encode(password))
				.roles(role)
				.build();

		return new InMemoryUserDetailsManager(user);
	}

}
