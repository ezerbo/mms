package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import com.mms.pojos.Parametres;
import com.mms.pojos.Utilisateur;
import com.mms.service.UserService;

@SuppressWarnings("serial")
public class MiseAJourMotDePasse extends JDialog {
	private JPanel panelCentre;
	private JPanel panelLabel;
	private JPanel panelField;
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelOuest;
	private JPanel panelEst;
	private JPasswordField f_motDePasse;
	private JPasswordField f_confirmation;
	private JButton b_enregistrer;
	private UserService userService;
	private int idUtilisateur;

	public MiseAJourMotDePasse(int idUtilisateur) {
		setTitle("Changement du mot de passe");
		this.idUtilisateur = idUtilisateur;
		panelCentre = new JPanel();
		panelLabel = new JPanel();
		panelField = new JPanel();
		panelSud = new JPanel();
		panelNord = new JPanel();
		panelOuest = new JPanel();
		panelEst = new JPanel();
		
		f_motDePasse = new JPasswordField(10);
		f_confirmation = new JPasswordField(15);
		userService = new UserService();
		getContentPane().setLayout(new BorderLayout());
		panelLabel.setLayout(new GridLayout(2, 1,5,5));
		panelLabel.add(new JLabel("Mot de passe : "));
		panelLabel.add(new JLabel("Retappez le mot de passe : "));
		panelLabel.setBackground(Color.WHITE);
		panelCentre.add(panelLabel);
		panelField.setLayout(new GridLayout(2,1,5,5));
		panelField.add(f_motDePasse);
		panelField.add(f_confirmation);
		panelField.setBackground(Color.WHITE);
		panelCentre.add(panelField);
		panelCentre.setBorder(new TitledBorder(""));
		panelCentre.setBackground(Color.WHITE);
		add(panelCentre,BorderLayout.CENTER);
		b_enregistrer = new JButton("Enregistrer");
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSud.add(b_enregistrer);
		add(panelSud,BorderLayout.SOUTH);
		add(panelNord,BorderLayout.NORTH);
		add(panelOuest,BorderLayout.WEST);
		add(panelEst,BorderLayout.EAST);
		b_enregistrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changerMotDePasse();
			}
		});
		f_confirmation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getKeyCode()==KeyEvent.VK_ENTER)
				changerMotDePasse();
			}
			
		});
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void changerMotDePasse(){
		if(!f_motDePasse.getText().equals("")&&!f_confirmation.getText().equals("")){
			/**modification du mot de passe***/
			Parametres parametres = userService.retourneParametres();
			if(f_motDePasse.getText().length()>=parametres.getLongueurmdp()){/**si la longueur du mot de passe est acceptable**/
				if(userService.egaliteMotDePasse(f_motDePasse.getText(), f_confirmation.getText())){
					if(!userService.verifierExistenceMotDePasse(idUtilisateur, f_motDePasse.getText())){/**Verification de l'existence du mot de passe entr�**/
						Utilisateur utilisateur = new Utilisateur();
						utilisateur.setIdutilisateur(idUtilisateur);/***renseignement de l'identifiant de l'utillisateur*/
						if(userService.creerNouveauMotDePasse(f_motDePasse.getText(),utilisateur)!=null){/***creation d'un nouveau mot de passe*/
							JOptionPane.showMessageDialog(null, "Mot de passe chang� avec succes !!","Succes de l'operation", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
				
					}else{
						JOptionPane.showMessageDialog(null, "Ce mot de passe a deja �t� utilis�","Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Les mots de passe ne sont pas identiques","Erreur", JOptionPane.ERROR_MESSAGE);
				}
		
			}else{
				JOptionPane.showMessageDialog(null, "Mot de passe trop court","Information",JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Tous les champs sont obligatoires","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String args[]){
		new MiseAJourMotDePasse(32);
	}
}
