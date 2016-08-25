package com.playlist.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class MainClass {

	private JFrame mainFrame;
	private static String previouscard;
	public static int width = 800;
	public static int height = 600;

	public MainClass() {
	}

	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		mainClass.displayFrame();
	}

	static JPanel cardPanel;
	private static CreatePlayListPanel createPlayListPanel;
	private static ReportsPanel reportsPanel;

	private void displayFrame() {

		mainFrame = new JFrame("Radio Station Play list");
		mainFrame.setSize(width, height);
		mainFrame.setLocation(100, 50);

		cardPanel = new JPanel();
		CardLayout card = new CardLayout();

		cardPanel.setLayout(card);

		MainDashBoard dashboard = new MainDashBoard();
		cardPanel.add(dashboard, "dashboard");

		ImportDashBoardPanel importDashBoardPanel = new ImportDashBoardPanel();
		cardPanel.add(importDashBoardPanel, "importDashBoardPanel");

		AlbumImport albumImport = new AlbumImport();
		cardPanel.add(albumImport, "albumImport");

		AnnouncementImport announcementImport = new AnnouncementImport();
		cardPanel.add(announcementImport, "announcementImport");

		ArtistImport artistImport = new ArtistImport();
		cardPanel.add(artistImport, "artistImport");

		TracksImport tracksImport = new TracksImport();
		cardPanel.add(tracksImport, "tracksImport");

		TrackManagement trackManagement = new TrackManagement();
		cardPanel.add(trackManagement, "trackManagement");

		AdvertisementManagement advertisementManagement = new AdvertisementManagement();
		cardPanel.add(advertisementManagement, "advertisementManagement");

		AnnouncementManagement announcementManagement = new AnnouncementManagement();
		cardPanel.add(announcementManagement, "announcementManagement");

		createPlayListPanel = new CreatePlayListPanel();
		cardPanel.add(createPlayListPanel, "createPlayListPanel");

		reportsPanel = new ReportsPanel();
		cardPanel.add(reportsPanel, "reportsPanel");

		mainFrame.add(cardPanel);
		display("dashboard");

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		mainFrame.setVisible(true);
	}

	public static void display(String name) {

		CardLayout layout = (CardLayout) (cardPanel.getLayout());

		if (name.equals("createPlayListPanel")) {

			cardPanel.remove(createPlayListPanel);
			cardPanel.add(createPlayListPanel, "createPlayListPanel");
			createPlayListPanel.start();
			layout.show(cardPanel, name);

		} else if (name.equals("reportsPanel")) {

			cardPanel.remove(reportsPanel);
			cardPanel.add(reportsPanel, "reportsPanel");
			reportsPanel.start();
			layout.show(cardPanel, name);

		}

		else {
			previouscard = name;
			layout.show(cardPanel, name);
		}

	}

	public static void goBack() {
		CardLayout layout = (CardLayout) (cardPanel.getLayout());

		layout.show(cardPanel, "");
	}

}
