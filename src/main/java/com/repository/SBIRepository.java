package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.SBI_Entity;

@Repository
public interface SBIRepository extends MongoRepository<SBI_Entity, String> {

	@Query("{'account_number': ?0}")
	SBI_Entity findByAccountNumber(String accountNumber);
	
}
