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
	
	public Machine(String name, String size, String status, boolean replace, String serialNumber,
			String type, List<Area> areaList) {
		super();
		this.name = name;
		this.size = switch(size) {
			case "Small" -> SizeEnum.Small;
			case "Medium" -> SizeEnum.Medium;
			case "Large" -> SizeEnum.Large;
			default -> SizeEnum.Small;
		};
		this.status = switch(status) {
			case "Start" -> StatusMachineEnum.Start;
			case "Stop" -> StatusMachineEnum.Stop;
			case "Wait" -> StatusMachineEnum.Wait;
			default -> StatusMachineEnum.Start;
		};
		this.replace = replace;
		this.serialNumber = serialNumber;
		this.type = switch(type) {
			case "Sorting" -> TypeEnum.Sorting;
			case "Assembly" -> TypeEnum.Assembly;
			case "Manufacturing" -> TypeEnum.Manufacturing;
			default -> TypeEnum.Sorting;
		};
		this.areaList = areaList;
	}
	
	public Machine(int id, String name, String size, String status, boolean replace, String serialNumber,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.size = switch(size) {
			case "Small" -> SizeEnum.Small;
			case "Medium" -> SizeEnum.Medium;
			case "Large" -> SizeEnum.Large;
			default -> SizeEnum.Small;
		};
		this.status = switch(status) {
			case "Start" -> StatusMachineEnum.Start;
			case "Stop" -> StatusMachineEnum.Stop;
			case "Wait" -> StatusMachineEnum.Wait;
			default -> StatusMachineEnum.Start;
		};
		this.replace = replace;
		this.serialNumber = serialNumber;
		this.type = switch(type) {
			case "Sorting" -> TypeEnum.Sorting;
			case "Assembly" -> TypeEnum.Assembly;
			case "Manufacturing" -> TypeEnum.Manufacturing;
			default -> TypeEnum.Sorting;
		};
	}
	
	public Machine(String name, String size, String status, boolean replace, String serialNumber,
			String type) {
		super();
		this.name = name;
		this.size = switch(size) {
			case "Small" -> SizeEnum.Small;
			case "Medium" -> SizeEnum.Medium;
			case "Large" -> SizeEnum.Large;
			default -> SizeEnum.Small;
		};
		this.status = switch(status) {
			case "Start" -> StatusMachineEnum.Start;
			case "Stop" -> StatusMachineEnum.Stop;
			case "Wait" -> StatusMachineEnum.Wait;
			default -> StatusMachineEnum.Start;
		};
		this.replace = replace;
		this.serialNumber = serialNumber;
		this.type = switch(type) {
			case "Sorting" -> TypeEnum.Sorting;
			case "Assembly" -> TypeEnum.Assembly;
			case "Manufacturing" -> TypeEnum.Manufacturing;
			default -> TypeEnum.Sorting;
		};
	}
	
	//Methodes
	
}