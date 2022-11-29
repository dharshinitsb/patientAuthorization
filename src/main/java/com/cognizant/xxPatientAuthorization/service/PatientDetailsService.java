package com.cognizant.xxPatientAuthorization.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cognizant.xxPatientAuthorization.model.PatientDetails;

@Service
public interface PatientDetailsService extends UserDetailsService {
	
	public UserDetails loadUserByUsername(String username);
	
	public void createPatientDetails(PatientDetails patientDetails);
	
	public void addData();

}
