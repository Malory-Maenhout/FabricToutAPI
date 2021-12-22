package be.fabrictoutapi.javabeans;

import be.fabrictoutapi.enums.SizeEnum;
import be.fabrictoutapi.enums.StatusMachineEnum;
import be.fabrictoutapi.enums.TypeEnum;

import java.io.Serializable;
import java.util.*;

public class Machine implements Serializable{

	//Attributes/Variables
	private static final long serialVersionUID = -3024905420459179567L;
	private int id;
	private String name;
	private SizeEnum size;
	private StatusMachineEnum status;
	private boolean replace;
	private String serialNumber;
	private TypeEnum type;
	private List<Maintenance> maintenanceList = new ArrayList<Maintenance>();
	private List<Area> areaList = new ArrayList<Area>();
	
	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public SizeEnum getSize() {
		return size;
	}
	public void setSize(SizeEnum size) {
		this.size = size;
	}
	
	public StatusMachineEnum getStatus() {
		return status;
	}
	public void setStatus(StatusMachineEnum status) {
		this.status = status;
	}
	
	public boolean isReplace() {
		return replace;
	}
	public void setReplace(boolean replace) {
		this.replace = replace;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public TypeEnum getType() {
		return type;
	}
	public void setType(TypeEnum type) {
		this.type = type;
	}
	
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
	
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}	
	public void addArea(Area area) {
			this.areaList.add(area);
	}	
	public void removeArea(Area area) {
		this.areaList.remove(area);
	}

	//Constructor
	public Machine() {
		super();
	}
	
	public Machine(int id, String name, SizeEnum size, StatusMachineEnum status, boolean replace, String serialNumber, List<Area> areaList) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.status = status;
		this.replace = replace;
		this.areaList = areaList;
		this.serialNumber = serialNumber;
	}
	
	//Methodes
	
}