package com.messaging.listener;

import com.rabbitmq.client.Channel;
/*
 * This class listens to the queue and processes the incoming messages
 */
public class QueueListener implements Runnable{
	private QueueDetails queueDetails = null;	
	private MessageProcessor msgProcessor = null;
	private boolean isStopped = false;
	private int secondsToSleep = 1000;	
	/*
	 * Constructor that takes queue details & the implementation for processing the incoming message 
	 */
	public QueueListener(QueueDetails queueDetails, MessageProcessor msgProcessor){
		this.queueDetails = queueDetails; 
		this.msgProcessor = msgProcessor;
	}
	
	/* 
	 * This method listens to the queue and processes the incoming messages
	 * @Param("queueDetails", details of the queue from which the messages have to read)
	 */
	public void listenToQueue(){
		try{
			QueueHelper helper = new QueueHelper();
			Channel c = QueueUtil.getChannel(queueDetails);	
			while(!isStopped){
				helper.listenForMessages(c, queueDetails.getQueueName(), msgProcessor);
				Thread.sleep(secondsToSleep);
			}						
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		listenToQueue();		
	}
	
	/*
	 * This function stops the thread that is listening to the queue
	 */
	public void exit(boolean flag){
		this.isStopped = flag;
	}
	
}
