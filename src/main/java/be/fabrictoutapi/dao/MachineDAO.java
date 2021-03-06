package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import be.fabrictoutapi.enums.*;
import be.fabrictoutapi.javabeans.*;

public class MachineDAO extends DAO<Machine> {

	public MachineDAO(Connection conn) {
		super(conn);
	}

	@Override
    public boolean create(Machine machine) {
        try {
            String querry = "INSERT INTO FT_MACHINE (NAME_, SIZE_, STATUS_, TYPE_, REPLACE, SERIALNUMBER) "
                    + "Values('" + machine.getName() + "','" + machine.getSize().toString() + "','" + machine.getStatus().toString() + "','"
                    + machine.getType().toString() + "','N','" + machine.getSerialNumber() + "')";
            
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate(querry);
            try {
                String querry2 = "SELECT * FROM FT_MACHINE WHERE id = (SELECT MAX(id) FROM FT_MACHINE)";
               
                ResultSet result = this.connect
                        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(querry2);
                
                if(result.first()) {
                	machine.setId(result.getInt("ID"));
                    
                	for(Area a : machine.getAreaList()) {
                        String querry3 = "INSERT INTO FT_AREA_MACHINE (area, machine) " +
                                "Values('" + a.getId() + "','" + machine.getId() + "')";
                        
                        this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeUpdate(querry3);
                    }
                }                
            } 
            catch(SQLException e) {
            	e.printStackTrace();
                return false;
            }
            
            return true;
        } 
        catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
    }
	
	@Override
	public boolean create(int id, Machine obj) {
		return false;
	}

	@Override
	public boolean delete(Machine obj) {
		return false;
	}

	@Override
    public boolean update(Machine machine) {
		char replace = 'N';
		
        if(machine.isReplace())
            replace = 'Y';
        
        String querry = "UPDATE FT_MACHINE SET " +
                " name_='" + machine.getName() +
                "', serialnumber='" + machine.getSerialNumber() + 
                "', size_='" + machine.getSize() + 
                "', status_='" + machine.getStatus() +
                "', type_='" + machine.getType() + 
                "', replace='" + replace + 
                "' WHERE id =" + machine.getId();

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
	public Machine find(int id) {
		String querry = "SELECT * FROM FT_MACHINE WHERE ID='" + id + "'";
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			if(result.first()) {
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
				
				String querry2 = "SELECT * FROM FT_MAINTENANCE WHERE id_machine='" + id + "' ORDER BY id";
				
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				
				while(result2.next()) {
					Maintenance maintenance = new Maintenance();
					maintenance.setId(result2.getInt("ID"));
					maintenance.setDate(result2.getDate("PLANNED"));					
					maintenance.setDuration(result2.getInt("DURATION_MINUTE"));
					
					String s = result2.getString("status_");
					
					if (s.equals("ToDo"))
						maintenance.setStatus(StatusEnum.ToDo);
					else if (s.equals("Do"))
						maintenance.setStatus(StatusEnum.Do);
					else if (s.equals("Validate"))
						maintenance.setStatus(StatusEnum.Validate);
					else if (s.equals("InValidate"))
						maintenance.setStatus(StatusEnum.InValidate);
					
					machine.getMaintenanceList().add(maintenance);
				}
				
				String querry3 = "SELECT * FROM FT_AREA_MACHINE WHERE machine='" + id + "'";
				
				ResultSet result3 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry3);
				
				while(result3.next()) {
					String querry4 = "SELECT * FROM FT_AREA where id='" + result3.getInt("area") + "'";
					
					ResultSet result4 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery(querry4);
					
					while(result4.next()) {
						Area area = new Area();
						area.setId(result4.getInt("id"));
						area.setLetter(result4.getString("letter").charAt(0));
						
						String col = result4.getString("COLOR");
						
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
						
						area.setDescription(result4.getString("DESCR"));
						machine.getAreaList().add(area);
					}
				}	
				
				return machine;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Machine find(String str1, String str2) {
		return null;
	}

	public ArrayList<Machine> findall() {
		String querry = "SELECT * FROM FT_MACHINE";
		
		ArrayList<Machine> machines = new ArrayList<>();
		
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
				
				String querry2 = "SELECT * FROM FT_MAINTENANCE WHERE id_machine='" + machine.getId() + "'";
				
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				
				while(result2.next()) {
					Maintenance maintenance = new Maintenance();
					maintenance.setId(result2.getInt("ID"));
					maintenance.setDate(result2.getDate("PLANNED"));		
					maintenance.setDuration(result2.getInt("DURATION_MINUTE"));
					
					String s = result2.getString("status_");
					
					if (s.equals("ToDo"))
						maintenance.setStatus(StatusEnum.ToDo);
					else if (s.equals("Do"))
						maintenance.setStatus(StatusEnum.Do);
					else if (s.equals("Validate"))
						maintenance.setStatus(StatusEnum.Validate);
					else if (s.equals("InValidate"))
						maintenance.setStatus(StatusEnum.InValidate);
					
					machine.getMaintenanceList().add(maintenance);
				}
				
				String querry3 = "SELECT * FROM FT_AREA_MACHINE WHERE machine='" + machine.getId() + "'";
				
				ResultSet result3 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry3);
				
				while(result3.next()) {
					
					String querry4 = "SELECT * FROM FT_AREA where id='" + result3.getInt("area") + "'";
					
					ResultSet result4 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery(querry4);
					
					while(result4.next()) {
						Area area = new Area();
						area.setId(result4.getInt("id"));
						area.setLetter(result4.getString("letter").charAt(0));
						
						String col = result4.getString("COLOR");
						
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
						
						area.setDescription(result4.getString("DESCR"));
						
						machine.getAreaList().add(area);
					}
				}
								
				machines.add(machine);
			}
			
			return machines;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Machine> findall(int id) {
		return null;
	}
}