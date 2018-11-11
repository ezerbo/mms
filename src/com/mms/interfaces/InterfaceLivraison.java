package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.event.FournisseurEvent;
import com.mms.event.ProduitEvent;
import com.mms.listener.FournisseurListener;
import com.mms.listener.ProduitListener;
import com.mms.pojos.Fournisseur;
import com.mms.service.FournisseurService;
import com.mms.tablemodels.LivraisonTableModel;
import com.mms.util.GenereDate;
@SuppressWarnings("serial")
public class InterfaceLivraison extends JPanel
		implements
			FournisseurListener,
			ProduitListener {
	/************/
	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelComptabiliser;
	/***********/
	private JScrollPane scrollPane;
	/**********/
	private JTable table;
	private LivraisonTableModel tableModel;
	/***********/
	private JComboBox comboListeFournisseur;
	/****************/
	private JTaskPaneGroup taskPaneChoixFournisseur;
	/*****************/
	private FournisseurService fournisseurService;
	/**********/
	private JLabel l_Fournisseur;
	/**************/
	private JTaskPaneGroup taskCompte;
	/*************/
	private JPopupMenu popupMenu;
	private JMenuItem itemSupprimerLigneLivraison;
	private JMenuItem itemAjouterLigneLivraison;
	private JMenuItem itemSupprimerTouteLigneLivraison;
	/******************/
	/* private */String loginUtilisateur;
	public InterfaceLivraison(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
		setLayout(new BorderLayout());
		/*******/
		panelNord = new JPanel();
		panelSud = new JPanel();
		comboListeFournisseur = new JComboBox();
		panelComptabiliser = new JPanel();
		taskPaneChoixFournisseur = new JTaskPaneGroup();
		taskCompte = new JTaskPaneGroup();
		fournisseurService = new FournisseurService();
		l_Fournisseur = new JLabel("Liste des fournisseurs :");
		popupMenu = new JPopupMenu();
		itemAjouterLigneLivraison = new JMenuItem("Ajouter une nouvelle ligne");
		itemSupprimerLigneLivraison = new JMenuItem(
				"Supprimer la ligne selectionnée");
		itemSupprimerLigneLivraison.setEnabled(false);
		itemSupprimerTouteLigneLivraison = new JMenuItem(
				"Supprimer toutes les lignes");
		itemSupprimerTouteLigneLivraison.setEnabled(false);
		/**********/
		panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNord.add(new JLabel((new StringBuilder("Date :  ")).append(
				(new GenereDate()).getDateCourante()).toString()));
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		comboListeFournisseur.addItem("");
		tableModel = new LivraisonTableModel();
		table = new JTable(tableModel);
		table.getColumn("DESIGNATION").setCellEditor(
				new DefaultCellEditor(new ComboBoxEditable()));
		scrollPane = new JScrollPane(table);
		panelComptabiliser.setLayout(new GridLayout(2, 2, 5, 5));
		taskPaneChoixFournisseur.setTitle("Choix du fournisseur");
		taskCompte.setTitle("Le compte");
		panelSud.add(taskPaneChoixFournisseur);
		panelSud.add(taskCompte);
		panelComptabiliser.add(comboListeFournisseur);
		panelComptabiliser.add(l_Fournisseur);
		taskPaneChoixFournisseur.add(panelComptabiliser);
		popupMenu.add(itemAjouterLigneLivraison);
		popupMenu.add(itemSupprimerLigneLivraison);
		popupMenu.add(itemSupprimerTouteLigneLivraison);
		add(panelNord, BorderLayout.NORTH);
		add(panelSud, BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);
		abonnement();
		afficheLigne();
	}
	public void afficheListeFournisseur() {
		comboListeFournisseur.removeAllItems();
		comboListeFournisseur.addItem("");
		LinkedList<Fournisseur> listeFournisseur = fournisseurService
				.listeFournisseur();
		System.out.println("taille liste fournisseur : "
				+ listeFournisseur.size());
		for (Fournisseur fournisseur : listeFournisseur) {
			comboListeFournisseur.addItem(fournisseur.getNomFournisseur() + " "
					+ fournisseur.getPrenomFournisseur());
		}
	}
	private void abonnement() {
		itemAjouterLigneLivraison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tableModel.ajouterLignes();
				if (tableModel.getRowCount() > 2) {
					/**
					 * si le nombre de ligne est superieure a celui par
					 * defaut,on active les items de suppression
					 */
					itemSupprimerLigneLivraison.setEnabled(true);
					itemSupprimerTouteLigneLivraison.setEnabled(true);
				}
			}

		});
		itemSupprimerLigneLivraison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tableModel.supprimerLigne(1);
				/** le 1 est à remplacer avec l'index de l'element selectionné **/
				if (tableModel.getRowCount() == 2) {
					/**
					 * si le nombre de ligne est egale a celui par defaut,on
					 * desactive les items de suppression
					 */
					itemSupprimerLigneLivraison.setEnabled(false);
					itemSupprimerTouteLigneLivraison.setEnabled(false);
				}
			}

		});
		itemSupprimerTouteLigneLivraison
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						tableModel.supprimerLignes();
						tableModel.ajouterLignes();
						tableModel.ajouterLignes();
						itemSupprimerTouteLigneLivraison.setEnabled(false);
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
				}
			}
		});
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					/**
					 * si on double-clique sur le scrollPane contenant le
					 * tableau,on ajoute une ligne a la table des livraisons
					 */
					tableModel.ajouterLignes();
					if (tableModel.getRowCount() >= 2) {
						itemSupprimerLigneLivraison.setEnabled(true);
						itemSupprimerTouteLigneLivraison.setEnabled(true);
					}
				}
			}
		});
	}
	public Fournisseur getFournisseur(String nom_prenom) {
		Fournisseur fournisseur = new Fournisseur();
		for (int i = 0; i < nom_prenom.length(); i++) {
			if (nom_prenom.charAt(i) == ' ') {
				fournisseur.setNomFournisseur(nom_prenom.substring(0, i - 1));
				fournisseur.setPrenomFournisseur(nom_prenom.substring(i + 2,
						nom_prenom.length()));
				break;
			}
		}
		return fournisseur;
	}
	private void afficheLigne() {
		//tableModel.
		tableModel.ajouterLignes();
		tableModel.ajouterLignes();
		comboListeFournisseur.setSelectedItem("");
	}
	@Override
	public void ajouterFournisseur(FournisseurEvent event) {

	}
	@Override
	public void ajouterProduit(ProduitEvent event) {
		// TODO Auto-generated method stub

	}
}
