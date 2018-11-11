package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

// Referenced classes of package interfaces:
//            EnteteBilan

@SuppressWarnings("serial")
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
			return Integer.valueOf(((EnteteBilan) liste.get(ligne))
					.getTelephone());

		case 1: // '\001'
			return ((EnteteBilan) liste.get(ligne)).getNom();

		case 2: // '\002'
			return ((EnteteBilan) liste.get(ligne)).getPrenom();

		case 3: // '\003'
			return Integer.valueOf(((EnteteBilan) liste.get(ligne))
					.getResteAPayer());

		case 4: // '\004'
			return Integer.valueOf(((EnteteBilan) liste.get(ligne))
					.getTotalAPayer());
		}
		return null;
	}

	public void ajouterLigne(int telephone, String nom, String prenom,
			int resteAPayer, int totalAPayer) {
		liste.add(new EnteteBilan(telephone, nom, prenom, resteAPayer,
				totalAPayer));
		fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
	}
}
