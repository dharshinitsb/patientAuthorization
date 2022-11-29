package com.cognizant.xxPatientAuthorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.xxPatientAuthorization.model.PatientDetails;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {
	
	public PatientDetails findByUsername(String username);

}
