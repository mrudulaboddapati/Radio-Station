package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.playlist.pojo.Artist;

public class ArtistdataDaoImpl extends BaseDao {

	public int create(Artist artist) {

		int id = -1;
		try {
			Connection con = getConnection();
			String insertQuery = "INSERT INTO artist" + "( name) VALUES"
					+ "(?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, artist.getName());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return id;
	}
}
