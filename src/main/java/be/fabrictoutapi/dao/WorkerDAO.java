package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import be.fabrictoutapi.enums.StatusEnum;
import be.fabrictoutapi.javabeans.*;

public class WorkerDAO extends DAO<Worker> {

	public WorkerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Worker obj) {
		return false;
	}

	@Override
	public boolean create(int id, Worker obj) {
		return false;
	}
	
	@Override
	public boolean delete(Worker obj) {
		return false;
	}

	@Override
	public boolean update(Worker obj) {
		return false;
	}

	@Override
    public Worker find(int id) {
        Worker worker = new Worker();
        
        String querry = "SELECT * FROM FT_MAINTENANCE INNER JOIN FT_WORKER_MAINTENANCE ON " 
                    + "ft_maintenance.id = ft_worker_maintenance.maintenance "
                    + "WHERE (status_ = 'ToDo' or status_ = 'InValidate') and ft_worker_maintenance.worker = " + id + " ORDER BY ft_maintenance.id";
        try{
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            
            while(result.next()) {
                Maintenance m = new Maintenance();
                m.setId(result.getInt("id"));
                m.setDate(result.getDate("planned"));
                m.setDuration(result.getInt("duration_minute"));
                
                switch(result.getString("status_")) {
                    case "Do": m.setStatus(StatusEnum.Do); break;
                    case "ToDo": m.setStatus(StatusEnum.ToDo); break;
                    case "Validate": m.setStatus(StatusEnum.Validate); break;
                    case "InValidate": m.setStatus(StatusEnum.InValidate); break;
                    default: break;
                }
                
                Machine machine = new MachineDAO(this.connect).find(result.getInt("id_machine"));
                machine.setId(result.getInt("id_machine"));
                m.setMachine(machine);
                worker.getMaintenanceList().add(m);
            }
            
            return worker;
        }
        catch(SQLException e) {
            return null;
        } 
    }

	@Override
	public Worker find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Worker> findall() {
		return null;
	}

	@Override
	public ArrayList<Worker> findall(int id) {
		ArrayList<Worker> workers = new ArrayList<Worker>();
		
		String querry = "SELECT * FROM FT_USER WHERE discriminator = 'WORKER' and id_site = (Select id_site FROM FT_USER where id =" + id + ")";
        
		try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            
            while(result.next()) {
                Worker user = new Worker();
                user.setId(result.getInt("ID"));
                user.setFirstname(result.getString("FIRSTNAME"));
                user.setLastname(result.getString("LASTNAME"));
                user.setAddress(result.getString("ADDRESS_"));
                user.setDateOfBirth(result.getDate("BIRTHDAY"));
                user.setSexe(result.getString("SEXE").charAt(0));
                user.setCity(result.getString("CITY"));
                user.setPostalCode(result.getInt("POSTALCODE"));
                user.setPhoneNumber(result.getInt("PHONENUMBER"));
                user.setEmailAddress(result.getString("EMAIL"));
                user.setPersonnelNumber(result.getString("personnelnumber"));
                user.setPassword(result.getString("pwd"));
                user.setDiscriminator(result.getString("discriminator"));
                workers.add(user);
            }
            
            return workers;
            
        }
        catch(SQLException e) {
            return null;
        }
    }
}