package com.mms.ui.user;

import java.awt.*;
import javax.swing.*;

public class ActeurExterne extends JDialog {

	protected JButton deleteClientButton;
	protected JButton updateClientButton;
	protected JPanel panelSouth;
	protected JPanel panelNord;
	protected static int COLONNETELEPHONE = 2;

	public ActeurExterne(String title1, String title2) {
		getContentPane().setLayout(new BorderLayout());
		panelNord = new JPanel();
		panelSouth = new JPanel();
		JButton b_fermer = new JButton("Fermer");
		deleteClientButton = new JButton("Supprimer");
		updateClientButton = new JButton("Modifier");
		panelNord.setLayout(new GridLayout(1, 2));
		JPanel panelIcon = new JPanel();
		JPanel panelTitle = new JPanel();
		panelIcon.add(new JLabel(new ImageIcon("ressources/images/acteurExterne.jpg")));
		panelTitle.setLayout(new BorderLayout());
		JLabel label = new JLabel(title1);
		JLabel label2 = new JLabel(title2);
		label.setFont(new Font("SansSerif", Font.ITALIC, 18));
		label2.setFont(new Font("SansSerif", Font.ITALIC, 12));
		panelTitle.add(label, "North");
		panelTitle.add(label2);
		panelTitle.setBackground(Color.WHITE);
		panelIcon.setBackground(Color.WHITE);
		panelIcon.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelNord.setBackground(Color.WHITE);
		panelNord.add(panelTitle);
		panelNord.add(panelIcon);
		add(panelNord, BorderLayout.NORTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSouth.add(b_fermer);
		panelSouth.add(deleteClientButton);
		panelSouth.add(updateClientButton);
		add(panelSouth, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		b_fermer.addActionListener(e -> dispose());
		setSize(new Dimension(580, 440));
	}
}
