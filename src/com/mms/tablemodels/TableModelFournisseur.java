package com.mms.tablemodels;

import com.mms.pojos.Fournisseur;

// Referenced classes of package interfaces:
//            ActeurExterneTableModel

@SuppressWarnings("serial")
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
