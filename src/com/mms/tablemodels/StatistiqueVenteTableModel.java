package com.mms.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StatistiqueVenteTableModel extends AbstractTableModel {
	class Produit {

		String designation;
		String qtVendue;

		public String getDesignation() {
			return designation;
		}

		public String getQtVendue() {
			return qtVendue;
		}

		public Produit(String designation, String qtVendue) {
			
			this.designation = designation;
			this.qtVendue = qtVendue;
		}

	}

	String header[] = { "DESIGNATION", "QUANTITE ACHETEE" };
	List<Produit> produit;

	public String getColumnName(int index) {
		return header[index];
	}

	public StatistiqueVenteTableModel() {
		produit = new ArrayList<Produit>();
	}

	public int getColumnCount() {
		return header.length;
	}

	public int getRowCount() {
		return produit.size();
	}

	public Object getValueAt(int indexLigne, int indexColonne) {
		switch (indexColonne) {
		case 0: // '\0'
			return ((Produit) produit.get(indexLigne)).getDesignation();

		case 1: // '\001'
			return ((Produit) produit.get(indexLigne)).getQtVendue();
		}
		return null;
	}

	public void ajouter(String designation, String qtVendue) {
		produit.add(new Produit(designation, qtVendue));
		fireTableRowsInserted(produit.size() - 1, produit.size() - 1);
	}
}
