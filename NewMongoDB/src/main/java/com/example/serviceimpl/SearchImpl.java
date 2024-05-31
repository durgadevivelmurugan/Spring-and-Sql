package com.example.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.SearchRepo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@Component
public class SearchImpl implements SearchRepo {

	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
	public List<User> findByText(String text) {
		
     final List<User>user= new ArrayList<>();	

	MongoDatabase database = client.getDatabase("School");
	MongoCollection<Document> collection = database.getCollection("students");
	//Arrays.asList(to create a list containing a single Document object.)
	AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
	    new Document("index", "default")
	            .append("text", 
	    new Document("query", text)
	                .append("path", "place"))), 
			
	    new Document("$sort", 
	    new Document("age", 1L))));
	//results of the MongoDB aggregation and converting each Document object to a User object using the MongoConverter
	result.forEach(doc->user.add(converter.read(User.class, doc)));
	//user.add(...)[ Finally, you are adding each converted User object to a list named user.]
		return user;
	}

}
