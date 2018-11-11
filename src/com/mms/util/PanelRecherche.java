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
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class PanelRecherche extends JPanel {

	public JPanel panelRecherche;
	JLabel l_search;
	JTextField f_search;
	JTable table;
	@SuppressWarnings("rawtypes")
	TableRowSorter sorter;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelRecherche(JTable table) {
		panelRecherche = new JPanel();
		f_search = new JTextField(20);
		l_search = new JLabel("Recherche :");
		f_search = new JTextField(20);
		this.table = table;
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
				filtre();
			}

		});
	}

	@SuppressWarnings("unchecked")
	public void filtre() {
		sorter.setRowFilter(RowFilter.regexFilter(f_search.getText(),
				new int[] { 0, 1, 2, 3, 4 }));
		table.setRowSorter(sorter);
	}
}
