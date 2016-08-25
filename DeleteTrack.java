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

import com.playlist.jdbc.TracksdataDaoImpl;
import com.playlist.pojo.Tracks;

public class DeleteTrack extends BasePanel {

	public DeleteTrack() {
		setLayout(null);

		int left = 130;
		int right = 10;
		int top = 170;
		int bottom = 25;
		int index = 30;

		JLabel label = new JLabel("Delete Track");
		label.setFont(new Font("Serif", Font.PLAIN, 21));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setBounds(left, right = right, top, bottom);

		JLabel ltrackid = new JLabel("Track ID:");
		ltrackid.setBounds(left - 30, right = right + 30, top, bottom);
		JButton submit = new JButton("Submit");
		submit.setBounds(left + 300, right = right, top - 40, bottom);

		left = 230;
		top = 300;
		final JTextField ttrackid = new JTextField();
		ttrackid.setBounds(left - 70, right = right, top - 30, bottom);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (ttrackid.getText().toString().trim().equals("")) {
					showError("Track id is required");
					return;
				}

				if (!isStringInt(ttrackid.getText().toString().trim())) {

					showError("Track id must be integer");
					return;
				}

				TracksdataDaoImpl tracksdataDaoImpl = new TracksdataDaoImpl();
				Tracks tracks = tracksdataDaoImpl.readTrack(Integer
						.parseInt(ttrackid.getText().toString().trim()));
				if (tracks == null) {

					showError("This is not a valid track id");
					return;
				} else {

					ttrackid.setText("" + tracks.getTrackid());

					tracksdataDaoImpl.delete(Integer.parseInt(ttrackid
							.getText().toString().trim()));

				}

				JOptionPane
				.showMessageDialog(DeleteTrack.this, "Track deleted");
			}

		});

		add(label);
		add(ltrackid);
		add(ttrackid);
		add(submit);

	}
}
