package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.build.MovieBuilder;
import com.example.demo.config.TestConfiguration;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.view.CreateMovieView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = TestConfiguration.class )
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@MockBean
	private MovieRepository movieRepository;	

	@Test
	public void createMovieTest(){

		Movie movie = new MovieBuilder()
				.buildWithId("Id_12345")
				.buildWithName("Gravity")
				.buildWithGenre("Sci-Fi")
				.buildWithReleaseYear(2015)
				.buildWithImdbRating(8.9)
				.build();

		when(movieRepository.createMovie(any())).thenReturn(movie);

		CreateMovieView createMovie = movieService.createMovie(movie);

		assertNotNull(createMovie);
		assertEquals("Id_12345", createMovie.getId());
		assertEquals("Gravity", createMovie.getMovieName());
		assertEquals("Sci-Fi",  createMovie.getGenre());
		assertEquals((Integer)2015, createMovie.getReleaseYear());
		assertEquals((Double)8.9, createMovie.getImdbRating());
	}

}
