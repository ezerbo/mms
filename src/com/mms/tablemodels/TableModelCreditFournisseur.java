package com.mms.tablemodels;

import javax.swing.table.AbstractTableModel;

// Referenced classes of package interfaces:
//            EnteteListe

@SuppressWarnings("serial")
public class TableModelCreditFournisseur extends AbstractTableModel {

	

	public boolean isCellEditable(int indexLigne, int indexColonne) {
		boolean teste = false;
		if (indexColonne == 2) {
			teste = true;
		}
		return teste;
	}

	public void setValueAt(Object value, int ligne, int colonne) {
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
		
		}
		return null;
	}

	public void ajouterLigne() {
		
	}

	public void supprimerLigne(int index) {
		
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
