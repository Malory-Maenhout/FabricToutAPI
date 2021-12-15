package be.fabrictoutapi.dao;

import java.sql.Connection;

import be.fabrictoutapi.javabeans.User;

public class UserDAO extends DAO<User>{

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}

	@Override
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	@Override
	public User find(int id) {
		return null;
	}
}