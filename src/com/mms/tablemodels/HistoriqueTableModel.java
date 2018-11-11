package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class HistoriqueTableModel extends AbstractTableModel {
	class Operations {

		private String designation;
		private int quantite;

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public int getQuantite() {
			return quantite;
		}

		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}

		public Operations(String designation, int quantite) {
			setDesignation(designation);
			setQuantite(quantite);
		}
	}

	LinkedList<Operations> operation;
	String entete[] = { "DESIGNATION", "QUANTITE" };

	public HistoriqueTableModel() {
		operation = new LinkedList<Operations>();
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0: // '\0'
			return ((Operations) operation.get(row)).getDesignation();

		case 1: // '\001'
			return Integer.valueOf(((Operations) operation.get(row))
					.getQuantite());
		}
		return null;
	}

	public int getRowCount() {
		return operation.size();
	}

	public int getColumnCount() {
		return entete.length;
	}

	public void ajouter(String designation, int quantiteVendue) {
		operation.add(new Operations(designation, quantiteVendue));
		fireTableRowsInserted(operation.size() - 1, operation.size() - 1);
	}

	public void supprimer() {
		operation.clear();
		fireTableRowsDeleted(operation.size(), operation.size());
	}
}
