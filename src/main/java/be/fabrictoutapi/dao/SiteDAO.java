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
	public boolean create(Site obj) {
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
		System.out.println("SITE find : " + id);
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
				// appel de la liste des area
				String querry2 = "SELECT * FROM FT_AREA WHERE id_site='" + id + "'";
				ResultSet result2 = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(querry2);
				ArrayList<Area> areas = new ArrayList<Area>();
				while(result2.next()) {
					Area area = new Area();
					area.setId(result2.getInt("ID"));
					int column = result2.findColumn("LETTER");
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
					site.getAreaList().add(area);
					}
				return site;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Site find(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Site> findall() {
		System.out.println("SITE findall");
		String querry = "SELECT * FROM FT_SITE ORDER BY id";
		ArrayList<Site> sites = new ArrayList<>();
		try {			
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			while(result.next()) {
				Site site = new Site();
				site.setCity(result.getString("CITY"));
				site.setCountry(result.getString("COUNTRY"));
				
				
				sites.add(site);
			}
			return sites;
			
		} catch (SQLException e) {
			System.out.println("ERREUR");
			return null;
		}
	}
}