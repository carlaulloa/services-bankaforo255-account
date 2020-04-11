package com.app.aforo255.account.kafka.consumer.listerner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.app.aforo255.account.service.TransactionEvents;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class TransactionEventsConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionEventsConsumer.class);
	
	private @Autowired TransactionEvents transactionEvents;
	
	@KafkaListener(topics= {"transaction-events"})
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, 
		JsonProcessingException {
		LOG.info("consumer record account {}", consumerRecord);
		this.transactionEvents.processTransactionEvent(consumerRecord);
	}

}
