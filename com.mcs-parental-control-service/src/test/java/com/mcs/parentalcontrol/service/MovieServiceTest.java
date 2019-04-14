package com.mcs.parentalcontrol.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mcs.parentalcontrol.service.impl.MovieServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(JUnit4.class)
public class MovieServiceTest {

	private MovieService movieService;
	
	@Before
	public void setup() {
		this.movieService = new MovieServiceImpl();
	}
	
	@Test
	public void testGetParentalControlLevel_MovieExists() throws TitleNotFoundException, TechnicalFailureException {
		String movieId = "3";
		String controlLevel = movieService.getParentalControlLevel(movieId);
		
		assertThat(controlLevel, equalTo("PG"));
	}
	
	@Test
	public void testGetParentalControlLevel_TitleNotFoundException() throws TitleNotFoundException, TechnicalFailureException {
		String movieId = "XYZ";
		try {
			movieService.getParentalControlLevel(movieId);
		} catch (TitleNotFoundException e) {
			assertThat(e.getMessage(), equalTo("Movie XYZ does not exist"));			
		}
	}	
}