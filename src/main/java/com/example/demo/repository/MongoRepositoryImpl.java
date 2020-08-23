package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movie;

@Repository
public class MongoRepositoryImpl implements MongoRepository<Movie>{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Movie> getAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveObject(Movie object) {
		this.mongoTemplate.save(object);		
	}

	@Override
	public Movie getObject(String movieName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movieName").is(movieName));
		return (Movie)mongoTemplate.findOne(query,Movie.class);		
	}

	@Override
	public void deleteObject(String id) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void createCollection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropCollection() {
		// TODO Auto-generated method stub
		
	}

}
