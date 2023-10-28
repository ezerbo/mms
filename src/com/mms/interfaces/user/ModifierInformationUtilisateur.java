package com.mms.interfaces.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.mms.event.UtilisateurEvent;
import com.mms.listener.UtilisateurListener;
import com.mms.domain.Utilisateur;
import com.mms.service.UserService;

public class ModifierInformationUtilisateur extends JDialog {
	private JTextField f_nom;
	private JTextField f_prenom;
	private JTextField f_login;
	private JFormattedTextField f_telephone;
	private JPanel panelField;
	private JPanel panelLabel;
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelOuest;
	private JPanel panelEst;
	private JButton boutonFermer;
	private JButton boutonModifier; 
	String loginUtilisateur;
	private UserService userService;
	private Utilisateur utilisateur = UserService.getLoggedInUser();
	private MaskFormatter maskFormatter = null;
	private ArrayList<UtilisateurListener> utilisateurListeners = new ArrayList<UtilisateurListener>();

	public synchronized void addUtilisateurListener(UtilisateurListener listener) {
		if (utilisateurListeners.contains(listener)) {
			return;
		}
		utilisateurListeners.add(listener);
	}

	public synchronized void removeUtilisateurListener(
			UtilisateurListener listener) {
		utilisateurListeners.remove(listener);
	}

	public void fireModificationUtilisateur() {
		if (utilisateurListeners.size() == 0) {
			return;
		}
		UtilisateurEvent event = new UtilisateurEvent(this);
		for (UtilisateurListener listener : utilisateurListeners) {
			listener.ajouterUilisateur(event);
		}

	}
	public ModifierInformationUtilisateur() {
		userService = new UserService();
		getContentPane().setLayout(new BorderLayout());
		setTitle("Modifications des informations");
		f_nom = new JTextField(20);
		f_prenom = new JTextField();
		f_login = new JTextField();
		panelField = new JPanel();
		panelLabel = new JPanel();
		panelCentre = new JPanel();
		panelSud = new JPanel();
		panelNord = new JPanel();
		panelOuest = new JPanel();
		panelEst = new JPanel();
		boutonFermer = new JButton("Fermer");
		boutonModifier = new JButton("Modifier");
		panelCentre.setBorder(new TitledBorder(""));
		panelField.setLayout(new GridLayout(4, 1, 5, 5));
		panelField.add(f_nom);
		panelField.add(f_prenom);
		panelField.add(f_login);
		try {
			maskFormatter = new MaskFormatter("(226) - ## - ## - ## - ##");
		} catch (Exception e) {
		}
		f_telephone = new JFormattedTextField(maskFormatter);
		panelField.add(f_telephone);
		panelLabel.setLayout(new GridLayout(4, 1, 5, 10));
		panelLabel.add(new JLabel("Nom :"));
		panelLabel.add(new JLabel("Prenom :"));
		panelLabel.add(new JLabel("Login :"));
		panelLabel.add(new JLabel("Telephone :"));
		panelCentre.add(panelLabel);
		panelCentre.add(panelField);
		add(panelCentre, BorderLayout.CENTER);
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSud.add(boutonFermer);
		panelSud.add(boutonModifier);
		panelCentre.setBackground(Color.WHITE);
		panelLabel.setBackground(Color.WHITE);
		panelField.setBackground(Color.WHITE);
		add(panelSud, BorderLayout.SOUTH);
		add(panelNord, BorderLayout.NORTH);
		add(panelOuest, BorderLayout.WEST);
		add(panelEst, BorderLayout.EAST);
		abonne();
		chargerInformation();
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
	}
	private void abonne() {
		boutonFermer.addActionListener(arg0 -> dispose());
		boutonModifier.addActionListener(arg0 -> {
			if (f_nom.getText().equals("") || f_prenom.getText().equals("")|| f_login.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Tous les champs sont obligatoires !!!", "Erreur",JOptionPane.ERROR_MESSAGE);
			} else {
					if (userService.ifLoginExiste(f_login.getText())) {
						/*** Teste l'existence du login */
						JOptionPane.showMessageDialog(null,"Ce login existe deja !!!", "Erreur",JOptionPane.ERROR_MESSAGE);
					} else {
						if (userService.verifierNumeroTelephone(f_telephone.getText().substring(8,f_telephone.getText().length()))) {
								/*** enregistrement de l'utilisateur */
								userService.verifierNumeroTelephone(f_telephone.getText().substring(8,f_telephone.getText().length()));
								if (userService.modifierInformationUtilisateur(utilisateur.getIdutilisateur(),f_nom.getText(),f_prenom.getText(),f_telephone.getText().substring(8,f_telephone.getText().length()), f_login.getText()) != 0) {
									/**
									 * Notification des ecouteurs de
									 * l'evenement
									 */
									fireModificationUtilisateur();
									JOptionPane.showMessageDialog(null,"Utilisateur enregistr� avec sucess !!!","succes de l'enregistrement",JOptionPane.INFORMATION_MESSAGE);

									dispose();
								} else {
									JOptionPane.showMessageDialog(null,"L'utilisateur n'a pas pu etre enregistr� !!!","Erreur",JOptionPane.ERROR_MESSAGE);
								}

						} else {
							JOptionPane.showMessageDialog(null,"Le numero de telephone n'est pas valide","Erreur",JOptionPane.ERROR_MESSAGE);
						}
						//
					}

			}

		});
	}
	public void chargerInformation() {
		f_nom.setText(utilisateur.getNomutilisateur());
		f_prenom.setText(utilisateur.getPrenomutilisateur());
		f_telephone.setText(utilisateur.getTelephoneutilisateur());
		f_login.setText(utilisateur.getLoginutilisateur());
	}
	public static void main(String[] agrs) {
		new ModifierInformationUtilisateur().setVisible(true);
	}
}
