package com.mms.pojos;

// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA


/**
 * Etat generated by hbm2java
 */
@SuppressWarnings("serial")
public class Etat implements java.io.Serializable {

	private int idetat;
	private String libelleetat;

	public Etat() {
	}

	public Etat(int idetat) {
		this.idetat = idetat;
	}

	public int getIdetat() {
		return this.idetat;
	}

	public void setIdetat(int idetat) {
		this.idetat = idetat;
	}

	public String getLibelleetat() {
		return this.libelleetat;
	}

	public void setLibelleetat(String libelleetat) {
		this.libelleetat = libelleetat;
	}

}
