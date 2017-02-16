package com.messaging.util;

import java.io.IOException;

import com.messaging.dto.QueueDetails;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QueueUtil {
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

	private static Channel createChannel(Connection connection, QueueDetails details) {
		Channel channel = null;
		try {
			channel = connection.createChannel();
			channel.exchangeDeclare(details.getExchangeName(), "topic", true);
			channel.queueDeclare(details.getQueueName(), true, false, false, null);
			channel.queueBind(details.getQueueName(), details.getExchangeName(), details.getRoutingKey());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return channel;
	}

	private static ConnectionFactory getConnectionFactory(QueueDetails details) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(details.getUserName());
		factory.setPassword(details.getPassword());
		factory.setHost(details.getHostName());
		factory.setPort(Integer.parseInt(details.getPortNumber()));	
		return factory;
	}
	
	
}
