package com.mms.ui.tablemodels;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.dao.ParametresRepository;
import com.mms.domain.Parameters;
import com.mms.event.VenteEvent;
import com.mms.listener.VenteListener;
import com.mms.service.CategorieService;
import com.mms.service.SalesService;

public class TableModelVente extends AbstractTableModel {

	protected static String[] entetLigneVente = {"DESIGNATION", "QUANTITE",
			"PRIX UNITAIRE H.T.", "TOTAL H.T.", "TAUX T.V.A", "MONTANT TVA",
			"TOTAL T.T.C."};// entete de la JTable
	private LinkedList<LigneDeVente> ligneDeVente;// ligne de vente
	private static int COLONNEPRIXUNITAIRE = 2;
	private static int COLONNETOTAL = 3;
	private static int COLONNETAUXTVA = 4;
	private static int COLONNEMONTANTTVA = 5;
	private static int COLONNETOTALTTC = 6;
	private CategorieService categorieService;
	private SalesService salesService;
	private ParametresRepository parametresRepository;
	private Parameters parameters;
	private ArrayList<VenteListener> venteListeners = new ArrayList<>();

	public synchronized void addVenteListener(VenteListener listener) {
		if (venteListeners.contains(listener)) {
			return;
		}
		venteListeners.add(listener);
	}

	public void fireNouvelleLigneDeVente() {
		if (venteListeners.size() == 0) {
			return;
		}
		VenteEvent event = new VenteEvent(this);
		for (VenteListener listener : venteListeners) {
			listener.nouvelleLigneDeVente(event);
		}
	}
	public TableModelVente() {
		parametresRepository = new ParametresRepository();
		parameters = parametresRepository.findAll().get(0);
		salesService = new SalesService();
		categorieService = new CategorieService();
		ligneDeVente = new LinkedList<>();
	}

	public int getColumnCount() {// retourne le nombre de colonnes
		return entetLigneVente.length;
	}

	public int getRowCount() {// retourne le nombre de ligne
		return ligneDeVente.size();
	}

	public Object getValueAt(int ligne, int colonne) {
		switch (colonne) {
			case 0 :
				/** colonne des designations */
				return ligneDeVente.get(ligne).getDesignation();
			case 1 :
				/** colonne des quantit�s */
				return ligneDeVente.get(ligne).getQuantite();
			case 2 :
				/** colonne des prix unitaires */
				return ligneDeVente.get(ligne).getPrixUnitaire();
			case 3 :
				/** colonne des totaux */
				return ligneDeVente.get(ligne).getPrixGlobalHt();
			case 4 :
				return ligneDeVente.get(ligne).getTauxTva();
			case 5 :
				return ligneDeVente.get(ligne).getMontantTva();
			case 6 :
				return ligneDeVente.get(ligne).getPrixGlobalTtc();
			default :
				return null;
		}

	}

	public String getColumnName(int index) {
		return entetLigneVente[index];
	}

	public void setValueAt(Object value, int ligne, int colonne) {
		/**
		 * definition des valeurs des lignes de vente
		 */
		switch (colonne) {
			case 0 : {
				String designation = (String) value;
				ligneDeVente.get(ligne).setDesignation(designation);
				ligneDeVente.get(ligne).setPrixUnitaireHt(
						categorieService.getCategorie(designation)
								.getPrixunitairevente() + "");
				fireTableCellUpdated(ligne, COLONNEPRIXUNITAIRE);
				break;
			}
			case 1 : {
				ligneDeVente.get(ligne).setQuantite((String) value);
				String designation = ligneDeVente.get(ligne).getDesignation();
				String quantite = ligneDeVente.get(ligne).getQuantite();
				if (!designation.equals("")) {
					Double totalHT = salesService.calculTotalHt(designation, Integer.parseInt(quantite));
					Double totalTva = salesService.calculMontantTva(totalHT);
					Double totalTtc = salesService.calculTotalTtc(totalHT, totalTva);
					ligneDeVente.get(ligne).setMontantTva(totalTva + "");
					ligneDeVente.get(ligne).setPrixGlobalHt("" + totalHT);
					ligneDeVente.get(ligne).setPrixGlobalTtc("" + totalTtc);
					ligneDeVente.get(ligne).setTauxTva(parameters.getTauxtva() + "");
					fireTableCellUpdated(ligne, COLONNEMONTANTTVA);
					fireTableCellUpdated(ligne, COLONNETAUXTVA);
					fireTableCellUpdated(ligne, COLONNETOTAL);
					fireTableCellUpdated(ligne, COLONNETOTALTTC);
					fireNouvelleLigneDeVente();
				}
				break;
			}
		}
	}

	public boolean isCellEditable(int ligne, int colonne) {
		return colonne == 0 || colonne == 1;
	}

	public void ajouteLigne() {// permet l'ajout d'une nouvelle ligne de vente
		ligneDeVente.add(new LigneDeVente("", "", "", "", "", "", ""));
		fireTableRowsInserted(ligneDeVente.size() - 1, ligneDeVente.size() - 1);
	}

	public void supprimerLignes() {
		ligneDeVente.clear();
		fireTableRowsDeleted(0, 0);
	}

	public void supprimerLigne(int rowIndex) {
		/**
		 * permet de supprimer une ligne de vente
		 */
		ligneDeVente.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/**
	 * @return the ligneDeVente
	 */
	public LinkedList<LigneDeVente> getLigneDeVente() {
		return ligneDeVente;
	}

}
