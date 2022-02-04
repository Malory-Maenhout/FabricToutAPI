package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	//Attribute/variable
	protected Connection connect = null;

	//Constructor
	public DAO(Connection conn) {
		this.connect = APIRestConnection.getInstance();
	}

	//Methodes
	public abstract boolean create(T obj);
	
	public abstract boolean create(int id, T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract T find(String str1, String str2);
	
	public abstract ArrayList<T> findall();
	
	public abstract ArrayList<T> findall(int id);
}