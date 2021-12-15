package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Worker extends User implements Serializable {

	//Attributes/Variables
	private static final long serialVersionUID = 288501069493727942L;
	private List<Maintenance> maintenanceList = new ArrayList<Maintenance>();
	
	//Getters & Setters
	public List<Maintenance> getMaintenanceList() {
		return maintenanceList;
	}
	public void setMaintenanceList(List<Maintenance> maintenanceList) {
		this.maintenanceList = maintenanceList;
	}	
	public void addMaintenance(Maintenance maintenance) {
			this.maintenanceList.add(maintenance);
	}	
	public void removeMaintenance(Maintenance maintenance) {
		this.maintenanceList.remove(maintenance);
	}
	
	//Constructor
	public Worker() {
		super();
	}
	
	public Worker(String firstname, String lastname, String address, Date dateOfBirth, char sexe, String city,
			int postalCode, int phoneNumber, String emailAddress, String matricule, String password, boolean active) {
		super(firstname, lastname, address, dateOfBirth, sexe, city, postalCode, phoneNumber, emailAddress, matricule, password,
				active);
	}	
	
	//Methodes
	
}