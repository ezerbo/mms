package com.mms.tablemodels;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.mms.dao.ParametresDAO;
import com.mms.event.VenteEvent;
import com.mms.listener.ProduitListener;
import com.mms.listener.VenteListener;
import com.mms.pojos.Parametres;
import com.mms.service.CategorieService;
import com.mms.service.VenteService;

// Referenced classes of package interfaces:
//            EnteteVente
@SuppressWarnings("serial")
public class TableModelVente extends AbstractTableModel {

	protected static String entetLigneVente[] = {"DESIGNATION", "QUANTITE",
			"PRIX UNITAIRE H.T.", "TOTAL H.T.", "TAUX T.V.A", "MONTANT TVA",
			"TOTAL T.T.C."};// entete de la JTable
	private LinkedList<LigneDeVente> ligneDeVente;// ligne de vente
	private static int COLONNEPRIXUNITAIRE = 2;
	private static int COLONNETOTAL = 3;
	private static int COLONNETAUXTVA = 4;
	private static int COLONNEMONTANTTVA = 5;
	private static int COLONNETOTALTTC = 6;
	private CategorieService categorieService;
	private VenteService venteService;
	private ParametresDAO parametresDAO;
	private Parametres parametres;
	private ArrayList<VenteListener> venteListeners = new ArrayList<VenteListener>();

	public synchronized void addVenteListener(VenteListener listener) {
		if (venteListeners.contains(listener)) {
			return;
		}
		venteListeners.add(listener);
	}

	public synchronized void removeProduitListener(ProduitListener listener) {
		venteListeners.remove(listener);
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
		parametresDAO = new ParametresDAO();
		parametres = parametresDAO.findAll().get(0);
		venteService = new VenteService();
		categorieService = new CategorieService();
		ligneDeVente = new LinkedList<LigneDeVente>();
	}

	public int getColumnCount() {// retourne le nombre de colonnes
		return entetLigneVente.length;
	}

	public int getRowCount() {// retourne le nombre de ligne
		return ligneDeVente.size();
	}

	public Object getValueAt(int ligne, int colonne) {
		/**
		 * definition des valeur attendues dans les differentes cellules
		 */
		switch (colonne) {
			case 0 :
				/** colonne des designations */
				return ((LigneDeVente) ligneDeVente.get(ligne))
						.getDesignation();
			case 1 :
				/** colonne des quantités */
				return ((LigneDeVente) ligneDeVente.get(ligne)).getQuantite();
			case 2 :
				/** colonne des prix unitaires */
				return ((LigneDeVente) ligneDeVente.get(ligne))
						.getPrixUnitaire();
			case 3 :
				/** colonne des totaux */
				return ((LigneDeVente) ligneDeVente.get(ligne))
						.getPrixGlobalHt();
			case 4 :
				return ((LigneDeVente) ligneDeVente.get(ligne)).getTauxTva();
			case 5 :
				return ((LigneDeVente) ligneDeVente.get(ligne)).getMontantTva();
			case 6 :
				return ((LigneDeVente) ligneDeVente.get(ligne))
						.getPrixGlobalTtc();
			default :
				return null;
		}

	}

	public String getColumnName(int index) {
		/** definition des entetes */
		return entetLigneVente[index];
	}

	public void setValueAt(Object value, int ligne, int colonne) {
		/**
		 * definition des valeurs des lignes de vente
		 */
		switch (colonne) {
			case 0 : {
				/***/
				String designation = (String) value;
				ligneDeVente.get(ligne).setDesignation(designation);
				ligneDeVente.get(ligne).setPrixUnitaireHt(
						categorieService.getCategorie(designation)
								.getPrixunitairevente() + "");
				fireTableCellUpdated(ligne, COLONNEPRIXUNITAIRE);
				/** mise a jour de la colonne 3 du tableau */
				break;
			}
			case 1 : {
				/***/
				ligneDeVente.get(ligne).setQuantite((String) value);
				/**** calcul du montant HT,total TVA et total TTC *****/
				String designation = ligneDeVente.get(ligne).getDesignation();
				String quantite = ligneDeVente.get(ligne).getQuantite();
				if (!designation.equals("")) {
					Double totalHT = venteService.calculTotalHt(designation,
							Integer.valueOf(quantite));
					Double totalTva = venteService.calculMontantTva(totalHT);
					Double totalTtc = venteService.calculTotalTtc(totalHT,
							totalTva);
					ligneDeVente.get(ligne).setMontantTva(totalTva + "");
					ligneDeVente.get(ligne).setPrixGlobalHt("" + totalHT);
					ligneDeVente.get(ligne).setPrixGlobalTtc("" + totalTtc);
					ligneDeVente.get(ligne).setTauxTva(
							parametres.getTauxtva() + "");
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
		/**
		 * defini si la cellule est editable
		 */
		if (colonne == 0 || colonne == 1)
			return true;
		else
			return false;
	}
	public void afficheMontants(String designation, int quantite) {

	}
	public void ajouteLigne() {// permet l'ajout d'une nouvelle ligne de vente
		ligneDeVente.add(new LigneDeVente("", "", "", "", "", "", ""));
		fireTableRowsInserted(ligneDeVente.size() - 1, ligneDeVente.size() - 1);
	}

	public void supprimerLignes() {
		/**
		 * permet de supprimer toutes les lignes de ventes
		 */
		ligneDeVente.clear();
		fireTableRowsDeleted(ligneDeVente.size(), ligneDeVente.size());
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

	/**
	 * @param ligneDeVente
	 *            the ligneDeVente to set
	 */
	public void setLigneDeVente(LinkedList<LigneDeVente> ligneDeVente) {
		this.ligneDeVente = ligneDeVente;
	}
}
