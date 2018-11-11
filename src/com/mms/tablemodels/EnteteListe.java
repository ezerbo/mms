package com.mms.tablemodels;

public class EnteteListe {

	private String designation;
	private int quantite;
	private long sommeDejaPayee;
	private long totalAPayer;

	public EnteteListe() {
	}

	public EnteteListe(String designation, int quantite) {
		setDesignation(designation);
		setQuantite(quantite);
	}

	public EnteteListe(String designation, int quantite, long l, long m) {
		setSommeDejaPayee(l);
		setTotalAPayer(m);
		setDesignation(designation);
		setQuantite(quantite);
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public long getSommeDejaPayee() {
		return sommeDejaPayee;
	}

	public void setSommeDejaPayee(long l) {
		sommeDejaPayee = l;
	}

	public long getTotalAPayer() {
		return totalAPayer;
	}

	public void setTotalAPayer(long m) {
		totalAPayer = m;
	}
}
