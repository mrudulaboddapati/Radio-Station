package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.playlist.pojo.Tracks;

public class TracksdataDaoImpl extends BaseDao {

	public int create(Tracks tracks) {

		int id = -1;
		try {
			Connection con = getConnection();
			String insertQuery = "INSERT INTO tracks"
					+ "( trackid,totaltime,discnumber,disccount,tracknumber,trackcount,year,artist,album,genre,name,albumartist) VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, tracks.getTrackid());
			preparedStatement.setInt(2, tracks.getTotaltime());
			preparedStatement.setInt(3, tracks.getDiscnumber());
			preparedStatement.setInt(4, tracks.getDisccount());
			preparedStatement.setInt(5, tracks.getTracknumber());
			preparedStatement.setInt(6, tracks.getTrackcount());
			preparedStatement.setInt(7, tracks.getYear());
			preparedStatement.setString(8, tracks.getArtist());
			preparedStatement.setString(9, tracks.getAlbum());
			preparedStatement.setString(10, tracks.getGenre());
			preparedStatement.setString(11, tracks.getName());
			preparedStatement.setString(12, tracks.getAlbumartist());

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

	public void updateTrack(Tracks track) {

		try {
			Connection con = getConnection();
			String insertQuery = "update tracks "
					+ "set totaltime = ?,discnumber=?,disccount=?,tracknumber=?,trackcount=?,year=?,artist=?,album=?,genre=?,albumartist=?,name=? where trackid=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, track.getTotaltime());
			preparedStatement.setInt(2, track.getDiscnumber());
			preparedStatement.setInt(3, track.getDisccount());
			preparedStatement.setInt(4, track.getTracknumber());
			preparedStatement.setInt(5, track.getTrackcount());
			preparedStatement.setInt(6, track.getYear());
			preparedStatement.setString(7, track.getArtist());
			preparedStatement.setString(8, track.getAlbum());
			preparedStatement.setString(9, track.getGenre());
			preparedStatement.setString(10, track.getAlbumartist());
			preparedStatement.setString(11, track.getName());
			preparedStatement.setInt(12, track.getTrackid());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void delete(int trackid) {

		try {
			Connection con = getConnection();
			String insertQuery = "delete from tracks" + " where trackid = "
					+ " ?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, trackid);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int trackalreadytaken(int trackid) {
		int result = -1;
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from tracks where trackid=" + trackid;

			System.out.println(SQL);

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {
				result = resultSet.getInt("trackid");
			} else {
				result = -1;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		return result;

	}

	public Tracks readTrack(int trackid) {

		Tracks tracks = new Tracks();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from tracks where trackid=" + trackid;

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {

				tracks.setTrackid(resultSet.getInt("trackid"));
				tracks.setTotaltime(resultSet.getInt("totaltime"));
				tracks.setDiscnumber(resultSet.getInt("discnumber"));
				tracks.setDisccount(resultSet.getInt("disccount"));
				tracks.setTracknumber(resultSet.getInt("tracknumber"));
				tracks.setTrackcount(resultSet.getInt("trackcount"));
				tracks.setYear(resultSet.getInt("year"));
				tracks.setArtist(resultSet.getString("artist"));
				tracks.setAlbum(resultSet.getString("album"));
				tracks.setGenre(resultSet.getString("genre"));

			} else {
				return null;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tracks;

	}
	
	public Tracks readTrackbyname(String name) {
		name = name.replace("'", "\\'");

		Tracks tracks = new Tracks();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from tracks where name='" + name+"'";
			

			System.out.println(SQL);
			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {

				tracks.setTrackid(resultSet.getInt("trackid"));
				tracks.setTotaltime(resultSet.getInt("totaltime"));
				tracks.setDiscnumber(resultSet.getInt("discnumber"));
				tracks.setDisccount(resultSet.getInt("disccount"));
				tracks.setTracknumber(resultSet.getInt("tracknumber"));
				tracks.setTrackcount(resultSet.getInt("trackcount"));
				tracks.setYear(resultSet.getInt("year"));
				tracks.setArtist(resultSet.getString("artist"));
				tracks.setAlbum(resultSet.getString("album"));
				tracks.setGenre(resultSet.getString("genre"));
				tracks.setName(resultSet.getString("name"));

			} else {
				return null;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tracks;

	}

}
