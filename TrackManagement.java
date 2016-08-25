package com.playlist.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TrackManagement extends BasePanel {

	private JTabbedPane tabbedPane;

	TrackManagement() {
		createComponents();
		makeLayout();
	}

	private void createComponents() {
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setUI(new BasicTabbedPaneUI() {
			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex,
					int fontHeight) {
				return 30;
			}
		});
		tabbedPane.addTab("Add", new CreateTrack());
		tabbedPane.addTab("Update", new UpdateTrack());
		tabbedPane.addTab("Delete", new DeleteTrack());
		

	}

	private void makeLayout() {
		setLayout(new BorderLayout());

		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		
		listPane.add(Box.createRigidArea(new Dimension(5,5)));

		JPanel hpane = new JPanel();
		hpane.setLayout(new BoxLayout(hpane, BoxLayout.X_AXIS));
		JLabel label = new JLabel("Track Management");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpane.add(label);
		hpane.add(Box.createRigidArea(new Dimension(15,0)));

		hpane.add(backbutton);
		
		
		listPane.add(hpane);
		listPane.add(Box.createRigidArea(new Dimension(5,5)));
		
		listPane.add(tabbedPane);

	
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

		add(listPane, BorderLayout.CENTER);
	}
}
