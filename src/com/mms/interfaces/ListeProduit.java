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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mms.event.ProduitEvent;
import com.mms.listener.ProduitListener;
import com.mms.pojos.Categorie;
import com.mms.service.CategorieService;
import com.mms.tablemodels.TableModelNouveauProduit;

// Referenced classes of package interfaces:
//            CatalogueTableModel

@SuppressWarnings("serial")
public class ListeProduit extends JDialog implements ProduitListener {

	private JPanel panelNorth;
	private JPanel panelBouton;
	private JPanel panelCentre;
	private JPanel panelIcon;
	private JPanel panelTitle;
	private JButton b_ajouter;
	private JButton b_fermer;
	private JButton b_supprimer;
	private JButton b_modifier;
	private JTable tableProduit;
	private JLabel labelGrandTitre;
	private JLabel labelPetitTitre;
	private CategorieService categorieService;
	private TableModelNouveauProduit tableModelNouveauProduit;
	private NouveauProduit nouveauProduit;
	private JPopupMenu popupMenu;
	private JMenuItem itemSupprimer;
	private JMenuItem itemModifier;

	private static int COLONNEDESIGNATION = 0;
	private static int COLONNEQUANTITEIDEALE = 4;
	private static int COLONNEQUANTITESECURITE = 5;
	private static int COLONNEPRIXVENTE = 2;
	private static int COLONNEPRIXACHAT = 1;

	public ListeProduit(NouveauProduit nouveauProduit) {
		this.nouveauProduit = nouveauProduit;
		setTitle("Catalogue des produits");
		getContentPane();
		setLayout(new BorderLayout());
		panelNorth = new JPanel();
		panelBouton = new JPanel();
		panelCentre = new JPanel();
		panelIcon = new JPanel();
		panelTitle = new JPanel();
		b_ajouter = new JButton("Ajouter");
		b_fermer = new JButton("Fermer");
		b_supprimer = new JButton("Supprimer");
		b_modifier = new JButton("Modifier");
		popupMenu = new JPopupMenu();
		itemModifier = new JMenuItem("Modifier");
		itemSupprimer = new JMenuItem("Supprimer");
		popupMenu.add(itemModifier);
		popupMenu.addSeparator();
		popupMenu.add(itemSupprimer);
		b_modifier.setEnabled(false);
		b_supprimer.setEnabled(false);
		categorieService = new CategorieService();
		panelCentre.setLayout(new BorderLayout());

		panelNorth.setLayout(new GridLayout(1, 2));
		labelGrandTitre = new JLabel(" Enregistrement d'un nouveau produit");
		labelGrandTitre.setFont(new Font("Times new Roman", Font.ITALIC, 20));
		labelPetitTitre = new JLabel("fenetre d'ajout de nouveau produit");
		labelPetitTitre.setFont(new Font("Times new Roman", Font.ITALIC, 14));
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(labelGrandTitre, BorderLayout.NORTH);
		panelTitle.add(labelPetitTitre);
		panelTitle.setBackground(Color.WHITE);
		panelIcon.add(new JLabel(new ImageIcon(
				"ressources/images/acteurExterne.jpg")));
		panelIcon.setBackground(Color.WHITE);
		panelIcon.setLayout(new FlowLayout(2));
		panelNorth.setBackground(Color.WHITE);
		panelNorth.add(panelTitle);
		panelNorth.add(panelIcon);

		add(panelNorth, BorderLayout.NORTH);
		add(panelBouton, BorderLayout.SOUTH);
		panelBouton.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBouton.add(b_fermer);
		panelBouton.add(b_modifier);
		panelBouton.add(b_supprimer);
		panelBouton.add(b_ajouter);
		tableModelNouveauProduit = new TableModelNouveauProduit();
		tableProduit = new JTable(tableModelNouveauProduit);
		panelCentre.add(new JScrollPane(tableProduit), BorderLayout.CENTER);
		add(panelCentre, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(640, 480));
		abonne(this);
		afficheListeProduit();
		setLocationRelativeTo(null);
		setModal(true);
		// setVisible(true);
	}

	public void abonne(JDialog dialog) {
		itemModifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				modifierProduit();
			}
		});
		itemSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Suppression !!!",
						"Succes de l'operation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		tableProduit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void mouseReleased(MouseEvent event) {
				showIfPopupTrigger(event);
			}

			public void showIfPopupTrigger(MouseEvent event) {
				if (event.isPopupTrigger()) {
					for (int i = 0; i < tableModelNouveauProduit.getRowCount(); i++) {
						if (tableProduit.isRowSelected(i)) {
							if (i == (event.getY() - 2) / 15) {
								popupMenu.show(tableProduit, event.getX(),
										event.getY());
								break;
							}
						}
					}

				}
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				b_supprimer.setEnabled(true);
				b_modifier.setEnabled(true);
			}
		});
		b_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modifierProduit();
			}

		});

		b_supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Suppression !!!");
			}
		});
		b_fermer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
		b_ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				nouveauProduit.setVisible(true);
			}

		});

	}
	public ListeProduit getThis() {
		return this;
	}
	public static void main(String[] args) {
		// new ListeProduit().setVisible(true);
	}
	/*** affiche dans la table la liste des produits */
	private void afficheListeProduit() {
		LinkedList<Categorie> listeCatgeorie = categorieService
				.listeCategorie();
		for (Categorie categorie : listeCatgeorie) {
			tableModelNouveauProduit.ajouter(categorie);
		}
	}
	public void modifierProduit(){

		// TODO Auto-generated method stub
		Categorie categorie = new Categorie();
		categorie.setDesignation((String) tableProduit.getValueAt(
				tableProduit.getSelectedRow(), COLONNEDESIGNATION));

		categorie.setPrixunitaireachat((Integer) tableProduit
				.getValueAt(tableProduit.getSelectedRow(),
						COLONNEPRIXACHAT));

		categorie.setPrixunitairevente((Integer) tableProduit
				.getValueAt(tableProduit.getSelectedRow(),
						COLONNEPRIXVENTE));

		categorie.setQuantiteIdeale((Integer) tableProduit.getValueAt(
				tableProduit.getSelectedRow(), COLONNEQUANTITEIDEALE));

		categorie.setQuantiteSecurite((Integer) tableProduit
				.getValueAt(tableProduit.getSelectedRow(),
						COLONNEQUANTITESECURITE));

		ModifierProduit modifierProduit = new ModifierProduit(categorie);
		modifierProduit.addProduitListener(getThis());
		modifierProduit.setVisible(true);

	
	}
	@Override
	public void ajouterProduit(ProduitEvent event) {
		// TODO Auto-generated method stub
		tableModelNouveauProduit.supprimerToutesLesLignes();
		afficheListeProduit();
	}

}
