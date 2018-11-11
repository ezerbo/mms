package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

// Referenced classes of package interfaces:
//            Bilan

@SuppressWarnings("serial")
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
			return ((LigneBilan) liste.get(ligne)).getLibelle();

		case 1: // '\001'
			return Double.valueOf(((LigneBilan) liste.get(ligne)).getValeur());
		}
		return null;
	}

	public void ajouterLigne(String libelle, double valeur) {
		liste.add(new LigneBilan(libelle, valeur));
		fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
	}
}
