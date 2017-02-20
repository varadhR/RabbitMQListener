package com.messaging.listener;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/*
 * This class creates a consumer and binds it to the queue, so as to read the messages  
 */
public class QueueHelper { 
	private MessageProcessor messageProcessor = new TextMessageProcessor();;
	/*
	 * This method creates a message and binds it to the queue to read the messages
	 * @Param("channel", used for sending messages to the AMQP Broker)
	 * @Param("queueName", the queue where the messages have to read )
	 */
	public void listenForMessages(Channel channel, String queueName){
		try {
			channel.basicConsume(queueName, true, new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] message)
				          throws IOException {
				      	// Message Processor implementation goes here.
						messageProcessor.processMessage(message);
				      }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
