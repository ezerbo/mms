package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import com.mms.service.UtilisateurService;

// Referenced classes of package interfaces:
//            Configuration

@SuppressWarnings("serial")
public class Authentification extends JDialog {

	private JPanel panelCentre;
	private JPanel panelSud;
	protected JPanel panelNord;
	protected JPasswordField f_passe;
	String loginUtilisateur;
	public Authentification(String loginUtilisateur) {
		setTitle("Authentification");
		this.loginUtilisateur = loginUtilisateur;
		getContentPane().setLayout(new BorderLayout(2,2));
		panelSud = new JPanel();
		panelCentre = new JPanel();
		panelNord = new JPanel();
		f_passe = new JPasswordField(20);
		panelSud.setLayout(new GridLayout(1,1));
		panelSud.add(new CopyRight());
		panelCentre.setBackground(Color.WHITE);
		panelCentre.setBorder(new TitledBorder(""));
		panelCentre.add(new JLabel("Mot de passe Administrateur :"));
		panelCentre.add(f_passe);
		panelNord.setLayout(new FlowLayout(0));
		
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		add(panelNord, BorderLayout.NORTH);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(460,120));
		setResizable(false);
		setLocationRelativeTo(null);
		f_passe.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					ecranDeConfiguration();
			}
		});
		setModal(true);
		setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void ecranDeConfiguration() {
		if (!f_passe.getText().equals("")) {
			if (new UtilisateurService().authentificationAdministrateur(f_passe.getText())) {
				panelCentre.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				dispose();
				new Configuration(loginUtilisateur);
				
			} else {
				JOptionPane.showMessageDialog(null,"Mot de passe incorrect !!!","Echec de l'authentification",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null,"Tous les champs sont obligatoires !!!", "Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String[] args){
		new Authentification("tintin");
	}

}
