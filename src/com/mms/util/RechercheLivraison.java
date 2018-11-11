package com.mms.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;

import com.mms.interfaces.HistoriqueLivraison;
import com.mms.tablemodels.TableModelHistoriqueVente;

// Referenced classes of package interfaces:
//            Model, EnteteOperation, LivraisonEnregistrees, ModelListe

@SuppressWarnings("serial")
public class RechercheLivraison extends JPanel {

	public JPanel panelRecherche;
	JLabel l_search;
	JTextField f_search;
	JTable table;
	@SuppressWarnings("rawtypes")
	TableRowSorter sorter;
	TableModelHistoriqueVente model;
	HistoriqueLivraison l;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RechercheLivraison(JTable table, TableModelHistoriqueVente model, HistoriqueLivraison l) {
		panelRecherche = new JPanel();
		this.l = l;
		f_search = new JTextField(20);
		l_search = new JLabel("Rechercher un produit : ");
		f_search = new JTextField(20);
		this.table = table;
		this.model = model;
		sorter = new TableRowSorter(table.getModel());
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(50, 80));
		add(panelRecherche, "South");
		panelRecherche.setLayout(new FlowLayout(2));
		panelRecherche.add(l_search);
		panelRecherche.add(f_search);
		f_search.setToolTipText("Entrer le produit recherch\351");
		f_search.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent event) {
				construitTableTriee();
			}

		});
	}

	public void construitTableTriee() {}
}
