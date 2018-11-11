package com.mms.tablemodels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.pojos.Utilisateur;

// Referenced classes of package interfaces:
//            User

@SuppressWarnings("serial")
public class TableModelUtilisateur extends AbstractTableModel {

	private String entete[] = { "NOM","PRENOM","LOGIN","TELEPHONE"};
	private LinkedList<Utilisateur> listeUtilisateur;

	public TableModelUtilisateur() {
		listeUtilisateur = new LinkedList<Utilisateur>();
	}

	public LinkedList<Utilisateur> getUserList() {
		return listeUtilisateur;
	}

	public void setUserList(LinkedList<Utilisateur> userList) {
		this.listeUtilisateur = userList;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getColumnCount() {
		return entete.length;
	}

	public int getRowCount() {
		return listeUtilisateur.size();
	}

	public Object getValueAt(int indexLigne, int indexColonne) {
		switch (indexColonne) {
		case 0:
			return listeUtilisateur.get(indexLigne).getNomutilisateur();
		case 1:
			return listeUtilisateur.get(indexLigne).getPrenomutilisateur();
		case 2:
			return listeUtilisateur.get(indexLigne).getLoginutilisateur();
		case 3:
			return listeUtilisateur.get(indexLigne).getTelephoneutilisateur();
		default:return null;
		}
		
	}

	public void setValueAt(Object value, int ligne, int colonne) {}

	public boolean isCellEditable(int x, int y) {
		return false;
	}

	public void ajouter(Utilisateur utilisateur) {/**Ajout d'un nouvel utilisateur dans la JTable*/
		listeUtilisateur.add(utilisateur);
		fireTableRowsInserted(listeUtilisateur.size()-1, listeUtilisateur.size()-1);	
		}

	public void supprimerUtilisateur(int rowIndex) {/***Suppression d'utilisateur de la JTable**/
		listeUtilisateur.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public void netoyerListe(){
		listeUtilisateur.clear();
	}
}
