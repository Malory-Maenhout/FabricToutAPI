package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Administrator;

public class AdministratorDAO extends DAO<Administrator> {

	public AdministratorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Administrator obj) {
		return false;
	}

	@Override
	public boolean delete(Administrator obj) {
		return false;
	}

	@Override
	public boolean update(Administrator obj) {
		return false;
	}

	@Override
	public Administrator find(int id) {
		return null;
	}
}