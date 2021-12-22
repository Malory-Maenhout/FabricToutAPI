package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.fabrictoutapi.enums.StatusEnum;
import be.fabrictoutapi.javabeans.*;

public class MaintenanceDAO extends DAO<Maintenance>{

	public MaintenanceDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Maintenance obj) {
		return false;
	}

	@Override
	public boolean delete(Maintenance obj) {
		return false;
	}

	@Override
	public boolean update(Maintenance obj) {
		return false;
	}

	@Override
	public Maintenance find(int id) {
		System.out.println("MAINTENANCE find : " + id);
		
		String querry = "SELECT * FROM FT_MAINTENANCE WHERE id='" + id + "'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			if(result.first()) {
				Maintenance maintenance = new Maintenance();
				maintenance.setId(result.getInt("ID"));
				maintenance.setDate(result.getDate("PLANNED"));
				maintenance.setDuration(result.getInt("DURATION_MINUTE"));
				String s = result.getString("status_");
				if (s.equals("ToDo"))
					maintenance.setStatus(StatusEnum.ToDo);
				else if (s.equals("Do"))
					maintenance.setStatus(StatusEnum.Do);
				else if (s.equals("Validate"))
					maintenance.setStatus(StatusEnum.Validate);
				else if (s.equals("InValidate"))
					maintenance.setStatus(StatusEnum.InValidate);
				// Charger liste ouvrier
				
				// Charger liste rapport
				String querry2 = "SELECT * FROM FT_REPORT WHERE id_maintenance='" + id +"'";
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				while(result2.next()) {
					Report report = new Report();
					report.setId(result2.getInt("id"));
					report.setDescription(result2.getString("descr"));
					report.setDate(result2.getDate("date_"));
					User w = new Worker();
					w = new UserDAO(this.connect).find(id);
					report.setWorker((Worker) w);
				}
				// Set machine -- appel machinedao.find(id)
				Machine m = new MachineDAO(this.connect).find(result.getInt("id_machine"));
				maintenance.setMachine(m);
				return maintenance;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Maintenance find(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Maintenance> findall() {
		// TODO Auto-generated method stub
		return null;
	}
}