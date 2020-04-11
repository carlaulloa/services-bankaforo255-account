package com.app.aforo255.account.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aforo255.account.model.entity.Account;
import com.app.aforo255.account.model.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionEvents {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionEvents.class);
	
	private @Autowired IAccountService accountService;
	private @Autowired ObjectMapper objectMapper;
	
	public void processTransactionEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, 
		JsonProcessingException {
		double amount = 0;
		Account account = new Account();
		LOG.info("Before Process TransactionEvent {}", consumerRecord.value());
		Transaction transactionEvent = this.objectMapper.readValue(consumerRecord.value(), Transaction.class);
		LOG.info("TransactionEvent: {}", transactionEvent.getAccountId());
		account = this.accountService.findById(transactionEvent.getAccountId());
		LOG.info("Get Account: {}", account);
		switch (transactionEvent.getType()) {
			case "deposito":
				amount = account.getTotalAmount() + transactionEvent.getAmount();
				break;
			case "retiro":
				amount = account.getTotalAmount() - transactionEvent.getAmount();
				break;
			default:
				LOG.info("Invalid event type");
				break;
		}
		LOG.info("New amount: {}", amount);
		account.setTotalAmount(amount);
		this.accountService.save(account);
		
	}
	
}
