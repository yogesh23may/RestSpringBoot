package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.ExceptionResponse;
import com.example.demo.exception.MovieNotFoundException;
import com.example.demo.model.Movie;

@Repository
public class MovieRepository {
	
	@Autowired
	MongoRepositoryImpl mongoRepositoryImpl;	

	public Movie createMovie(Movie movie) {
		this.mongoRepositoryImpl.saveObject(movie);
		return movie;	
	}
	
	public Movie getMovie(String name)throws MovieNotFoundException{
		Movie movie = this.mongoRepositoryImpl.getObject(name);
		if(null==movie){
			throw new MovieNotFoundException("movie name doesn't exist in the database");
		}
		return movie;
	}
}
