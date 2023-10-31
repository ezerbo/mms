package com.mms.ui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mms.domain.User;
import com.mms.service.UserService;

public class InfoUtilisateur extends JDialog {

	String login;
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelEst;
	private JPanel panelOuest;
	private JPanel panelLabelLibelle;
	private JPanel panelLabelValeur;
	private UserService userService;
	private User user;
	private JButton b_OK;
	public InfoUtilisateur(String login) {
		setTitle("Plus d'information");
		this.login = login;
		getContentPane().setLayout(new BorderLayout());
		panelCentre = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		panelOuest = new JPanel();
		panelEst = new JPanel();
		panelLabelLibelle = new JPanel();
		panelLabelValeur = new JPanel();
		b_OK = new JButton("   OK   ");
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setHgap(50);

		panelCentre.setLayout(flowlayout);
		panelCentre.setBackground(Color.WHITE);
		panelCentre.setBorder(new TitledBorder(""));
		panelLabelLibelle.setBackground(Color.WHITE);
		panelLabelValeur.setBackground(Color.WHITE);
		userService = new UserService();
		panelLabelLibelle.setLayout(new GridLayout(6, 1, 5, 5));
		panelLabelLibelle.add(new JLabel("Nom : "));
		panelLabelLibelle.add(new JLabel("Prenom : "));
		panelLabelLibelle.add(new JLabel("Numero de telephone : "));
		panelLabelLibelle.add(new JLabel("Login : "));
		panelLabelLibelle.add(new JLabel("Date d'enregistrement : "));
		panelLabelLibelle.add(new JLabel("Statut : "));
		panelCentre.add(panelLabelLibelle);
		panelLabelValeur.setLayout(new GridLayout(6, 1, 5, 5));
		user = userService.findByLogin(login);
		if (user != null) {
			panelLabelValeur.add(new JLabel(user.getNomutilisateur()));
			panelLabelValeur
					.add(new JLabel(user.getPrenomutilisateur()));
			panelLabelValeur.add(new JLabel(user
					.getTelephoneutilisateur()));
			panelLabelValeur.add(new JLabel(user.getLoginutilisateur()));
			panelLabelValeur.add(new JLabel(user
					.getDateembaucheutilisateur() + ""));
			if (user.getDatedepartutilisateur() == null) {
				panelLabelValeur.add(new JLabel("Actif"));
			} else {
				panelLabelValeur.add(new JLabel("Non actif"));
			}
		}
		panelCentre.add(panelLabelValeur);
		add(panelCentre, BorderLayout.CENTER);
		add(panelNord, BorderLayout.NORTH);
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSud.add(b_OK);
		add(panelSud, BorderLayout.SOUTH);
		add(panelEst, BorderLayout.EAST);
		add(panelOuest, BorderLayout.WEST);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		b_OK.addActionListener(e -> dispose());
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	public static void main(String[] args) {
		new InfoUtilisateur("kataston");
	}
}
