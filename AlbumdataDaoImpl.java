package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.playlist.pojo.Albums;

public class AlbumdataDaoImpl extends BaseDao {

	public int create(Albums albums) {

		int id = -1;
		try {
			Connection con = getConnection();
			String insertQuery = "INSERT INTO albums"
					+ "( year,artist,album,genre) VALUES" + "(?,?,?,?)";
			
			System.out.println(insertQuery);
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, albums.getYear());
			preparedStatement.setString(2, albums.getArtist());
			preparedStatement.setString(3, albums.getAlbum());
			preparedStatement.setString(4, albums.getGenre());

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
