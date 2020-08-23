package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.MovieNotFoundException;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.view.CreateMovieView;
import com.example.demo.view.FindMovieView;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	public CreateMovieView createMovie(Movie movie){
		Movie movieCreated = this.movieRepository.createMovie(movie);
		return new CreateMovieView(movieCreated);
	}

	public FindMovieView findByName(String name) throws MovieNotFoundException {
		  Movie movie = this.movieRepository.getMovie(name);
		  return new FindMovieView(movie);
	}
}
