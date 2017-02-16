package com.messaging.helper;

import java.io.IOException;

import com.messaging.processor.MessageProcessor;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class QueueHelper<T> {
	
	
	public T listenForMessages(Channel channel, String queueName){
		try {
			channel.basicConsume(queueName, true, new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] message)
				          throws IOException {
				      	new MessageProcessor().processMessage(message);
				      }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
