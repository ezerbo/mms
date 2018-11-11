package com.mms.tablemodels;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelCreditClient extends AbstractTableModel {

	
	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {

			default :
				return null;
		}

	}

	public void setValueAt(Object value, int ligne, int colonne) {
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
