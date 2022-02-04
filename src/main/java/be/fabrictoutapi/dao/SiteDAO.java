package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import be.fabrictoutapi.enums.*;
import be.fabrictoutapi.javabeans.*;

public class SiteDAO extends DAO<Site>{

	public SiteDAO(Connection conn) {
		super(conn);
	}

	@Override
    public boolean create(Site site) {
        try {
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate("INSERT INTO FT_SITE (city, country) "
                            + "Values('" + site.getCity() + "','" + site.getCountry() + "')");
           
            return true;
        } 
        catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
    }

	@Override
	public boolean create(int id, Site obj) {
		return false;
	}
	
	@Override
	public boolean delete(Site obj) {
		return false;
	}

	@Override
	public boolean update(Site obj) {
		return false;
	}

	@Override
    public Site find(int id) {
        String querry = "SELECT * FROM FT_SITE WHERE id='" + id + "'";
        
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(querry);
            
            if(result.first()) {
                Site site = new Site();
                site.setId(result.getInt("ID"));
                site.setCity(result.getString("CITY"));
                site.setCountry(result.getString("COUNTRY"));
                
                String querry2 = "SELECT * FROM FT_AREA WHERE id_site='" + id + "' ORDER BY id";
                
                ResultSet result2 = this.connect
                        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(querry2);
                
                ArrayList<Area> areas = new ArrayList<Area>();
                
                while(result2.next()) {
                    Area area = new Area();
                    area.setId(result2.getInt("ID"));
                    char letter = result2.getString("LETTER").charAt(0);
                    area.setLetter(letter);
                    String col = result2.getString("COLOR");
                    
                    switch (col) {
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
                    
                    area.setDescription(result2.getString("DESCR"));
                    areas.add(area);
                }
                
                site.setAreaList(areas);
                
                String querry3 = "SELECT * FROM FT_USER WHERE id_site='" + id + "' and active='Y' ORDER BY id";
                
                ResultSet result3 = this.connect
                        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(querry3);
                
                while(result3.next()) {
                    User user = null;
                    String discriminator = result3.getString("discriminator");
                    
                    if(discriminator.equals("ADMIN")) {
                        user = new Administrator();
                    }
                    else if (discriminator.equals("EMPLOYE")) {
                        user = new Employee();
                    } 
                    else if (discriminator.equals("MANAGER")) {
                        user = new Manager();
                    }
                    else if (discriminator.equals("WORKER")) {
                        user = new Worker();
                    }
                    
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
                    
                    site.getUserList().add(user);
                }
                
                return site;
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

	@Override
	public Site find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Site> findall() {
		String querry = "SELECT * FROM FT_SITE ORDER BY id";
		
		ArrayList<Site> sites = new ArrayList<>();
		
		try {			
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			while(result.next()) {
				Site site = new Site();
				site.setId(result.getInt("ID"));
				site.setCity(result.getString("CITY"));
				site.setCountry(result.getString("COUNTRY"));			
				sites.add(site);
			}
			
			return sites;
			
		} 
		catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public ArrayList<Site> findall(int id) {
		return null;
	}
}