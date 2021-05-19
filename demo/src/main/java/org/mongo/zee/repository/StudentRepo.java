package org.mongo.zee.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepo {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	

}
