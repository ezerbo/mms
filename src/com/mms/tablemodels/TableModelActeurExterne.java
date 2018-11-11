package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelActeurExterne<T> extends AbstractTableModel {

	private String entete[] = {"NOM", "PRENOM", "TELEPHONE"};
	LinkedList<T> liste;

	public TableModelActeurExterne() {
		liste = new LinkedList<T>();
	}

	public int getColumnCount() {
		return entete.length;
	}

	public int getRowCount() {
		return liste.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public void ajouter(T objet) {
		liste.add(objet);
		fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
	}

	public void supprimer(int index) {
		liste.remove(index);
		fireTableRowsDeleted(liste.size(), liste.size());
	}
	public void supprimerTous() {
		liste.clear();
	}
}
