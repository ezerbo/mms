package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mms.pojos.Parametres;
import com.mms.service.UtilisateurService;

// Referenced classes of package interfaces:
//            VenteJFrame

@SuppressWarnings("serial")
public class EcranDeConnexion extends JFrame {

	private JPanel panelCentre;
	private JPanel panelLabel;
	private JPanel panelField;
	private JPanel panelIcon;
	private JPanel panelComponent;
	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEst;
	private JPanel panelOuest;
	private JTextField f_login;
	private JPasswordField f_password;
	private int nombreTentative = 0;
	private Parametres parametres;
	private UtilisateurService utilisateurService;

	public EcranDeConnexion() {
		super("Ecran de connexion");
		getContentPane().setLayout(new BorderLayout(10, 10));
		panelCentre = new JPanel();
		panelCentre.setBorder(new TitledBorder(""));
		panelLabel = new JPanel();
		panelField = new JPanel();
		panelIcon = new JPanel();
		panelSud = new JPanel();
		panelOuest = new JPanel();
		panelEst = new JPanel();
		utilisateurService = new UtilisateurService();
		parametres = utilisateurService.retourneParametres();
		panelSud.setLayout(new GridLayout(1,1));
		panelComponent = new JPanel();
		panelComponent.setBorder(new TitledBorder(""));
		panelNord = new JPanel();
		f_password = new JPasswordField(10);
		f_login = new JTextField(10);
		panelField.setLayout(new GridLayout(2, 1, 5, 5));
		panelField.add(f_login);
		panelField.add(f_password);
		add(panelNord, BorderLayout.NORTH);
		panelLabel.setLayout(new GridLayout(2, 1));
		panelLabel.add(new JLabel("Login :  "));
		panelLabel.add(new JLabel("Mot de passe :"));
		panelComponent.add(panelLabel);
		panelComponent.add(panelField);
		panelIcon.add(new JLabel(new ImageIcon("ressources/images/user.jpg")));
		panelCentre.add(panelIcon);
		panelCentre.add(panelComponent);
		panelField.setBackground(Color.WHITE);
		panelLabel.setBackground(Color.WHITE);
		panelIcon.setBackground(Color.WHITE);
		panelComponent.setBackground(Color.WHITE);
		panelCentre.setBackground(Color.WHITE);
		panelSud.add(new CopyRight());
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		add(panelEst,BorderLayout.EAST);
		add(panelOuest,BorderLayout.WEST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		abonne();
		setSize(new Dimension(500,210));
		//pack();
		setLocationRelativeTo(null);
		f_password.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getKeyCode()==KeyEvent.VK_ENTER){
					connecterUtilisateur();
				}
			}
			
			
		});
	}

	public static void main(String args[]) {
		(new EcranDeConnexion()).setVisible(true);
	}
@SuppressWarnings("deprecation")
public void connecterUtilisateur(){
	// TODO Auto-generated method stub
	if(!f_login.getText().equals("")&&!f_password.getText().equals("")){
		int returValue = utilisateurService.connecterUtilisateur(f_login.getText(), f_password.getText());
		if(returValue==0){
			//JOptionPane.showMessageDialog(null, "connexion reussie !!!");
			setCursor(Cursor.WAIT_CURSOR);
			utilisateurService.mettreInformationEnSession(utilisateurService.retourneUtilisateurParLogin(f_login.getText()).getIdutilisateur());
			InterfacePrincipale interfacePrincipale = 	new InterfacePrincipale(f_login.getText());
			interfacePrincipale.setVisible(true);
			interfacePrincipale.setSize(new Dimension(1100, 720));
			interfacePrincipale.setLocationRelativeTo(null);
			dispose();
		}else{
				if(returValue==2){
				//desactivation des champs de connexion
					JOptionPane.showMessageDialog(null, "Votre mot de passe n'est plus valide,veuillez le changer svp !!!","Mot de passe non valide", JOptionPane.INFORMATION_MESSAGE);
				new MiseAJourMotDePasse(utilisateurService.retourneUtilisateurParLogin(f_login.getText()).getIdutilisateur());
				f_password.setText("");
				}else{
			
					//incrementation du nombre de tentatives echoué!!
			
					if(++nombreTentative==parametres.getTentativemdp()){
						f_login.setEnabled(false);
						f_password.setEnabled(false);
						//desactivation des champs de connexion
						try {
							Thread.sleep(parametres.getTempsinactivitesmdp()*60000);//desactivation des champs de saisie pendant la duree specifiée
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						f_login.setEnabled(true);
						f_password.setEnabled(true);
					}
			
					JOptionPane.showMessageDialog(null, "Echec de la tentative de connexion,\nlogin ou mot de passe incorrect","Echec de la connexion",JOptionPane.ERROR_MESSAGE);
		
				}
		
		}
	
	}else
	
		JOptionPane.showMessageDialog(null, "tous les champs sont obligatoires !!","Erreur",JOptionPane.ERROR_MESSAGE);

}
	public void abonne() {
		f_password.addKeyListener(new KeyAdapter() {
		});
	}

	public boolean controle() {
		return false;
	}

}
