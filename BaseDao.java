package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {

	public  Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/radioplaylist", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}
