package com.mms.pojos;

// Generated 25 nov. 2012 13:14:59 by Hibernate Tools 3.2.1.GA

/**
 * Client generated by hbm2java
 */
public class Client {

	private int idclient;
	private String nomClient;
	private String prenomClient;
	private String telephoneclient;

	public Client() {
	}

	public Client(int idclient) {
		this.idclient = idclient;
	}

	public int getIdclient() {
		return this.idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getTelephoneclient() {
		return this.telephoneclient;
	}

	public void setTelephoneclient(String telephoneclient) {
		this.telephoneclient = telephoneclient;
	}

	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * @param nomClient
	 *            the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	/**
	 * @return the prenomClient
	 */
	public String getPrenomClient() {
		return prenomClient;
	}

	/**
	 * @param prenomClient
	 *            the prenomClient to set
	 */
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

}
