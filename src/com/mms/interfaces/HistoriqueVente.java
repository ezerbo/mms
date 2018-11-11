package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.pojos.Categorie;
import com.mms.pojos.Client;
import com.mms.pojos.Lignedevente;
import com.mms.service.CategorieService;
import com.mms.service.VenteService;
import com.mms.tablemodels.TableModelHistoriqueVente;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class HistoriqueVente extends JDialog {

	private JButton boutonValider;
	private JButton boutonRechercher;
	private JButton boutonEffectuerRecherche;
	/***********/
	private JPanel panelSud;
	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelEst;
	private JPanel panelMontantHT;
	private JPanel panelMontantTVA;
	private JPanel panelMontantTTC;
	public JPanel panelTable;
	private JPanel panelComposant;
	private JPanel panelDateDebut;
	private JPanel panelChoixDate;
	private JPanel panelDateFin;
	private JPanel panelChoixDesignation;
	private JPanel panelChoixClient;
	private JPanel panelNumeroVente;
	private JDateChooser choixDateDebut;
	private JDateChooser choixDateFin;
	/**********/
	private JComboBox comboListeDesignation;
	private JComboBox comboListeClient;
	/********/
	private JLabel labelTitre;
	private JLabel labelLibelleMontantHT;
	private JLabel labelMontantHT;
	private JLabel labelLibelleMontantTVA;
	private JLabel labelMontantTVA;
	private JLabel labelLibelleMontantTTC;
	private JLabel labelMontantTTC;
	/*********/
	private TableModelHistoriqueVente tableModel;
	private JTable table;
	/********/
	private VenteService venteService;
	private CategorieService categorieService;
	/**************/
	private JLabel labelTousLesProduits;
	private JLabel labelTousLesClients;
	/**********/
	private Box boxtaskPane;
	/***************/
	private JTaskPaneGroup taskPaneCompte;
	private JTaskPaneGroup taskPaneRecherche;
	/****************/
	private TextFieldTable f_numeroVente;
	/************/
	private JPopupMenu popupMenu;
	private JMenuItem itemTouteLaVente;
	private JMenuItem itemImprimerFacture;
	private JMenuItem itemSupprimer;
	private JMenuItem itemModifier;

	public HistoriqueVente() {
		getContentPane().setLayout(new BorderLayout());
		panelTable = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		panelCentre = new JPanel();
		panelEst = new JPanel();
		panelComposant = new JPanel();
		panelDateDebut = new JPanel();
		panelDateFin = new JPanel();
		panelChoixClient = new JPanel();
		panelChoixDesignation = new JPanel();
		panelChoixDate = new JPanel();
		panelMontantHT = new JPanel();
		panelMontantTTC = new JPanel();
		panelMontantTVA = new JPanel();
		panelNumeroVente = new JPanel();
		choixDateDebut = new JDateChooser();
		choixDateFin = new JDateChooser();
		comboListeClient = new JComboBox();
		comboListeDesignation = new JComboBox();
		tableModel = new TableModelHistoriqueVente();
		table = new JTable(tableModel);
		boutonValider = new JButton("Valider");
		boutonRechercher = new JButton("Rechercher");
		boutonEffectuerRecherche = new JButton();
		boutonEffectuerRecherche.setIcon(new ImageIcon("ressources/images/rechercher.jpg"));
		boutonEffectuerRecherche.setPreferredSize(new Dimension(50,20));
		f_numeroVente = new TextFieldTable();
		labelTitre = new JLabel("<html>Ventes realisées<br>Liste de toutes le vente enregistrées </html>");
		venteService = new VenteService();
		categorieService = new CategorieService();
		labelTousLesClients = new JLabel("Tous les clients");
		labelTousLesProduits = new JLabel("Tous les produits");
		labelLibelleMontantHT = new JLabel("Montant HT : ");
		labelLibelleMontantTVA = new JLabel("Montant TVA : ");
		labelLibelleMontantTTC = new JLabel("Montant TTC : ");
		labelMontantHT = new JLabel("0.0");
		labelMontantTTC = new JLabel("0.0");
		labelMontantTVA = new JLabel("0.0");
		/***************/
		add(panelSud, BorderLayout.SOUTH);
		panelSud.setLayout(new GridLayout(1,1));
		panelSud.add(new CopyRight());
		labelTitre.setFont(new Font("Times new Roman", 2, 22));
		panelNord.setBackground(Color.WHITE);
		panelNord.setLayout(new BorderLayout());
		panelNord.add(labelTitre, BorderLayout.CENTER);
		panelTable.setLayout(new BorderLayout());
		
		panelTable.add(new JScrollPane(table));
		panelCentre.setLayout(new BorderLayout());
		setTitle("Liste des ventes");
		panelCentre.add(panelTable, BorderLayout.CENTER);
		panelComposant.setLayout(new FlowLayout(FlowLayout.LEFT));

		panelDateDebut.add(new JLabel("Début : "));
		choixDateDebut.setDateFormatString("d-MM-yyyy");
		choixDateDebut.setDate(new Date());
		choixDateDebut.setPreferredSize(new Dimension(100, 20));
		panelDateDebut.add(choixDateDebut);
		panelChoixDate.add(panelDateDebut);

		panelDateFin.add(new JLabel("Fin : "));
		choixDateFin.setDateFormatString("d-MM-yyyy");
		choixDateFin.setDate(new Date());
		choixDateFin.setPreferredSize(new Dimension(100, 20));
		panelDateFin.add(choixDateFin);
		panelChoixDate.add(panelDateFin);
		boutonValider.setPreferredSize(new Dimension(100,20));
		panelChoixDate.add(boutonValider);
		panelComposant.add(panelChoixDate);

		panelChoixDesignation.add(new JLabel("Produit : "));
		comboListeDesignation.setPreferredSize(new Dimension(130, 20));
		comboListeDesignation.addItem(labelTousLesProduits.getText());
		panelChoixDesignation.add(comboListeDesignation);
		panelComposant.add(panelChoixDesignation);

		panelChoixClient.add(new JLabel("Client : "));
		comboListeClient.setPreferredSize(new Dimension(130, 20));
		comboListeClient.addItem(labelTousLesClients.getText());
		panelChoixClient.add(comboListeClient);
		panelComposant.add(panelChoixClient);
		panelComposant.setBorder(BorderFactory.createEtchedBorder());
		panelEst.setBorder(BorderFactory.createEtchedBorder());
		panelNord.add(panelComposant, BorderLayout.SOUTH);
		
		boxtaskPane = Box.createVerticalBox();
		taskPaneCompte = new JTaskPaneGroup();
		taskPaneCompte.setTitle("Compte");
		taskPaneCompte.setLayout(new GridLayout(3,1));
		panelMontantHT.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantHT.add(labelLibelleMontantHT);
		panelMontantHT.add(labelMontantHT);
		taskPaneCompte.add(panelMontantHT);
		
		panelMontantTVA.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantTVA.add(labelLibelleMontantTVA);
		panelMontantTVA.add(labelMontantTVA);
		taskPaneCompte.add(panelMontantTVA);
		
		panelMontantTTC.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMontantTTC.add(labelLibelleMontantTTC);
		panelMontantTTC.add(labelMontantTTC);
		taskPaneCompte.add(panelMontantTTC);
		
		taskPaneCompte.getContentPane().setPreferredSize(new Dimension(200,100));
		boxtaskPane.add(taskPaneCompte);
		
		boxtaskPane.add(new JLabel(" "));
		taskPaneRecherche = new JTaskPaneGroup();
		taskPaneRecherche.setTitle("Recherche");
		taskPaneRecherche.getContentPane().setPreferredSize(new Dimension(200,100));
		panelNumeroVente.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNumeroVente.add(new JLabel("Num. vente : "));
		f_numeroVente.setColumns(8);
		panelNumeroVente.add(f_numeroVente);
		taskPaneRecherche.setLayout(new BorderLayout());
		taskPaneRecherche.add(panelNumeroVente,BorderLayout.CENTER);
		boutonRechercher.setPreferredSize(new Dimension(100,20));
		taskPaneRecherche.add(boutonRechercher,BorderLayout.SOUTH);
		taskPaneRecherche.setVisible(false);
		boxtaskPane.add(taskPaneRecherche);
		panelEst.add(boxtaskPane);
		panelComposant.add(boutonEffectuerRecherche);
		
		popupMenu = new JPopupMenu();
		itemImprimerFacture = new JMenuItem("Imprimer la facture");
		itemModifier = new JMenuItem("Modifier la vente");
		itemSupprimer = new JMenuItem("Supprimer la vente");
		itemTouteLaVente = new JMenuItem("Afficher toute la vente");
		popupMenu.add(itemTouteLaVente);
		popupMenu.add(itemModifier);
		popupMenu.add(itemSupprimer);
		
		add(panelCentre, BorderLayout.CENTER);
		add(panelNord, BorderLayout.NORTH);
		add(panelEst,BorderLayout.EAST);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		abonne();
		afficheHistoriqueVente(choixDateDebut.getDate(),choixDateFin.getDate());
		afficheListeProduit();
		afficheListeClient();
		setSize(new Dimension(900, 580));
		setLocationRelativeTo(null);
		setModal(true);
	}
	private void afficheListeProduit() {
		LinkedList<Categorie> listeProduit = categorieService.listeCategorie();
		for (Categorie categorie : listeProduit) {
			comboListeDesignation.addItem(categorie.getDesignation());
		}
	}
	private void afficheListeClient() {
		LinkedList<Client> listeClient = venteService.listeClient();
		for (Client client : listeClient) {
			comboListeClient.addItem(client.getNomClient() + " "+ client.getPrenomClient());
		}
	}
	private void venteParametree(String designation) {
		tableModel.supprimerToutesLesLignes();
		LinkedList<Lignedevente> ligneDeVente = venteService.retourneHistoriqueVente(choixDateDebut.getDate(),choixDateFin.getDate());
		if(ligneDeVente!=null){
			for (int i = 0; i < ligneDeVente.size(); i++) {
				Lignedevente ligneCourante = ligneDeVente.get(i);
				if (ligneCourante.getCategorie().getDesignation().equals(designation)) {
					tableModel.getListeVente().add(ligneCourante);
				}
			}
		}
	}
	private void abonne() {
		itemTouteLaVente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Lignedevente ligneSelectionnee = tableModel.getListeVente().get(table.getSelectedRow());
				effectuerRecherche(ligneSelectionnee.getVente().getNumerovente());
				popupMenu.add(itemImprimerFacture);
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
					for (int i = 0; i < tableModel.getRowCount(); i++) {
						if (table.isRowSelected(i)) {
							if (i == (event.getY() - 2) / 15) {
								popupMenu.show(table, event.getX(),
										event.getY());
								break;
							}
						}
					}
				}
			}
		});
		boutonRechercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!f_numeroVente.getText().equals(""))
				effectuerRecherche(Integer.parseInt(f_numeroVente.getText()));
				popupMenu.remove(itemImprimerFacture);	
			}
		});
		f_numeroVente.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(!f_numeroVente.getText().equals(""))
				effectuerRecherche(Integer.parseInt(f_numeroVente.getText()));
				popupMenu.remove(itemImprimerFacture);
				}
			}
		});
		boutonEffectuerRecherche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f_numeroVente.setText("");
				if(taskPaneRecherche.isVisible())
					taskPaneRecherche.setVisible(false);
				else{
					taskPaneRecherche.setVisible(true);
					f_numeroVente.requestFocus();
				}
				
			}
		});
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tableModel.supprimerToutesLesLignes();
				afficheHistoriqueVente(choixDateDebut.getDate(), choixDateFin.getDate());
				popupMenu.remove(itemImprimerFacture);
			}
		});
		comboListeClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		comboListeDesignation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!comboListeDesignation.getSelectedItem().equals(labelTousLesProduits.getText())) {
					venteParametree((String) comboListeDesignation.getSelectedItem());
				} else {
					LinkedList<Lignedevente> ligneDeVente = venteService.retourneHistoriqueVente(choixDateDebut.getDate(),choixDateFin.getDate());
					if(ligneDeVente!=null){
						tableModel.supprimerToutesLesLignes();
						for (int i = 0; i < ligneDeVente.size(); i++) {
							Lignedevente ligneCourante = ligneDeVente.get(i);
							tableModel.getListeVente().add(ligneCourante);
						}
					
					}
					
				}
			}
		});
	}
	private void afficheHistoriqueVente(Date dateDebut,Date dateFin) {
		LinkedList<Lignedevente> listeVente = venteService.retourneHistoriqueVente(dateDebut,dateFin);
		double montantHT = 0.;
		double montantTVA = 0.;
		double montantTTC = 0.;
		for (Lignedevente ligneDeVente : listeVente) {
			tableModel.ajouterLigne(ligneDeVente);
			montantHT += ligneDeVente.getMontantligneventeht();
			montantTVA += ligneDeVente.getMontantLigneVenteTva();
			montantTTC += ligneDeVente.getMontantligneventettc();
		}
		if(montantHT!=0.){
			labelMontantHT.setText(montantHT+"");
			labelMontantTVA.setText(montantTVA+"");
			labelMontantTTC.setText(montantTTC+"");
		}else{
			labelMontantHT.setText("0.0");
			labelMontantTVA.setText("0.0");
			labelMontantTTC.setText("0.0");
		}
	}
	private void effectuerRecherche(int numeroVente){
			LinkedList<Lignedevente> vente = venteService.retournerVente(numeroVente);
			try{
				if(vente.get(0)!=null){
					System.out.println("teste");
					tableModel.supprimerToutesLesLignes();
					for(Lignedevente ligneCourante : vente){
						tableModel.ajouterLigne(ligneCourante);
					}
					
				}
			}catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Aucune vente trouvée !!!","Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
	}
	public static void main(String args[]) {
		new HistoriqueVente().setVisible(true);
	}
}
