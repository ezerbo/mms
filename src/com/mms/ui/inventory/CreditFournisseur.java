package com.mms.ui.inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.mms.ui.tablemodels.CreditClientTableModel;

public class CreditFournisseur extends JDialog {

	JPanel panelCentre;
	CreditClientTableModel model;
	JTable tableCredit;
	JPanel panelBouton;
	JButton b_fermer;
	ResultSet resultat;
	private JPanel panelNord;
	private JPanel panelIcon;
	private JPanel panelTitle;
	private JPanel panelTable;
	private JPanel panelListeCredit;
	private JPanel panelListeProduit;
	private JSplitPane splitPane;
	private JSplitPane splitPaneVertical;
	private JLabel label1;
	private JLabel label2;

	public CreditFournisseur() {
		panelCentre = new JPanel();
		panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		panelListeCredit = new JPanel();
		model = new CreditClientTableModel();
		tableCredit = new JTable(model);
		panelBouton = new JPanel();
		b_fermer = new JButton("Fermer");
		resultat = null;
		setLayout(new BorderLayout());
		setTitle("Liste des credits octroy\351s au clients");
		panelCentre.setLayout(new BorderLayout());
		panelBouton.setLayout(new FlowLayout(0));
		panelBouton.add(b_fermer);
		label1 = new JLabel("    Credits fournisseur");
		label2 = new JLabel(
				"          fenetre donnant la liste des fournisseurs ");
		panelListeProduit = new JPanel();
		panelListeProduit.setLayout(new BorderLayout());
		panelNord = new JPanel();
		panelTitle = new JPanel();
		panelIcon = new JPanel();
		label1.setFont(new Font("SansSerif", 2, 18));
		label2.setFont(new Font("SansSerif", 2, 12));
		panelIcon.setLayout(new FlowLayout(2));
		panelIcon.setBackground(Color.WHITE);
		panelIcon
				.add(new JLabel(new ImageIcon("ressources/images/credit.jpg")));
		panelTitle.setLayout(new FlowLayout(0));
		panelTitle.setBackground(Color.WHITE);
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(label2, "Center");
		panelTitle.add(label1, "North");
		panelNord.setBackground(Color.WHITE);
		panelNord.setLayout(new GridLayout(1, 2));
		panelNord.add(panelTitle);
		panelNord.add(panelIcon);
		add(panelNord, "North");
		add(panelBouton, "South");
		panelTable.add(new JScrollPane(tableCredit), "Center");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelCentre, panelListeProduit);
		add(splitPane, "Center");
		splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				panelTable, panelListeCredit);
		splitPaneVertical.setDividerLocation(250);
		panelCentre.add(splitPaneVertical);
		setSize(new Dimension(740, 520));
		setLocationRelativeTo(null);
		afficheCreditFournisseur();
		b_fermer.addActionListener(e -> setVisible(false));
		tableCredit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
			}

		});
		setModal(true);
		setVisible(true);
	}

	public void afficheCreditFournisseur() {
	}

	public static void main(String[] args) {
		new CreditFournisseur();
	}
}
