package com.cognizant.xxPatientAuthorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.xxPatientAuthorization.model.AuthResponse;
import com.cognizant.xxPatientAuthorization.model.PatientDetails;
import com.cognizant.xxPatientAuthorization.service.JWTUtil;
import com.cognizant.xxPatientAuthorization.service.PatientDetailsServiceImpl;


@RestController
@CrossOrigin
public class PatientDetailsController {
	
	private static Logger logger = LoggerFactory.getLogger(PatientDetailsController.class);
	
	@Autowired
	private PatientDetailsServiceImpl patientDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody PatientDetails patientLoginCredentials)
	{
		logger.info("METHOD EXECUTION START");
		
		final UserDetails userDetails = patientDetailsService.loadUserByUsername(patientLoginCredentials.getUsername());
		
		String generateToken = "";
		
		if(passwordEncoder.matches(patientLoginCredentials.getPassword(), userDetails.getPassword()))
		{
			generateToken = jwtUtil.generateToken(userDetails);
			logger.info("GENERATED TOKEN : {}", generateToken);
			logger.info("METHOD EXECUTION END - CORRECT CREDENTIALS");
			
			return new ResponseEntity<>(generateToken, HttpStatus.OK);
		}
		else
		{
			logger.info("METHOD EXECUTION END - WRONG CREDENTIALS");
			return new ResponseEntity<>("NOT ACCESSIBLE", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token)
	{
		logger.info("METHOD EXECUTION START");
		
		AuthResponse response = new AuthResponse();
		
		if(token == null)
		{
			response.setValid(false);
			
			logger.info("METHOD EXECUTION END - NULL TOKEN");
			
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
		
		else
		{
			String token1 = token.substring(7);
			if(jwtUtil.validateToken(token1))
			{
				response.setUsername(jwtUtil.extractUsername(token1));
				response.setValid(true);
			}
			else
			{
				response.setValid(false);
				logger.info("METHOD EXECUTION END - TOKEN EXPIRED");
				
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
		}
		
		logger.info("METHOD EXECUTION END - TOKEN ACCEPTED");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/registerPatientDetails")
	public ResponseEntity<?> registerPatientDetails(@RequestBody PatientDetails patientDetails)
	{
		try 
		{
			patientDetailsService.createPatientDetails(patientDetails);
			
			return new ResponseEntity<>("CREATED PATIENT DETAILS SUCCESSFULLY", HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
	}


}
