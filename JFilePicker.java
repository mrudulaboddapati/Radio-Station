package com.playlist.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFilePicker extends JPanel {
	
	private String textFieldLabel;
	private String buttonLabel="Choose File";

	private JLabel label;
	public JTextField textField;
	public JButton button;
	public JButton submit;

	private JFileChooser fileChooser;

	public static final int MODE_OPEN = 1;

	public JFilePicker(String textFieldLabel ) {
		this.textFieldLabel = textFieldLabel;

		fileChooser = new JFileChooser();

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// creates the GUI
		label = new JLabel(textFieldLabel);

		textField = new JTextField(30);
		button = new JButton(buttonLabel);

		submit = new JButton("Submit");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});

		add(label);
		add(textField);
		add(button);
		add(submit);

	}

	private void buttonActionPerformed(ActionEvent evt) {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}

	}

	public String getSelectedFilePath() {
		return textField.getText();
	}

	public JFileChooser getFileChooser() {
		return this.fileChooser;
	}
}
