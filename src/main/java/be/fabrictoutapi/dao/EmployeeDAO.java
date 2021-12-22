package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.fabrictoutapi.enums.ColorEnum;
import be.fabrictoutapi.javabeans.Area;
import be.fabrictoutapi.javabeans.Employee;
import be.fabrictoutapi.javabeans.Site;

public class EmployeeDAO extends DAO<Employee> {

	public EmployeeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Employee obj) {
		return false;
	}

	@Override
	public boolean delete(Employee obj) {
		return false;
	}

	@Override
	public boolean update(Employee obj) {
		return false;
	}

	@Override
	public Employee find(int id) {
		Employee employee = new Employee();
		String querry = "SELECT * FROM FT_SITE";
		try {

			ResultSet result = this.connect
				.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
				.executeQuery(querry);
			while(result.next()) {
				Site site = new Site();
				site.setId(result.getInt("id"));
				site.setCity(result.getString("city"));
				site.setCountry(result.getString("country"));
				String querry2 = "SELECT * FROM FT_AREA WHERE id_site='" + result.getInt("id") + "'";
				ResultSet result2 = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry2);
				while(result2.next()) {
					Area area = new Area();
					area.setId(result2.getInt("id"));
					area.setLetter(result2.getString("LETTER").charAt(0));
					String col = result2.getString("COLOR");
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
					area.setDescription(result2.getString("DESCR"));
					site.getAreaList().add(area);
				}
				employee.getSiteList().add(site);
			}
			return employee;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Employee find(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee find(String str1, String str2) {
		return null;
	}
}