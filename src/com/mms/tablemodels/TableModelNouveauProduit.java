package com.mms.tablemodels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.pojos.Categorie;

@SuppressWarnings("serial")
public class TableModelNouveauProduit extends AbstractTableModel {
	private String entete[] = {"DESIGNATION", "P.U ACHAT", "P.U VENTE",
			"QTE EN STOCK", "QTE IDEALE", "QTE SECURITE"};
	public LinkedList<Categorie> ligneProduit;

	public TableModelNouveauProduit() {
		ligneProduit = new LinkedList<Categorie>();
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entete.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ligneProduit.size();
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		// TODO Auto-generated method stub
		switch (colonne) {
			case 0 :
				return ligneProduit.get(ligne).getDesignation();
			case 1 :
				return ligneProduit.get(ligne).getPrixunitaireachat();
			case 2 :
				return ligneProduit.get(ligne).getPrixunitairevente();
			case 3 :
				return ligneProduit.get(ligne).getQuantitestock();
			case 4 :
				return ligneProduit.get(ligne).getQuantiteIdeale();
			case 5 :
				return ligneProduit.get(ligne).getQuantiteSecurite();
			default :
				return null;
		}
	}

	public void ajouter(Categorie categorie) {
		ligneProduit.add(categorie);
		fireTableRowsInserted(ligneProduit.size() - 1, ligneProduit.size() - 1);
	}

	public void supprimer(int index) {
		ligneProduit.remove(index);
		fireTableRowsDeleted(ligneProduit.size(), ligneProduit.size());
	}
	public void supprimerToutesLesLignes() {
		ligneProduit.clear();
	}
}
