package com.mms.tablemodels;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mms.pojos.Produit;

@SuppressWarnings("serial")
public class TableModelCatalogue extends AbstractTableModel {

	public final List<Produit> produit;
	private String entete[] = { "DESIGNATION", "PRIX UNITAIRE",
			"PRIX DE VENTE UNITAIRE" };

	public TableModelCatalogue() {
		produit = new LinkedList<Produit>();
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getRowCount() {
		return produit.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {}
		return null;
	}

	public boolean isCellEditable(int indexLigne, int indexColonne) {
		return true;
	}

	public void setValueAt(Object valeur, int indexLigne, int indexColonne) {
	}

	public void ajouterProduit(String designation, Integer prixAchat,
			Integer prixVente) {}

	public void supprimerProduit(int rowIndex) {
		produit.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public int getColumnCount() {
		return entete.length;
	}
}
