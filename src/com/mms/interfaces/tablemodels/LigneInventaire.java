package com.mms.interfaces.tablemodels;

import java.awt.Color;

public class LigneInventaire {

	private String designation;
	private int stockTheorique;
	private Color etat;

	/**
	 * @return the stockTheorique
	 */
	public int getStockTheorique() {
		return stockTheorique;
	}

	/**
	 * @param stockTheorique
	 *            the stockTheorique to set
	 */
	public void setStockTheorique(int stockTheorique) {
		this.stockTheorique = stockTheorique;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the etat
	 */
	public Color getEtat() {
		return etat;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(Color etat) {
		this.etat = etat;
	}

}