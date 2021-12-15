package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Area;

public class AreaDAO extends DAO<Area> {

	public AreaDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Area obj) {
		return false;
	}

	@Override
	public boolean delete(Area obj) {
		return false;
	}

	@Override
	public boolean update(Area obj) {
		return false;
	}

	@Override
	public Area find(int id) {
		return null;
	}
}