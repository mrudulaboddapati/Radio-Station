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

public class UpdateTrack extends BasePanel {

	public UpdateTrack() {
		setLayout(null);

		int left = 130;
		int right = 10;
		int top = 170;
		int bottom = 25;
		int index = 30;

		JPanel listPane = new JPanel();
		listPane.setLayout(null);

		JLabel label = new JLabel("Update Track");
		label.setBounds(left, right = right, top, bottom);

		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel ltrackid = new JLabel("Track ID:");
		ltrackid.setBounds(left - 30, right = right + 30, top, bottom);
		JButton submit = new JButton("Fetch Details");
		submit.setBounds(left + 300, right = right, top - 40, bottom);

		right = 70;

		JLabel ltotaltime = new JLabel("Total Time");
		ltotaltime.setBounds(left, right = right + 30, top, bottom);

		JLabel ldiscnumber = new JLabel("Disc Number:");
		ldiscnumber.setBounds(left, right = right + 30, top, bottom);

		JLabel ldisccount = new JLabel("Disc Count:");
		ldisccount.setBounds(left, right = right + 30, top, bottom);

		JLabel ltracknumber = new JLabel("Track Number:");
		ltracknumber.setBounds(left, right = right + 30, top, bottom);

		JLabel ltrackcount = new JLabel("Track Count:");
		ltrackcount.setBounds(left, right = right + 30, top, bottom);

		JLabel lyear = new JLabel("Year:");
		lyear.setBounds(left, right = right + 30, top, bottom);

		JLabel lname = new JLabel("Name:");
		lname.setBounds(left, right = right + 30, top, bottom);

		JLabel lartist = new JLabel("Artist:");
		lartist.setBounds(left, right = right + 30, top, bottom);

		JLabel lalbumartist = new JLabel("Album Artist:");
		lalbumartist.setBounds(left, right = right + 30, top, bottom);

		JLabel lalbum = new JLabel("Album:");
		lalbum.setBounds(left, right = right + 30, top, bottom);

		JLabel lgenre = new JLabel("Genre:");
		lgenre.setBounds(left, right = right + 30, top, bottom);

		left = 230;
		right = 10;
		top = 300;

		final JTextField ttrackid = new JTextField();
		ttrackid.setBounds(left - 70, right = right + 30, top - 30, bottom);

		right = 70;

		final JTextField ttotaltime = new JTextField();
		ttotaltime.setBounds(left, right = right + 30, top, bottom);

		final JTextField tdiscnumber = new JTextField();
		tdiscnumber.setBounds(left, right = right + 30, top, bottom);

		final JTextField tdisccount = new JTextField();
		tdisccount.setBounds(left, right = right + 30, top, bottom);

		final JTextField ttracknumber = new JTextField();
		ttracknumber.setBounds(left, right = right + 30, top, bottom);

		final JTextField ttrackcount = new JTextField();
		ttrackcount.setBounds(left, right = right + 30, top, bottom);

		final JTextField tyear = new JTextField();
		tyear.setBounds(left, right = right + 30, top, bottom);

		final JTextField tname = new JTextField();
		tname.setBounds(left, right = right + 30, top, bottom);

		final JTextField tartist = new JTextField();
		tartist.setBounds(left, right = right + 30, top, bottom);

		final JTextField talbumartist = new JTextField();
		talbumartist.setBounds(left, right = right + 30, top, bottom);

		final JTextField talbum = new JTextField();
		talbum.setBounds(left, right = right + 30, top, bottom);

		final JTextField tgenre = new JTextField();
		tgenre.setBounds(left, right = right + 30, top, bottom);

		JButton save = new JButton("Save");

		save.setBounds(left, right = right + 30, top, bottom);

		add(label);
		add(ltrackid);
		add(ttrackid);
		add(submit);
		add(ltotaltime);
		add(ttotaltime);

		add(ldiscnumber);
		add(tdiscnumber);

		add(ldisccount);
		add(tdisccount);

		add(ltracknumber);
		add(ttracknumber);

		add(ltrackcount);
		add(ttrackcount);

		add(lyear);
		add(tyear);

		add(lname);
		add(tname);

		add(lartist);
		add(tartist);

		add(lalbumartist);
		add(talbumartist);

		add(lalbum);
		add(talbum);

		add(lgenre);
		add(tgenre);

		add(save);

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

					ttrackid.setText(""+tracks.getTrackid());
					ttotaltime.setText(""+tracks.getTotaltime());
					tdiscnumber.setText(""+tracks.getDiscnumber());
					tdisccount.setText(""+tracks.getDisccount());
					ttrackcount.setText(""+tracks.getTrackcount());
					ttracknumber.setText(""+tracks.getTracknumber());
					ttotaltime.setText(""+tracks.getTotaltime());
					tyear.setText(""+tracks.getYear());
					tartist.setText(tracks.getArtist());
					talbum.setText(tracks.getAlbum());
					tgenre.setText(tracks.getGenre());
				
				

				}

			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				TracksdataDaoImpl tracksdataDaoImpl = new TracksdataDaoImpl();

