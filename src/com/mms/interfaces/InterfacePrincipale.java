package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.theme.ShapedGradientTheme;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.infonode.tabbedpanel.titledtab.TitledTabProperties;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.event.UtilisateurEvent;
import com.mms.listener.UtilisateurListener;
import com.mms.pojos.Utilisateur;
import com.mms.service.UtilisateurService;
import com.mms.util.Listener;

@SuppressWarnings("serial")
public class InterfacePrincipale extends JFrame implements UtilisateurListener {
	private InterfaceVente interfaceVente;
	private InterfaceLivraison interfaceLivraison;
	private JPanel panelSud;
	private JPanel panelOuest;
	private JPanel panelNord;
	private JPanel panelUtilisateurConnecte;
	private JPanel panelLabelUtilisateurConnecte;
	private JPanel panelLabelNomUtilisateur;
	private JTaskPaneGroup taskPaneVente;
	private JTaskPaneGroup taskPaneAchat;
	private JTaskPaneGroup taskPaneProduit;
	private JTaskPaneGroup taskPaneApplication;

	private JLabel l_vente;
	private JLabel l_achat;
	private JLabel l_creditClient;
	private JLabel l_creditFournisseur;
	private JLabel l_listeClient;
	private JLabel l_listeFournisseur;
	private JLabel l_inventaire;
	private JLabel l_nouveauProduit;
	private JLabel l_catalogueProduit;
	private JLabel l_quitter;
	private JLabel l_deconnexion;
	private JLabel l_admin;
	private JLabel l_utilisateurConnecte;
	private JLabel l_nomUtilisateurConnecte;
	private Box boxAgencementTaskPane;
	private JMenuBar barreDeMenu;
	private JMenu menuClient;
	private JMenu menuFournisseur;
	private JMenu menuUtilisateur;
	private JMenu menuAPropos;
	private JMenu menuProduits;
	private JMenuItem itemNouveauClient;
	private JMenuItem itemListeClient;
	private JMenuItem itemCreditClient;
	private JMenuItem itemNouveauFournisseur;
	private JMenuItem itemListeFournisseur;
	private JMenuItem itemCreditFournisseur;
	private JMenuItem itemChangerIdentifiant;
	private JMenuItem itemActiviteUtilisateur;
	private JMenuItem itemLogiciel;
	private JMenuItem itemDeveloppeurs;
	private JMenuItem itemNouveauProduit;
	private JMenuItem itemCatalogue;
	private JMenuItem itemInventaire;
	private JToolBar toolBarUtilisateurConnecte;
	private TitledTabProperties titledTabProperties;
	private TabbedPanel tabbedPanel;
	private TitledTab titledTabVente;
	private TitledTab titledTabLivraison;
	private NouveauClient nouveauClient;
	private ModifierClient modifierClient;
	private ListeClient listeClient;
	private NouveauFournisseur nouveauFournisseur;
	private ModifierFournisseur modifierFournisseur;
	private ListeFournisseur listeFournisseur;
	private NouveauProduit nouveauProduit;
	private ListeProduit catalogue;
	private String loginUtilisateur;
	private UtilisateurService utilisateurService;
	private Utilisateur utilisateur;
	private ModifierInformationUtilisateur modifierInformationUtilisateur;

