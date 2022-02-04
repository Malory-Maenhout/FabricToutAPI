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
	private List<Report> reportList = new ArrayList<Report>();
	private List<Worker> workerList = new ArrayList<Worker>();
	private Machine machine;
	
	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}	
	public void addReport(Report report) {
			this.reportList.add(report);
	}	
	public void removeReport(Report report) {
		this.reportList.remove(report);
	}
	
	public List<Worker> getWorkerList() {
		return workerList;
	}
	public void setWorkerList(List<Worker> workerList) {
		this.workerList = workerList;
	}	
	public void addWorker(Worker worker) {
			this.workerList.add(worker);
	}	
	public void removeWorker(Worker worker) {
		this.workerList.remove(worker);
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
	
	public Maintenance(int id, Date date, int duration, String status, List<Worker> workerList) {
		super();
		this.id = id;
		this.date = date;
		this.duration = duration;
		this.status = switch(status) {
			case "Do" -> StatusEnum.Do;
			case "ToDo" -> StatusEnum.ToDo;
			case "InValidate" -> StatusEnum.InValidate;
			case "Validate" -> StatusEnum.Validate;
			default -> StatusEnum.Do;
		};
		this.workerList = workerList;
	}
	
	public Maintenance(Date date, int duration, String status, List<Worker> workerList) {
		super();
		this.date = date;
		this.duration = duration;
		this.status = switch(status) {
			case "Do" -> StatusEnum.Do;
			case "ToDo" -> StatusEnum.ToDo;
			case "InValidate" -> StatusEnum.InValidate;
			case "Validate" -> StatusEnum.Validate;
			default -> StatusEnum.Do;
		};
		this.workerList = workerList;
	}
	
	public Maintenance(int id, Date date, int duration, String status) {
		super();
		this.id = id;
		this.date = date;
		this.duration = duration;
		this.status = switch(status) {
			case "Do" -> StatusEnum.Do;
			case "ToDo" -> StatusEnum.ToDo;
			case "InValidate" -> StatusEnum.InValidate;
			case "Validate" -> StatusEnum.Validate;
			default -> StatusEnum.Do;
		};
	}
	
	public Maintenance(Date date, int duration, String status) {
		super();
		this.date = date;
		this.duration = duration;
		this.status = switch(status) {
			case "Do" -> StatusEnum.Do;
			case "ToDo" -> StatusEnum.ToDo;
			case "InValidate" -> StatusEnum.InValidate;
			case "Validate" -> StatusEnum.Validate;
			default -> StatusEnum.Do;
		};
	}
	
	//Methodes
	
}