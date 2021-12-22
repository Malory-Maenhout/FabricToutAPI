package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.fabrictoutapi.enums.SizeEnum;
import be.fabrictoutapi.enums.StatusMachineEnum;
import be.fabrictoutapi.enums.TypeEnum;
import be.fabrictoutapi.javabeans.Machine;
import be.fabrictoutapi.javabeans.Manager;

public class ManagerDAO extends DAO<Manager>{

	public ManagerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Manager obj) {
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
		String querry =	"SELECT * FROM FT_MACHINE WHERE FT_MACHINE.ID = ( " +
						"SELECT machine FROM ft_area_machine WHERE AREA = ( " +
						"SELECT id from ft_area WHERE id_site = ( " +
						"SELECT id FROM ft_site WHERE ID = ( " + 
						"SELECT id_site FROM ft_user WHERE id ='" + id + "'))))";
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
				
				manager.getMachineList().add(machine);
			}
			return manager;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}

	@Override
	public Manager find(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Manager> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager find(String str1, String str2) {
		return null;
	}
}