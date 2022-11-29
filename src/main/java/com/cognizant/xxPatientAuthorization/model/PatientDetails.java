package com.cognizant.xxPatientAuthorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class PatientDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "patient_name", nullable = false)
	private String patientName;
	
	@Column(name = "contact_number", nullable = false, unique = true)
	private int contactNumber;
	
	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;
	
	@Column(name = "address", nullable = false)
	private String address;

	public PatientDetails() {
		super();
	}

	public PatientDetails(String username, String password, String patientName, int contactNumber, String emailId, String address) {
		super();
		this.username = username;
		this.password = password;
		this.patientName = patientName;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.address = address;
	}

	public PatientDetails(int id, String username, String password, String patientName, int contactNumber, String emailId, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.patientName = patientName;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
