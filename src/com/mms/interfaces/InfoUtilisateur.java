package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mms.pojos.Utilisateur;
import com.mms.service.UserService;

@SuppressWarnings("serial")
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
	private Utilisateur utilisateur;
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
		utilisateur = userService.retourneUtilisateurParLogin(login);
		if (utilisateur != null) {
			panelLabelValeur.add(new JLabel(utilisateur.getNomutilisateur()));
			panelLabelValeur
					.add(new JLabel(utilisateur.getPrenomutilisateur()));
			panelLabelValeur.add(new JLabel(utilisateur
					.getTelephoneutilisateur()));
			panelLabelValeur.add(new JLabel(utilisateur.getLoginutilisateur()));
			panelLabelValeur.add(new JLabel(utilisateur
					.getDateembaucheutilisateur() + ""));
			if (utilisateur.getDatedepartutilisateur() == null) {
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
		
		b_OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	public static void main(String args[]) {
		new InfoUtilisateur("kataston");
	}
}
