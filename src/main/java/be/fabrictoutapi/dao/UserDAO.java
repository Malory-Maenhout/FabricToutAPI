package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import be.fabrictoutapi.javabeans.*;

public class UserDAO extends DAO<User>{

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}
	
	@Override
	public boolean create(int id_site, User user) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(user.getDateOfBirth());
		
        String querry = "INSERT INTO FT_USER (firstname, lastname, address_, birthday, sexe, city, postalcode, phonenumber, email, personnelnumber, pwd, active, discriminator, id_site) "
                        + " Values ('" + user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getAddress() + "', '" + date + "', '"
                        + user.getSexe() + "', '" + user.getCity() + "', " + user.getPostalCode() + ", " + user.getPhoneNumber() + ", '"
                        + user.getEmailAddress() + "', '" + user.getPersonnelNumber() + "', '" + user.getPassword() + "', " + "'Y', '" + user.getDiscriminator() + "', " + id_site + ")";
        
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
	public boolean delete(User obj) {
		return false;
	}

	@Override
    public boolean update(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(user.getDateOfBirth());      
        char active = 'Y';
        
        if (user.isActive())
            active = 'Y';
        else
            active = 'N';
        
        String querry = "UPDATE FT_USER SET " +
                " firstname='" + user.getFirstname() +
                "', lastname='" + user.getLastname() + 
                "', address_='" + user.getAddress() + 
                "', birthday='" + date +
                "', sexe='" + user.getSexe() + 
                "', city='" + user.getCity() + 
                "', postalcode=" + user.getPostalCode() + 
                ", phonenumber=" + user.getPhoneNumber() + 
                ", email='" + user.getEmailAddress() + 
                "', personnelnumber='" + user.getPersonnelNumber() +
                "', pwd='" + user.getPassword() +
                "', active='" + active +
                "', discriminator='" + user.getDiscriminator() + "' " +
                " WHERE id=" + user.getId();
        
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
	public User find(int id) {
		String querry = "SELECT * FROM FT_USER WHERE id='" + id + "'";
		
		try{
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            
            User user = null;
            
            if(result.first()) {     
            	if(result.getString("discriminator").equals("ADMIN")) {
            		user = new AdministratorDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("EMPLOYE")) {
            		user = new EmployeeDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("MANAGER")) {
            		user = new ManagerDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("WORKER")) {
            		user = new WorkerDAO(this.connect).find(id);
            	}

            	user.setId(id);
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
                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                return user;
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public User find(String personnelNumber, String password) {
        String querry = "SELECT * FROM FT_USER WHERE PERSONNELNUMBER='" + personnelNumber + "' AND PWD='" + password + "'";
        
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            
            if (result.first()) {
                User user = null;
                if(result.getString("discriminator").equals("ADMIN")) {
            		user = new AdministratorDAO(this.connect).find(result.getInt("ID"));
            	}
            	else if(result.getString("discriminator").equals("EMPLOYE")) {
            		user = new EmployeeDAO(this.connect).find(result.getInt("ID"));
            	}
            	else if(result.getString("discriminator").equals("MANAGER")) {
            		user = new ManagerDAO(this.connect).find(result.getInt("ID"));
            	}
            	else if(result.getString("discriminator").equals("WORKER")) {
            		user = new WorkerDAO(this.connect).find(result.getInt("ID"));
            	}

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
                user.setPersonnelNumber(personnelNumber);
                user.setPassword(password);
                user.setDiscriminator(result.getString("DISCRIMINATOR"));

                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                
                return user;
            } else {
            	return null;
            }
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
            return null;
        }
    }

	@Override
	public ArrayList<User> findall() {
		String querry = "SELECT * FROM FT_USER ORDER BY id";
		
		ArrayList<User> users = new ArrayList<>();
		
		try {			
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			while(result.next()) {
				User user = null;
                if(result.getString("DISCRIMINATOR").equals("ADMIN")) {
                    user = new Administrator();
                    user.setDiscriminator("ADMIN");
                } else if (result.getString("DISCRIMINATOR").equals("EMPLOYE")) {
                    user = new Employee();
                    user.setDiscriminator("EMPLOYE");
                } else if (result.getString("DISCRIMINATOR").equals("MANAGER")) {
                    user = new Manager();
                    user.setDiscriminator("MANAGER");
                } else {
                    user = new Worker();
                    user.setDiscriminator("WORKER");
                }

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
                user.setPersonnelNumber(result.getString("PERSONNELNUMBER"));
                user.setPassword(result.getString("PWD"));

                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                
                users.add(user);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<User> findall(int id) {
		return null;
	}
}