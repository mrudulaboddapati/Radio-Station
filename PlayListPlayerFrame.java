package com.playlist.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.playlist.jdbc.FileParser;
import com.playlist.pojo.PlayList;

public class PlayListPlayerFrame {

	/**
	 * @param args
	 */

	private JFrame mainFrame;
	public static int width = 800;
	public static int height = 600;

	private void displayFrame() {

		mainFrame = new JFrame("Radio Station Play list");
		mainFrame.setSize(width, height);
		mainFrame.setLocation(100, 50);

		
		final JFilePicker filePicker = new JFilePicker("Pick play list file");

	

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		JPanel mainPanel = new JPanel();
		mainFrame.setLayout(null);
		mainPanel.setBounds(10,10,800,200);

//		mainPanel.setPreferredSize(new Dimension(MainClass.width - 100,
//				MainClass.height - 100));

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(filePicker);
		
		//mainPanel.add(Box.createRigidArea(new Dimension(5, 50)));

		final JLabel label = new JLabel("");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		//label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		final JLabel duration = new JLabel("");
		duration.setFont(new Font("Serif", Font.PLAIN, 24));
		//duration.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		final JLabel type = new JLabel("");
		type.setFont(new Font("Serif", Font.PLAIN, 24));
		//type.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		final JLabel timer = new JLabel("");
		timer.setFont(new Font("Serif", Font.PLAIN, 24));
		//timer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(label);
		mainPanel.add(duration);
		mainPanel.add(type);
		mainPanel.add(timer);

		
		mainFrame.add(mainPanel);
		

		

		
		
		filePicker.submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				// TODO Auto-generated method stub

				SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {
						FileParser fileParser = new FileParser();
						try {

							
						 ArrayList<PlayList> playlists = 	fileParser.playlistFileParser(filePicker.textField
									.getText());
						 
						 for (PlayList playList : playlists) {
							
							 label.setText("Playing Now : "+playList.getName());
							 duration.setText("Duration : "+playList.getDuration());
							 type.setText("Type : "+playList.getType());
							 
								int time = playList.getDuration();
								while (time > 0) {

									time--;
									timer.setText("Time left : "+time);
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
							 }
						}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return null;
					}
				};
				mySwingWorker.execute();

			}
		});

		mainFrame.setVisible(true);

	}

	public static void main(String[] args) {
		PlayListPlayerFrame mainClass = new PlayListPlayerFrame();
		mainClass.displayFrame();
	}

}
