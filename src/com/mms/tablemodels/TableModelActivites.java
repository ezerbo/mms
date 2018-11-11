package com.mms.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mms.pojos.Lignedevente;

@SuppressWarnings("serial")
public class TableModelActivites extends AbstractTableModel {
	private String entete[] = { "DESIGNATION", "QUANTITE", "TOTAL H.T","TOTAL T.V.A","TOTAL T.T.C" };
	private List<Lignedevente> listeVente;

	public TableModelActivites() {
		listeVente = new ArrayList<Lignedevente>();
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getRowCount() {
		return listeVente.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listeVente.get(rowIndex).getCategorie().getDesignation();
		case 1: 
			return listeVente.get(rowIndex).getQuantitelignevente();
		case 2: 
			return listeVente.get(rowIndex).getMontantligneventeht();
		case 3:
			return listeVente.get(rowIndex).getMontantLigneVenteTva();
		case 4:
			return listeVente.get(rowIndex).getMontantligneventettc();
		default:
			return null;
		}
		
	}

	public int getColumnCount() {
		return entete.length;
	}

	public void ajouter(Lignedevente ligneCourante) {
		listeVente.add(ligneCourante);
		fireTableRowsInserted(listeVente.size() - 1, listeVente.size() - 1);
	}

	public void supprimerToutesLesLignes() {
		// TODO Auto-generated method stub
		listeVente.clear();
		fireTableDataChanged();
	}

	/**
	 * @return the listeVente
	 */
	public List<Lignedevente> getListeVente() {
		return listeVente;
	}

	/**
	 * @param listeVente the listeVente to set
	 */
	public void setListeVente(List<Lignedevente> listeVente) {
		this.listeVente = listeVente;
	}

}
