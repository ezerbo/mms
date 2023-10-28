package com.mms.interfaces.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class TableModelHistoriqueOperation extends AbstractTableModel {

	private final LinkedList<EnteteListe> liste;
	String entete[] = { "DESIGNATION", "QUANTITE" };

	public TableModelHistoriqueOperation() {
		liste = new LinkedList<>();
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
			return liste.get(ligne).getDesignation();

		case 1: // '\001'
			return liste.get(ligne).getQuantite();
		}
		return null;
	}

}
