package com.mms.interfaces;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package interfaces:
//            Configuration

@SuppressWarnings("serial")
public class Historique extends JPanel {

	protected JDateChooser date1;
	protected JDateChooser date2;
	protected JButton b_valider;
	protected Configuration frame;

	public Historique(final Configuration configuration) {
		frame = configuration;
		b_valider = new JButton("Valider");
		date1 = new JDateChooser();
		date2 = new JDateChooser();
		add(date1);
		add(new JLabel("Date 2 :"));
		add(date2);
		add(b_valider);
		b_valider.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				configuration.setSize(new Dimension(640, 580));
			}

			
		});
	}
}
