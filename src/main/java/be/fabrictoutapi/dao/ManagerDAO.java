package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import be.fabrictoutapi.enums.*;
import be.fabrictoutapi.javabeans.*;

public class ManagerDAO extends DAO<Manager>{

	public ManagerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Manager obj) {
		return false;
	}
	
	@Override
	public boolean create(int id, Manager obj) {
		return false;
	}

	@Override
	public boolean delete(Manager obj) {
		return false;
	}

	@Override
	public boolean update(Manager obj) {
		return false;
	}

	@Override
	public Manager find(int id) {
		Manager manager = new Manager();
		
		String querry =    "SELECT DISTINCT ft_machine.id, ft_machine.name_, ft_machine.size_, ft_machine.status_, "
                + "ft_machine.type_, ft_machine.serialnumber, ft_machine.replace "
                + "FROM FT_MACHINE INNER JOIN FT_AREA_MACHINE ON FT_MACHINE.ID = FT_AREA_MACHINE.MACHINE "
                + "INNER JOIN FT_AREA ON FT_AREA_MACHINE.AREA = FT_AREA.ID WHERE id_site = ( "
                + "SELECT id FROM ft_site WHERE id = ( "
                + "SELECT id_site FROM ft_user WHERE id='" + id + "')) ORDER BY ft_machine.id";
		
		ArrayList<Machine> machines = new ArrayList<Machine>();
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			while(result.next()) {
				Machine machine = new Machine();
				machine.setId(result.getInt("id"));
				machine.setName(result.getString("name_"));
				machine.setSerialNumber(result.getString("serialnumber"));
				
				if(result.getString("size_").equals("Small"))
					machine.setSize(SizeEnum.Small);
				else if(result.getString("size_").equals("Medium"))
					machine.setSize(SizeEnum.Medium);
				else if(result.getString("size_").equals("Large"))
					machine.setSize(SizeEnum.Large);

				String status = result.getString("status_");
				
				if(status.equals("Start"))
					machine.setStatus(StatusMachineEnum.Start);
				else if(status.equals("Stop"))
					machine.setStatus(StatusMachineEnum.Stop);
				else if(status.equals("Wait"))
					machine.setStatus(StatusMachineEnum.Wait);

				String type = result.getString("type_");
				
				if(type.equals("Assembly"))
					machine.setType(TypeEnum.Assembly);
				else if(type.equals("Manufacturing"))
					machine.setType(TypeEnum.Manufacturing);
				else if(type.equals("Sorting"))
					machine.setType(TypeEnum.Sorting);
				
				if(result.getString("replace").equals("Y"))
					machine.setReplace(true);
				else
					machine.setReplace(false);
				
				String querry2 = "SELECT * FROM FT_MAINTENANCE WHERE id_machine = " + machine.getId();
				
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				
				while(result2.next()) {
					Maintenance m = new Maintenance();
					m.setId(result2.getInt("id"));
					m.setDate(result2.getDate("planned"));
					m.setDuration(result2.getInt("duration_minute"));
					
					String stat = result2.getString("status_");
					
					switch(stat) {
						case "Do": m.setStatus(StatusEnum.Do); break;
						case "ToDo": m.setStatus(StatusEnum.ToDo); break;
						case "Validate": m.setStatus(StatusEnum.Validate); break;
						case "Invalidate": m.setStatus(StatusEnum.InValidate); break;
					};
					
					machine.getMaintenanceList().add(m);
				}
				
				machines.add(machine);
			}
			
			manager.setMachineList(machines);
			
			return manager;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return manager;
	}

	@Override
	public Manager find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Manager> findall() {
		return null;
	}

	@Override
	public ArrayList<Manager> findall(int id) {
		return null;
	}
}