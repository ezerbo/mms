package com.mms.ui.inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.mms.ui.tablemodels.TableModelBilanCredit;
import com.mms.ui.tablemodels.TableModelHistoriqueVente;
import com.mms.ui.tablemodels.TableModelHistoriqueOperation;
import com.mms.util.RechercheLivraison;

public class HistoriqueLivraison extends JDialog {

	private JButton b_fermer;
	JButton b_annulerVente;
	private JPanel panelBouton;
	private JPanel panelTitle;
	private JLabel l_title;
	private JPanel panelCentre;
	public JPanel panelTable;
	public JPanel panelListeProduit;
	private RechercheLivraison panelRecherche;
	private TableModelHistoriqueVente model;
	private JTable table;
	JTable tableListeLivraison;
	TableModelHistoriqueOperation modelListeLivraison;
	private JSplitPane splitPane;
	private JTable tableBilan;
	private JPanel panelBilanCentre;
	private TableModelBilanCredit bilan;

	public HistoriqueLivraison() {
		getContentPane().setLayout(new BorderLayout());
		panelTable = new JPanel();
		panelBilanCentre = new JPanel();
		panelBilanCentre.setLayout(new BorderLayout());
		panelCentre = new JPanel();
		panelListeProduit = new JPanel();
		panelTitle = new JPanel();
		panelTitle.setBackground(Color.WHITE);
		panelBouton = new JPanel();
		b_fermer = new JButton("Fermer");
		model = new TableModelHistoriqueVente();
		table = new JTable(model);
		panelRecherche = new RechercheLivraison(table, model, this);
		panelRecherche.setBackground(Color.WHITE);
		panelRecherche.panelRecherche.setBackground(Color.WHITE);
		panelRecherche.add(panelTitle, "North");
		b_annulerVente = new JButton("Annuler la livraison");
		bilan = new TableModelBilanCredit();
		tableBilan = new JTable(bilan);
		panelBilanCentre.add(new JScrollPane(tableBilan), "Center");
		l_title = new JLabel("<html>Livraisons r\351alis\351es<br>Liste de toutes les livraisons enregistrés</html>");
		add(panelRecherche, "North");
		add(panelBouton, "South");
		panelBouton.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelBouton.add(b_fermer);
		panelBouton.add(b_annulerVente);
		l_title.setFont(new Font("SansSerif", Font.ITALIC, 18));
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTitle.add(l_title);

		panelTable.setLayout(new BorderLayout());
		panelTable.add(new JScrollPane(table));
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTable, panelListeProduit);
		setTitle("Liste des Livraison");
		panelCentre.setLayout(new BorderLayout());
		panelCentre.add(splitPane, "Center");
		panelCentre.add(panelBilanCentre, "East");
		add(panelCentre, "Center");
		abonne();
		afficheCredit();
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	public void abonne() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
			}
		});
		b_fermer.addActionListener(e -> setVisible(false));
		b_annulerVente.setEnabled(false);
		b_annulerVente.addActionListener(e -> {});
	}

	public void afficheCredit() {
	}

	public static void main(String[] args) {
		new HistoriqueLivraison();
	}

}
