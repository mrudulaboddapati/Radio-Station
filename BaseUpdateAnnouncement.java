package com.playlist.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.playlist.jdbc.AnnouncementdataDaoImpl;
import com.playlist.pojo.Announcments;

public class BaseUpdateAnnouncement extends BasePanel{

	public JLabel label;
	public String type;
    public String header="";
	
	public BaseUpdateAnnouncement(final String type) {
	
		this.type = type;
		setLayout(null);

		int left = 130;
		int right = 30;
		int top = 220;
		int bottom = 25;
		int index = 30;

		JPanel listPane = new JPanel();
		label = new JLabel("New Track");
		label.setBounds(left, right = right, top+50, bottom);

		right = 40;

		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lannouncementid = new JLabel(header+" ID:");
		lannouncementid.setBounds(left, right = right + 30, top, bottom);
		
		JButton submit = new JButton("Fetch Details");
		submit.setBounds(left + 300, right = right, top - 30, bottom);
		right = 110;

		JLabel ltotaltime = new JLabel("Total Time");
		ltotaltime.setBounds(left, right = right + 30, top, bottom);

		JLabel lname = new JLabel("Name:");
		lname.setBounds(left, right = right + 30, top, bottom);

		JLabel ltype = new JLabel("Type:");
		ltype.setBounds(left, right = right + 30, top, bottom);

		left = 250;
		right = 40;
		top = 300;

		final JTextField tannouncementid = new JTextField();
		tannouncementid.setBounds(left, right = right + 30, top-100, bottom);

		
		right = 110;

		final JTextField ttotaltime = new JTextField();
		ttotaltime.setBounds(left, right = right + 30, top, bottom);

		final JTextField tname = new JTextField();
		tname.setBounds(left, right = right + 30, top, bottom);

		final JTextField ttype = new JTextField();
		ttype.setBounds(left, right = right + 30, top, bottom);

		JButton save = new JButton("Save");

		save.setBounds(left, right = right + 30, top, bottom);

		add(label);
		add(lannouncementid);
		add(tannouncementid);
		add(submit);

		add(ltotaltime);
		add(ttotaltime);


		add(lname);
		add(tname);

		//add(ltype);
		//add(ttype);

		add(save);
		
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
				
				tannouncementid.setText(""+announcments.getAnnoucment_Id());
				tname.setText(announcments.getName());
				ttotaltime.setText(""+announcments.getTotaltime());
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				AnnouncementdataDaoImpl announcementdataDaoImpl = new AnnouncementdataDaoImpl();

				Announcments announcments = new Announcments();

				if (tannouncementid.getText().toString().trim().equals("")) {
					showError("Announcement is required");
					return;
				}

				if (!isStringInt(tannouncementid.getText().toString().trim())) {

					showError("Announcement id must be integer");
					return;
				}

		

				if (ttotaltime.getText().toString().trim().equals("")) {
					showError("Total time is required");
					return;
				}

				if (!isStringInt(ttotaltime.getText().toString().trim())) {

					showError("Total time must be integer");
					return;
				}

				if (tname.getText().toString().trim().equals("")) {
					showError("Name is required");
					return;
				}


				announcments.setAnnoucment_Id(Integer.parseInt(tannouncementid.getText().toString()));
				announcments.setTotaltime(Integer.parseInt(ttotaltime.getText()
						.toString().trim()));
				announcments.setName(tname.getText().toString().trim());
				announcments.setType(type);
				announcementdataDaoImpl.update(announcments);
				JOptionPane.showMessageDialog(BaseUpdateAnnouncement.this,
						"Data Updated");

				tannouncementid.setText("");
				tname.setText("");
				ttotaltime.setText("");
			
			}
		});

	}
}

