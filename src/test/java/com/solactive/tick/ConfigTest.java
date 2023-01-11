/**
 * 
 */
package com.solactive.tick;

import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.solactive.tick.consumer.TickConsumer;

/**
 * @author Jeena A V
 *
 */
@Configuration
@RabbitListenerTest
public class ConfigTest {

	@Bean
	public TickConsumer ticklistener() {
		return new TickConsumer();
	}

}
