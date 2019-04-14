package com.mcs.parentalcontrol.domain;

import java.util.Optional;

public enum ParentControlLevels {

	UNIVERSAL("U", 0), 
	
	PARENTAL_GUIDANCE("PG", 1), 
	
	TWELVE("12", 2), 
	
	FIFTEEN("15", 3),
	
	EIGHTEEN("18", 4);
	
	private final String level;
	
	private final int rating;
	
	private ParentControlLevels(String level, int rating) {
		this.level = level;
		this.rating = rating;
	}
	
	public final String level() {
		return this.level;
	}
	
	public final int rating() {
		return rating;
	}
	
	public static Optional<ParentControlLevels> getControlLevel(String level) {
		Optional<ParentControlLevels> result = Optional.empty();
		for (ParentControlLevels parentControlLevel : values()) {
			if (parentControlLevel.level().equals(level)) {
				result = Optional.of(parentControlLevel);
				break;
			}			
		}	
		return result;
	}
}
