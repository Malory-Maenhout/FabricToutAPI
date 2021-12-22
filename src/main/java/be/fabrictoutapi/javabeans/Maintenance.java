package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import be.fabrictoutapi.enums.StatusEnum;

public class Maintenance implements Serializable{

	//Attributes/Variables
	private static final long serialVersionUID = 7964490051068044299L;
	private int id;
	private Date date;
	private int duration;
	private StatusEnum status;
	private List<Worker> maintenanceWorkerList = new ArrayList<Worker>(); 
	private List<Report> maintenanceReportList = new ArrayList<Report>();
	private Machine machine;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//Getters & Setters
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public List<Worker> getMaintenanceWorkerList() {
		return maintenanceWorkerList;
	}
	public void setMaintenanceWorkerList(List<Worker> maintenanceWorkerList) {
		this.maintenanceWorkerList = maintenanceWorkerList;
	}	
	public void addMaintenanceWorker(Worker maintenanceWorker) {
			this.maintenanceWorkerList.add(maintenanceWorker);
	}	
	public void removeMaintenanceWorker(Worker maintenanceWorker) {
		this.maintenanceWorkerList.remove(maintenanceWorker);
	}
	
	public List<Report> getMaintenanceReportList() {
		return maintenanceReportList;
	}
	public void setMaintenanceReportList(List<Report> maintenanceReportList) {
		this.maintenanceReportList = maintenanceReportList;
	}	
	public void addMaintenanceReport(Report maintenanceReport) {
			this.maintenanceReportList.add(maintenanceReport);
	}	
	public void removeMaintenanceReport(Report maintenanceReport) {
		this.maintenanceReportList.remove(maintenanceReport);
	}
	
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	//Constructor
	public Maintenance() {
		super();
	}
	
	public Maintenance(Date date, int duration, StatusEnum status, List<Worker> maintenanceWorkerList, Machine machine) {
		super();
		this.date = date;
		this.duration = duration;
		this.status = status;
		this.maintenanceWorkerList = maintenanceWorkerList;
		this.machine = machine;
	}
	
	//Methodes
	
}