package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.SBI_Entity;
import com.repository.SBIRepository;

@RestController
public class SBI_New_Acoount_Controller {

	
	@Autowired
	private SBIRepository sBIRepository;
		
	@PostMapping("/addNewToSBI")
	public ResponseEntity<String>  addNewAccout(@RequestBody SBI_Entity entity){
		SBI_Entity save = sBIRepository.save(entity);
		System.out.println(save);
		return new ResponseEntity<>("Account Open succesfully",HttpStatus.OK);
	}
}
