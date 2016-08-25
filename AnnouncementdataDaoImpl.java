package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.playlist.pojo.Albums;
import com.playlist.pojo.Announcments;
import com.playlist.pojo.Tracks;

public class AnnouncementdataDaoImpl extends BaseDao {

	public int create(Announcments announcments) {

		int id = -1;
		try {
			Connection con = getConnection();
			String insertQuery = "INSERT INTO announcments"
					+ "( annoucment_Id,totaltime,name,type) VALUES"
					+ "(?,?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, announcments.getAnnoucment_Id());
			preparedStatement.setInt(2, announcments.getTotaltime());
			preparedStatement.setString(3, announcments.getName());
			preparedStatement.setString(4, announcments.getType());

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

	public Announcments readbyname(String name,String type) {

		Announcments announcments = new Announcments();
		name = name.replace("'", "\\'");

		
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from announcments where name='" + name+"' and type= '"+type+"'";

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {

				announcments.setAnnoucment_Id(resultSet.getInt("annoucment_Id"));
				announcments.setTotaltime(resultSet.getInt("totaltime"));
				announcments.setName(resultSet.getString("name"));
				announcments.setType(resultSet.getString("type"));
		
			} else {
				return null;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcments;

	}
	
	public Announcments read(int announcementid) {

		Announcments announcments = new Announcments();

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from announcments where annoucment_Id=" + announcementid;

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {

				announcments.setAnnoucment_Id(resultSet.getInt("annoucment_Id"));
				announcments.setTotaltime(resultSet.getInt("totaltime"));
				announcments.setName(resultSet.getString("name"));
				announcments.setType(resultSet.getString("type"));
		
			} else {
				return null;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcments;

	}
	
	public void update(Announcments announcments) {

		try {
			Connection con = getConnection();
			String insertQuery = "update announcments	 "
					+ "set totaltime = ?,name=?,type=? where annoucment_Id=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, announcments.getTotaltime());
			preparedStatement.setString(2, announcments.getName());
			preparedStatement.setString(3, announcments.getType());
			preparedStatement.setInt(4, announcments.getAnnoucment_Id());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
	
	public void delete(int announcementid){
		
		try {
			Connection con = getConnection();
			String insertQuery = "delete from announcments" + " where annoucment_Id = "
					+ " ?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setInt(1, announcementid);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
