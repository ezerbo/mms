package com.mms.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StatistiqueAchatTableModel extends AbstractTableModel {
	class Produit {

		String reference;
		String frequence;

		public String getReference() {
			return reference;
		}

		public String getFrequence() {
			return frequence;
		}

		public Produit(String reference, String frequence) {
			
			this.reference = reference;
			this.frequence = frequence;
		}

	}

	String header[] = { "CLIENTS", "FREQUENCE" };
	List<Produit> produit;

	public StatistiqueAchatTableModel() {
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
			return ((Produit) produit.get(indexLigne)).getReference();

		case 1: // '\001'
			return ((Produit) produit.get(indexLigne)).getFrequence();
		}
		return null;
	}

	public void ajouter(String reference, String frequence) {
		produit.add(new Produit(reference, frequence));
		fireTableRowsInserted(produit.size() - 1, produit.size() - 1);
	}
}
