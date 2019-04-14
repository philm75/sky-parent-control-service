package com.mcs.parentalcontrol.domain;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(JUnit4.class)
public class ParentControlLevelsTest {
	
	@Test
	public void testGetControlLevelWithNull() {
		Optional<ParentControlLevels> result = ParentControlLevels.getControlLevel(null);
		assertThat(result.isPresent(), equalTo(false));		
	}
	
	@Test
	public void testGetControlLevelWithEmptyString() {
		Optional<ParentControlLevels> result = ParentControlLevels.getControlLevel("");	
		assertThat(result.isPresent(), equalTo(false));	
	}
	
	@Test
	public void testGetControlLevelWithInValidControlLevel() {
		Optional<ParentControlLevels> result = ParentControlLevels.getControlLevel("XYZ");	
		assertThat(result.isPresent(), equalTo(false));		
	}
	
	@Test
	public void testGetControlLevelWithValidControlLevel() {
		Optional<ParentControlLevels> result = ParentControlLevels.getControlLevel("PG");		
		ParentControlLevels resultLevel = result.get();
		assertThat(resultLevel.level(), equalTo("PG"));		
	}	
}