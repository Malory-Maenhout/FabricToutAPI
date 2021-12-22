package be.fabrictoutapi.javabeans;

import java.io.Serializable;
import java.util.*;
import be.fabrictoutapi.enums.ColorEnum;

public class Area implements Serializable {

	//Attributes/Variables
	private static final long serialVersionUID = -5609292885299153581L;
	private int id;
	private char letter;
	private ColorEnum color;
	private String description;
	private List<Machine> machineList = new ArrayList<Machine>();

	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public ColorEnum getColor() {
		return color;
	}
	public void setColor(ColorEnum color) {
		this.color = color;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Machine> getMachineList() {
		return machineList;
	}
	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}	
	public void addMachine(Machine machine) {
			this.machineList.add(machine);
	}	
	public void removeMachine(Machine machine) {
		this.machineList.remove(machine);
	}
	
	//Constructor
	public Area() {
		super();
	}
	
	public Area(int id, char letter, ColorEnum color, String description) {
		super();
		this.id = id;
		this.letter = letter;
		this.color = color;
		this.description = description;
	}
	
	//Methodes
	
}