package com.mms.interfaces.tablemodels;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelActeurExterne<T> extends AbstractTableModel {

	private final String[] header = {"NOM", "PRENOM", "TELEPHONE"};
	LinkedList<T> liste;

	public TableModelActeurExterne() {
		liste = new LinkedList<T>();
	}

	public int getColumnCount() {
		return header.length;
	}

	public int getRowCount() {
		return liste.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

	public String getColumnName(int index) {
		return header[index];
	}

	public void add(T objet) {
		liste.add(objet);
		fireTableRowsInserted(liste.size() - 1, liste.size() - 1);
	}

	public void addAll(List<T> items) {
		clear();
		items.forEach(this::add);
	}

	public void delete(int index) {
		liste.remove(index);
		fireTableRowsDeleted(liste.size(), liste.size());
	}
	public void clear() {
		liste.clear();
	}
}
