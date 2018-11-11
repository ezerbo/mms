package com.mms.tablemodels;

import com.mms.pojos.Client;

// Referenced classes of package interfaces:
//            ActeurExterneTableModel

@SuppressWarnings("serial")
public class TableModelClient extends TableModelActeurExterne<Client> {

	public TableModelClient() {
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
			case 0 :
				return liste.get(ligne).getNomClient();
			case 1 :
				return liste.get(ligne).getPrenomClient();
			case 2 :
				return liste.get(ligne).getTelephoneclient();
			default :
				return null;
		}
	}
}
