package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Manager extends User implements Serializable{

	//Attributes/Variables
	private static final long serialVersionUID = -9162353254227108589L;	
	private List<Machine> machineList = new ArrayList<Machine>();
	
	//Getters & Setters
	public List<Machine> getMachineList() {
		return machineList;
	}
	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}	
	public void addMachine(Machine machine) {
			this.machineList.add(machine);
	}
	public void removeMachine(Machine machine)  {
		this.machineList.remove(machine);
	}
	
	//Constructor
	public Manager() {
		super();
	}
	
	public Manager(int id, String firstname, String lastname, String address, Date dateOfBirth, char sexe, String city,
			int postalCode, int phoneNumber, String emailAddress, String matricule, String password, String discriminator, boolean active) {
		super(id, firstname, lastname, address, dateOfBirth, sexe, city, postalCode, phoneNumber, emailAddress, matricule, password, discriminator, 
				active);
	}
	
	//Methodes
	
}