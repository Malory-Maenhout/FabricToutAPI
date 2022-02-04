package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import be.fabrictoutapi.javabeans.Report;
import be.fabrictoutapi.javabeans.Worker;

public class ReportDAO extends DAO<Report>{

	public ReportDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Report obj) {
		return false;
	}
	
	@Override
    public boolean create(int id, Report obj) {
        try {
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		String date = simpleDateFormat.format(obj.getDate());
    		
            String querry = "INSERT INTO FT_REPORT (descr, date_, id_maintenance, id_worker) "
                    + "Values('" + obj.getDescription() + "','" + date + "',"
                    + id + "," + obj.getWorker().getId() + ")";
            
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate(querry);
            
            String querry2 = "UPDATE FT_MAINTENANCE SET " +
                    "status_='Do' WHERE id =" + id;
                
            	try {
                    this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeUpdate(querry2);
                    return true;
                }
                catch(SQLException e) {
                    return false;
                }
        } 
        catch (SQLException e) {
            return false;
        }
    }

	@Override
	public boolean delete(Report obj) {
		return false;
	}

	@Override
	public boolean update(Report obj) {
		return false;
	}

	@Override
	public Report find(int id) {
		String querry = "SELECT * FROM FT_REPORT WHERE id='" + id + "'";
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			if(result.first()) {
				Report report = new Report();
				report.setId(result.getInt("id"));
				report.setDescription(result.getString("descr"));
				report.setDate(result.getDate("date_"));
				
				Worker w = new Worker();
				w.setId(result.getInt("id_worker"));
				
				report.setWorker(w);
								
				return report;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Report find(String str1, String str2) {
		return null;
	}

	@Override
	public ArrayList<Report> findall() {
		String querry = "SELECT * FROM FT_REPORT";
		
		ArrayList<Report> reports = new ArrayList<Report>();
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(querry);
			
			while(result.next()) {
				Report report = new Report();
				report.setId(result.getInt("id"));
				report.setDescription(result.getString("descr"));
				report.setDate(result.getDate("date_"));
				
				Worker w = new Worker();
				w.setId(result.getInt("id_worker"));
				
				report.setWorker(w);
				
				reports.add(report);
			}
			
			return reports;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Report> findall(int id) {
		return null;
	}
}