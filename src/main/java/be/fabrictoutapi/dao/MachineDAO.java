package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Machine;

public class MachineDAO extends DAO<Machine> {

	public MachineDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Machine obj) {
		return false;
	}

	@Override
	public boolean delete(Machine obj) {
		return false;
	}

	@Override
	public boolean update(Machine obj) {
		return false;
	}

	@Override
	public Machine find(int id) {
		return null;
	}

	@Override
	public Machine find(String str1, String str2) {
		return null;
	}
}