package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.fabrictoutapi.javabeans.Report;

public class ReportDAO extends DAO<Report>{

	public ReportDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Report obj) {
		return false;
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
		return null;
	}

	@Override
	public Report find(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Report> findall() {
		// TODO Auto-generated method stub
		return null;
	}
}