	public InterfacePrincipale(String loginUtilisateur) {
		super("Market Management System 2.0");
		this.loginUtilisateur = loginUtilisateur;
		utilisateurService = new UtilisateurService();
		utilisateur = utilisateurService
				.retourneUtilisateurParLogin(loginUtilisateur);
		modifierInformationUtilisateur = new ModifierInformationUtilisateur(
				loginUtilisateur);
		modifierInformationUtilisateur.addUtilisateurListener(this);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		afficheComposant();
		afficheImages();
		abonnement();
		/*System.setProperty("Quaqua.tableLayoutPolicy", "wrap");
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager
					.getLookAndFeel());
		} catch (Exception e) {
			System.out
					.println("An error occured while using the look and feel !!!");
		}
		SwingUtilities.updateComponentTreeUI(this);*/
	}
	public void afficheComposant() {
		panelSud = new JPanel();
		panelOuest = new JPanel();
		panelNord = new JPanel();
		panelUtilisateurConnecte = new JPanel();
		panelLabelUtilisateurConnecte = new JPanel();
		panelLabelNomUtilisateur = new JPanel();
		interfaceVente = new InterfaceVente(loginUtilisateur);
		interfaceLivraison = new InterfaceLivraison(loginUtilisateur);
		l_vente = new JLabel("Ventes enregistr�es");
		l_achat = new JLabel("Livraisons enregistr�es");
		l_creditClient = new JLabel("Credits clients");
		l_creditFournisseur = new JLabel("Credits fournisseurs");
		l_inventaire = new JLabel("Inventaire");
		l_listeClient = new JLabel("Liste des clients");
		l_listeFournisseur = new JLabel("Liste des fournisseurs");
		l_nouveauProduit = new JLabel("Nouveau produit");
		l_catalogueProduit = new JLabel("Catalogue de vente");
		l_quitter = new JLabel("Quitter");
		l_deconnexion = new JLabel("Deconnexion");
		l_admin = new JLabel("Panneau d'administration");
		l_utilisateurConnecte = new JLabel("Utilisateur connect� : ");
		l_nomUtilisateurConnecte = new JLabel(loginUtilisateur);
		/*** prendra en parametre le login de l'utilisateur connect� ****/
		taskPaneVente = new JTaskPaneGroup();
		taskPaneAchat = new JTaskPaneGroup();
		taskPaneProduit = new JTaskPaneGroup();
		taskPaneApplication = new JTaskPaneGroup();
		taskPaneVente.setTitle("Gestion des ventes               ");
		taskPaneProduit.setTitle("Gestion des produits            ");
		taskPaneAchat.setTitle("Gestion des Livraisons         ");
		taskPaneApplication.setTitle("Application ");
		taskPaneAchat.add(l_achat);
		taskPaneAchat.add(l_creditFournisseur);
		taskPaneAchat.add(l_listeFournisseur);
		taskPaneVente.add(l_vente);
		taskPaneVente.add(l_creditClient);
		taskPaneVente.add(l_listeClient);
		taskPaneProduit.add(l_nouveauProduit);
		taskPaneProduit.add(l_catalogueProduit);
		taskPaneProduit.add(l_inventaire);
		taskPaneApplication.add(l_admin);
		taskPaneApplication.add(l_deconnexion);
		taskPaneApplication.add(l_quitter);
		boxAgencementTaskPane = Box.createVerticalBox();
		panelSud.setLayout(new GridLayout(1, 1));
		panelSud.add(new CopyRight());
		add(panelSud, BorderLayout.SOUTH);
		add(new JScrollPane(panelOuest), BorderLayout.WEST);
		boxAgencementTaskPane.add(new JLabel("    "));
		boxAgencementTaskPane.add(taskPaneVente);
		boxAgencementTaskPane.add(new JLabel("    "));
		boxAgencementTaskPane.add(taskPaneAchat);
		boxAgencementTaskPane.add(new JLabel("    "));
		boxAgencementTaskPane.add(taskPaneProduit);
		boxAgencementTaskPane.add(new JLabel("    "));
		boxAgencementTaskPane.add(taskPaneApplication);
		panelOuest.add(boxAgencementTaskPane);
		add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		barreDeMenu = new JMenuBar();
		menuClient = new JMenu("Clients");
		menuFournisseur = new JMenu("Fournisseurs");
		menuUtilisateur = new JMenu("Utilisateurs");
		menuAPropos = new JMenu("A propos");
		menuProduits = new JMenu("Produits");
		barreDeMenu.add(menuProduits);
		barreDeMenu.add(menuClient);
		barreDeMenu.add(menuFournisseur);
		barreDeMenu.add(menuUtilisateur);
		barreDeMenu.add(menuAPropos);
		itemNouveauClient = new JMenuItem("Nouveau client");
		itemListeClient = new JMenuItem("Liste des clients");
		itemCreditClient = new JMenuItem("Credits client");
		itemNouveauFournisseur = new JMenuItem("Nouveau fournisseur");
		itemListeFournisseur = new JMenuItem("Liste fournisseur");
		itemCreditFournisseur = new JMenuItem("Credits fournisseur");
		itemChangerIdentifiant = new JMenuItem("Modifier les identifiants");
		itemActiviteUtilisateur = new JMenuItem(
				"Voir les activit�s d'un utilisateur");
		itemLogiciel = new JMenuItem("A propos du logiciels");
		itemDeveloppeurs = new JMenuItem("Developpeurs");
		itemNouveauProduit = new JMenuItem("Nouveau produit");
		itemCatalogue = new JMenuItem("Catalogue des produits");
		itemInventaire = new JMenuItem("Inventaire des produits");
		menuClient.add(itemNouveauClient);
		menuClient.add(itemListeClient);
		menuClient.add(itemCreditClient);
		menuFournisseur.add(itemNouveauFournisseur);
		menuFournisseur.add(itemListeFournisseur);
		menuFournisseur.add(itemCreditFournisseur);
		menuUtilisateur.add(itemActiviteUtilisateur);
		menuUtilisateur.add(itemChangerIdentifiant);
		menuAPropos.add(itemLogiciel);
		menuAPropos.add(itemDeveloppeurs);
		menuProduits.add(itemNouveauProduit);
		menuProduits.add(itemCatalogue);
		menuProduits.add(itemInventaire);
		setJMenuBar(barreDeMenu);
		toolBarUtilisateurConnecte = new JToolBar();
		panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelUtilisateurConnecte.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelNord.add(panelUtilisateurConnecte);
		toolBarUtilisateurConnecte.add(panelLabelUtilisateurConnecte);
		panelLabelUtilisateurConnecte
				.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelLabelUtilisateurConnecte.add(l_utilisateurConnecte);
		toolBarUtilisateurConnecte.add(panelLabelNomUtilisateur);
		panelUtilisateurConnecte.add(toolBarUtilisateurConnecte);
		panelLabelNomUtilisateur.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLabelNomUtilisateur.add(l_nomUtilisateurConnecte);
		titledTabProperties = new TitledTabProperties();
		titledTabProperties.addSuperObject(new ShapedGradientTheme()
				.getTitledTabProperties());
		tabbedPanel = new TabbedPanel();
		tabbedPanel.getProperties().addSuperObject(
				new ShapedGradientTheme().getTabbedPanelProperties());
		titledTabVente = new TitledTab("Enregistrement des ventes" + "  ",
				new ImageIcon("ressources/images/enregistrement.png"),
				interfaceVente, null);
		titledTabVente.getProperties().addSuperObject(titledTabProperties);
		titledTabLivraison = new TitledTab("Enregistrement des livraisons",
				new ImageIcon("ressources/images/enregistrement.png"),
				interfaceLivraison, null);

		titledTabLivraison.getProperties().addSuperObject(titledTabProperties);
		tabbedPanel.addTab(titledTabVente);
		tabbedPanel.addTab(titledTabLivraison);
		add(tabbedPanel, BorderLayout.CENTER);
		/************/
		nouveauClient = new NouveauClient();
		modifierClient = new ModifierClient();
		listeClient = new ListeClient(nouveauClient, modifierClient);
		nouveauClient.addClientListener(interfaceVente);
		nouveauClient.addClientListener(listeClient);
		modifierClient.addClientListener(listeClient);
		modifierClient.addClientListener(interfaceVente);

		nouveauFournisseur = new NouveauFournisseur();
		modifierFournisseur = new ModifierFournisseur();
		listeFournisseur = new ListeFournisseur(nouveauFournisseur,
				modifierFournisseur);
		nouveauFournisseur.addFournisseurListener(interfaceLivraison);
		nouveauFournisseur.addFournisseurListener(listeFournisseur);
		modifierFournisseur.addFournisseurListener(listeFournisseur);
		modifierFournisseur.addFournisseurListener(interfaceLivraison);

		nouveauProduit = new NouveauProduit();
		catalogue = new ListeProduit(nouveauProduit);
		nouveauProduit.addProduitListener(interfaceLivraison);
		nouveauProduit.addProduitListener(interfaceVente);
		nouveauProduit.addProduitListener(catalogue);
		pack();
		setLocationRelativeTo(null);
	}
	public void afficheImages() {
		l_quitter.setIcon(new ImageIcon("ressources/images/quitter.png"));
		l_admin.setIcon(new ImageIcon("ressources/images/administrer.png"));
		l_deconnexion
				.setIcon(new ImageIcon("ressources/images/deconnexion.png"));
		l_achat.setIcon(new ImageIcon("ressources/images/historique.png"));
		l_vente.setIcon(new ImageIcon("ressources/images/historique.png"));
		l_nouveauProduit.setIcon(new ImageIcon(
				"ressources/images/nouveauProduit.png"));
		l_listeClient.setIcon(new ImageIcon("ressources/images/liste.png"));
		l_listeFournisseur
				.setIcon(new ImageIcon("ressources/images/liste.png"));
		l_creditClient.setIcon(new ImageIcon("ressources/images/credit.png"));
		l_creditFournisseur.setIcon(new ImageIcon(
				"ressources/images/credit.png"));
		l_inventaire.setIcon(new ImageIcon("ressources/images/inventaire.png"));
		l_catalogueProduit.setIcon(new ImageIcon(
				"ressources/images/catalogue.png"));
		l_utilisateurConnecte.setIcon(new ImageIcon(
				"ressources/images/utilisateurConnecte.jpg"));
	}
	public void abonnement() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				utilisateurService.mettreAJourSession(utilisateur
						.getIdutilisateur());
			}

		});

		itemInventaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Inventaire();
			}
		});
		itemCatalogue.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.WAIT_CURSOR);
				catalogue.setVisible(true);
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		itemNouveauProduit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nouveauProduit.setVisible(true);
			}
		});
		itemDeveloppeurs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		itemLogiciel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}

		});
		itemNouveauClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nouveauClient.setVisible(true);

			}
		});
		itemListeClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listeClient.setVisible(true);
			}
		});
		itemCreditClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CreditClient();

			}

		});
		itemNouveauFournisseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nouveauFournisseur.setVisible(true);
			}
		});
		itemListeFournisseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listeFournisseur.setVisible(true);
			}
		});
		itemCreditFournisseur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CreditFournisseur();
			}

		});
		itemActiviteUtilisateur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Activite(utilisateur.getIdutilisateur());
			}

		});
		itemChangerIdentifiant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modifierInformationUtilisateur.chargerInformation();
				modifierInformationUtilisateur.setVisible(true);
			}
		});

		l_admin.addMouseListener(new Listener(l_admin, (new StringBuilder(
				"<html><a href = '' color = black>")).append(l_admin.getText())
				.append("</a></html>").toString(), "Panneau d'administration") {
		});
		l_admin.setToolTipText("Panneau d'administration de l'application");
		l_admin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				new Authentification(utilisateur.getLoginutilisateur());
			}
		});
		l_vente.addMouseListener(new Listener(l_vente, (new StringBuilder(
				"<html><a href='' color = black>")).append(l_vente.getText())
				.append("</a></html>").toString(), "Ventes enregistr\351es"));
		l_vente.setToolTipText("Liste des ventes realis\351es");
		l_vente.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent event) {
				setCursor(Cursor.WAIT_CURSOR);
				new HistoriqueVente().setVisible(true);
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		l_achat.addMouseListener(new Listener(l_achat, (new StringBuilder(
				"<html><a href='' color = black>")).append(l_achat.getText())
				.append("</a></html>").toString(), "Livraisons enregistr\351es"));
		l_achat.setToolTipText("Liste des achats realis\351s");
		l_achat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				new HistoriqueLivraison();
			}
		});
		l_creditClient.addMouseListener(new Listener(l_creditClient,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_creditClient.getText()).append("</a></html>")
						.toString(), "Credits clients"));
		l_creditClient.setToolTipText("Liste des credits client");
		l_creditClient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				new CreditClient();
			}
		});
		l_creditFournisseur.addMouseListener(new Listener(l_creditFournisseur,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_creditFournisseur.getText())
						.append("</a></html>").toString(),
				"Credits fournisseurs"));
		l_creditFournisseur.setToolTipText("Liste des credits fournisseur");
		l_creditFournisseur.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				new CreditFournisseur();
				System.gc();
			}
		});
		l_inventaire.addMouseListener(new Listener(l_inventaire,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_inventaire.getText()).append("</a></html>")
						.toString(), "Inventaire"));
		l_inventaire.setToolTipText("Inventaire des produits");
		l_inventaire.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				new Inventaire().setVisible(true);
				System.gc();
			}
		});
		l_listeClient.addMouseListener(new Listener(l_listeClient,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_listeClient.getText()).append("</a></html>")
						.toString(), "Liste des clients"));
		l_listeClient.setToolTipText("Liste des clients enregistr\351s");
		l_listeClient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				listeClient.setVisible(true);
				System.gc();
			}
		});
		l_listeFournisseur.addMouseListener(new Listener(l_listeFournisseur,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_listeFournisseur.getText())
						.append("</a></html>").toString(),
				"Liste des fournisseurs"));
		l_listeFournisseur
				.setToolTipText("Liste des fournisseurs enregistr\351s");
		l_listeFournisseur.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				listeFournisseur.setVisible(true);
			}
		});
		l_nouveauProduit.addMouseListener(new Listener(l_nouveauProduit,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_nouveauProduit.getText())
						.append("</a></html>").toString(), "Nouveau produit"));
		l_nouveauProduit.setToolTipText("Enregistrer un nouveau produit");
		l_nouveauProduit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				nouveauProduit.setVisible(true);
			}
		});
		l_catalogueProduit
				.addMouseListener(new Listener(l_catalogueProduit,
						(new StringBuilder("<html><a href='' color = black>"))
								.append(l_catalogueProduit.getText())
								.append("</a></html>").toString(),
						"Catalogue de vente"));
		l_catalogueProduit.setToolTipText("catalogue des produits");
		l_catalogueProduit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				catalogue.setVisible(true);
			}
		});
		l_quitter.addMouseListener(new Listener(l_quitter, (new StringBuilder(
				"<html><a href='' color = black>")).append(l_quitter.getText())
				.append("</a></html>").toString(), "Quitter"));
		l_quitter.setToolTipText("quitter l'application");
		l_quitter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				/*** mis a jour de la session de l'utilisateur connect� ****/
				utilisateurService.mettreAJourSession(utilisateurService
						.retourneUtilisateurParLogin(loginUtilisateur)
						.getIdutilisateur());
				System.exit(0);
			}
		});
		l_deconnexion.addMouseListener(new Listener(l_deconnexion,
				(new StringBuilder("<html><a href='' color = black>"))
						.append(l_deconnexion.getText()).append("</a></html>")
						.toString(), "Deconnexion"));
		l_deconnexion.setToolTipText("Deconnectez-vous de l'application");
		l_deconnexion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int i = JOptionPane.showConfirmDialog(null,
						"Voulez-vous vraiment vous deconnectez ?",
						"Deconnexion", 0);
				if (i == 0) {
					utilisateurService.mettreAJourSession(utilisateurService
							.retourneUtilisateurParLogin(loginUtilisateur)
							.getIdutilisateur());
					dispose();
					(new EcranDeConnexion()).setVisible(true);
				}
			}
		});
	}

	public static void main(String args[]) {
		InterfacePrincipale interfacePrincipale = new InterfacePrincipale(
				"ezerbo");
		interfacePrincipale.setVisible(true);
	}
	@Override
	public void ajouterUilisateur(UtilisateurEvent event) {
		// TODO Auto-generated method stub
		utilisateur = utilisateurService.retourneUtilisateurParId(utilisateur
				.getIdutilisateur());
	}

}
