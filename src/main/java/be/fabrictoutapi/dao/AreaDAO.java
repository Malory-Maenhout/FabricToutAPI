package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.fabrictoutapi.enums.*;
import be.fabrictoutapi.javabeans.*;

public class AreaDAO extends DAO<Area> {

	public AreaDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Area obj) {
		return false;
	}

	@Override
	public boolean delete(Area obj) {
		return false;
	}

	@Override
	public boolean update(Area obj) {
		return false;
	}

	@Override
	public Area find(int id) {
		System.out.println("AREA find : " + id);
		String querry = "SELECT * FROM FT_AREA WHERE id='" + id + "'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			if(result.first()) {
				Area area = new Area();
				area.setId(id);
				area.setLetter(result.getString("LETTER").charAt(0));
				String col = result.getString("COLOR");
				switch (col){
					case "Green":
						area.setColor(ColorEnum.Green);
						break;
					case "Orange":
						area.setColor(ColorEnum.Orange);
						break;
					case "Red":
						area.setColor(ColorEnum.Red);
						break;
					case "Black":
						area.setColor(ColorEnum.Black);
						break;
				}
				area.setDescription(result.getString("DESCR"));
				
				String querry2 = "SELECT * FROM FT_AREA_MACHINE WHERE area='" + id + "'";
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				while(result2.next()) {
					int id_machine = result2.getInt("machine");
					String querry3 = "SELECT * FROM FT_MACHINE WHERE id='" + result2.getInt("machine") + "'";
					ResultSet result3 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery(querry3);
					while(result3.next()) {
						Machine machine = new Machine();
						machine.setId(result3.getInt("id"));
						machine.setName(result3.getString("name_"));
						machine.setSerialNumber(result3.getString("serialnumber"));
						if(result3.getString("size_").equals("Small"))
							machine.setSize(SizeEnum.Small);
						else if(result3.getString("size_").equals("Medium"))
							machine.setSize(SizeEnum.Medium);
						else if(result3.getString("size_").equals("Large"))
							machine.setSize(SizeEnum.Large);

						String status = result3.getString("status_");
						if(status.equals("Start"))
							machine.setStatus(StatusMachineEnum.Start);
						else if(status.equals("Stop"))
							machine.setStatus(StatusMachineEnum.Stop);
						else if(status.equals("Wait"))
							machine.setStatus(StatusMachineEnum.Wait);

						String type = result3.getString("type_");
						if(type.equals("Assembly"))
							machine.setType(TypeEnum.Assembly);
						else if(type.equals("Manufacturing"))
							machine.setType(TypeEnum.Manufacturing);
						else if(type.equals("Sorting"))
							machine.setType(TypeEnum.Sorting);
						
						if(result3.getString("replace").equals("Y"))
							machine.setReplace(true);
						else
							machine.setReplace(false);
						
						area.getMachineList().add(machine);
					}
				}
				return area;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Area find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Area> findall() {
		String querry = "SELECT * FROM FT_AREA";
		ArrayList<Area> areas = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			while (result.next()) {
				Area area = new Area();
				area.setId(result.getInt("ID"));
				area.setLetter(result.getString("LETTER").charAt(0));
				String col = result.getString("COLOR");
				switch (col){
					case "GREEN":
						area.setColor(ColorEnum.Green);
						break;
					case "ORANGE":
						area.setColor(ColorEnum.Orange);
						break;
					case "RED":
						area.setColor(ColorEnum.Red);
						break;
					case "BLACK":
						area.setColor(ColorEnum.Black);
						break;
				}
				area.setDescription(result.getString("DESCR"));
				
				areas.add(area);
			}
			return areas;
		} catch (SQLException e) {
			return null;
		}
	}
}