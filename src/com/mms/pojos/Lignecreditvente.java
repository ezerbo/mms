package com.mms.pojos;
// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * Lignecreditvente generated by hbm2java
 */
@SuppressWarnings("serial")
public class Lignecreditvente implements java.io.Serializable {

	private Produit produit;
	private Creditclient creditclient;
	private Integer quantitelignecredit;
	private BigDecimal montantlignecreditht;
	private BigDecimal montantlignecreditttc;

	public Lignecreditvente() {
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Creditclient getCreditclient() {
		return this.creditclient;
	}

	public void setCreditclient(Creditclient creditclient) {
		this.creditclient = creditclient;
	}
	public Integer getQuantitelignecredit() {
		return this.quantitelignecredit;
	}

	public void setQuantitelignecredit(Integer quantitelignecredit) {
		this.quantitelignecredit = quantitelignecredit;
	}
	public BigDecimal getMontantlignecreditht() {
		return this.montantlignecreditht;
	}

	public void setMontantlignecreditht(BigDecimal montantlignecreditht) {
		this.montantlignecreditht = montantlignecreditht;
	}
	public BigDecimal getMontantlignecreditttc() {
		return this.montantlignecreditttc;
	}

	public void setMontantlignecreditttc(BigDecimal montantlignecreditttc) {
		this.montantlignecreditttc = montantlignecreditttc;
	}

}
