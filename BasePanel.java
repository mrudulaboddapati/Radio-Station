package com.playlist.view;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class BasePanel extends JPanel {

	public JButton backbutton;
	public JDialog dialog;

	public BasePanel() {

		backbutton = new JButton("Cancel");
		
		backbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainClass.display("dashboard");
			}
		});

		Window win = SwingUtilities
				.getWindowAncestor(BasePanel.this);
		 dialog = new JDialog(win, "Loading",
				ModalityType.APPLICATION_MODAL);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(progressBar, BorderLayout.CENTER);
		panel.add(new JLabel("Please wait......."),
				BorderLayout.PAGE_START);
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(win);
	
	}
	
	public static boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	
	public void showError(String message) {
		JOptionPane.showMessageDialog(BasePanel.this, message);

	}


}
