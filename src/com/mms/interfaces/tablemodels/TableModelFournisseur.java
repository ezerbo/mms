package com.mms.interfaces.tablemodels;

import com.mms.domain.Fournisseur;

public class TableModelFournisseur extends TableModelActeurExterne<Fournisseur> {

	public TableModelFournisseur() {
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
			case 0 :
				return liste.get(ligne).getNomFournisseur();
			case 1 :
				return liste.get(ligne).getPrenomFournisseur();
			case 2 :
				return liste.get(ligne).getTelephonefournisseur();
			default :
				return null;
		}
	}
}
