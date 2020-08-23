package com.example.demo.view;

import com.example.demo.model.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CreateMovieView {
	
	private Movie movie;

	public CreateMovieView(Movie movie) {
		super();
		this.movie = movie;
	}
	
	public String getId() {
		return this.movie.getId();
	}
	
	public String getMovieName() {
		return this.movie.getMovieName();
	}
	
	public String getGenre() {
		return this.movie.getGenre();
	}
	
	public Integer getReleaseYear() {
		return this.movie.getReleaseYear();
	}
	
	public Double getImdbRating() {
		return this.movie.getImdbRating();
	}
	
}
