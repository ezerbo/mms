package com.mms.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class ActeurExterne extends JDialog {

	private JButton b_fermer;
	protected JButton b_supprimer;
	protected JButton b_modifier;
	protected JPanel panelSouth;
	protected JPanel panelNord;
	private JPanel panelTitle;
	private JPanel panelIcon;
	private String title1;
	private String title2;
	private JLabel label;
	private JLabel label2;
	protected static int COLONNETELEPHONE = 2;

	public ActeurExterne(String title1, String title2) {
		getContentPane().setLayout(new BorderLayout());
		panelNord = new JPanel();
		panelSouth = new JPanel();
		b_fermer = new JButton("Fermer");
		b_supprimer = new JButton("Supprimer");
		b_modifier = new JButton("Modifier");
		panelNord.setLayout(new GridLayout(1, 2));
		panelIcon = new JPanel();
		panelTitle = new JPanel();
		panelIcon.add(new JLabel(new ImageIcon(
				"ressources/images/acteurExterne.jpg")));
		panelTitle.setLayout(new BorderLayout());
		label = new JLabel(title1);
		label2 = new JLabel(title2);
		label.setFont(new Font("SansSerif", 2, 18));
		label2.setFont(new Font("SansSerif", 2, 12));
		panelTitle.add(label, "North");
		panelTitle.add(label2);
		panelTitle.setBackground(Color.WHITE);
		panelIcon.setBackground(Color.WHITE);
		panelIcon.setLayout(new FlowLayout(2));
		panelNord.setBackground(Color.WHITE);
		panelNord.add(panelTitle);
		panelNord.add(panelIcon);
		add(panelNord, BorderLayout.NORTH);
		panelSouth.setLayout(new FlowLayout(0));
		panelSouth.add(b_fermer);
		panelSouth.add(b_supprimer);
		panelSouth.add(b_modifier);
		add(panelSouth, BorderLayout.SOUTH);
		setL_title1(title1);
		setL_title2(title2);
		setLocationRelativeTo(null);
		b_fermer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		setSize(new Dimension(580, 440));
	}

	public String getL_title1() {
		return title1;
	}

	public void setL_title1(String l_title1) {
		title1 = l_title1;
	}

	public String getL_title2() {
		return title2;
	}

	public void setL_title2(String l_title2) {
		title2 = l_title2;
	}
}
