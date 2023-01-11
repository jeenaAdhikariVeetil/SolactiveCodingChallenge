/**
 * 
 */
package com.solactive.tick.consumer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.model.Tick;
import com.solactive.tick.model.TickReponse;

/**
 * @author Jeena A V
 *
 */

@Component
public class TickConsumer {

	static Map<String, List<Tick>> tickvaluesMap = new ConcurrentHashMap<>(50000, 1);

	public static Map<String, List<Tick>> getTickMap() {
		return tickvaluesMap;
	}

	@RabbitListener(queues = TickConstant.QUEUE_NAME)
	public void consumeTickValues(Tick tick) {
		tickvaluesMap.put(tick.getRic(), new TickReponse().addTickValues(tick, tickvaluesMap));

	}
}
