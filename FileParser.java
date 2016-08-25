package com.playlist.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.playlist.pojo.Albums;
import com.playlist.pojo.Announcments;
import com.playlist.pojo.Artist;
import com.playlist.pojo.PlayList;
import com.playlist.pojo.Tracks;
import com.playlist.view.ArtistImport;

public class FileParser {

	public FileParser() {

	}
	
	
	public ArrayList<PlayList> playlistFileParser(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String array_line;
		ArrayList<PlayList> playLists = new ArrayList<>();
		while ((array_line = reader.readLine()) != null) {

		
			String[] array = array_line.split(",");
			PlayList playList = new PlayList();
			playList.setName(array[0]);
			playList.setDuration(Integer.parseInt(array[1]));
			playList.setType(array[2]);

			playLists.add(playList);

		}
		reader.close();
		return playLists;
	}

	public void albumsFileParser(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String array_line;
		boolean firstline = true;
		while ((array_line = reader.readLine()) != null) {

			if (firstline) {
				firstline = false;
				continue;
			}
			String[] array = array_line.split(",");
			Albums albums = new Albums();
			albums.setYear(Integer.parseInt(array[0]));
			albums.setArtist(array[1]);
			albums.setAlbum(array[2]);
			albums.setGenre(array[3]);

			AlbumdataDaoImpl albumdataDaoImpl = new AlbumdataDaoImpl();
			albumdataDaoImpl.create(albums);

		}
		reader.close();
	}

	public void announcmentFileParser(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String array_line;
		boolean firstline = true;
		while ((array_line = reader.readLine()) != null) {

			if (firstline) {
				firstline = false;
				continue;
			}
			String[] array = array_line.split(",");
			Announcments announcments = new Announcments();
			announcments.setAnnoucment_Id(Integer.parseInt(array[0]));
			announcments.setTotaltime(Integer.parseInt(array[1]));
			announcments.setName(array[3]);
			announcments.setType(array[4]);

			AnnouncementdataDaoImpl announcementdataDaoImpl = new AnnouncementdataDaoImpl();
			announcementdataDaoImpl.create(announcments);

		}
		reader.close();
	}

	public void artistFileParser(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String array_line;
		boolean firstline = true;
		while ((array_line = reader.readLine()) != null) {

			System.out.println("Testing");
			if (firstline) {
				firstline = false;
				continue;
			}
			String[] array = array_line.split(",");
			Artist artist = new Artist();
			artist.setName(array[0]);
			ArtistdataDaoImpl artistdataDaoImpl = new ArtistdataDaoImpl();
			artistdataDaoImpl.create(artist);

		}
		reader.close();
	}

	public void tracksFileParser(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String array_line;
		boolean firstline = true;
		while ((array_line = reader.readLine()) != null) {

			if (firstline) {
				firstline = false;
				continue;
			}
			String[] array = array_line.split(",");
			Tracks tracks = new Tracks();
			tracks.setTrackid(Integer.parseInt(array[0]));
			tracks.setTotaltime(Integer.parseInt(array[1]));
			tracks.setDiscnumber(Integer.parseInt(array[2]));
			tracks.setDisccount(Integer.parseInt(array[3]));
			tracks.setTracknumber(Integer.parseInt(array[4]));
			tracks.setTrackcount(Integer.parseInt(array[5]));
			tracks.setYear(Integer.parseInt(array[6]));
			tracks.setName(array[7]);
			tracks.setArtist(array[8]);
			tracks.setAlbumartist(array[9]);
			tracks.setAlbum(array[10]);
			tracks.setGenre(array[11]);

			TracksdataDaoImpl tracksdataDaoImpl = new TracksdataDaoImpl();

			tracksdataDaoImpl.create(tracks);
		}
		reader.close();
	}

}
