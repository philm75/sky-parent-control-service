package com.mcs.parentalcontrol.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import com.mcs.parentalcontrol.service.impl.ParentalControlServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

	@Mock
	private MovieService movieService;
	
	ParentalControlService parentalControlService;
		
	@Before
	public void setup() {
		parentalControlService = new ParentalControlServiceImpl(movieService);
	}
	
	@Test
	public void testCheckCustomerControlLevel_IsValidLessThan() throws TitleNotFoundException, TechnicalFailureException {
		String customerControlLevel = "PG"; 
		String movieId = "3";
		
		Mockito.when(movieService.getParentalControlLevel(movieId)).thenReturn("U");
		boolean result = parentalControlService.checkCustomerControlLevel(customerControlLevel, movieId);
		
		assertThat(result, equalTo(true));	
	}
	
	@Test
	public void testCheckCustomerControlLevel_IsValidEqual() throws TitleNotFoundException, TechnicalFailureException {
		String customerControlLevel = "PG"; 
		String movieId = "3";
		
		Mockito.when(movieService.getParentalControlLevel(movieId)).thenReturn("PG");
		boolean result = parentalControlService.checkCustomerControlLevel(customerControlLevel, movieId);
		
		assertThat(result, equalTo(true));	
	}

	@Test
	public void testCheckCustomerControlLevel_IsInvalid() throws TitleNotFoundException, TechnicalFailureException {
		String customerControlLevel = "PG"; 
		String movieId = "3";
		
		Mockito.when(movieService.getParentalControlLevel(movieId)).thenReturn("15");
		boolean result = parentalControlService.checkCustomerControlLevel(customerControlLevel, movieId);
		
		assertThat(result, equalTo(false));			
	}
	
	@Test
	public void testCheckCustomerControlLevel_InvalidCustomerControlLevel() throws TitleNotFoundException, TechnicalFailureException {
		String customerControlLevel = "PG"; 
		String movieId = "3";
		
		Mockito.when(movieService.getParentalControlLevel(movieId)).thenReturn("YY");
		
		try {
			parentalControlService.checkCustomerControlLevel(customerControlLevel, movieId);			
		} catch (TechnicalFailureException e) {
			assertThat(e.getMessage(), equalTo("Parent Control level not found for movie id 3"));
		}	
	}
	
	@Test
	public void testCheckCustomerControlLevel_InvalidMovieControlLevel() throws TitleNotFoundException, TechnicalFailureException {
		String customerControlLevel = "XX"; 
		String movieId = "3";
		
		Mockito.when(movieService.getParentalControlLevel(movieId)).thenReturn("PG");
		
		try {
			parentalControlService.checkCustomerControlLevel(customerControlLevel, movieId);			
		} catch (TechnicalFailureException e) {
			assertThat(e.getMessage(), equalTo("Customer Parent Control level XX does not exist"));
		}	
	}	
}