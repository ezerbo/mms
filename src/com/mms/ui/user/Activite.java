package com.mms.ui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.mms.ui.common.CopyRight;
import com.mms.domain.Categorie;
import com.mms.domain.Lignedevente;
import com.mms.service.CategorieService;
import com.mms.service.UserService;
import com.mms.service.SalesService;
import com.mms.ui.tablemodels.TableModelActivites;
import com.toedter.calendar.JDateChooser;

public class Activite extends JDialog {

	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEntree;
	private JPanel panelSortie;
	private JPanel panelIcon;
	private JPanel panelLabel;
	private JPanel panelComposant;
	private JPanel panelChoixDate;
	private JPanel panelTitre;
	private JPanel panelDateDebut;
	private JPanel panelDateFin;
	private JPanel panelDesignation;
	private JPanel panelBouton;
	/***********************/
	private JLabel labelNord;
	private JLabel labelTousLesProduits;
	/**************/
	private JButton b_valider;
	/*****************/
	private JDateChooser dateDebut;
	private JDateChooser dateFin;
	/***************/
	private JComboBox comboDesignation;
	/*************/
	private TableModelActivites tableModelVente;
	private TableModelActivites tableModelLivraison;
	private JTable tableSortie;
	private JTable tableEntree;
	private JTabbedPane tabbedPane;
	private SalesService salesService;
	private CategorieService categorieService;

	public Activite() {
		setTitle("Activit�s de l'utilisateur");
		setLayout(new BorderLayout());
		salesService = new SalesService();
		categorieService = new CategorieService();
		panelNord = new JPanel();
		panelSud = new JPanel();
		panelSortie = new JPanel();
		panelEntree = new JPanel();
		panelChoixDate = new JPanel();
		panelComposant = new JPanel();
		panelTitre = new JPanel();
		b_valider = new JButton("Valider");
		panelDateDebut = new JPanel();
		panelDateFin = new JPanel();
		dateDebut = new JDateChooser();
		dateFin = new JDateChooser();
		tabbedPane = new JTabbedPane(4);
		panelBouton = new JPanel();
		tableModelVente = new TableModelActivites();
		tableModelLivraison = new TableModelActivites();
		tableSortie = new JTable(tableModelVente);
		tableEntree = new JTable(tableModelLivraison);
		labelTousLesProduits = new JLabel("Tous les produits");
		panelLabel = new JPanel();
		panelIcon = new JPanel();
		panelDesignation = new JPanel();
		comboDesignation = new JComboBox<>();
		panelLabel.setLayout(new BorderLayout());
		labelNord = new JLabel("   Activit\351s de l'utilisateur selectionn\351");
		panelLabel.add(labelNord, "North");
		panelIcon = new JPanel();
		panelIcon.setLayout(new FlowLayout(2));
		panelIcon.add(new JLabel(new ImageIcon("ressources/images/userActivities.jpg")));
		tabbedPane.addTab("Ventes r�alis�es", panelSortie);
		tabbedPane.addTab("Livraisons enregistr�es", panelEntree);
		panelSortie.setLayout(new BorderLayout());
		panelSortie.add(new JScrollPane(tableSortie), BorderLayout.CENTER);
		panelEntree.setLayout(new BorderLayout());
		panelEntree.add(new JScrollPane(tableEntree), BorderLayout.CENTER);
		labelNord.setFont(new Font("Times new Roman", 2, 24));
		panelNord.setLayout(new BorderLayout());
		panelNord.setBackground(Color.WHITE);
		panelLabel.setBackground(Color.WHITE);
		panelIcon.setBackground(Color.WHITE);
		panelNord.add(panelComposant,BorderLayout.SOUTH);
		panelTitre.setLayout(new GridLayout(1,2,0,0));
		panelTitre.add(panelLabel);
		panelTitre.add(panelIcon);
		panelNord.add(panelTitre,BorderLayout.CENTER);
		panelComposant.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelComposant.setBorder(BorderFactory.createEtchedBorder());
		panelDateDebut.add(new JLabel("Debut : "));
		dateDebut.setDateFormatString("d-MM-yyyy");
		dateDebut.setDate(new Date());
		dateDebut.setPreferredSize(new Dimension(100, 20));
		panelDateDebut.add(dateDebut);
		panelChoixDate.add(panelDateDebut);
		
		panelDateFin.add(new JLabel("Fin : "));
		dateFin.setDateFormatString("d-MM-yyyy");
		dateFin.setDate(new Date());
		dateFin.setPreferredSize(new Dimension(100, 20));
		panelDateFin.add(dateFin);
		panelChoixDate.add(panelDateFin);
		
		b_valider.setPreferredSize(new Dimension(100,20));
		panelChoixDate.add(b_valider);
		panelComposant.add(panelChoixDate);
		
		panelDesignation.add(new JLabel("Produit : "));
		panelDesignation.add(comboDesignation);
		comboDesignation.setPreferredSize(new Dimension(150,20));
		panelComposant.add(panelDesignation);
		
		panelBouton.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(panelNord, BorderLayout.NORTH);
		panelSud.setLayout(new GridLayout(1,1));
		panelSud.add(new CopyRight());
		add(panelSud,BorderLayout.SOUTH);
		add(tabbedPane, BorderLayout.CENTER);
		
		abonne();
		afficheDesignation();
		afficherVente();
		setSize(new Dimension(840, 580));
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void afficheDesignation(){
		comboDesignation.addItem(labelTousLesProduits.getText());
		LinkedList<Categorie> listeCategorie = categorieService.listeCategorie();
		for(Categorie categorie:listeCategorie){
			comboDesignation.addItem(categorie.getDesignation());
		}
	}
	private void venteParametree(String designation) {
		tableModelVente.supprimerToutesLesLignes();
		LinkedList<Lignedevente> ligneDeVente = salesService.statistiqueVente(dateDebut.getDate(), dateFin.getDate(),
				UserService.getLoggedInUser().getIdutilisateur());
		if(ligneDeVente!=null){
			for (int i = 0; i < ligneDeVente.size(); i++) {
				Lignedevente ligneCourante = ligneDeVente.get(i);
				if (ligneCourante.getCategorie().getDesignation().equals(designation)) {
					tableModelVente.getListeVente().add(ligneCourante);
				}
			}
		}
	}
	private void abonne() {
		comboDesignation.addActionListener(arg0 -> {
			if (!comboDesignation.getSelectedItem().equals(labelTousLesProduits.getText())) {
				venteParametree((String) comboDesignation.getSelectedItem());
			} else {
				LinkedList<Lignedevente> ligneDeVente = salesService.statistiqueVente(dateDebut.getDate()
						, dateFin.getDate(), UserService.getLoggedInUser().getIdutilisateur());
				if(ligneDeVente!=null){
					tableModelVente.supprimerToutesLesLignes();
					for (Lignedevente ligneCourante : ligneDeVente) {
						tableModelVente.getListeVente().add(ligneCourante);
					}

				}

			}
		});
		tabbedPane.registerKeyboardAction(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,InputEvent.ALT_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		b_valider.addActionListener(arg0 -> afficherVente());
	}
	public void afficherVente() {
		tableModelVente.supprimerToutesLesLignes();
		try{
			LinkedList<Lignedevente> ligneDeVente = salesService.statistiqueVente(dateDebut.getDate()
					, dateFin.getDate(), UserService.getLoggedInUser().getIdutilisateur());
			for(Lignedevente ligneCourante : ligneDeVente ){
				tableModelVente.ajouter(ligneCourante);
			}
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "L'une au moins des dates est non valide", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public static void main(String[] args) {
		new Activite();
	}

}
