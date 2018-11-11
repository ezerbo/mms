package com.mms.tablemodels;

public class LigneDeCredit {

	private String nom;
	private String prenom;
	private String date;
	private String num;
	private int telephone;

	public LigneDeCredit(String numero, String nom, String prenom, String date) {
		setDate(date);
		setNom(nom);
		setNum(numero);
		setPrenom(prenom);
	}

	public LigneDeCredit(String numero, int telephone, String nom,
			String prenom, String date) {
		setNum(numero);
		setDate(date);
		setNom(nom);
		setTelephone(telephone);
		setPrenom(prenom);
	}

	public LigneDeCredit(String num, String vente) {
		setDate(vente);
		setNum(num);
	}

	public LigneDeCredit() {
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
}
