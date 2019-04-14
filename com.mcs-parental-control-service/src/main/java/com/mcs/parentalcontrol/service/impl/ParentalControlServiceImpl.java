package com.mcs.parentalcontrol.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mcs.parentalcontrol.domain.ParentControlLevels;
import com.mcs.parentalcontrol.service.MovieService;
import com.mcs.parentalcontrol.service.ParentalControlService;
import com.mcs.parentalcontrol.service.TechnicalFailureException;
import com.mcs.parentalcontrol.service.TitleNotFoundException;

@Service
public class ParentalControlServiceImpl implements ParentalControlService {

	private final MovieService movieService;
	
	public ParentalControlServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@Override
	public boolean checkCustomerControlLevel(String customerParentControlLevel, String movieId) throws TitleNotFoundException, TechnicalFailureException {		
		String movieLevel = movieService.getParentalControlLevel(movieId);	
		
		Optional<ParentControlLevels> movieControlLevel = ParentControlLevels.getControlLevel(movieLevel);
		Optional<ParentControlLevels> customerControlLevel = ParentControlLevels.getControlLevel(customerParentControlLevel);
			
		if (!movieControlLevel.isPresent()) {
			throw new TechnicalFailureException(String.format("Parent Control level not found for movie id %s", movieId));
		}
				
		if (!customerControlLevel.isPresent()) {
			throw new TechnicalFailureException(String.format("Customer Parent Control level %s does not exist", customerParentControlLevel));			
		}				
		return (movieControlLevel.get().rating() <= customerControlLevel.get().rating());
	}

}

/*

Parental Control level A string
e.g. “PG”

If the parental control level of the
movie is equal to or less than the
customer’s preference indicate to
the caller that the customer can
watch the movie


TitleNotFound exception The movie
service could not
find the given
movie
Indicate the error to the calling
client.


Technical failure exception System error Indicate that the customer cannot
watch this movie


The service should accept as input the customer’s parental control level
preference and a movie id. If the customer is able to watch the movie the
ParentalControlService should indicate this to the calling client.



*/
