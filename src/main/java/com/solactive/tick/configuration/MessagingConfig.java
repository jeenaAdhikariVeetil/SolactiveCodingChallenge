/**
 * 
 */
package com.solactive.tick.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.solactive.tick.constant.TickConstant;

/**
 * @author Jeena A V
 *
 */

@Configuration
public class MessagingConfig {

	@Bean
	public Queue queue() {
		return new Queue(TickConstant.QUEUE_NAME);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(TickConstant.TOPIC_NAME);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(TickConstant.ROUTING_KEY);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFcatory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFcatory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
