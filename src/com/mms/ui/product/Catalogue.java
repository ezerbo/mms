package com.mms.ui.product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.ui.common.BarreOutils;
import com.mms.ui.tablemodels.TableModelCatalogue;
import com.mms.util.PanelRecherche;

public class Catalogue extends JDialog  {

	private JPanel panelBouton;
	private JPanel panelSud;
	private JPanel panelCentre;
	private JPanel panelToolBar;
	private PanelRecherche panelNord;
	private JMenuBar barreDeMenu;
	private JMenu menuCatalogue;
	private JMenu menuAide;
	private JMenuItem itemImprimer;
	private JMenuItem itemExportPdf;
	private JMenuItem itemExportExcel;
	private JMenuItem itemSommaireAide;
	private JMenuItem itemRapportBogue;
	private JMenuItem itemCommentaireSuggestions;
	private JButton b_ajouterProduit;
	private JButton b_fermer;
	private TableModelCatalogue tableModelCatalogue;
	private JTable tableCatalogue;

	public Catalogue() {
		setTitle("Edition du catalogue de vente");
		getContentPane().setLayout(new BorderLayout());
		panelBouton = new JPanel();
		b_ajouterProduit = new JButton("Nouveau produit");
		panelSud = new JPanel();
		b_fermer = new JButton("Fermer");
		tableCatalogue = new JTable(tableModelCatalogue);
		panelNord = new PanelRecherche(tableCatalogue);
		panelToolBar = new JPanel();
		panelToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelCentre = new JPanel();
		panelCentre.setLayout(new BorderLayout());
		panelCentre.add(new JScrollPane(tableCatalogue), "Center");
		add(panelCentre, BorderLayout.CENTER);
		panelBouton.setLayout(new FlowLayout(0));
		panelBouton.add(b_fermer);
		panelBouton.add(b_ajouterProduit);
		panelSud.add(panelBouton);
		add(panelSud, "South");
		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNord.setPreferredSize(new Dimension(340, 30));
		barreDeMenu = new JMenuBar();
		menuCatalogue = new JMenu("Catalogue");
		menuAide = new JMenu("Aide");
		itemImprimer = new JMenuItem("Imprimer");
		itemExportPdf = new JMenuItem("Exporter en PDF");
		itemExportExcel = new JMenuItem("Exporter en Excel");
		itemSommaireAide = new JMenuItem("Sommaire de l'aide");
		itemRapportBogue = new JMenuItem("Envoyer un rapport de bogue");
		itemCommentaireSuggestions = new JMenuItem(
				"Envoyer des commantaires ou des suggestions d'amelioration");
		menuCatalogue.add(itemImprimer);
		menuCatalogue.add(itemExportPdf);
		menuCatalogue.add(itemExportExcel);
		menuAide.add(itemSommaireAide);
		menuAide.add(itemRapportBogue);
		menuAide.add(itemCommentaireSuggestions);
		barreDeMenu.add(menuCatalogue);
		barreDeMenu.add(menuAide);
		setJMenuBar(barreDeMenu);
		panelToolBar.add(new BarreOutils());
		add(panelToolBar, BorderLayout.NORTH);
		panelCentre.add(panelNord, "North");
		setSize(new Dimension(640, 480));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(2);
		b_ajouterProduit.addActionListener(arg0 -> {
		});
		b_fermer.addActionListener(arg0 -> dispose());
		afficheProduit();
		setModal(true);
	}

	public void afficheProduit() {
	}

	public static void main(String[] agrs) {
		// new Catalogue();
	}
	
}
