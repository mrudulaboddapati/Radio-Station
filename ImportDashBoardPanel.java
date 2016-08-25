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

public class ImportDashBoardPanel extends BasePanel implements IPanel {

	public ImportDashBoardPanel() {
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

	public void addButton(JButton button, JPanel listPane) {

		button.setPreferredSize(new Dimension(250, 50));
		button.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				Integer.MAX_VALUE));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		listPane.add(Box.createRigidArea(new Dimension(5, 20)));
		listPane.add(button);

	}

	public void addListener(JButton button, final String actionpanel) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				MainClass.display(actionpanel);
			}
		});

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

		JPanel listPane = new JPanel();
		JLabel label = new JLabel("Import csv files");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		listPane.add(label);
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

		JButton importalbum = new JButton("Import Album csv");
		JButton importannouncement = new JButton("Import Announcment csv");
		JButton importartist = new JButton("Import artist csv");
		JButton importtrack = new JButton("Import tracks csv");

		addButton(importalbum, listPane);
		addListener(importalbum, "albumImport");

		addButton(importannouncement, listPane);
		addListener(importannouncement, "announcementImport");

		addButton(importartist, listPane);
		addListener(importartist, "artistImport");

		addButton(importtrack, listPane);
		addListener(importtrack, "tracksImport");

		addButton(backbutton, listPane);

		add(listPane);

	}
}
