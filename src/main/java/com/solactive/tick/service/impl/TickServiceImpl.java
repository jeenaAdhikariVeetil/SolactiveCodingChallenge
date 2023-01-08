/**
 * 
 */
package com.solactive.tick.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solactive.tick.model.Tick;
import com.solactive.tick.service.TickService;

/**
 * @author Jeena A V
 *
 */
@Service
public class TickServiceImpl implements TickService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void insertTickvalues(Tick tick) {
		rabbitTemplate.convertAndSend("tick_topic", "tick_routingKey", tick);

	}

}
