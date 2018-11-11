package com.mms.tablemodels;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelCompte extends AbstractTableModel {
	class Compte {

		private String etatDeResultat;
		private String montant;
		private String solde;
		private String pourcentage;

		public String getEtatDeResultat() {
			return etatDeResultat;
		}

		public void setEtatDeResultat(String etatDeResultat) {
			this.etatDeResultat = etatDeResultat;
		}

		public String getMontant() {
			return montant;
		}

		public void setMontant(String montant) {
			this.montant = montant;
		}

		public String getSolde() {
			return solde;
		}

		public void setSolde(String solde) {
			this.solde = solde;
		}

		public String getPourcentage() {
			return pourcentage;
		}

		public void setPourcentage(String pourcentage) {
			this.pourcentage = pourcentage;
		}

		public Compte(String etatDeResultat, String montant, String solde,
				String pourcentage) {
			
			setEtatDeResultat(etatDeResultat);
			setMontant(montant);
			setPourcentage(pourcentage);
			setSolde(solde);
		}
	}

	private String entete[];
	private LinkedList<Compte> lignes;

	public TableModelCompte() {
		lignes = new LinkedList<Compte>();
		String entete[] = { "Etat de resultat", "Montants", "Soldes", "%" };
		setEntete(entete);
	}

	public int getColumnCount() {
		return entete.length;
	}

	public int getRowCount() {
		return lignes.size();
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
		case 0: // '\0'
			return ((Compte) lignes.get(ligne)).getEtatDeResultat();

		case 1: // '\001'
			return ((Compte) lignes.get(ligne)).getMontant();

		case 2: // '\002'
			return ((Compte) lignes.get(ligne)).getSolde();

		case 3: // '\003'
			return ((Compte) lignes.get(ligne)).getPourcentage();
		}
		return null;
	}

	public String[] getEntete() {
		return entete;
	}

	public void setEntete(String entete[]) {
		this.entete = entete;
	}
}
