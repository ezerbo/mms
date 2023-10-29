package com.mms.ui.tablemodels;

public class EnteteBilan {

	private int telephone;
	private String nom;
	private String prenom;
	private int resteAPayer;
	private int totalAPayer;

	public EnteteBilan(int telephone, String nom, String prenom,
			int resteAPayer, int totalAPayer) {
		setTelephone(telephone);
		setNom(nom);
		setPrenom(prenom);
		setResteAPayer(resteAPayer);
		setTotalAPayer(totalAPayer);
	}

	public EnteteBilan() {
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getResteAPayer() {
		return resteAPayer;
	}

	public void setResteAPayer(int resteAPayer) {
		this.resteAPayer = resteAPayer;
	}

	public int getTotalAPayer() {
		return totalAPayer;
	}

	public void setTotalAPayer(int totalAPayer) {
		this.totalAPayer = totalAPayer;
	}
}
