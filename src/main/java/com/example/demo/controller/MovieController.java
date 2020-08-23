package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.ExceptionResponse;
import com.example.demo.exception.MovieNotFoundException;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import com.example.demo.view.CreateMovieView;
import com.example.demo.view.FindMovieView;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping(value="/create" , method = RequestMethod.POST)
	public ResponseEntity<?> createMovie(@RequestBody Movie movie){
		CreateMovieView createMovieView = null;
		try{
			if(null!=movie && 
					null!=movie.getMovieName() && 
					null!=movie.getGenre() && 
					null!=movie.getReleaseYear() &&
					null!=movie.getImdbRating()){
				createMovieView = movieService.createMovie(movie);
			}else{
				ExceptionResponse exceptionResponse = new ExceptionResponse();
				exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
				exceptionResponse.setErrorMessage("partial payload...");
				exceptionResponse.setExceptionHandlingLevel("method-level");
				return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
			}			
		}catch(Exception exception){
			//exception handling at method level
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			exceptionResponse.setErrorMessage(exception.getMessage());
			exceptionResponse.setExceptionHandlingLevel("method-level");
			return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(createMovieView,HttpStatus.CREATED);		
	}	

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("name") String name) throws MovieNotFoundException {        
		FindMovieView findMovieView = movieService.findByName(name); 
		return new ResponseEntity<>(findMovieView, HttpStatus.OK);		        
	}		
	
	//controller level exception handling for MovieNotFoundException
	@ExceptionHandler(value = MovieNotFoundException.class)  
	public ResponseEntity<?> handlingMovieNotFoundException(MovieNotFoundException exception){  
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		exceptionResponse.setErrorMessage(exception.getErrorMessage());
		exceptionResponse.setExceptionHandlingLevel("controller-level");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}	

}
