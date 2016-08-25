package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.playlist.pojo.Announcments;
import com.playlist.pojo.Tracks;

public class PlayListDaoImpl extends BaseDao {

	public ArrayList<String> getPlayListNames() {

		ArrayList<String> playlistnames = new ArrayList<>();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select distinct playlistname from playlist";

			System.out.println(SQL);
			ResultSet resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				playlistnames.add(resultSet.getString("playlistname"));

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playlistnames;
	}

	public ArrayList<String> getTrackNames(String playlistname) {

		ArrayList<String> tracknames = new ArrayList<>();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from playlist where playlistname = '"
					+ playlistname + "'";

			System.out.println(SQL);
			ResultSet resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				tracknames.add(resultSet.getString("name")+" "+resultSet.getString("time"));

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tracknames;
	}

	public ArrayList<Announcments> getAnnouncements(String type) {

		ArrayList<Announcments> announcementslist = new ArrayList<>();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from announcments where type='" + type + "'";

			System.out.println(SQL);
			ResultSet resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Announcments announcments = new Announcments();

				announcments
						.setAnnoucment_Id(resultSet.getInt("annoucment_Id"));
				announcments.setTotaltime(resultSet.getInt("totaltime"));
				announcments.setName(resultSet.getString("name"));
				announcments.setType(resultSet.getString("type"));
				announcementslist.add(announcments);

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcementslist;
	}

	public ArrayList<Tracks> getAlltracks() {

		ArrayList<Tracks> trackslist = new ArrayList<>();
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from tracks";

			ResultSet resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Tracks tracks = new Tracks();

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
				tracks.setAlbumartist(resultSet.getString("albumartist"));
				trackslist.add(tracks);

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trackslist;
	}
}
