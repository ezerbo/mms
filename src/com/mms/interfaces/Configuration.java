package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.theme.ShapedGradientTheme;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.infonode.tabbedpanel.titledtab.TitledTabProperties;

import com.mms.event.UtilisateurEvent;
import com.mms.listener.UtilisateurListener;
import com.mms.pojos.Parametres;
import com.mms.pojos.Utilisateur;
import com.mms.service.UserService;
import com.mms.tablemodels.StatistiqueVenteTableModel;
import com.mms.tablemodels.TableModelBilan;
import com.mms.tablemodels.TableModelUtilisateur;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Configuration extends JDialog implements UtilisateurListener {

	private JPanel panelCentre;
	private JPanel panelUtilisateur;
	private JPanel panelProduitNord;
	private JPanel panelProduitSud;
	private JPanel panelNord;
	private JPanel panelUtilisateurEst;
	private JPanel panelUtilisateurNord;
	private JPanel panelOperationUtilisateur;
	private JPanel panelUtilisateurEstCentre;
	private JPanel panelBouton;
	private JPanel panelSud;
	private JPanel panelStatistiqueVente;
	private JPanel statistiqueVentePanelNord;
	private JPanel statistiqueVentePanelEst;
	private JPanel panelBilan;
	private JPanel panelBilanNord;
	private JPanel panelBilanEst;
	private JPanel titlePanel;
	private JPanel panelIcon;
	private JPanel panelSpinnerNbreTentative;
	private JPanel panelSpinnerTempsInactivite;
	private JPanel panelSpinner;
	private JLabel title;
	private JLabel userLabel;
	private JLabel l_statistiqueVente;
	private JLabel l_expAdmin;
	private JButton b_nouveauProduit;
	private JLabel productLabel;
	private JTable tableUtilisateur;
	private JTable tableStatistiqueVente;
	private JTable tableBilan;
	private TableModelBilan bilanTableModel;
	private TableModelUtilisateur tableModelUtilisateur;
	private StatistiqueVenteTableModel statistiqueVenteTableModel;
	private JButton b_nouvelUtilisateur;
	private JButton b_activiteUtilisateur;
	private JButton b_supprimerUtilisateur;
	private JButton b_supprimerProduit;
	private JButton b_validerBilan;
	private JButton b_enregistrerParametres;
	private JButton b_modifier;
	private JButton b_annulerModificationParametres;
	private JDateChooser dateChooserBilan;
	private Historique historiqueVente;
	private JPanel panelCentreBilan;
	private JPanel panelMontantVente;
	private JPanel panelMontantLivraison;
	private JLabel l_recette;
	private TitledTabProperties titledTabProperties;
	private TabbedPanel tabbedPanel;
	private TitledTab titledTabUtilisateur;
	private TitledTab titledTabProduit;
	private TitledTab titledTabHistoriqueVente;
	private TitledTab titledTabSatistiqueVente;
	private TitledTab titledTabBilan;

	private JSpinner spinnerNbreTentative;
	private JSlider sliderLongueurMDP;
	private JSlider sliderDureeVieMDP;
	private JSpinner spinnerTempsInactivite;

	private NouvelUtilisateur nouvelUtilisateur;
	private static int COLONNELOGIN = 2;
	private JPopupMenu popupMenuUtilisateur;
	private JMenuItem itemInfoUtilisateur;
	private JMenuItem itemSupprimerUtilisateur;
	private JMenuItem itemActiviteUtilisateur;
	private Utilisateur utilisateur;
	private UserService userService;
	String loginUtilisateur;

	public Configuration(String loginUtilisateur) {
		setTitle("Panneau d'administration");
		getContentPane().setLayout(new BorderLayout());
		this.loginUtilisateur = loginUtilisateur;
		l_recette = new JLabel("Recette totale :");
		panelMontantVente = new JPanel();
		panelMontantLivraison = new JPanel();
		panelCentreBilan = new JPanel();
		panelUtilisateur = new JPanel();
		panelUtilisateurEst = new JPanel();
		panelOperationUtilisateur = new JPanel();
		panelUtilisateurEstCentre = new JPanel();
		panelUtilisateurNord = new JPanel();
		panelNord = new JPanel();
		panelProduitNord = new JPanel();
		panelProduitSud = new JPanel();
		panelCentre = new JPanel();
		panelStatistiqueVente = new JPanel();
		statistiqueVentePanelNord = new JPanel();
		statistiqueVentePanelEst = new JPanel();
		panelIcon = new JPanel();
		titlePanel = new JPanel();
		panelBilan = new JPanel();
		panelBilanNord = new JPanel();
		panelBilanEst = new JPanel();
		panelSud = new JPanel();
		dateChooserBilan = new JDateChooser();
		tableModelUtilisateur = new TableModelUtilisateur();
		statistiqueVenteTableModel = new StatistiqueVenteTableModel();
		bilanTableModel = new TableModelBilan();
		userService = new UserService();
		utilisateur = userService.retourneUtilisateurParLogin(loginUtilisateur);
		b_nouvelUtilisateur = new JButton("Nouvel Utilisateur");
		b_activiteUtilisateur = new JButton("Activit\351s");
		b_supprimerUtilisateur = new JButton("Supprimer");
		b_validerBilan = new JButton("Valider");
		b_supprimerProduit = new JButton("Supprimer le produit");
		b_enregistrerParametres = new JButton("Enregistrer");
		b_enregistrerParametres.setEnabled(false);
		b_annulerModificationParametres = new JButton("Annuler");
		b_annulerModificationParametres.setEnabled(false);
		b_modifier = new JButton("Modifier");
		l_statistiqueVente = new JLabel("Statistiques des ventes");
		l_statistiqueVente.setIcon(new ImageIcon("ressources/images/statistique.png"));
		l_expAdmin = new JLabel("           fenetre d'administration de l'application");
		l_expAdmin.setFont(new Font("Times new Roman",Font.ITALIC,18));
		historiqueVente = new Historique(this);
		historiqueVente.setVisible(false);
		panelCentreBilan.setLayout(new GridLayout(2, 1));
		panelMontantVente.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelCentreBilan.add(panelMontantVente);
		panelMontantVente.add(l_recette);
		panelCentreBilan.add(panelMontantLivraison);
		tableUtilisateur = new JTable(tableModelUtilisateur);
		tableStatistiqueVente = new JTable(statistiqueVenteTableModel);
		tableBilan = new JTable(bilanTableModel);
		b_nouveauProduit = new JButton("Nouveau Produit");
		productLabel = new JLabel("Liste des produits avec leurs prix respectifs");
		title = new JLabel("Panneau d'administration");
		userLabel = new JLabel("Liste des utilisateurs");
		userLabel.setIcon(new ImageIcon("ressources/images/listeUtilisateur.jpg"));
		panelBilan.setLayout(new BorderLayout());
		panelBilan.add(panelBilanNord, "North");
		panelBilanNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBilanNord.add(new JLabel("Choisissez la date : "));
		panelBilanNord.add(dateChooserBilan);
		panelBilanNord.add(b_validerBilan);
		panelBilan.add(panelBilanEst, BorderLayout.EAST);
		panelBilanEst.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBilan.add(new JScrollPane(tableBilan), BorderLayout.CENTER);
		panelUtilisateur.setLayout(new BorderLayout());
		panelUtilisateur.add(panelUtilisateurNord, BorderLayout.NORTH);
		panelUtilisateurNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelUtilisateur.add(new JScrollPane(tableUtilisateur),BorderLayout.CENTER);
		panelUtilisateurEst.setLayout(new BorderLayout(10, 10));
		panelOperationUtilisateur.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelOperationUtilisateur.add(b_nouvelUtilisateur);
		b_activiteUtilisateur.setEnabled(false);
		panelOperationUtilisateur.add(b_activiteUtilisateur);
		b_supprimerUtilisateur.setEnabled(false);
		panelOperationUtilisateur.add(b_supprimerUtilisateur);
		panelUtilisateur.add(panelOperationUtilisateur, BorderLayout.SOUTH);

		sliderDureeVieMDP = new JSlider(10, 90, 10);
		sliderDureeVieMDP.setSnapToTicks(true);
		sliderDureeVieMDP.setPaintLabels(true);
		sliderDureeVieMDP.setMajorTickSpacing(10);
		sliderDureeVieMDP.setMinorTickSpacing(5);
		sliderDureeVieMDP.setBorder(new TitledBorder("dur�e de vie du mot de passe"));
		sliderDureeVieMDP.setPaintTicks(true);
		sliderDureeVieMDP.setEnabled(false);

		sliderLongueurMDP = new JSlider(10, 100, 10);
		sliderLongueurMDP.setSnapToTicks(true);
		sliderLongueurMDP.setPaintLabels(true);
		sliderLongueurMDP.setMajorTickSpacing(10);
		sliderLongueurMDP.setMinorTickSpacing(5);
		sliderLongueurMDP.setBorder(new TitledBorder("Longueur du mot de passe"));
		sliderLongueurMDP.setPaintTicks(true);
		sliderLongueurMDP.setEnabled(false);
		SpinnerNumberModel spinnerNumberModelTentative = new SpinnerNumberModel(3, 3, 15, 1);
		spinnerNbreTentative = new JSpinner(spinnerNumberModelTentative);
		spinnerNbreTentative.setEnabled(false);
		SpinnerNumberModel spinnerNumberModelInactivite = new SpinnerNumberModel(1, 1, 20, 1);
		spinnerTempsInactivite = new JSpinner(spinnerNumberModelInactivite);
		spinnerTempsInactivite.setEnabled(false);
		panelUtilisateurEstCentre.setLayout(new GridLayout(4, 1, 0, 0));
		panelUtilisateurEstCentre.add(sliderLongueurMDP);
		panelUtilisateurEstCentre.add(sliderDureeVieMDP);
		panelSpinnerNbreTentative = new JPanel();
		panelSpinnerNbreTentative.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSpinnerNbreTentative.add(new JLabel("Nombre de tentatives : "));
		panelSpinnerNbreTentative.add(spinnerNbreTentative);
		panelSpinnerTempsInactivite = new JPanel();
		panelSpinnerTempsInactivite.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSpinnerTempsInactivite.add(new JLabel("Temps d'inactivit� apres echec : "));
		panelSpinnerTempsInactivite.add(spinnerTempsInactivite);
		panelSpinner = new JPanel();
		panelSpinner.setLayout(new BoxLayout(panelSpinner, BoxLayout.Y_AXIS));
		panelSpinner.add(panelSpinnerNbreTentative);
		panelSpinner.add(panelSpinnerTempsInactivite);
		panelUtilisateurEstCentre.add(panelSpinner);
		panelUtilisateurEstCentre.setBorder(new TitledBorder("Gestion des mots de passe"));
		panelUtilisateurEst.add(panelUtilisateurEstCentre, BorderLayout.CENTER);
		panelUtilisateur.add(panelUtilisateurEst, BorderLayout.EAST);
		panelBouton = new JPanel();
		panelBouton.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBouton.add(b_modifier);
		panelBouton.add(b_enregistrerParametres);
		panelBouton.add(b_annulerModificationParametres);
		panelSpinner.add(panelBouton);
		b_supprimerProduit.setEnabled(false);
		add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout());
		panelCentre.setBackground(Color.WHITE);
		panelNord.setLayout(new GridLayout(1, 2));
		titlePanel.setLayout(new BorderLayout());
		title.setFont(new Font("Times new Roman",Font.ITALIC,36));
		titlePanel.add(title, BorderLayout.NORTH);
		titlePanel.add(l_expAdmin, BorderLayout.CENTER);
		titlePanel.setBackground(Color.WHITE);
		panelNord.add(titlePanel);
		panelIcon.setLayout(new FlowLayout(2));
		panelIcon.add(new JLabel(new ImageIcon("ressources/images/configuration.png"), 4));
		panelIcon.setBackground(Color.WHITE);
		panelNord.add(panelIcon);
		panelNord.setBackground(Color.WHITE);
		add(panelNord, BorderLayout.NORTH);
		panelUtilisateurNord.add(userLabel);
		panelProduitNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelProduitNord.add(productLabel);
		panelProduitSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelProduitSud.add(b_nouveauProduit);
		panelStatistiqueVente.setLayout(new BorderLayout());
		panelStatistiqueVente.add(new JScrollPane(tableStatistiqueVente),"Center");
		panelStatistiqueVente.add(statistiqueVentePanelNord, BorderLayout.NORTH);
		statistiqueVentePanelNord.add(l_statistiqueVente);
		panelStatistiqueVente.add(statistiqueVentePanelEst, BorderLayout.EAST);
		panelProduitSud.add(b_supprimerProduit);
		titledTabProperties = new TitledTabProperties();
		titledTabProperties.addSuperObject(new ShapedGradientTheme().getTitledTabProperties());
		tabbedPanel = new TabbedPanel();
		tabbedPanel.getProperties().addSuperObject(new ShapedGradientTheme().getTabbedPanelProperties());
		titledTabUtilisateur = new TitledTab("Utilisateurs", null,panelUtilisateur, null);
		titledTabUtilisateur.getProperties().addSuperObject(titledTabProperties);
		titledTabSatistiqueVente = new TitledTab("Statistiques des ventes",null, panelStatistiqueVente, null);
		titledTabSatistiqueVente.getProperties().addSuperObject(titledTabProperties);
		titledTabBilan = new TitledTab("Bilan", null, panelBilan, null);
		titledTabBilan.getProperties().addSuperObject(titledTabProperties);
		tabbedPanel.addTab(titledTabUtilisateur);
		tabbedPanel.addTab(titledTabProduit);
		tabbedPanel.addTab(titledTabHistoriqueVente);
		tabbedPanel.addTab(titledTabSatistiqueVente);
		tabbedPanel.addTab(titledTabBilan);
		panelCentre.add(tabbedPanel, BorderLayout.NORTH);
		nouvelUtilisateur = new NouvelUtilisateur();
		nouvelUtilisateur.addUtilisateurListener(this);
		popupMenuUtilisateur = new JPopupMenu();
		itemActiviteUtilisateur = new JMenuItem("Voir les activit�s de cet utilisateur");
		itemInfoUtilisateur = new JMenuItem("Plus d'informations sur cet utilisateur");
		itemSupprimerUtilisateur = new JMenuItem("Supprimer cet utilisateur");
		popupMenuUtilisateur.add(itemInfoUtilisateur);
		popupMenuUtilisateur.add(itemActiviteUtilisateur);
		popupMenuUtilisateur.add(itemSupprimerUtilisateur);
		panelSud.setLayout(new GridLayout(1,1));
		panelSud.add(new CopyRight());
		add(panelSud,BorderLayout.SOUTH);
		afficherListeGestionnaire();
		afficheParametre();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		itemActiviteUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Activite(utilisateur.getIdutilisateur());
			}
		});
		itemInfoUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new InfoUtilisateur((String)tableUtilisateur.getValueAt(tableUtilisateur.getSelectedRow(), COLONNELOGIN));
				//JOptionPane.showMessageDialog(null, "plus d'informations");
			}
		});
		itemSupprimerUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				supprimerUtilisateur();
			}
		});
		tableUtilisateur.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void mouseReleased(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void showIfPopupTrigger(MouseEvent event) {
				if (event.isPopupTrigger()) {
					popupMenuUtilisateur.show(tableUtilisateur, event.getX(), /***
					 * 
					 * 
					 * affichage du popup
					 */
					event.getY());
				}
			}
		});

		spinnerNbreTentative.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				spinnerNbreTentative.updateUI();
				spinnerNbreTentative.requestFocus();
			}
		});
		spinnerTempsInactivite.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				spinnerTempsInactivite.updateUI();
				spinnerTempsInactivite.requestFocus();
			}
		});
		b_nouvelUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*** reinitialisation des champs de la table */
				nouvelUtilisateur.reinitialiseChamps();
				nouvelUtilisateur.setVisible(true);
			}
		});
		b_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerNbreTentative.setEnabled(true);
				spinnerTempsInactivite.setEnabled(true);
				sliderDureeVieMDP.setEnabled(true);
				sliderLongueurMDP.setEnabled(true);

			}

		});
		spinnerNbreTentative.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				b_enregistrerParametres.setEnabled(true);
				b_annulerModificationParametres.setEnabled(true);
			}
		});
		spinnerTempsInactivite.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				b_enregistrerParametres.setEnabled(true);
				b_annulerModificationParametres.setEnabled(true);
			}
		});
		sliderDureeVieMDP.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				b_enregistrerParametres.setEnabled(true);
				b_annulerModificationParametres.setEnabled(true);
			}
		});
		sliderLongueurMDP.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				b_enregistrerParametres.setEnabled(true);
				b_annulerModificationParametres.setEnabled(true);
			}
		});
		b_enregistrerParametres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (new UserService().miseAJourParametres(
						sliderLongueurMDP.getValue(),
						sliderDureeVieMDP.getValue(),
						(Integer) spinnerNbreTentative.getValue(),
						(Integer) spinnerTempsInactivite.getValue()) != 0) {
					spinnerNbreTentative.setEnabled(false);
					spinnerTempsInactivite.setEnabled(false);
					sliderDureeVieMDP.setEnabled(false);
					sliderLongueurMDP.setEnabled(false);
					b_enregistrerParametres.setEnabled(false);
					b_annulerModificationParametres.setEnabled(false);
					JOptionPane.showMessageDialog(null,
							"Parametres mis � jour avec succes !!!",
							"succes de la mise � jour",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		b_annulerModificationParametres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Parametres parametres = new UserService().retourneParametres();
				spinnerNbreTentative.setValue(parametres.getTentativemdp());
				spinnerTempsInactivite.setValue(parametres.getTempsinactivitesmdp());
				sliderDureeVieMDP.setValue(parametres.getDureeviemdp());
				sliderLongueurMDP.setValue(parametres.getLongueurmdp());
				spinnerNbreTentative.setEnabled(false);
				spinnerTempsInactivite.setEnabled(false);
				sliderDureeVieMDP.setEnabled(false);
				sliderLongueurMDP.setEnabled(false);
				b_annulerModificationParametres.setEnabled(false);
				b_enregistrerParametres.setEnabled(false);
			}
		});
		tableUtilisateur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				b_activiteUtilisateur.setEnabled(true);
				b_supprimerUtilisateur.setEnabled(true);
			}
		});
		b_supprimerUtilisateur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				supprimerUtilisateur();
			}
		});
		b_activiteUtilisateur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Activite(utilisateur.getIdutilisateur());
			}
		});
		setResizable(false);
		setSize(new Dimension(950, 660));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(2);
		setModal(true);
		setVisible(true);
	}

	public static void main(String args[]) {
		new Configuration("tintin");
	}

	@Override
	public void ajouterUilisateur(UtilisateurEvent event) {
		tableModelUtilisateur.netoyerListe();
		/**
		 * suppression de toutes les entr�es de la table des utilisateur
		 */
		afficherListeGestionnaire();
	}

	public void supprimerUtilisateur() {

		// TODO Auto-generated method stub
		int confirmation = JOptionPane
				.showConfirmDialog(
						null,
						"Toutes les informations concernant cet utilisateur seront perdues,voulez-vous vraiment continuer ?",
						"Confirmation de la suppression",
						JOptionPane.YES_NO_OPTION);
		if (confirmation == 0) {
			if (new UserService()
					.supprimerUtilisateur((String) tableUtilisateur.getValueAt(
							tableUtilisateur.getSelectedRow(), COLONNELOGIN)) != 0) {
				tableModelUtilisateur.netoyerListe();
				/**
				 * suppression de toutes les entr�es de la table des utilisateur
				 */
				afficherListeGestionnaire();
				JOptionPane.showMessageDialog(null,
						"Utilisateur supprim� avec succes",
						"Reussite de l'operation de suppression",
						JOptionPane.INFORMATION_MESSAGE);
				b_activiteUtilisateur.setEnabled(false);
				b_supprimerUtilisateur.setEnabled(false);
			}
		}

	}

	public void afficheParametre() {
		Parametres parametres = new UserService().retourneParametres();
		System.out.println("Duree : " + parametres.getDureeviemdp());
		sliderDureeVieMDP.setValue(parametres.getDureeviemdp());
		sliderLongueurMDP.setValue(parametres.getLongueurmdp());
		spinnerNbreTentative.setValue(parametres.getTentativemdp());
		spinnerTempsInactivite.setValue(parametres.getTempsinactivitesmdp());
	}

	public void afficherListeGestionnaire() {
		LinkedList<Utilisateur> listeGestionnaire = new UserService()
				.listeGestionnaire();
		for (Utilisateur utilisateur : listeGestionnaire) {
				tableModelUtilisateur.ajouter(utilisateur);
		}
	}
}
