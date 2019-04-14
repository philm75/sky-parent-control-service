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