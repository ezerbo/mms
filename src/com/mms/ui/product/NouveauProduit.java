package com.mms.ui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mms.event.ProduitEvent;
import com.mms.ui.common.TextFieldTable;
import com.mms.listener.ProduitListener;
import com.mms.service.CategorieService;

public class NouveauProduit extends JDialog {
	private JPanel panelCentre;
	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEst;
	private JPanel panelOuest;
	private JPanel panelLabel;
	private JPanel panelField;
	private JTextField f_designation;
	private TextFieldTable f_quantiteInitiale;
	private TextFieldTable f_prixUnitaireAchat;
	private TextFieldTable f_quantiteIdeale;
	private TextFieldTable f_quantiteSecurite;
	private TextFieldTable f_prixUnitaireVente;
	private JButton b_enregistrer;
	private JButton b_annuler;
	private CategorieService categorieService;
	private ArrayList<ProduitListener> produitListeners = new ArrayList<ProduitListener>();

	public synchronized void addProduitListener(ProduitListener listener) {
		if (produitListeners.contains(listener)) {
			return;
		}
		produitListeners.add(listener);
	}

	public void fireNouveauProduit() {
		if (produitListeners.size() == 0) {
			return;
		}
		ProduitEvent event = new ProduitEvent(this);
		for (ProduitListener listener : produitListeners) {
			listener.ajouterProduit(event);
		}
	}
	public NouveauProduit() {
		getContentPane().setLayout(new BorderLayout());
		setTitle("Nouveau produit");
		categorieService = new CategorieService();
		panelCentre = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		panelEst = new JPanel();
		panelOuest = new JPanel();
		panelLabel = new JPanel();
		panelField = new JPanel();
		b_enregistrer = new JButton("Enregistrer");
		b_annuler = new JButton("Annuler");
		panelCentre.setBackground(Color.WHITE);
		panelCentre.setBorder(new TitledBorder(""));
		panelLabel.setBackground(Color.WHITE);
		panelField.setBackground(Color.WHITE);
		panelLabel.setLayout(new GridLayout(6, 1, 10, 10));
		panelLabel.add(new JLabel("Designation :"));
		panelLabel.add(new JLabel("Prix unitaire d'achat :"));
		panelLabel.add(new JLabel("Prix unitaire de vente :"));
		panelLabel.add(new JLabel("Qt� initiale :"));
		panelLabel.add(new JLabel("Qt� id�ale :"));
		panelLabel.add(new JLabel("Qt� securit� :"));
		f_designation = new JTextField();
		f_designation.setPreferredSize(new Dimension(200, 8));
		f_prixUnitaireAchat = new TextFieldTable();
		f_quantiteIdeale = new TextFieldTable();
		f_quantiteInitiale = new TextFieldTable();
		f_quantiteSecurite = new TextFieldTable();
		f_prixUnitaireVente = new TextFieldTable();
		panelCentre.add(panelLabel);

		panelField.setLayout(new GridLayout(6, 1, 5, 5));
		panelField.add(f_designation);
		panelField.add(f_prixUnitaireAchat);
		panelField.add(f_prixUnitaireVente);
		panelField.add(f_quantiteInitiale);
		panelField.add(f_quantiteIdeale);
		panelField.add(f_quantiteSecurite);
		panelCentre.add(panelField);

		panelSud.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSud.add(b_annuler);
		panelSud.add(b_enregistrer);

		add(panelCentre, BorderLayout.CENTER);
		add(panelEst, BorderLayout.EAST);
		add(panelNord, BorderLayout.NORTH);
		add(panelOuest, BorderLayout.WEST);
		add(panelSud, BorderLayout.SOUTH);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		b_annuler.addActionListener(e -> dispose());
		b_enregistrer.addActionListener(e -> enregistrerProduit());
		setModal(true);
	}
	private void enregistrerProduit() {
		if (!f_designation.getText().equals("")
				&& !f_prixUnitaireAchat.getText().equals("")
				&& !f_prixUnitaireVente.getText().equals("")
				&& !f_quantiteIdeale.getText().equals("")
				&& !f_quantiteSecurite.equals("")) {
			if (Integer.parseInt(f_prixUnitaireAchat.getText()) > Integer
					.parseInt(f_prixUnitaireVente.getText())) {
				JOptionPane
						.showMessageDialog(
								null,
								"Le prix d'achat ne peut etre superieur au prix de vente",
								"Information", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (categorieService.creerCategorie(
						retourneDesignation(f_designation.getText()),
						Integer.parseInt(f_prixUnitaireAchat.getText()),
						Integer.parseInt(f_prixUnitaireVente.getText()),
						Integer.parseInt(f_quantiteInitiale.getText()),
						Integer.parseInt(f_quantiteIdeale.getText()),
						Integer.parseInt(f_quantiteSecurite.getText())) != null) {
					JOptionPane.showMessageDialog(null,
							"Produit enregistr� avec succes !!!",
							"Succes de l'operation",
							JOptionPane.INFORMATION_MESSAGE);
					fireNouveauProduit();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Ce produit existe d�j� !!!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Renseignez les champs svp !!!", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	private String retourneDesignation(String designation) {
		int index = 0;
		String chaineARetourner = "";
		while (designation.charAt(index) == ' ') {
			index++;
		}
		chaineARetourner = designation.substring(index);
		return chaineARetourner;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NouveauProduit().setVisible(true);
		// System.out.println(new
		// NouveauProduit().retourneDesignation("Teste"));
	}

}
