package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import be.fabrictoutapi.enums.*;
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
    public boolean create(int id, Maintenance obj) {
        try {
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		String date = simpleDateFormat.format(obj.getDate());
    		
            String querry = "INSERT INTO FT_MAINTENANCE (planned, duration_minute, status_, id_manager, id_machine) "
                    + "Values('" + date + "','" + obj.getDuration() + "', '" + obj.getStatus() + "'," + id + ", " + obj.getMachine().getId() + ")";
            
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate(querry);
            
            String querry2 = "SELECT * from FT_MAINTENANCE WHERE id = (SELECT max(id) from FT_MAINTENANCE)";
           
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry2);
            
            if(result.first()) {
                obj.setId(result.getInt("id"));
                
                for(Worker w : obj.getWorkerList()) {
                    String querry3 = "INSERT INTO FT_WORKER_MAINTENANCE (worker, maintenance) " +
                            "Values('" + w.getId() + "','" + obj.getId() + "')";
                    
                    this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate(querry3);
                }
            }
            
            return true;
        } 
        catch (SQLException e) {
            return false;
        }
    }

	@Override
	public boolean delete(Maintenance obj) {
		return false;
	}

	@Override
    public boolean update(Maintenance obj) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(obj.getDate());
		
        String querry = "UPDATE FT_MAINTENANCE SET " +
                " planned='" + date + "'," + 
                " duration_minute=" + obj.getDuration() + "," + 
                " status_='" + obj.getStatus() + "'" + 
                " WHERE id=" + obj.getId(); 
        try {
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
            .executeUpdate(querry);
            
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public Maintenance find(int id) {
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
				
				String querry2 = "SELECT * FROM FT_REPORT WHERE id_maintenance='" + id +"' ORDER BY id";
				
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				
				while(result2.next()) {
					Report report = new Report();
					report.setId(result2.getInt("id"));
					report.setDescription(result2.getString("descr"));
					report.setDate(result2.getDate("date_"));
					
					maintenance.getReportList().add(report);					
					
					String querry4 = "SELECT * FROM FT_USER WHERE id='" + result2.getInt("id_worker") + "'";

					ResultSet result4 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery(querry4);
					
					if(result4.first()) {					
						User user = new Worker();
		            	user.setId(result4.getInt("ID"));
		                user.setFirstname(result4.getString("FIRSTNAME"));
		                user.setLastname(result4.getString("LASTNAME"));
		                user.setAddress(result4.getString("ADDRESS_"));
		                user.setDateOfBirth(result4.getDate("BIRTHDAY"));
		                user.setSexe(result4.getString("SEXE").charAt(0));
		                user.setCity(result4.getString("CITY"));
		                user.setPostalCode(result4.getInt("POSTALCODE"));
		                user.setPhoneNumber(result4.getInt("PHONENUMBER"));
		                user.setEmailAddress(result4.getString("EMAIL"));
		                user.setPersonnelNumber(result4.getString("personnelnumber"));
		                user.setPassword(result4.getString("pwd"));
		            	user.setDiscriminator(result4.getString("discriminator"));
		            	
		                if(result4.getString("ACTIVE").equals("Y"))
		                    user.setActive(true);
		                else
		                    user.setActive(false);
		                
		                report.setWorker((Worker) user);  
					}
				}
				
				String querry3 = "SELECT * FROM FT_USER INNER JOIN FT_WORKER_MAINTENANCE " 
							+ "ON FT_USER.id = FT_WORKER_MAINTENANCE.worker WHERE FT_WORKER_MAINTENANCE.maintenance='" + maintenance.getId() +"'";

				ResultSet result3 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry3);
				
				while(result3.next()) {
					User user = new Worker();
	            	user.setId(result3.getInt("ID"));
	                user.setFirstname(result3.getString("FIRSTNAME"));
	                user.setLastname(result3.getString("LASTNAME"));
	                user.setAddress(result3.getString("ADDRESS_"));
	                user.setDateOfBirth(result3.getDate("BIRTHDAY"));
	                user.setSexe(result3.getString("SEXE").charAt(0));
	                user.setCity(result3.getString("CITY"));
	                user.setPostalCode(result3.getInt("POSTALCODE"));
	                user.setPhoneNumber(result3.getInt("PHONENUMBER"));
	                user.setEmailAddress(result3.getString("EMAIL"));
	                user.setPersonnelNumber(result3.getString("personnelnumber"));
	                user.setPassword(result3.getString("pwd"));
	            	user.setDiscriminator(result3.getString("discriminator"));
	            	
	                if(result3.getString("ACTIVE").equals("Y"))
	                    user.setActive(true);
	                else
	                    user.setActive(false);
	                
					maintenance.getWorkerList().add((Worker) user);
				}
				
				String querry5 = "SELECT * FROM FT_MACHINE WHERE id='" + result.getInt("id_machine") + "'";

				ResultSet result5 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry5);
				
				if(result5.first()) {
					Machine machine = new Machine();
					machine.setId(result5.getInt("id"));
					machine.setName(result5.getString("name_"));
					machine.setSerialNumber(result5.getString("serialnumber"));
					
					if(result5.getString("size_").equals("Small"))
						machine.setSize(SizeEnum.Small);
					else if(result5.getString("size_").equals("Medium"))
						machine.setSize(SizeEnum.Medium);
					else if(result5.getString("size_").equals("Large"))
						machine.setSize(SizeEnum.Large);

					String status = result5.getString("status_");
					
					if(status.equals("Start"))
						machine.setStatus(StatusMachineEnum.Start);
					else if(status.equals("Stop"))
						machine.setStatus(StatusMachineEnum.Stop);
					else if(status.equals("Wait"))
						machine.setStatus(StatusMachineEnum.Wait);

					String type = result5.getString("type_");
					
					if(type.equals("Assembly"))
						machine.setType(TypeEnum.Assembly);
					else if(type.equals("Manufacturing"))
						machine.setType(TypeEnum.Manufacturing);
					else if(type.equals("Sorting"))
						machine.setType(TypeEnum.Sorting);
					
					if(result5.getString("replace").equals("Y"))
						machine.setReplace(true);
					else
						machine.setReplace(false);
					
					String querry6 = "SELECT * FROM FT_AREA_MACHINE WHERE machine='" + machine.getId() + "'";
					
					ResultSet result6 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery(querry6);
					
					while(result6.next()) {
						String querry7 = "SELECT * FROM FT_AREA where id='" + result6.getInt("area") + "'";
						
						ResultSet result7 = this.connect
								.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
								.executeQuery(querry7);
						
						while(result7.next()) {
							Area area = new Area();
							area.setId(result7.getInt("id"));
							area.setLetter(result7.getString("letter").charAt(0));
							
							String col = result7.getString("COLOR");
							
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
							
							area.setDescription(result7.getString("DESCR"));
							
							machine.getAreaList().add(area);
						}
					}
					
					maintenance.setMachine(machine);
				}
				
				return maintenance;
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Maintenance find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Maintenance> findall() {
		return null;
	}

	@Override
	public ArrayList<Maintenance> findall(int id) {
		return null;
	}
}