package com.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.entity.SBI_Entity;
import com.mongodb.client.result.UpdateResult;
import com.repository.SBIRepository;
import com.request.SBIRequest;

@Service
public class SBIConsumer {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SBIRepository repository;
	
	@RabbitListener(queues = "sbi_queue")
	public String sbi_queue(SBIRequest request) {

		System.out.println(request);

		String accountNumber = request.getAccount_number();
		Double newBalance = request.getBalance();

		SBI_Entity account = repository.findByAccountNumber(accountNumber);
		Double orgl_balance = account.getBalance();

		Double final_bal = orgl_balance + newBalance;

		System.out.println("old balance " + account.getBalance());

		Query query = new Query(Criteria.where("account_number").is(accountNumber));
		Update update = new Update().set("balance", final_bal);

		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, SBI_Entity.class);
		System.out.println(updateFirst);

		System.out.println("received to SBI");
		return "balance updated in SBI BANK :: account number = "+ accountNumber +" SBI Balance : " + final_bal;
		
	}

	
}
