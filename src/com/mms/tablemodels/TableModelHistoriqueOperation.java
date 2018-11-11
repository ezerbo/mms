package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

// Referenced classes of package interfaces:
//            EnteteListe

@SuppressWarnings("serial")
public class TableModelHistoriqueOperation extends AbstractTableModel {

	private LinkedList<EnteteListe> liste;
	String entete[] = { "DESIGNATION", "QUANTITE" };

	public TableModelHistoriqueOperation() {
		liste = new LinkedList<EnteteListe>();
	}

	public int getColumnCount() {
		return entete.length;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getRowCount() {
		return liste.size();
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
		case 0: // '\0'
			return ((EnteteListe) liste.get(ligne)).getDesignation();

		case 1: // '\001'
			return Integer.valueOf(((EnteteListe) liste.get(ligne))
					.getQuantite());
		}
		return null;
	}

	public void ajouterLigne(String designation, int quantite) {
		liste.add(new EnteteListe(designation, quantite));
		fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
	}
}
