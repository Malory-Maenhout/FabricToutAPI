package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Maintenance;

public class MaintenanceDAO extends DAO<Maintenance>{

	public MaintenanceDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Maintenance obj) {
		return false;
	}

	@Override
	public boolean delete(Maintenance obj) {
		return false;
	}

	@Override
	public boolean update(Maintenance obj) {
		return false;
	}

	@Override
	public Maintenance find(int id) {
		return null;
	}

	@Override
	public Maintenance find(String str1, String str2) {
		return null;
	}
}