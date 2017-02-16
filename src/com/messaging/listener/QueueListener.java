package com.messaging.listener;

import com.messaging.dto.QueueDetails;
import com.messaging.helper.QueueHelper;
import com.messaging.util.QueueUtil;
import com.rabbitmq.client.Channel;

public class QueueListener<T> {
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
