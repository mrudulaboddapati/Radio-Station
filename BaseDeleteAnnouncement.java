package com.playlist.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.playlist.jdbc.AnnouncementdataDaoImpl;
import com.playlist.jdbc.TracksdataDaoImpl;
import com.playlist.pojo.Announcments;
import com.playlist.pojo.Tracks;

public class BaseDeleteAnnouncement extends BasePanel{

	public JLabel label;
	
	public String type;
	
    public String header="";
	public BaseDeleteAnnouncement(final String type){
	
		this.type = type;
		setLayout(null);

		int left = 130;
		int right = 10;
		int top = 170;
		int bottom = 25;
		int index = 30;

	    label = new JLabel("Delete Track");
		label.setFont(new Font("Serif", Font.PLAIN, 21));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setBounds(left, right = right, top+50, bottom);

		JLabel lannouncementid = new JLabel(header+" ID:");
		lannouncementid.setBounds(left - 30, right = right + 30, top, bottom);
		JButton submit = new JButton("Submit");
		submit.setBounds(left + 300, right = right, top - 40, bottom);

		left = 230;
		top = 300;
		final JTextField tannouncementid = new JTextField();
		tannouncementid.setBounds(left - 70, right = right, top - 30, bottom);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				AnnouncementdataDaoImpl announcementdataDaoImpl = new AnnouncementdataDaoImpl();
				if (tannouncementid.getText().toString().trim().equals("")) {
					showError("Id is required");
					return;
				}

				if (!isStringInt(tannouncementid.getText().toString().trim())) {

					showError(" Id must be integer");
					return;
				}

				Announcments announcments =  announcementdataDaoImpl.read(Integer
						.parseInt(tannouncementid.getText().toString().trim()));
			
				if ( announcments==null || !announcments.getType().equals(type)) {

					showError("This is not a valid  id");
					return;
				}
				
				announcementdataDaoImpl.delete(Integer.parseInt(tannouncementid.getText().toString()));

				JOptionPane
				.showMessageDialog(BaseDeleteAnnouncement.this, "Deleted");
			}

		});

		add(label);
		add(lannouncementid);
		add(tannouncementid);
		add(submit);
	}
	
}
