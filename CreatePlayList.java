package com.playlist.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.playlist.pojo.Announcments;
import com.playlist.pojo.PlayList;
import com.playlist.pojo.Tracks;

public class CreatePlayList extends BaseDao {

	public void createPlayList(List<String> selectedtrackslist,
			List<String> selectedadlist, List<String> selectedpsalist,
			List<String> selectedalist, String playlistname, long time,
			String filepath) {

		ArrayList<PlayList> playLists = new ArrayList<PlayList>();
		int runningduration = 0;
		int addlistcounter = 0;
		int psacounter = 0;
		int acounter = 0;

		System.out.println("sfds"+selectedtrackslist.size());
		System.out.println("fdsfd"+selectedadlist.size());
		System.out.println("fsd"+selectedpsalist.size());
		System.out.println("fsfds"+selectedalist.size());

		for (String tracklist : selectedtrackslist) {

			TracksdataDaoImpl tracksdataDaoImpl = new TracksdataDaoImpl();
			System.out.println(tracklist);
			Tracks tracks = tracksdataDaoImpl.readTrackbyname(tracklist);
			PlayList playList = new PlayList();
			playList.setName(tracks.getName());
			playList.setDuration(tracks.getTotaltime());
			runningduration += tracks.getTotaltime();
			playList.setType("Track");
			System.out.println("Adding to play list");
			
			if(!alreadyadded(tracks.getName(), "Track", time)){
				System.out.println("track added");
			playLists.add(playList);
			}
            
			String previousadd="";
			
			if (runningduration > (15 * 60) && playLists.size()>0) {
				try {
				
				runningduration = 0;

				if (addlistcounter < selectedadlist.size() && !previousadd.equals("Ad")) {
					Announcments announcment = getAnnouncement(
							selectedadlist.get(addlistcounter), "Ad");
					 playList = new PlayList();

					playList.setName(announcment.getName());
					playList.setDuration(announcment.getTotaltime());
					playList.setType("Ad");
					
				//	if(!alreadyadded(announcment.getName(), "Ad", time)){
						System.out.println("Ad added");

				     	playLists.add(playList);
				     	previousadd="Ad";
					//}
					
					addlistcounter++;
				}

				
				if (psacounter < selectedpsalist.size() && !previousadd.equals("PSA")) {
					Announcments announcment = getAnnouncement(
							selectedpsalist.get(psacounter), "PSA");
					 playList = new PlayList();

					playList.setName(announcment.getName());
					playList.setDuration(announcment.getTotaltime());
					playList.setType("PSA");
				//	if(!alreadyadded(announcment.getName(), "PSA", time)){
					playLists.add(playList);
					previousadd="PSA";
					//}
					psacounter++;

				}

				if (acounter < selectedalist.size() && !previousadd.equals("Announcement")) {
					Announcments announcment = getAnnouncement(
							selectedalist.get(acounter), "Announcement");
					 playList = new PlayList();

					playList.setName(announcment.getName());
					playList.setDuration(announcment.getTotaltime());
					playList.setType("Announcement");
					//if(!alreadyadded(announcment.getName(), "Announcement", time))

						previousadd="Announcement";
					playLists.add(playList);
					acounter++;

				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
				

		}

		System.out.println("Adding to csv");
		addToCsvFile(filepath, playLists, playlistname, time);
	}

	public void addToCsvFile(String filepath, ArrayList<PlayList> playLists,String playlistname,long unixtime) {

		FileWriter fileWriter = null;
		filepath+=".csv";
		
		try {
			fileWriter = new FileWriter(filepath);

			// Write a new student object list to the CSV file
			for (PlayList playList : playLists) {
				fileWriter.append(playList.getName());
				fileWriter.append(",");
				fileWriter.append(playList.getDuration() + "");
				fileWriter.append(",");
				fileWriter.append(playList.getType());
				fileWriter.append("\n");
				create(playList, playlistname, unixtime);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}

	public int create(PlayList playList,String playlistname,long unixtime) {

		int id = -1;
		try {
			Connection con = getConnection();
			String insertQuery = "INSERT INTO playlist"
					+ "( playlistname,name,time,type,playlisttime) VALUES"
					+ "(?,?,?,?,?)";
			System.out.println(insertQuery);
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, playlistname);
			preparedStatement.setString(2, playList.getName());
			preparedStatement.setInt(3, playList.getDuration());
			preparedStatement.setString(4, playList.getType());
			preparedStatement.setLong(5, unixtime);

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

	public Announcments getAnnouncement(String name, String type) {

		AnnouncementdataDaoImpl announcementdataDaoImpl = new AnnouncementdataDaoImpl();
		return announcementdataDaoImpl.readbyname(name, type);
	}

	public boolean alreadyadded(String name, String type, long time) {

		boolean result = false;
		name = name.replace("'", "\\'");

		long ptime = time - 60 * 60 * 24 * 1;

		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from playlist where name='" + name
					+ "' and type = '" + type + "' and playlisttime> " + ptime
					+ " and playlisttime<" + time;

			System.out.println(SQL);

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {
				result = true;
			} else {
				result = false;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}
}
