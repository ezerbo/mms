package com.mms.interfaces.tablemodels;

import com.mms.domain.Client;

public class ClientTableModel extends TableModelActeurExterne<Client> {

	public Object getValueAt(int line, int column) {
		switch (column) {
			case 0 :
				return liste.get(line).getNomClient();
			case 1 :
				return liste.get(line).getPrenomClient();
			case 2 :
				return liste.get(line).getTelephoneclient();
			default :
				return null;
		}
	}
}
