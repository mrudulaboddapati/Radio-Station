package com.playlist.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.playlist.jdbc.PlayListDaoImpl;
import com.playlist.pojo.Tracks;

public class ReportsPanel extends BasePanel {

	public ReportsPanel() {

	}

	public void addScrollPane(JList<String> jlist, JPanel jlistpanel,
			String label) {

		JPanel vpane = new JPanel();

		vpane.setLayout(new BoxLayout(vpane, BoxLayout.Y_AXIS));

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setViewportView(jlist);
		vpane.setMaximumSize(new Dimension(300, 550));

		vpane.add(listScrollPane);
		JLabel slabel = new JLabel(label);
		// slabel.setMaximumSize(new Dimension(200, 350));

		vpane.add(slabel);
		
		
	

		jlistpanel.add(vpane);
	}

	public void start() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(5, 5)));

		JPanel hpane = new JPanel();
		hpane.setLayout(new BoxLayout(hpane, BoxLayout.X_AXIS));
		JLabel label = new JLabel("Playlist Report");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpane.add(label);
		hpane.add(Box.createRigidArea(new Dimension(55, 0)));

		JButton backbutton = new JButton("Cancel");

		hpane.add(backbutton);
		backbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("dashboard");
			}
		});

		hpane.add(Box.createRigidArea(new Dimension(15, 0)));

		JPanel jlistpanel = new JPanel();
		jlistpanel.setPreferredSize(new Dimension(200, 200));
		jlistpanel.setLayout(new BoxLayout(jlistpanel, BoxLayout.X_AXIS));

		PlayListDaoImpl playListDaoImpl = new PlayListDaoImpl();
		ArrayList<String> playlistnames = playListDaoImpl.getPlayListNames();

		DefaultListModel<String> trackslistModel = new DefaultListModel<>();

		final JList<String> trackslist = new JList<>(trackslistModel);
		for (String name : playlistnames) {
			System.out.println(name);
			trackslistModel.addElement(name);
		}
		addScrollPane(trackslist, jlistpanel, "PlayList");

		
		add(hpane);
		
		listPane.add(Box.createRigidArea(new Dimension(50, 50)));
		listPane.add(jlistpanel);
		listPane.add(Box.createRigidArea(new Dimension(50, 50)));
		
		mainPanel.add(listPane, BorderLayout.WEST);
		
	
		
		final DefaultListModel<String> resultslistModel = new DefaultListModel<>();

		final JList<String> resultslist = new JList<>(resultslistModel);
		
		addScrollPane(resultslist, jlistpanel, "Results");		
		
		trackslist.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	resultslistModel.removeAllElements();

	                	PlayListDaoImpl playListDaoImpl = new PlayListDaoImpl();
	            		ArrayList<String> playlistnames = playListDaoImpl.getTrackNames(trackslist.getSelectedValue().toString());
	            		for (String name : playlistnames) {
	            			System.out.println(name);
	            			resultslistModel.addElement(name);

	            		}
	                }
	            }
	        });

		add(mainPanel, BorderLayout.CENTER);

	}

}
