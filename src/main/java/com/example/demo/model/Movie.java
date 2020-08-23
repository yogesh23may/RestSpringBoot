package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie {
	@Id
	String id;
	String movieName;
	String genre;
	Integer releaseYear;
	Double imdbRating;	
	
	public Movie() {
		super();		
	}
	
	public Movie(String movieName, String genre, Integer releaseYear, Double imdbRating) {
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.imdbRating = imdbRating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public Double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", genre=" + genre + ", releaseYear=" + releaseYear
				+ ", imdbRating=" + imdbRating + "]";
	}	
}
