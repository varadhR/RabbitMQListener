package com.messaging.listener;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/*
 * This is a utility class used to get the queue related parameters
 */
public class QueueUtil {
	/*
	 *  Used to get the channel for sending the AMPQ details to the broker.
	 *  @Param("details", details of the queue from where the messages have to be read)
	 *  @Return("Channel", channel for sending the AMPQ details to the broker)
	 */
	public static Channel getChannel(QueueDetails details) {
		 Channel channel= null;
		try {
			Connection connection = getConnectionFactory(details).newConnection();
			channel = createChannel(connection, details);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}

	/*
	 * Used to create the channel with given connection and queue details
	 */
	private static Channel createChannel(Connection connection, QueueDetails details) {
		Channel channel = null;
		try {
			channel = connection.createChannel();
			channel.queueDeclare(details.getQueueName(), true, false, false, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return channel;
	}

	/*
	 * Creates connection for a given queue
	 */	
	private static ConnectionFactory getConnectionFactory(QueueDetails details) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(details.getHostName());	
		return factory;
	}
	
	
}
