package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.pojos.Categorie;
import com.mms.service.CategorieService;
import com.mms.tablemodels.LigneInventaire;
import com.mms.tablemodels.TableModelInventaire;

// Referenced classes of package interfaces:
//            InventaireTableModel, PanelRecherche

@SuppressWarnings("serial")
public class Inventaire extends JDialog {

	JPanel panelTable;
	JPanel panelSud;
	JButton b_fermer;
	TableModelInventaire tableModelInventaire;
	JTable tableInventaire;
	private JPanel panelCentre;
	private JMenuBar barreDeMenu;
	private JMenu menuInventaire;
	private JMenu menuAide;
	private JMenuItem itemImprimer;
	private JMenuItem itemExportPdf;
	private JMenuItem itemExportExcel;
	private CategorieService categorieService;

	public Inventaire() {
		panelTable = new JPanel();
		panelSud = new JPanel();
		b_fermer = new JButton("Fermer");
		tableModelInventaire = new TableModelInventaire();
		tableInventaire = new JTable(tableModelInventaire);
		setTitle("Inventaire");
		setLayout(new BorderLayout());
		panelCentre = new JPanel();
		add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout());
		panelCentre.add(panelTable, BorderLayout.CENTER);
		add(panelSud, "South");
		panelSud.setLayout(new FlowLayout(0));
		panelSud.add(b_fermer);
		panelTable.setLayout(new BorderLayout());
		panelTable.add(new JScrollPane(tableInventaire), BorderLayout.CENTER);
		barreDeMenu = new JMenuBar();
		menuInventaire = new JMenu("Inventaire");
		menuAide = new JMenu("Aide");
		itemImprimer = new JMenuItem("Imprimer");
		itemExportPdf = new JMenuItem("Exporter en PDF");
		itemExportExcel = new JMenuItem("Exporter en Excel");
		menuInventaire.add(itemImprimer);
		menuInventaire.add(itemExportPdf);
		menuInventaire.add(itemExportExcel);
		barreDeMenu.add(menuInventaire);
		barreDeMenu.add(menuAide);
		setJMenuBar(barreDeMenu);
		categorieService = new CategorieService();
		add(new BarreOutils(), BorderLayout.NORTH);
		setSize(new Dimension(640, 480));
		setLocationRelativeTo(null);
		afficheInventaire();
		b_fermer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		afficheInventaire();
		setModal(true);
	}

	private void afficheInventaire() {
		LinkedList<Categorie> listeCategorie = categorieService.listeCategorie();
		LigneInventaire ligneInventaire = null;
		for(Categorie categorie : listeCategorie){
			ligneInventaire = new LigneInventaire();
			ligneInventaire.setDesignation(categorie.getDesignation());
			ligneInventaire.setStockTheorique(categorie.getQuantitestock());
			if(categorie.getQuantitestock()<categorie.getQuantiteSecurite()){
				ligneInventaire.setEtat(Color.RED);
			}else{
				if(categorie.getQuantitestock()<categorie.getQuantiteIdeale()){
					ligneInventaire.setEtat(Color.ORANGE);
				}else{
					ligneInventaire.setEtat(Color.GREEN);
				}
			}
			tableModelInventaire.ajoutProduit(ligneInventaire);
		}
	}

	public static void main(String[] agrs) {
		new Inventaire().setVisible(true);
	}
}
