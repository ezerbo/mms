package com.mms.interfaces.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelInventaire extends AbstractTableModel {

	private String header[] = { "DESIGNATION", "STOCK THEORIQUE","ETAT"};
	private List<LigneInventaire> produit;

	public String getColumnName(int index) {
		return header[index];
	}

	public boolean isCellEditable(int l, int c) {
		return c == 1;
	}

	public void setValueAt(Object value, int ligne, int colonne) {
	}

	public TableModelInventaire() {
		produit = new ArrayList<LigneInventaire>();
	}

	public int getColumnCount() {
		return header.length;
	}

	public int getRowCount() {
		return produit.size();
	}

	public Object getValueAt(int indexLigne, int indexColonne) {
		switch (indexColonne) {
		case 0: 
			return  produit.get(indexLigne).getDesignation();
		case 1: 
			return  produit.get(indexLigne).getStockTheorique();
		case 2:
			return "";
		default:
			return null;
		}
	}

	public void ajoutProduit(LigneInventaire ligneInventaire) {
		produit.add(ligneInventaire);
		fireTableRowsInserted(produit.size() - 1, produit.size() - 1);
	}

	/**
	 * @return the produit
	 */
	public List<LigneInventaire> getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(List<LigneInventaire> produit) {
		this.produit = produit;
	}
}
