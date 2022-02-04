package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {

	//Attributes/Variables
	private static final long serialVersionUID = -2265362790380307095L;
	private int id;
	private Worker worker;
	private String description;
	private Date date;

	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	

	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
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
	
	public Report(int id, Worker worker, String description, Date date) {
		super();
		this.id = id;
		this.worker = worker;
		this.description = description;
		this.date = date;
	}	
	
	public Report(Worker worker, String description, Date date) {
		super();
		this.worker = worker;
		this.description = description;
		this.date = date;
	}		
	
	//Methodes
	
}