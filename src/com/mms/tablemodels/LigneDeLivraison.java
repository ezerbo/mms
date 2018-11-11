package com.mms.tablemodels;

public class LigneDeLivraison {

	String designation;
	String quantite;

	LigneDeLivraison() {
	}

	public LigneDeLivraison(String designation, String quantite) {
		this.designation = designation;
		this.quantite = quantite;
	}

	public String getDesignation() {
		return designation;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}
}
