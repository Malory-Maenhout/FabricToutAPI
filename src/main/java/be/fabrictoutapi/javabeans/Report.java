package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {

	//Attributes/Variables
	private static final long serialVersionUID = -2265362790380307095L;
	private String description;
	private Date date;

	//Getters & Setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	//Constructor
	public Report() {
		super();
	}
	
	public Report(String description, Date date) {
		super();
		this.description = description;
		this.date = date;
	}	
	
	//Methodes
	
}