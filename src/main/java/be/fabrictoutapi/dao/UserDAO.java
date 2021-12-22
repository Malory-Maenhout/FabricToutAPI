package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	@Override
	public User find(int id) {
		return null;
	}

	@Override
	public User find(String personnelNumber, String password) {
		User user = null;
        String querry = "SELECT * FROM FT_USER WHERE PERSONNELNUMBER='" + personnelNumber + "' AND PWD ='" + password + "'";
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            if (result.first()) {
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
                user.setPersonnelNumber(personnelNumber);
                user.setPassword(password);
                
                if(result.getString("ACTIVE").equals("Y"))
                	user.setActive(true);
                else
                	user.setActive(false);
            }
            return user;
        }
        catch (SQLException e)
        {
            return null;
        }
	}
}