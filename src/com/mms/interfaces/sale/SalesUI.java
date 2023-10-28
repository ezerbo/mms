package com.mms.interfaces.sale;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.event.ClientEvent;
import com.mms.event.ProduitEvent;
import com.mms.event.VenteEvent;
import com.mms.interfaces.client.NouveauClient;
import com.mms.interfaces.common.ComboBoxEditable;
import com.mms.interfaces.common.TextFieldTable;
import com.mms.listener.ClientListener;
import com.mms.listener.ProduitListener;
import com.mms.listener.VenteListener;
import com.mms.domain.Categorie;
import com.mms.domain.Client;
import com.mms.domain.Session;
import com.mms.service.CategorieService;
import com.mms.service.ClientService;
import com.mms.service.UserService;
import com.mms.service.VenteService;
import com.mms.interfaces.tablemodels.LigneDeVente;
import com.mms.interfaces.tablemodels.TableModelVente;
import com.mms.util.GenereDate;

public class SalesUI extends JPanel
		implements
			VenteListener,
			ClientListener,
			ProduitListener {
	/****/
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelChoixClient;
	private JPanel panelSudChoixClient;
	private JPanel panelCompte;
	private JPanel panelMontantTotalTTC;
	private JPanel panelMontantTotalHT;
	private JPanel panelMontantTotalTVA;
	private JPanel panelLibelleMontantTotalTTC;
	private JPanel panelLibelleMontantTotalHT;
	private JPanel panelLibelleMontantTotalTVA;
	private JPanel panelFacturation;
	private JPanel panelTypeVente;
	/*****/
	private JComboBox comboListeClient;
	private ComboBoxEditable comboboxDesignation;
	/****/
	private JScrollPane scrollPane;
	/****/
	private JButton b_nouveauClient;
	private JButton b_apercuFacture;
	private JButton b_enregistrer;
	/******/
	private JTable table;
	private TableModelVente tableModel;
	/******/
	/*******/
	private JTaskPaneGroup taskPaneChoixClient;
	private JTaskPaneGroup taskPaneCompte;
	private JTaskPaneGroup taskPaneFacturation;
	/******/
	private JPopupMenu popupMenu;
	private JMenuItem itemAjouterLigne;
	private JMenuItem itemSupprimerToutesLesLignes;
	private JMenuItem itemSupprimerLigneSelectionnee;
	/*******/
	private JLabel labelClient;
	private JLabel labelLibelleMontantTotalTTC;
	private JLabel labelLibelleMontantTotalTVA;
	private JLabel labelLibelleMontantTotalHT;
	private JLabel labelMontantTotalTTC;
	private JLabel labelMontantTotalTVA;
	private JLabel labelMontantTotalHT;
	/*******/
	private CategorieService categorieService;
	private ClientService clientService;
	private VenteService venteService;
	private UserService userService;
	/******/
	private static String VENTECOMPTANT = "COMPTANT";
	private static String VENTECREDIT = "CREDIT";
	/*******/
	private NouveauClient nouveauClient;
	/*********/
	private JCheckBox venteComptant;
	private JCheckBox venteCredit;
	public SalesUI() {
		setLayout(new BorderLayout());
		/**************/
		panelSud = new JPanel();
		panelNord = new JPanel();
		panelCompte = new JPanel();
		panelMontantTotalHT = new JPanel();
		panelMontantTotalTTC = new JPanel();
		panelMontantTotalTVA = new JPanel();
		panelLibelleMontantTotalHT = new JPanel();
		panelLibelleMontantTotalTTC = new JPanel();
		panelLibelleMontantTotalTVA = new JPanel();
		panelTypeVente = new JPanel();
		taskPaneChoixClient = new JTaskPaneGroup();
		taskPaneCompte = new JTaskPaneGroup();
		taskPaneFacturation = new JTaskPaneGroup();
		panelChoixClient = new JPanel();
		panelSudChoixClient = new JPanel();
		panelFacturation = new JPanel();
		labelClient = new JLabel("Liste des clients");
		labelLibelleMontantTotalTTC = new JLabel("Montant total TTC :");
		labelLibelleMontantTotalHT = new JLabel("Montant total HT : ");
		labelLibelleMontantTotalTVA = new JLabel("Montant total TVA : ");

		labelMontantTotalHT = new JLabel("0.0");
		labelMontantTotalTTC = new JLabel("0.0");
		labelMontantTotalTVA = new JLabel("0.0");
		comboListeClient = new JComboBox();
		comboboxDesignation = new ComboBoxEditable();
		b_nouveauClient = new JButton("Nouveau client");
		b_apercuFacture = new JButton("Aper�u de la facture");
		b_enregistrer = new JButton("Enregistrer");
		itemSupprimerToutesLesLignes = new JMenuItem(
				"Supprimer toutes les lignes");
		itemSupprimerToutesLesLignes.setEnabled(false);
		itemSupprimerLigneSelectionnee = new JMenuItem(
				"Supprimer la ligne selectionn�e");
		itemSupprimerLigneSelectionnee.setEnabled(false);
		itemAjouterLigne = new JMenuItem("Ajouter une nouvelle ligne");
		categorieService = new CategorieService();
		clientService = new ClientService();
		venteService = new VenteService();
		userService = new UserService();
		nouveauClient = new NouveauClient();
		venteComptant = new JCheckBox("Vente au comptant");
		venteCredit = new JCheckBox("Vente � cr�dit");

		nouveauClient.addClientListener(this);
		tableModel = new TableModelVente();
		tableModel.addVenteListener(this);
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNord.add(new JLabel((new StringBuilder("Date :  ")).append(
				(new GenereDate()).getDateCourante()).toString()));
		panelCompte.setLayout(new GridLayout(3, 2));

		panelFacturation.setLayout(new FlowLayout(FlowLayout.LEFT));
		b_enregistrer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b_apercuFacture.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelFacturation.add(b_apercuFacture);
		panelFacturation.add(b_enregistrer);
		taskPaneFacturation.setTitle("Facturation");
		taskPaneFacturation.setLayout(new BorderLayout());
		taskPaneFacturation.add(panelFacturation, BorderLayout.SOUTH);
		panelTypeVente.setLayout(new GridLayout(2, 1));
		panelTypeVente.add(venteComptant);
		panelTypeVente.add(venteCredit);
		venteComptant.setSelected(true);
		taskPaneFacturation.add(panelTypeVente, BorderLayout.CENTER);
		panelSud.add(taskPaneFacturation);

		taskPaneChoixClient.setTitle("Choix du client");
		taskPaneChoixClient.setLayout(new BorderLayout());
		panelChoixClient.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelChoixClient.add(labelClient);
		panelChoixClient.add(comboListeClient);
		taskPaneChoixClient.add(panelChoixClient, BorderLayout.CENTER);
		panelSudChoixClient.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSudChoixClient.add(b_nouveauClient);
		taskPaneChoixClient.add(panelSudChoixClient, BorderLayout.SOUTH);
		panelSud.add(taskPaneChoixClient);

		taskPaneCompte.setTitle("Le compte");
		taskPaneCompte.setLayout(new BorderLayout(0, 0));
		taskPaneCompte.add(panelCompte, BorderLayout.CENTER);
		panelSud.add(taskPaneCompte);

		panelLibelleMontantTotalHT.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLibelleMontantTotalHT.add(labelLibelleMontantTotalHT);
		panelCompte.add(panelLibelleMontantTotalHT);
		panelMontantTotalHT.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantTotalHT.add(labelMontantTotalHT);
		panelCompte.add(panelMontantTotalHT);

		panelLibelleMontantTotalTVA.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLibelleMontantTotalTVA.add(labelLibelleMontantTotalTVA);
		panelCompte.add(panelLibelleMontantTotalTVA);
		panelMontantTotalTVA.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantTotalTVA.add(labelMontantTotalTVA);
		panelCompte.add(panelMontantTotalTVA);

		panelLibelleMontantTotalTTC.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelLibelleMontantTotalTTC.add(labelLibelleMontantTotalTTC);
		panelCompte.add(panelLibelleMontantTotalTTC);
		panelMontantTotalTTC.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantTotalTTC.add(labelMontantTotalTTC);
		panelCompte.add(panelMontantTotalTTC);

		popupMenu = new JPopupMenu();
		popupMenu.add(itemAjouterLigne);
		popupMenu.add(itemSupprimerLigneSelectionnee);
		popupMenu.add(itemSupprimerToutesLesLignes);
		add(panelNord, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		abonnement();
		afficheListeClient();
		afficheLigne();
		afficheComposantTable();
		afficheListeProduit();

	}
	private void abonnement() {
		registerKeyboardAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tableModel.ajouteLigne();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		registerKeyboardAction(
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						nouveauClient.setVisible(true);
					}
				},
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK
						| InputEvent.ALT_MASK),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		registerKeyboardAction(
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						effectuerVente();
					}
				},
			KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK| InputEvent.CTRL_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW);
		comboListeClient.registerKeyboardAction(new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						comboListeClient.setPopupVisible(true);
					}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					/**
					 * si on double-clique sur le scrollPane contenant le
					 * tableau,on ajoute une ligne a la table des ventes
					 * 
					 */
					tableModel.ajouteLigne();
					if (tableModel.getRowCount() >= 2) {
						itemSupprimerLigneSelectionnee.setEnabled(true);
						itemSupprimerToutesLesLignes.setEnabled(true);
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void mouseReleased(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void showIfPopupTrigger(MouseEvent event) {
				if (event.isPopupTrigger()) {
					popupMenu.show(table, event.getX(), event.getY());
					/*** affichage du popup */
				}
			}
		});
		itemAjouterLigne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tableModel.ajouteLigne();
				if (tableModel.getRowCount() > 2) {
					/**
					 * si le nombre de ligne est superieure a celui par
					 * defaut,on active les items de suppression
					 */
					itemSupprimerLigneSelectionnee.setEnabled(true);
					itemSupprimerToutesLesLignes.setEnabled(true);
				}
			}

		});

		itemSupprimerLigneSelectionnee.addActionListener(arg0 -> {
			tableModel.supprimerLigne(table.getSelectedRow());
			/** le 1 est � remplacer avec l'index de l'element selectionn� **/
			if (tableModel.getRowCount() == 2) {
				/**
				 * si le nombre de ligne est egale a celui par defaut,on
				 * desactive les items de suppression
				 */
				itemSupprimerLigneSelectionnee.setEnabled(false);
				itemSupprimerToutesLesLignes.setEnabled(false);
			}
		});
		itemSupprimerToutesLesLignes.addActionListener(arg0 -> {
			tableModel.supprimerLignes();
			tableModel.ajouteLigne();
			tableModel.ajouteLigne();
			itemSupprimerToutesLesLignes.setEnabled(false);
		});
		b_nouveauClient.addActionListener(arg0 -> nouveauClient.setVisible(true));
		venteComptant.addActionListener(arg0 -> {
			if (venteComptant.isSelected())
				venteCredit.setSelected(false);
			else
				venteComptant.setSelected(true);
		});
		venteCredit.addActionListener(e -> {
			if (venteCredit.isSelected())
				venteComptant.setSelected(false);
			else
				venteCredit.setSelected(true);
		});

		b_enregistrer.addActionListener(e -> effectuerVente());
		b_apercuFacture.addActionListener(e -> new InterfaceFacture(tableModel.getLigneDeVente()));

	}
	/***
	 * Permet d'affecter a chaque colonne de la table des ventes son apparence
	 * la colonne des designation est de type JComboBox le reste des colonnes
	 * est de type JTextField
	 ***/
	private void afficheComposantTable() {
		table.getColumn("DESIGNATION").setCellEditor(new DefaultCellEditor(comboboxDesignation));
		table.getColumn("QUANTITE").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
		table.getColumn("PRIX UNITAIRE H.T.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
		table.getColumn("TOTAL H.T.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
		table.getColumn("TAUX T.V.A").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
		table.getColumn("MONTANT TVA").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
		table.getColumn("TOTAL T.T.C.").setCellEditor(new DefaultCellEditor(new TextFieldTable()));
	}
	/*****
	 * Affiche la liste des clients Elle supprime toutes les entre�s dans la
	 * liste selectionne le vide et renseigne les nouvelles valeurs
	 *****/
	private void afficheListeClient() {
		comboListeClient.removeAllItems();
		comboListeClient.addItem("");
		LinkedList<Client> listeClient = clientService.listeClient();
		for (Client client : listeClient) {
			comboListeClient.addItem(client.getNomClient() + " "+ client.getPrenomClient());
		}
	}
	/*****
	 * Permet d'enregistrer une vente
	 * 
	 * @param etatVente
	 *            : correspond a l'etat de la vente qui peut etre l'une des
	 *            valeurs suivantes ['COMPTANT','CREDIT']
	 * @param nomClient
	 *            : nom du client
	 * @param prenomClient
	 *            : prenom du client
	 *****/
	public int enregistrerVente(String etatVente, String nomClient,String prenomClient) {
		Session session = userService.derniereSessionUtilisateur(UserService.getLoggedInUser().getIdutilisateur());
		return venteService.enregistreOperationDeVente(tableModel.getLigneDeVente()
				, session.getIdsession()
				, etatVente
				, Double.parseDouble(labelMontantTotalHT.getText())
				, Double.parseDouble(labelMontantTotalTVA.getText())
				, nomClient
				, prenomClient
				, Double.parseDouble(labelMontantTotalTTC.getText()));
	}
	public void effectuerVente() {

		Client client = getClient((String) comboListeClient.getSelectedItem());
		/***
		 * Separation du nom et du prenom concaten� dans comboListeClient
		 **/
		if (verifierListeVide()) {
			/**** si la liste n'est pas totalement vide *****/
			if (verifierLesLignesDeVente()) {
				/**
				 * si toutes les lignes sont correctement renseign�es
				 ***/
				if (venteComptant.isSelected()) {
					/**
					 * Le composant vnenteComptant est selectionn�,on effectue
					 * donc la vente au comptant
					 ***/
					/***** Vente au comptant ********/
					if (enregistrerVente(VENTECOMPTANT, client.getNomClient(),client.getPrenomClient()) == VenteService.SUCCES_OPERATION) {
						afficheLigne();
						/**
						 * on reaffiche les deux lignes sur l'interface de vente
						 **/
						afficheListeClient();
						/**
						 * On selectionne le vide dans la liste des clients
						 ****/
						reinitilaiseMontants();
						/**
						 * met du vide dan les labels affichant les montants
						 **/
						System.out.println("Succes de l'operation !!");
					} else {
						System.out.println("Echec de l'operation !!");
					}
				} else {
					/******* Vente � credit ************/
					if (enregistrerVente(VENTECREDIT, client.getNomClient(),client.getPrenomClient()) == VenteService.SUCCES_OPERATION) {
						afficheLigne();
						/**
						 * on reaffiche les deux lignes sur l'interface de vente
						 **/
						afficheListeClient();
						/**
						 * On selectionne le vide dans la liste des clients
						 ****/
						reinitilaiseMontants();
						/**
						 * met du vide dan les labels affichant les montants
						 **/
						System.out.println("Succes de l'operation !!");
					} else {
						System.out.println("Echec  de l'operation !!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(nouveauClient,"Verifier les differentes lignes de vente", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(nouveauClient,"Aucune ligne de vente trouv�e", "Erreur",JOptionPane.ERROR_MESSAGE);
		}

	}
	/**** affiche la liste des produits dans la comboBox des designations ***/
	private void afficheListeProduit() {
		LinkedList<Categorie> listeCategorie = categorieService.listeCategorie();
		for (Categorie categorie : listeCategorie) {
			comboboxDesignation.addItem(categorie.getDesignation());
		}
	}
	/****
	 * Est execut�e lorsqu'une nouvelle ligne de vente est definie elle calcule
	 * le montant total de la tva,le montant total ht et ttc de la vente
	 * 
	 * @param event
	 *            : represente l'evenement de definition d'une ligne de vente
	 ********/
	@Override
	public void nouvelleLigneDeVente(VenteEvent event) {
		// TODO Auto-generated method stub
		ListIterator<LigneDeVente> listeDeVente = tableModel.getLigneDeVente().listIterator();
		LigneDeVente ligneCourante;
		double montantTotalHT = 0;
		double montantTotalTVA = 0;
		double montantTotalTTC = 0;
		while (listeDeVente.hasNext()) {
			ligneCourante = listeDeVente.next();
			if (!ligneCourante.getDesignation().equals("")) {
				montantTotalHT += Double.parseDouble(ligneCourante.getPrixGlobalHt());
				montantTotalTVA += Double.parseDouble(ligneCourante.getMontantTva());
				montantTotalTTC += Double.parseDouble(ligneCourante.getPrixGlobalTtc());
			}
		}
		labelMontantTotalHT.setText(montantTotalHT + "");
		labelMontantTotalTVA.setText(montantTotalTVA + "");
		labelMontantTotalTTC.setText(montantTotalTTC + "");
	}
	/****
	 * Permet d'obtenir le nom et le prenom du client selectionn� dans la liste
	 * des clients � partir de leur concatenation
	 * 
	 * @param nom_prenom
	 *            : represente la concatenation du nom et du prenom du client
	 ****/
	public Client getClient(String nom_prenom) {
		Client client = new Client();
		if (!nom_prenom.equals("")) {
			for (int i = 0; i < nom_prenom.length(); i++) {
				if (nom_prenom.charAt(i) == ' ') {
					client.setNomClient(nom_prenom.substring(0, i));client.setPrenomClient(nom_prenom.substring(i + 1));
					break;
				}
			}
		} else {
			client.setNomClient("DEFAULT");
			client.setPrenomClient("DEFAULT");
		}
		return client;
	}
	/****
	 * Methode affichant les deux ligne sur l'interface de vente au demarrage de
	 * l'application et a l'enregistrement d'une vente. Elle efface d'abord
	 * toutes les donn�es de la JTable,affiche ensuite les deux ligne et enfin
	 * elle selectionne le vide dans la liste des clients
	 *****/
	private void afficheLigne() {
		tableModel.getLigneDeVente().clear();
		tableModel.ajouteLigne();
		tableModel.ajouteLigne();
		comboListeClient.setSelectedItem("");
	}
	/****
	 * Verifie la validit� de toutes les lignes de vente elle s'arrete a la
	 * rencontre de la premiere erreur
	 *****/
	private boolean verifierLesLignesDeVente() {
		boolean returnValue = true;
		ListIterator<LigneDeVente> listeDeVente = tableModel.getLigneDeVente().listIterator();
		LigneDeVente ligneCourante;
		while (listeDeVente.hasNext()) {
			ligneCourante = listeDeVente.next();
			if ((ligneCourante.getDesignation().equals("") && !ligneCourante.getQuantite().equals(""))|| (!ligneCourante.getDesignation().equals("") && ligneCourante.getQuantite().equals(""))) {
				returnValue = false;
				break;
			}
		}
		return returnValue;
	}
	/**
	 * Verifie si aucune ligne de vente n'a �t� renseign�e elle s'arrete si elle
	 * rencontre une ligne de vente dont la designation ou la quantit� a ete
	 * renseign�e
	 ***/
	private boolean verifierListeVide() {
		boolean returnValue = false;
		ListIterator<LigneDeVente> listeDeVente = tableModel.getLigneDeVente().listIterator();
		LigneDeVente ligneCourante;
		while (listeDeVente.hasNext()) {
			ligneCourante = listeDeVente.next();
			if (!ligneCourante.getDesignation().equals("")|| !ligneCourante.getQuantite().equals("")) {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}
	/*** Reinitialise les labels affichant les montants **/
	public void reinitilaiseMontants() {
		labelMontantTotalHT.setText("0.0");
		labelMontantTotalTTC.setText("0.0");
		labelMontantTotalTVA.setText("0.0");
	}
	/*** methode executée lorsque l'événement ClientEvent survient ***/
	@Override
	public void ajouterClient(ClientEvent event) {
		// TODO Auto-generated method stub
		afficheListeClient();
	}
	/*** methode executée lorsque l'événement Produit survient ***/
	@Override
	public void ajouterProduit(ProduitEvent event) {
		afficheListeProduit();
	}
}
