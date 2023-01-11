/**
 * 
 */
package com.solactive.tick.constant;

/**
 * @author Jeena A V
 *
 */
public class TickConstant {
	
	public static final String QUEUE_NAME = "tick_queue";
	public static final String TOPIC_NAME = "tick_topic";
	public static final String ROUTING_KEY = "tick_routingKey";
	
	public static final String FUTURE_TIMESTAMP="Tick value of future timestamp is not allowed";
	public static final String PRICE_LESS_THAN_ZERO="Price can not be less than Zero";
	public static final String RIC_EMPTY="RIC can not be a blank value";
	public static final String INVALID_TICKS="Please provide the valid tick values";
	public static final String EXPORT_ERROR="Error occurred during export to CSV";
	public static final String EMPTY_EXPORT="No Data to export";
	
	
}
