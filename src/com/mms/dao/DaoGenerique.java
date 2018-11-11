package com.mms.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DaoGenerique<T> {
	public Connection connection;

	public DaoGenerique() {
		connection = Singleton.getInstance();
	}

	public abstract T create(T objet);

	public abstract int delete(T objet);

	public abstract int update(T objet);

	public abstract T findByName(String name);

	public abstract List<T> findAll();

	public abstract T findById(int id);

}
