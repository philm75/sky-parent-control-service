package com.mcs.parentalcontrol.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.mcs.parentalcontrol.domain.ParentControlLevels;
import com.mcs.parentalcontrol.service.MovieService;
import com.mcs.parentalcontrol.service.TechnicalFailureException;
import com.mcs.parentalcontrol.service.TitleNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {
	
	private static final Map<String, ParentControlLevels> movies = new HashMap<>();
	
	static {
		movies.put("1",  ParentControlLevels.UNIVERSAL);
		movies.put("2",  ParentControlLevels.UNIVERSAL);
		movies.put("3",  ParentControlLevels.PARENTAL_GUIDANCE);
		movies.put("4",  ParentControlLevels.PARENTAL_GUIDANCE);
		movies.put("5",  ParentControlLevels.TWELVE);
		movies.put("6",  ParentControlLevels.TWELVE);
		movies.put("7",  ParentControlLevels.FIFTEEN);
		movies.put("8",  ParentControlLevels.FIFTEEN);
		movies.put("9",  ParentControlLevels.EIGHTEEN);
		movies.put("10", ParentControlLevels.EIGHTEEN);		
	}
	
	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		if (movies.containsKey(movieId)) {
			return movies.get(movieId).level();
		} else {
			throw new TitleNotFoundException(String.format("Movie %s does not exist", movieId));
		}
	}
}