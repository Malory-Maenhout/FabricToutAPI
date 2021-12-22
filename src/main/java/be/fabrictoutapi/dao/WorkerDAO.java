package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Worker;

public class WorkerDAO extends DAO<Worker> {

	public WorkerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Worker obj) {
		return false;
	}

	@Override
	public boolean delete(Worker obj) {
		return false;
	}

	@Override
	public boolean update(Worker obj) {
		return false;
	}

	@Override
	public Worker find(int id) {
		return null;
	}

	@Override
	public Worker find(String str1, String str2) {
		return null;
	}
}