package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
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
		String querry = "SELECT * FROM FT_USER WHERE id='" + id + "'";
		try{
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            User user = null;
            if(result.first()) {     
            	String discri = result.getString("discriminator");
            	if(result.getString("discriminator").equals("ADMIN")) {
            		//Charger la liste des sites
            		user = new AdministratorDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("EMPLOYE")) {
            		//Charger la liste des sites
            		user = new EmployeeDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("MANAGER")) {
            		//Charger la liste des machines
            		user = new ManagerDAO(this.connect).find(id);
            	}
            	else if(result.getString("discriminator").equals("WORKER")) {
            		//Charger la liste des report à faire
            		user = new WorkerDAO(this.connect).find(id);
            	}

                user.setFirstname(result.getString("FIRSTNAME"));
                user.setLastname(result.getString("LASTNAME"));
                user.setAddress(result.getString("ADDRESS_"));
                user.setDateOfBirth(result.getDate("BIRTHDAY"));
                user.setSexe(result.getString("SEXE").charAt(0));
                user.setCity(result.getString("CITY"));
                user.setPostalCode(result.getInt("POSTALCODE"));
                user.setPhoneNumber(result.getInt("PHONENUMBER"));
                user.setEmailAddress(result.getString("EMAIL"));
                user.setPersonelNumber(result.getString("personnelnumber"));
                user.setPassword(result.getString("pwd"));
            	user.setDiscriminator(result.getString("discriminator"));
                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                return user;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
<<<<<<< HEAD
	public User find(String personnelNumber, String password) {
		User user = null;
        String querry = "SELECT * FROM FT_USER WHERE PERSONNELNUMBER='" + personnelNumber + "' AND PWD ='" + password + "'";
=======
    public User find(String str1, String str2) {
		System.out.print("USER find -> " + str1 + "/" + str2 + " : ");
        String querry = "SELECT * FROM FT_USER WHERE PERSONNELNUMBER='" + str1 + "' AND PWD='" + str2 + "'";
>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            if (result.first()) {
<<<<<<< HEAD
=======
                User user = null;
>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
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
<<<<<<< HEAD
                
                user.setId(result.getInt("ID"));
=======

>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
                user.setFirstname(result.getString("FIRSTNAME"));
                user.setLastname(result.getString("LASTNAME"));
                user.setAddress(result.getString("ADDRESS_"));
                user.setDateOfBirth(result.getDate("BIRTHDAY"));
                user.setSexe(result.getString("SEXE").charAt(0));
                user.setCity(result.getString("CITY"));
                user.setPostalCode(result.getInt("POSTALCODE"));
                user.setPhoneNumber(result.getInt("PHONENUMBER"));
                user.setEmailAddress(result.getString("EMAIL"));
<<<<<<< HEAD
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
=======
                user.setPersonelNumber(str1);
                user.setPassword(str2);

                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                return user;
            } else {
            	System.out.println("PAS DE RESULTAT !");
            	return null;
            }
        }
        catch (SQLException e)
        {
        	System.out.println("************** SQLException !");
            return null;
        }
    }

	@Override
	public ArrayList<User> findall() {
		System.out.println("USER findall");
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

                user.setFirstname(result.getString("FIRSTNAME"));
                user.setLastname(result.getString("LASTNAME"));
                user.setAddress(result.getString("ADDRESS_"));
                user.setDateOfBirth(result.getDate("BIRTHDAY"));
                user.setSexe(result.getString("SEXE").charAt(0));
                user.setCity(result.getString("CITY"));
                user.setPostalCode(result.getInt("POSTALCODE"));
                user.setPhoneNumber(result.getInt("PHONENUMBER"));
                user.setEmailAddress(result.getString("EMAIL"));
                user.setPersonelNumber(result.getString("PERSONNELNUMBER"));
                user.setPassword(result.getString("PWD"));

                if(result.getString("ACTIVE").equals("Y"))
                    user.setActive(true);
                else
                    user.setActive(false);
                users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			System.out.println("ERREUR");
			return null;
		}
>>>>>>> 92ff7125b1daf93286d63825f3cbd313dabb073d
	}
}