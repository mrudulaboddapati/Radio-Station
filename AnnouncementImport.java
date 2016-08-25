package com.playlist.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.playlist.jdbc.DBTabletoJTable;
import com.playlist.jdbc.FileParser;

public class AnnouncementImport extends BasePanel {

	public AnnouncementImport() {

		start();
	}

	public void refreshData(final DefaultTableModel model) {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {

				DBTabletoJTable dbTabletoJTable = new DBTabletoJTable();
				dbTabletoJTable.showdatainTable(model, "select * from announcments");
				return null;
			}
		}.execute();
	}

	public void start() {

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(MainClass.width - 100,
				MainClass.height - 100));

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		final JFilePicker filePicker = new JFilePicker("Pick announcment file");
		final DefaultTableModel model = new DefaultTableModel();

		filePicker.submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				// TODO Auto-generated method stub

				SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {
						FileParser fileParser = new FileParser();
						try {

							fileParser.announcmentFileParser(filePicker.textField
									.getText());
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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
										
										System.out.println("DONE");
										
										refreshData(model);
										JOptionPane.showMessageDialog(AnnouncementImport.this,
												"Announcement csv imported to database");
										dialog.dispose();

									}
								}
							}
						});

				dialog.setVisible(true);

			}
		});
		mainPanel.add(filePicker);

		JTable table = new JTable(model);
		table.setPreferredSize(new Dimension(MainClass.width - 100,
				MainClass.height - 100));

		JButton reload = new JButton("Reload");
		reload.setAlignmentX(Component.LEFT_ALIGNMENT);

		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				refreshData(model);
			}
		});
		mainPanel.add(reload);
		mainPanel.add(Box.createRigidArea(new Dimension(5, 10)));

		mainPanel.add(new JScrollPane(table));
		add(mainPanel);
		refreshData(model);
		mainPanel.add(Box.createRigidArea(new Dimension(5, 10)));
		mainPanel.add(backbutton);

	}

}
