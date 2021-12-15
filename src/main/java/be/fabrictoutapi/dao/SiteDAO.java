package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Site;

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
		return null;
	}
}