package com.mms.tablemodels;

public class LigneDeVente {

	private String designation;
	private String quantite;
	private String prixUnitaireHt;
	private String prixGlobalHt;
	private String tauxTva;
	private String montantTva;
	private String prixGlobalTtc;

	public LigneDeVente(String designation, String quantite,
			String prixUnitaire, String prixGlobalHt, String tauxTva,
			String montantTva, String prixGlobalTtc) {
		setDesignation(designation);
		setQuantite(quantite);
		setPrixUnitaireHt(prixUnitaire);
		setPrixGlobalHt(prixGlobalHt);
		setTauxTva(tauxTva);
		setMontantTva(montantTva);
		setPrixGlobalTtc(prixGlobalTtc);
	}

	public LigneDeVente() {
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public String getPrixUnitaire() {
		return prixUnitaireHt;
	}

	public void setPrixUnitaireHt(String prixUnitaire) {
		this.prixUnitaireHt = prixUnitaire;
	}

	public String getPrixGlobalHt() {
		return prixGlobalHt;
	}

	public void setPrixGlobalHt(String prixGlobalHt) {
		this.prixGlobalHt = prixGlobalHt;
	}

	public String getTauxTva() {
		return tauxTva;
	}

	public void setTauxTva(String tauxTva) {
		this.tauxTva = tauxTva;
	}

	public String getMontantTva() {
		return montantTva;
	}

	public void setMontantTva(String montantTva) {
		this.montantTva = montantTva;
	}

	public String getPrixGlobalTtc() {
		return prixGlobalTtc;
	}

	public void setPrixGlobalTtc(String prixGlobalTtc) {
		this.prixGlobalTtc = prixGlobalTtc;
	}
}
