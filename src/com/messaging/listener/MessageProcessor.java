package com.messaging.listener;

/*
 * This interface has to be extended with custom implementation
 * for processing the incoming messages from the queue. 
 */
public interface MessageProcessor {
	/*
	 * This method processes the incoming messages from the queue
	 * @Param("message", incoming message from the queue)
	 */
	public void processMessage(byte[] message);
}
