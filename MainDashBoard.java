package com.playlist.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainDashBoard extends BasePanel implements IPanel {

	public MainDashBoard() {
		super();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent evt) {
				// stop();
			}

			@Override
			public void componentShown(ComponentEvent evt) {
				// init();
			}
		});

		init();

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

		JPanel listPane = new JPanel();
		JLabel label = new JLabel("Main Dashboard");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		listPane.add(label);

		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

		JButton importfiles = new JButton("Import Files");
		JButton trackmanagement = new JButton("Track Management");
		JButton advmanagement = new JButton("Advertisement Management");
		JButton announcemanagement = new JButton("Announcement Management");
		JButton playlistmanagement = new JButton("Playlist Management");
		JButton reports = new JButton("Reports");
		reports.setPreferredSize(new Dimension(250, 50));

		importfiles.setPreferredSize(new Dimension(250, 50));
		trackmanagement.setPreferredSize(new Dimension(250, 50));
		advmanagement.setPreferredSize(new Dimension(250, 50));
		announcemanagement.setPreferredSize(new Dimension(250, 50));
		playlistmanagement.setPreferredSize(new Dimension(250, 50));

		importfiles.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));
		importfiles.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));
		listPane.add(importfiles);

		trackmanagement.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));
		trackmanagement.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));
		listPane.add(trackmanagement);

		advmanagement.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));

		advmanagement.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));

		listPane.add(advmanagement);
		announcemanagement.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));

		announcemanagement.setAlignmentX(Component.CENTER_ALIGNMENT);

		listPane.add(announcemanagement);
		playlistmanagement.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));

		playlistmanagement.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));

		listPane.add(playlistmanagement);
		reports.setAlignmentX(Component.CENTER_ALIGNMENT);
		reports.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));

		listPane.add(reports);

		importfiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("importDashBoardPanel");
			}
		});

		trackmanagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("trackManagement");
			}
		});
		advmanagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("advertisementManagement");
			}
		});
		announcemanagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("announcementManagement");
			}
		});
		
		playlistmanagement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("createPlayListPanel");
			}
		});
		
		
		reports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("reportsPanel");
			}
		});

		
		add(listPane);
	}

}
