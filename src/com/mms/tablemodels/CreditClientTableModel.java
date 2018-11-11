package com.mms.tablemodels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.pojos.Lignedevente;

@SuppressWarnings("serial")
public class CreditClientTableModel extends AbstractTableModel {

	private String entete[] = {"DESIGNATION", "QUANTITE", "TOTAL H.T","TOTAL T.V.A","TOTAL T.T.C", "DATE" };
	private LinkedList<Lignedevente> listeCreditClient;

	public CreditClientTableModel() {
		listeCreditClient = new LinkedList<Lignedevente>();
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public Object getValueAt(int indexLigne, int indexColonne) {
		switch (indexColonne) {
		case 0:
			return listeCreditClient.get(indexLigne).getCategorie().getDesignation();
		case 1:
			return listeCreditClient.get(indexLigne).getQuantitelignevente();
		case 2:
			return listeCreditClient.get(indexLigne).getMontantligneventeht();
		case 3:
			return  listeCreditClient.get(indexLigne).getMontantLigneVenteTva();
		case 4:
			return listeCreditClient.get(indexLigne).getMontantligneventettc();
		default:
				return null;
		}
	}

	public int getRowCount() {
		return listeCreditClient.size();
	}

	public void ajouter(Lignedevente ligneDeVente) {
		listeCreditClient.add(ligneDeVente);
		fireTableRowsInserted(listeCreditClient.size() - 1,listeCreditClient.size() - 1);
	}

	public void supprimer(int index) {
		listeCreditClient.remove(index);
		fireTableRowsDeleted(listeCreditClient.size(), listeCreditClient.size());
	}

	public int getColumnCount() {
		return entete.length;
	}

	public void supprimerToutesLesLignes(){
		listeCreditClient.clear();
		fireTableDataChanged();
	}
	/**
	 * @return the listeCreditClient
	 */
	public LinkedList<Lignedevente> getListeCreditClient() {
		return listeCreditClient;
	}

	/**
	 * @param listeCreditClient the listeCreditClient to set
	 */
	public void setListeCreditClient(LinkedList<Lignedevente> listeCreditClient) {
		this.listeCreditClient = listeCreditClient;
	}
}