				Tracks tracks = new Tracks();

				if (ttrackid.getText().toString().trim().equals("")) {
					showError("Track id is required");
					return;
				}

				if (!isStringInt(ttrackid.getText().toString().trim())) {

					showError("Track id must be integer");
					return;
				}

				if (tracksdataDaoImpl.trackalreadytaken(Integer
						.parseInt(ttrackid.getText().toString().trim())) == -1) {

					showError("You can not use this track id");
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

				if (tdiscnumber.getText().toString().trim().equals("")) {
					showError("Disc number is required");
					return;
				}

				if (!isStringInt(tdiscnumber.getText().toString().trim())) {

					showError("Disc number must be integer");
					return;
				}

				if (ttracknumber.getText().toString().trim().equals("")) {
					showError("Track number is required");
					return;
				}

				if (!isStringInt(ttracknumber.getText().toString().trim())) {

					showError("Track number must be integer");
					return;
				}

				if (ttrackcount.getText().toString().trim().equals("")) {
					showError("Track count is required");
					return;
				}

				if (!isStringInt(ttrackcount.getText().toString().trim())) {

					showError("Track count must be integer");
					return;
				}

				if (tyear.getText().toString().trim().equals("")) {
					showError("Year is required");
					return;
				}

				if (!isStringInt(tyear.getText().toString().trim())) {

					showError("Year must be integer");
					return;
				}

				if (tartist.getText().toString().trim().equals("")) {
					showError("Artist is required");
					return;
				}

				if (talbum.getText().toString().trim().equals("")) {
					showError("Album is required");
					return;
				}

				if (tgenre.getText().toString().trim().equals("")) {
					showError("Genre is required");
					return;
				}

				tracks.setTrackid(Integer.parseInt(ttrackid.getText()
						.toString().trim()));
				tracks.setAlbum(talbum.getText().toString().trim());
				tracks.setArtist(tartist.getText().toString().trim());
				tracks.setDisccount(Integer.parseInt(tdisccount.getText()
						.toString().trim()));
				tracks.setDiscnumber(Integer.parseInt(tdiscnumber.getText()
						.toString().trim()));
				tracks.setGenre(tgenre.getText().toString().trim());
				tracks.setTotaltime(Integer.parseInt(ttotaltime.getText()
						.toString().trim()));
				tracks.setTrackcount(Integer.parseInt(ttrackcount.getText()
						.toString().trim()));
				tracks.setTrackid(Integer.parseInt(ttrackid.getText()
						.toString().trim()));
				tracks.setTracknumber(Integer.parseInt(ttracknumber.getText()
						.toString().trim()));
				tracks.setYear(Integer.parseInt(tyear.getText().toString()
						.trim()));
				tracks.setAlbumartist(talbumartist.getText().toString());
				tracks.setName(tname.getText().toString().trim());

				tracksdataDaoImpl.updateTrack(tracks);
				JOptionPane
						.showMessageDialog(UpdateTrack.this, "Track updated");

				ttrackid.setText("");
				talbum.setText("");
				tartist.setText("");
				tdisccount.setText("");
				tdiscnumber.setText("");
				ttotaltime.setText("");
				tgenre.setText("");
				ttrackcount.setText("");
				ttracknumber.setText("");
				tyear.setText("");

			}
		});
	}
}
