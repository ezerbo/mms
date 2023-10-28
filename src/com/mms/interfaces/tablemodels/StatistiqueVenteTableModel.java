package com.mms.interfaces.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StatistiqueVenteTableModel extends AbstractTableModel {
	static class Produit {

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
		produit = new ArrayList<>();
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
			return produit.get(indexLigne).getDesignation();

		case 1: // '\001'
			return produit.get(indexLigne).getQtVendue();
		}
		return null;
	}

	public void ajouter(String designation, String qtVendue) {
		produit.add(new Produit(designation, qtVendue));
		fireTableRowsInserted(produit.size() - 1, produit.size() - 1);
	}
}
