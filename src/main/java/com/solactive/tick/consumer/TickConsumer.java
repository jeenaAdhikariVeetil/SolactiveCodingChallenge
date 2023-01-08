/**
 * 
 */
package com.solactive.tick.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.solactive.tick.model.Tick;

/**
 * @author Jeena A V
 *
 */

@Component
public class TickConsumer {
	
	@RabbitListener(queues="tick_queue")
	public void consumeTickValues(Tick tick)
	{
		System.out.println(tick);
	}
}
