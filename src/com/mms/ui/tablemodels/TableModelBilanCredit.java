package com.mms.ui.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;


public class TableModelBilanCredit extends AbstractTableModel {

	String entete[] = { "TELEPHONE", "NOM", "PRENOM", "RESTE A PAYER",
			"TOTAL A PAYER" };
	LinkedList<EnteteBilan> liste;

	public TableModelBilanCredit() {
		liste = new LinkedList<EnteteBilan>();
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
			return liste.get(ligne)
					.getTelephone();

		case 1: // '\001'
			return liste.get(ligne).getNom();

		case 2: // '\002'
			return liste.get(ligne).getPrenom();

		case 3: // '\003'
			return liste.get(ligne)
					.getResteAPayer();

		case 4: // '\004'
			return liste.get(ligne).getTotalAPayer();
		}
		return null;
	}

}
