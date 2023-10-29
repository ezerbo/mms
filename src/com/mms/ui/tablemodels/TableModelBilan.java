package com.mms.ui.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class TableModelBilan extends AbstractTableModel {

	LinkedList<LigneBilan> liste;
	String entete[] = { "LIBELLE", "MONTANT" };

	public TableModelBilan() {
		liste = new LinkedList<LigneBilan>();
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
			return liste.get(ligne).getLibelle();

		case 1: // '\001'
			return liste.get(ligne).getValeur();
		}
		return null;
	}

}
