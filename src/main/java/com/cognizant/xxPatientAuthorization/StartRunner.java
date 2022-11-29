package com.cognizant.xxPatientAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cognizant.xxPatientAuthorization.service.PatientDetailsServiceImpl;

@Component
public class StartRunner implements ApplicationRunner {
	
	@Autowired
	private PatientDetailsServiceImpl patientDetailsServiceImpl;
	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		patientDetailsServiceImpl.addData();
	}

}
