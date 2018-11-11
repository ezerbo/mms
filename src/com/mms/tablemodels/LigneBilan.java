package com.mms.tablemodels;

class LigneBilan {

	private String libelle;
	private double valeur;

	public LigneBilan() {
	}

	public LigneBilan(String libelle, double valeur) {
		setLibelle(libelle);
		setValeur(valeur);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
}
