package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Manager;

public class ManagerDAO extends DAO<Manager>{

	public ManagerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Manager obj) {
		return false;
	}

	@Override
	public boolean delete(Manager obj) {
		return false;
	}

	@Override
	public boolean update(Manager obj) {
		return false;
	}

	@Override
	public Manager find(int id) {
		return null;
	}
}