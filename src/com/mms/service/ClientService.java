package com.mms.service;
/**
 * @author EDOUARDO
 * */
import java.util.LinkedList;

import com.mms.dao.ClientDAO;
import com.mms.pojos.Client;

public class ClientService {
	private ClientDAO clientDAO = new ClientDAO();
	/**
	 * Enregistrement d'un nouveau client
	 * 
	 * @param nomClient
	 *            : le nom du client
	 * @param prenomClient
	 *            : le prenom du client
	 * @param telephoneClient
	 *            : le numero de telephone du client
	 */
	public Client enregistrerClient(String nomClient, String prenomClient,
			String telephoneClient) {
		Client client = new Client();
		client.setNomClient(nomClient);
		client.setPrenomClient(prenomClient);
		client.setTelephoneclient(telephoneClient);
		return clientDAO.create(client);
	}
	/***
	 * Suppression d'un client
	 * 
	 * @param telephoneClient
	 *            : numero de telephone du client
	 * */

	public int supprimerClient(String telephone) {
		return clientDAO.delete(clientDAO.findByTel(telephone));
	}
	/**
	 * Mis a jour des information concernant un client
	 * 
	 * @param nomClient
	 * @param prenomClient
	 * @param telephoneClient
	 * @param ancienNumeroDeTelephone
	 * **/
	public int misAJourClient(String nomClient, String prenomClient,
			String telephoneClient, String ancienNumeroDeTelephone) {
		Client ancienClient = clientDAO.findByTel(ancienNumeroDeTelephone);
		Client client = new Client();
		client.setIdclient(ancienClient.getIdclient());
		client.setNomClient(nomClient);
		client.setPrenomClient(prenomClient);
		client.setTelephoneclient(telephoneClient);
		return clientDAO.update(client);
		/** met a jour les informations et retourne le resultats de l'operation */
	}
	/** Retourne la liste de tous les clients */
	public LinkedList<Client> listeClient() {
		return (LinkedList<Client>) clientDAO.findAll();
	}
	/**
	 * Retourne toutes les informations d'un client en fonctions de son numero
	 * de telephone
	 * 
	 * @param telephone
	 *            : numero de telephone du client
	 **/
	public Client retourneClient(String telephone) {
		return clientDAO.findByTel(telephone);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
