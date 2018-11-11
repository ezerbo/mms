package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.mms.service.UtilisateurService;

// Referenced classes of package interfaces:
//            ConnectionScreen

@SuppressWarnings("serial")
public class AdminCreation extends JDialog {

	private JButton b_annuler;
	private JButton b_creer;
	private TextFieldUtilisateur f_nom;
	private TextFieldUtilisateur f_prenom;
	private TextFieldUtilisateur f_login;
	private JPasswordField f_motDePasse;
	private JPasswordField f_confirmeMotDePasse;
	private JPanel panelCentre;
	private JPanel panelSud;
	private JPanel panelField;
	private JPanel panelLabel;
	private JPanel panelNord;
	private JPanel panelEst;
	private JPanel panelOuest;
	private JPanel panelComposant;
	private JPanel panelImage;
	private JLabel l_image;
	private JFormattedTextField f_telephone;
	private MaskFormatter maskFormatter;
	private UtilisateurService utilisateurService;

	public AdminCreation() {
		getContentPane();
		setLayout(new BorderLayout());
		b_annuler = new JButton("Annuler");
		b_creer = new JButton("  Creer  ");
		f_login = new TextFieldUtilisateur();
		f_nom = new TextFieldUtilisateur();
		f_prenom = new TextFieldUtilisateur();
		f_motDePasse = new JPasswordField(20);
		f_confirmeMotDePasse = new JPasswordField(20);
		panelCentre = new JPanel();
		panelSud = new JPanel();
		panelSud.setLayout(new FlowLayout(0));
		panelField = new JPanel();
		panelLabel = new JPanel();
		panelLabel = new JPanel();
		panelNord = new JPanel();
		panelEst = new JPanel();
		panelOuest = new JPanel();
		panelComposant = new JPanel();
		panelImage = new JPanel();
		l_image = new JLabel();
		utilisateurService = new UtilisateurService();
		panelCentre.setBorder(new TitledBorder(""));
		panelField.setBorder(new TitledBorder(""));
		panelCentre.setBackground(Color.WHITE);
		panelImage.setBackground(Color.WHITE);
		panelLabel.setBackground(Color.WHITE);
		panelField.setBackground(Color.WHITE);
		panelComposant.setBackground(Color.WHITE);
		panelSud.add(b_creer);
		panelSud.add(b_annuler);
		panelLabel.setLayout(new GridLayout(6, 1, 10, 10));
		panelLabel.add(new JLabel("Nom :"));
		panelLabel.add(new JLabel("Prenom :"));
		panelLabel.add(new JLabel("Telephone :"));
		panelLabel.add(new JLabel("Login :"));
		panelLabel.add(new JLabel("Mot de passe :"));
		panelLabel.add(new JLabel("Retappez le mot de passe :"));
		panelComposant.add(panelLabel);
		panelField.setLayout(new GridLayout(6, 1, 5, 5));
		panelField.add(f_nom);
		panelField.add(f_prenom);
		try {
			maskFormatter = new MaskFormatter("(226) - ## - ## - ## - ##");
		} catch (Exception e) {
		}
		f_telephone = new JFormattedTextField(maskFormatter);
		panelField.add(f_telephone);
		panelField.add(f_login);
		panelField.add(f_motDePasse);
		panelField.add(f_confirmeMotDePasse);
		panelComposant.add(panelField);
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		add(panelEst, BorderLayout.EAST);
		add(panelOuest, BorderLayout.WEST);
		add(panelNord, BorderLayout.NORTH);
		l_image.setIcon(new ImageIcon("ressources/images/admin.jpg"));
		panelImage.add(l_image);
		panelCentre.add(panelImage);
		panelCentre.add(panelComposant);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		setTitle("Creation du compte  administrateur");
		setLocationRelativeTo(null);
		b_annuler.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});
		f_confirmeMotDePasse.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event){
				if(event.getKeyCode()==KeyEvent.VK_ENTER)
				creerAdministrateur();
			}
		});
		b_creer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				creerAdministrateur();
			}

		});
		setModal(true);
	}
	@SuppressWarnings("deprecation")
	public void creerAdministrateur(){
		if (!f_nom.getText().equals("")
				&& !f_prenom.getText().equals("")
				&& !f_login.getText().equals("")
				&& !f_motDePasse.getText().equals("")
				&& !f_confirmeMotDePasse.getText().equals("")) {
			/** si toutes les valeurs sont renseignées **/
			if (verifierNumeroTelephone(f_telephone.getText().substring(8,f_telephone.getText().length()))) {
				if(utilisateurService.egaliteMotDePasse(f_motDePasse.getText(), f_confirmeMotDePasse.getText())){/**Verification de l'egalité des deux mots de passe*/
					if(f_motDePasse.getText().length()>=utilisateurService.retourneParametres().getLongueurmdp()){/**verification de la longueur du mot de passe*/
						
					if(utilisateurService.creerUtiliateur(f_nom.getText(), f_prenom.getText(), f_telephone.getText().substring(8, f_telephone.getText().length()), f_login.getText(), f_motDePasse.getText(),"ADMINISTRATEUR")==0){
						/***Creation de l'utilisateur et verification du succes de l'operation*/
						JOptionPane.showMessageDialog(null,
							"Administrateur crée avec succes !!!", "Succes de l'operation",
							JOptionPane.INFORMATION_MESSAGE);
						new EcranDeConnexion().setVisible(true);
						dispose();
						
				}else{
					JOptionPane.showMessageDialog(null,
							"Echec de l'operation de creation d'administrateur", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
				}else{
					JOptionPane.showMessageDialog(null,
							"Mot de passe trop court,entrez au moins"+utilisateurService.retourneParametres().getLongueurmdp()+" caracteres", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}	
				else{
				JOptionPane.showMessageDialog(null,
						"Les mots de passe ne sont pas identiques !!!", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Tous les champs sont obligatoires", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	
		
	}
	public boolean verifierNumeroTelephone(String telephone) {
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
				if (!telephone.substring(0, 1).equals("0")
						&& !telephone.substring(1, 2).equals("0"))
					testeValue = true;
			}

		}

		return testeValue;
	}
	public static void createFolder() {
		File file = new File("c://facture");
		if (!file.mkdir()) {
			JOptionPane.showMessageDialog(null,
					"Le dossier des factures n'a pas pu \352tre cr\351e",
					"Message", 1);
		}
	}

	public static void main(String args1[]) {
		new AdminCreation().setVisible(true);
	}
}
