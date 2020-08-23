package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.build.MovieBuilder;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import com.example.demo.view.CreateMovieView;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class, secure = false)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService movieService;
	
	@Test
	public void createMovieTest_CorrectPayload() throws Exception{		
		Movie movie = new MovieBuilder()				
				.buildWithName("Gravity")
				.buildWithGenre("Sci-Fi")
				.buildWithReleaseYear(2015)
				.buildWithImdbRating(8.9)
				.build();		
		
		ObjectMapper mapper = new ObjectMapper();
		String movieAsJSONPayload = mapper.writeValueAsString(movie);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/create")
				.accept(MediaType.APPLICATION_JSON).content(movieAsJSONPayload)
				.contentType(MediaType.APPLICATION_JSON);
		
		movie.setId("Id_12345");
		Mockito.when(movieService.createMovie(any())).thenReturn(new CreateMovieView(movie));
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());	
		
		Movie createdMovie = mapper.readValue(response.getContentAsString(), Movie.class);
			
		assertNotNull(createdMovie);
		assertEquals(movie.getId(), createdMovie.getId());
		assertEquals(movie.getMovieName(), createdMovie.getMovieName());
		assertEquals(movie.getReleaseYear(), createdMovie.getReleaseYear());
		assertEquals(movie.getImdbRating(), createdMovie.getImdbRating());
	}
	
	@Test
	public void createMovieTest_PartialPayload() throws Exception{		
		Movie movie = new MovieBuilder()				
				.buildWithGenre("Sci-Fi")
				.buildWithReleaseYear(2015)
				.buildWithImdbRating(8.9)
				.build();		
		
		ObjectMapper mapper = new ObjectMapper();
		String movieAsJSONPayload = mapper.writeValueAsString(movie);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/create")
				.accept(MediaType.APPLICATION_JSON).content(movieAsJSONPayload)
				.contentType(MediaType.APPLICATION_JSON);		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());		
	}
	
}
