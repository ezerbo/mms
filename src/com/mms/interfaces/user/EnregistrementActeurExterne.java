package com.mms.interfaces.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

public class EnregistrementActeurExterne extends JDialog {

	protected JPanel panelCentre;

	protected JPanel panelBouton;
	protected JPanel panelEst;
	protected JPanel panelOuest;
	protected JPanel panelNord;
	protected JPanel panelField;
	protected JPanel panelLabel;
	protected JButton b_annuler;
	protected JButton b_enregistrer;
	protected JFormattedTextField f_telephone;
	protected JTextField f_nom;
	protected JTextField f_prenom;

	private MaskFormatter maskFormatter;

	public EnregistrementActeurExterne() {
		panelCentre = new JPanel();
		panelBouton = new JPanel();
		panelField = new JPanel();
		panelLabel = new JPanel();
		panelNord = new JPanel();
		panelOuest = new JPanel();
		panelEst = new JPanel();

		b_annuler = new JButton("Annuler");
		b_enregistrer = new JButton("Enregistrer");
		f_nom = new JTextField(20);
		f_prenom = new JTextField(20);
		getContentPane().setLayout(new BorderLayout());
		panelBouton.setLayout(new FlowLayout(0));
		panelBouton.add(b_enregistrer);
		panelBouton.add(b_annuler);
		add(panelBouton, "South");
		panelLabel.setLayout(new GridLayout(3, 1, 8, 8));
		panelLabel.add(new JLabel("Nom :"));
		panelLabel.add(new JLabel("Prenom :"));
		panelLabel.add(new JLabel("Telephone :"));
		panelCentre.add(panelLabel);
		panelField.setLayout(new GridLayout(3, 1, 5, 5));
		panelField.add(f_nom);
		panelField.add(f_prenom);
		try {
			maskFormatter = new MaskFormatter("(226) - ## - ## - ## - ##");
		} catch (Exception e) {
		}
		f_telephone = new JFormattedTextField(maskFormatter);
		panelField.add(f_telephone);
		panelCentre.add(panelField);
		panelCentre.setBackground(Color.WHITE);
		panelCentre.setBorder(new TitledBorder(""));
		panelLabel.setBackground(Color.WHITE);
		panelField.setBackground(Color.WHITE);
		add(panelCentre, BorderLayout.CENTER);
		add(panelEst, BorderLayout.EAST);
		add(panelOuest, BorderLayout.WEST);
		add(panelNord, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(null);
		b_annuler.addActionListener(arg0 -> dispose());
	}
	protected boolean verifierSaisie() {
		return (!f_nom.getText().equals("") && !f_prenom.getText().equals("") && !f_telephone
				.getText().equals(""));
	}
	protected boolean verifierNumeroTelephone(String telephone) {
		boolean testeValue = false;
		if (!telephone.substring(0, 1).equals("")
				&& !telephone.substring(1, 2).equals("")
				&& !telephone.substring(5, 6).equals("")
				&& !telephone.substring(6, 7).equals("")
				&& !telephone.substring(10, 11).equals("")
				&& !telephone.substring(11, 12).equals("")
				&& !telephone.substring(15, 16).equals("")
				&& !telephone.substring(16, 17).equals("")) {
			if (!telephone.equals("00-00-00-00")) {
				if (!telephone.substring(0, 1).equals("0"))
					testeValue = true;
			}

		}

		return testeValue;
	}
	public void reinitialiserChamps() {
		f_nom.setText("");
		f_prenom.setText("");
		f_telephone.setText("");
	}
}
