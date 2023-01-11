/**
 * 
 */
package com.solactive.tick.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.consumer.TickConsumer;
import com.solactive.tick.model.Tick;
import com.solactive.tick.service.impl.TickServiceImpl;

/**
 * @author Jeena A V
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TickServiceImplTest {

	@InjectMocks
	TickServiceImpl tickServiceImpl;

	@Autowired
	private RabbitListenerTestHarness harness;

	@Mock
	RabbitTemplate rabbitTemplate;

	@Test
	public void insertTickvaluesTest() {

		Tick tick = new Tick(1673257904390L, 5.24, 0, "EUR", "AAPL.OQ");
		doNothing().when(rabbitTemplate).convertAndSend(TickConstant.QUEUE_NAME, tick);
		TickConsumer tickConsumer = this.harness.getSpy(TickConstant.ROUTING_KEY);
		Assertions.assertNotNull(tickConsumer);
		verify(tickConsumer).consumeTickValues(tick);

	}

}
