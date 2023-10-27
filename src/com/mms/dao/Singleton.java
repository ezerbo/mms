package com.mms.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {

	private static String url = "jdbc:postgresql://localhost:5432/mms_database";
	private static String user = "postgres";
	private static String password = "diallo1990";
	private static Connection connection;

	public Singleton() {
	}

	public static Connection getInstance() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver").newInstance();
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
