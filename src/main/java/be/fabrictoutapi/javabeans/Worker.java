package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;

public class Worker extends User implements Serializable {

	//Attributes/Variables
	private static final long serialVersionUID = 288501069493727942L;
	private Report report;
	
	//Getters & Setters
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	
	//Constructor
	public Worker() {
		super();
	}
	
	public Worker(int id, String firstname, String lastname, String address, Date dateOfBirth, char sexe, String city,
			int postalCode, int phoneNumber, String emailAddress, String matricule, String password, String discriminator, boolean active) {
		super(id, firstname, lastname, address, dateOfBirth, sexe, city, postalCode, phoneNumber, emailAddress, matricule, password, discriminator,
				active);
	}	
	
	//Methodes
	
}