package com.mcs.parentalcontrol.service;

public interface ParentalControlService {

	boolean checkCustomerControlLevel(String customerControlLevel, String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
