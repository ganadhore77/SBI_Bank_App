package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.producer.SBIProducer;
import com.request.SBIRequest;

@RestController
public class Send_Money_From_SBI {

	@Autowired
	private SBIProducer producer;
	
	@PostMapping("/sendMoneyFromSBI")
	public ResponseEntity<String>  sendMoney(@RequestBody SBIRequest request){
		
		String bank_name = request.getBank_name();
		
		if(bank_name.equals("hdfc")) {
			producer.send_to_hdfc(request);
		}
		
		if(bank_name.equals("icici")) {
			producer.send_to_icici(request);
		}
		
		if(bank_name.equals("axis")) {
			producer.send_to_axis(request);
		}
		
		return new ResponseEntity<>("Money send from Account Number :"+request.getYour_bank_account_number(),HttpStatus.OK);
	}
	
}
