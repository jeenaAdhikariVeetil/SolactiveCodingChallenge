/**
 * 
 */
package com.solactive.tick.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solactive.tick.constant.TickConstant;
import com.solactive.tick.consumer.TickConsumer;
import com.solactive.tick.export.TickCSVExporter;
import com.solactive.tick.model.Tick;
import com.solactive.tick.model.TickReponse;
import com.solactive.tick.service.TickService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Jeena A V
 *
 */
@Service
public class TickServiceImpl implements TickService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	TickCSVExporter exporter;

	@Override
	public void insertTickvalues(Tick tick) {
		rabbitTemplate.convertAndSend(TickConstant.TOPIC_NAME, TickConstant.ROUTING_KEY, tick);

	}

	@Override
	public TickReponse lookupTicks(String ric) {
		List<Tick> ticks = TickConsumer.getTickMap().get(ric);
		TickReponse tickReposne = new TickReponse(ticks);
		return tickReposne;
	}

	@Override
	public void exportToCSV(String ric, HttpServletResponse httpServletResponse) throws IOException {
		exporter.export(ric, httpServletResponse);

	}

}
