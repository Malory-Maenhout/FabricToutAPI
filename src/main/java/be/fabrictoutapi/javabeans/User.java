package be.fabrictoutapi.javabeans;

import java.sql.Date;

public abstract class User {
	
	//Attributes/Variables
	private String firstname;
	private String lastname;
	private String address;
	private Date dateOfBirth;
	private char sexe;
	private String city;
	private int postalCode;
	private int phoneNumber;
	private String emailAddress;
	private String matricule;
	private String password;
	private boolean active;
	
	//Getters & Setters
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		this.sexe = sexe;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//Constructor
	public User() {
		super();
	}
	
	public User(String firstname, String lastname, String address, Date dateOfBirth, char sexe, String city,
			int postalCode, int phoneNumber, String emailAddress, String matricule, String password, boolean active) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.sexe = sexe;
		this.city = city;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.matricule = matricule;
		this.password = password;
		this.active = active;
	}

	//Methodes
	
}