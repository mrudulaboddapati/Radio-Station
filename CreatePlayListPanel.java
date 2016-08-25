package com.playlist.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingWorker;

import com.playlist.jdbc.CreatePlayList;
import com.playlist.jdbc.FileParser;
import com.playlist.jdbc.PlayListDaoImpl;
import com.playlist.jdbc.TracksdataDaoImpl;
import com.playlist.pojo.Announcments;
import com.playlist.pojo.Tracks;

public class CreatePlayListPanel extends BasePanel {

	public CreatePlayListPanel() {

		super();
	}

	
	static String format ="M/dd/yyyy HH:mm";

	public void addScrollPane(JList<String> jlist, JPanel jlistpanel,
			String label) {

		JPanel vpane = new JPanel();

		vpane.setLayout(new BoxLayout(vpane, BoxLayout.Y_AXIS));

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setViewportView(jlist);
		vpane.setMaximumSize(new Dimension(200, 350));

		vpane.add(listScrollPane);
		JLabel slabel = new JLabel(label);
		// slabel.setMaximumSize(new Dimension(200, 350));

		vpane.add(slabel);

		jlistpanel.add(vpane);
	}

	public void start() {

		setLayout(new BorderLayout());

		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

		listPane.add(Box.createRigidArea(new Dimension(5, 5)));

		JPanel hpane = new JPanel();
		hpane.setLayout(new BoxLayout(hpane, BoxLayout.X_AXIS));
		JLabel label = new JLabel("Create Playlist");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpane.add(label);
		hpane.add(Box.createRigidArea(new Dimension(55, 0)));

		hpane.add(backbutton);
		hpane.add(Box.createRigidArea(new Dimension(15, 0)));

		listPane.add(hpane);
		listPane.add(Box.createRigidArea(new Dimension(5, 5)));

		// listPane.add(new JLabel("  Tracks    "+
		// "      "+" Advertisement"+"  PSA    "+ "      "+" Announcement"));

		JPanel jlistpanel = new JPanel();
		jlistpanel.setPreferredSize(new Dimension(200, 200));
		jlistpanel.setLayout(new BoxLayout(jlistpanel, BoxLayout.X_AXIS));

		PlayListDaoImpl playListDaoImpl = new PlayListDaoImpl();
		ArrayList<Tracks> tracksdata = playListDaoImpl.getAlltracks();

		DefaultListModel<String> trackslistModel = new DefaultListModel<>();

		final JList<String> trackslist = new JList<>(trackslistModel);
		for (Tracks tracks : tracksdata) {
			trackslistModel.addElement(tracks.getName());
		}
		addScrollPane(trackslist, jlistpanel, "Tracks");

		ArrayList<Announcments> adannouncementsdata = playListDaoImpl
				.getAnnouncements("Ad");
		DefaultListModel<String> adannouncementslistModel = new DefaultListModel<>();
		final JList<String> adannouncementslist = new JList<>(
				adannouncementslistModel);
		for (Announcments announcments : adannouncementsdata) {
			System.out.println(announcments.getName());
			adannouncementslistModel.addElement(announcments.getName());
		}
		addScrollPane(adannouncementslist, jlistpanel, "Advertisement");

		ArrayList<Announcments> psaannouncementsdata = playListDaoImpl
				.getAnnouncements("PSA");
		DefaultListModel<String> psaannouncementslistModel = new DefaultListModel<>();
		final JList<String> psaannouncementslist = new JList<>(
				psaannouncementslistModel);
		for (Announcments announcments : psaannouncementsdata) {
			psaannouncementslistModel.addElement(announcments.getName());
		}
		addScrollPane(psaannouncementslist, jlistpanel, "PSA");

		ArrayList<Announcments> aannouncementsdata = playListDaoImpl
				.getAnnouncements("Announcement");
		DefaultListModel<String> aannouncementslistModel = new DefaultListModel<>();
		final JList<String> aannouncementslist = new JList<>(aannouncementslistModel);
		for (Announcments announcments : aannouncementsdata) {
			aannouncementslistModel.addElement(announcments.getName());
		}
		addScrollPane(aannouncementslist, jlistpanel, "Announcement");

		JPanel vpanel = new JPanel();
		vpanel.setLayout(new BoxLayout(vpanel, BoxLayout.Y_AXIS));

		JPanel fhpanel = new JPanel();
		fhpanel.setLayout(new BoxLayout(fhpanel, BoxLayout.X_AXIS));

		JLabel lname = new JLabel("Name:");
		final JTextField tname = new JTextField();
		tname.setMaximumSize(new Dimension(300, 30));

		fhpanel.add(lname);
		fhpanel.add(tname);
		vpanel.add(fhpanel);

		final JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		final JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"M/dd/yyyy HH:mm");
		timeEditor.setMaximumSize(new Dimension(300, 30));

		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());

		JLabel ldate = new JLabel("Date:");
		final JTextField ttrackid = new JTextField();

		fhpanel = new JPanel();
		fhpanel.setLayout(new BoxLayout(fhpanel, BoxLayout.X_AXIS));
		fhpanel.add(ldate);
		fhpanel.add(timeSpinner);
		vpanel.add(Box.createRigidArea(new Dimension(0, 15)));

		vpanel.add(fhpanel);

		listPane.add(jlistpanel);
		listPane.add(Box.createRigidArea(new Dimension(0, 25)));

		vpanel.setMaximumSize(new Dimension(300, 60));

		listPane.add(vpanel);

		JButton submit = new JButton("Submit");

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String time  =timeEditor.getFormat().format(timeSpinner.getValue());

				System.out.println(time);
				if (tname.getText().toString().trim().equals("")) {
					showError("Name is required");
					return;
				}
				
			    if (time.equals("")) {
					showError("Time is required");
					return;
				}
			    
			    if(!isValidFormat(time)){
			    	
			    	showError("Time is not valid");
					return;
			    }
			    
			    final List<String> selectedtrackslist =  trackslist.getSelectedValuesList();
			    final List<String> selectedadlist  = adannouncementslist.getSelectedValuesList();
			   final List<String> selectedpsalist  = psaannouncementslist.getSelectedValuesList();
			    final List<String> selectedalist =  aannouncementslist.getSelectedValuesList();
				
			    String dateString = time;
			    DateFormat dateFormat = new SimpleDateFormat(format);
			    Date date = null;
				try {
					date = dateFormat.parse(dateString );
				} catch (ParseException e) {
					e.printStackTrace();
				}
			    final long unixTime = (long) date.getTime()/1000;
			    
			  
			    JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setDialogTitle("Specify a file to save");   
			     
			    int userSelection = fileChooser.showSaveDialog(CreatePlayListPanel.this);
			     
			    if (userSelection == JFileChooser.APPROVE_OPTION) {
			        final File fileToSave = fileChooser.getSelectedFile();
			        
					SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() {
							System.out.println("Creating");
							   CreatePlayList createPlayList = new CreatePlayList();
							    createPlayList.createPlayList(selectedtrackslist, selectedadlist, selectedpsalist, selectedalist, tname.getText().toString().trim(), unixTime,fileToSave.getAbsolutePath());
							
						        System.out.println("Save as file: " + fileToSave.getAbsolutePath());
						

							return null;
						}
					};
					mySwingWorker.execute();

					mySwingWorker
							.addPropertyChangeListener(new PropertyChangeListener() {

								@Override
								public void propertyChange(PropertyChangeEvent evt) {
									if (evt.getPropertyName().equals("state")) {
										if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
											
											
											JOptionPane.showMessageDialog(CreatePlayListPanel.this,
													"Play list created");
											dialog.dispose();

										}
									}
								}
							});

					dialog.setVisible(true);
			         }
			}
		});

		listPane.add(submit);

		add(listPane, BorderLayout.CENTER);

	}

	
	public static boolean isValidFormat( String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date != null;
	}
	
	
}
