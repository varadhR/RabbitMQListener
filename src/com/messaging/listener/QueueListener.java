package com.messaging.listener;

import com.rabbitmq.client.Channel;
/*
 * This class listens to the queue and processes the incoming messages
 */
public class QueueListener implements Runnable{
	private QueueDetails queueDetails = null;	
	/*
	 * Constructor that takes queue details & the implementation for processing the incoming message 
	 */
	public QueueListener(QueueDetails queueDetails){
		this.queueDetails = queueDetails; 
	}
	
	/* 
	 * This method listens to the queue and processes the incoming messages
	 * @Param("queueDetails", details of the queue from which the messages have to read)
	 */
	public void listenToQueue(){
		try{
			QueueHelper helper = new QueueHelper();
			Channel c = QueueUtil.getChannel(queueDetails);	
			helper.listenForMessages(c, queueDetails.getQueueName());			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		listenToQueue();		
	}		
	
}
