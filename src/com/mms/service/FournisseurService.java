package com.mms.service;
/**
 * @author EDOUARDO*/
import java.util.LinkedList;

import com.mms.dao.FournisseurDAO;
import com.mms.pojos.Fournisseur;

public class FournisseurService {
	private FournisseurDAO fournisseurDAO = new FournisseurDAO();
	private Fournisseur fournisseur = null;
	/**
	 * Enregistrement d'un nouveau fournisseur
	 * 
	 * @param nomFournisseur
	 *            : nom du fournisseur
	 * @param prenomFournisseur
	 *            : prenom du fournisseur
	 * @param telephoneFournisseur
	 *            : numero de telephone du fournisseur
	 * **/
	public Fournisseur enregistrerFournisseur(String nomFournisseur,
			String prenomFournisseur, String telephoneFournisseur) {
		fournisseur = new Fournisseur();
		fournisseur.setNomFournisseur(nomFournisseur);
		fournisseur.setPrenomFournisseur(prenomFournisseur);
		fournisseur.setTelephonefournisseur(telephoneFournisseur);
		return fournisseurDAO.create(fournisseur);
	}
	/**
	 * Suppression d'un fournisseur
	 * 
	 * @param telephoneFournisseur
	 *            : numero de telephone du fournisseur
	 * */
	public int supprimerFournisseur(String telephoneFournisseur) {
		return fournisseurDAO.delete(fournisseurDAO
				.findByTel(telephoneFournisseur));
	}
	/**
	 * Met a jour les informations concernant un fournisseur
	 * 
	 * @param nomFournisseur
	 *            : nouveau nom du fournisseur
	 * @param prenomFournisseur
	 *            : nouveau prenom du fournisseur
	 * @param telephoneFournisseur
	 *            : nouveau numero de telephone du fournissseur
	 * @param ancienNumeroDeTelephone
	 *            : l'anceien numero de telephone du fournisseur à partir duquel
	 *            on retrouvera les anciennes informations
	 **/
	public int misAJourFournisseur(String nomFournisseur,
			String prenomFournisseur, String telephoneFournisseur,
			String ancienNumeroDeTelephone) {
		Fournisseur ancienFournisseur = fournisseurDAO
				.findByTel(ancienNumeroDeTelephone);
		fournisseur = new Fournisseur();
		fournisseur.setIdfournisseur(ancienFournisseur.getIdfournisseur());
		fournisseur.setNomFournisseur(nomFournisseur);
		fournisseur.setPrenomFournisseur(prenomFournisseur);
		fournisseur.setTelephonefournisseur(telephoneFournisseur);
		return fournisseurDAO.update(fournisseur);

	}
	/** Retourne la liste des fournisseurs **/
	public LinkedList<Fournisseur> listeFournisseur() {
		return (LinkedList<Fournisseur>) fournisseurDAO.findAll();
	}
	/**
	 * Retourne toutes les informations concernant un fournisseur en fonction de
	 * son numero de telephone
	 * 
	 * @param telephone
	 *            : le numero de telephone du fournisseur
	 **/
	public Fournisseur retournerFournisseur(String telephone) {
		return fournisseurDAO.findByTel(telephone);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
