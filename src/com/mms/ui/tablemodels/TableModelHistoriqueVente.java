package com.mms.ui.tablemodels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.domain.Lignedevente;

public class TableModelHistoriqueVente extends AbstractTableModel {

	private  LinkedList<Lignedevente> listeVente;
	private String entete[] = {"DESIGNATION", "QUANTITE", "DATE", "PRIX UNITAIRE",
			"MONTANT HT", "MONTANT TVA", "MONTANT TTC"};

	public TableModelHistoriqueVente() {
		listeVente = new LinkedList<Lignedevente>();
	}

	/**
	 * @return the listeVente
	 */
	public LinkedList<Lignedevente> getListeVente() {
		return listeVente;
	}

	/**
	 * @param listeVente the listeVente to set
	 */
	public void setListeVente(LinkedList<Lignedevente> listeVente) {
		this.listeVente = listeVente;
	}

	public int getColumnCount() {
		return entete.length;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public int getRowCount() {
		return listeVente.size();
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
			case 0 :
				return listeVente.get(ligne).getCategorie().getDesignation();
			case 1 :
				return listeVente.get(ligne).getQuantitelignevente();
			case 2 :
				return new java.sql.Date(listeVente.get(ligne).getVente().getDatevente().getTime()).toString();
			case 3 :
				return listeVente.get(ligne).getMontantligneventeht();
			case 4 :
				return listeVente.get(ligne).getCategorie().getPrixunitairevente();
			case 5 :
				return listeVente.get(ligne).getMontantLigneVenteTva();
			case 6 :
				return listeVente.get(ligne).getMontantligneventettc();
			default :
				return null;
		}
	}

	public void ajouterLigne(Lignedevente lignedevente) {
		listeVente.add(lignedevente);
		fireTableRowsInserted(listeVente.size() - 1, listeVente.size() - 1);
	}

	public void supprimerToutesLesLignes(){
		listeVente.clear();
		fireTableDataChanged();
	}
}
