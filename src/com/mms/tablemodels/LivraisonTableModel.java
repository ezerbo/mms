package com.mms.tablemodels;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

// Referenced classes of package interfaces:
//            EntreeProduit

@SuppressWarnings("serial")
public class LivraisonTableModel extends AbstractTableModel {

	protected LinkedList<LigneDeLivraison> produit;
	private String entete[] = { "DESIGNATION", "QUANTITE" };

	public LivraisonTableModel() {
		produit = new LinkedList<LigneDeLivraison>();
	}

	public int getRowCount() {
		return produit.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: // '\0'
			return ((LigneDeLivraison) produit.get(rowIndex)).getDesignation();

		case 1: // '\001'
			return ((LigneDeLivraison) produit.get(rowIndex)).getQuantite();
		}
		return null;
	}

	public String getColumnName(int index) {
		return entete[index];
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int l, int c) {
		LigneDeLivraison p = (LigneDeLivraison) produit.get(l);
		switch (c) {
		default:
			break;

		case 0: // '\0'
			p.setDesignation((String) value);
			break;

		case 1: // '\001'
			boolean teste = false;
			try {
				Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				teste = true;
				if (!value.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Saisissez un entier svp!!", "Message", 1);
				}
			}
			if (!teste) {
				p.setQuantite((String) value);
			}
			break;
		}
	}

	public void ajouterLignes() {
		produit.add(new LigneDeLivraison("", ""));
		fireTableRowsInserted(produit.size() - 1, produit.size() - 1);
	}

	public void supprimerLignes() {
		produit.clear();
		fireTableRowsDeleted(produit.size(), produit.size());
	}

	public void supprimerLigne(int rowIndex) {
		produit.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public int getColumnCount() {
		return entete.length;
	}
}
