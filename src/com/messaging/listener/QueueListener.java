package com.messaging.listener;

import com.rabbitmq.client.Channel;

/*
 * This class listens to the queue and processes the incoming messages
 */
public class QueueListener<T> {
	/*
	 * This method listens to the queue and processes the incoming messages
	 * @Param("queueDetails", details of the queue from which the messages have to read)
	 */
	public void listenToQueue(QueueDetails queueDetails){
		try{
			QueueHelper helper = new QueueHelper();
			Channel c = QueueUtil.getChannel(queueDetails);		
			helper.listenForMessages(c, queueDetails.getQueueName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
