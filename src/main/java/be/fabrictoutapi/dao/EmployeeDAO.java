package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.Employee;

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
		return null;
	}

	@Override
	public Employee find(String str1, String str2) {
		return null;
	}
}