package com.mms.ui.tablemodels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.domain.User;

public class TableModelUtilisateur extends AbstractTableModel {

	private String entete[] = { "NOM","PRENOM","LOGIN","TELEPHONE"};
	private LinkedList<User> listeUser;

	public TableModelUtilisateur() {
		listeUser = new LinkedList<User>();
	}

	public LinkedList<User> getUserList() {
		return listeUser;
	}

	public void setUserList(LinkedList<User> userList) {
		this.listeUser = userList;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getColumnCount() {
		return entete.length;
	}

	public int getRowCount() {
		return listeUser.size();
	}

	public Object getValueAt(int indexLigne, int indexColonne) {
		switch (indexColonne) {
		case 0:
			return listeUser.get(indexLigne).getNomutilisateur();
		case 1:
			return listeUser.get(indexLigne).getPrenomutilisateur();
		case 2:
			return listeUser.get(indexLigne).getLoginutilisateur();
		case 3:
			return listeUser.get(indexLigne).getTelephoneutilisateur();
		default:return null;
		}
		
	}

	public void setValueAt(Object value, int ligne, int colonne) {}

	public boolean isCellEditable(int x, int y) {
		return false;
	}

	public void ajouter(User user) {/**Ajout d'un nouvel utilisateur dans la JTable*/
		listeUser.add(user);
		fireTableRowsInserted(listeUser.size()-1, listeUser.size()-1);
		}

	public void netoyerListe(){
		listeUser.clear();
	}
}
