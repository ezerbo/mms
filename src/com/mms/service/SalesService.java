package com.mms.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

import com.mms.dao.ClientDAO;
import com.mms.dao.EtatDAO;
import com.mms.dao.LigneVenteDAO;
import com.mms.dao.ParametresRepository;
import com.mms.dao.VenteDAO;
import com.mms.domain.Categorie;
import com.mms.domain.Client;
import com.mms.domain.Etat;
import com.mms.domain.Lignedevente;
import com.mms.domain.Parameters;
import com.mms.domain.Session;
import com.mms.domain.Vente;
import com.mms.ui.tablemodels.LigneDeVente;

public class SalesService {
	private CategorieService categorieService = new CategorieService();
	private Categorie categorie = null;
	private ParametresRepository parametresRepository = new ParametresRepository();
	private Parameters parameters = null;
	private VenteDAO venteDAO = new VenteDAO();
	private LigneVenteDAO ligneVenteDAO = new LigneVenteDAO();
	private EtatDAO etatDAO = new EtatDAO();
	private ClientDAO clientDAO = new ClientDAO();
	private Vente vente = null;
	private Lignedevente ligneDeVente = null;
	private Session session = null;
	private Etat etat = null;
	public static int SUCCES_OPERATION = 0;
	public static int ECHEC_OPERATION = 1;
	/**
	 * calcul le montant total hors taxe
	 * 
	 * @param designation
	 *            : designation du produit
	 * @param quantite
	 *            :quantite du produit correspondant
	 **/
	public Double calculTotalHt(String designation, int quantite) {
		categorie = categorieService.getCategorie(designation);
		Double montantHT = (double) (categorie.getPrixunitairevente() * quantite);
		return montantHT;
	}
	/**
	 * Calcul le montant TVA
	 * 
	 * @param totalHt
	 *            : montant hors taxe sur lequel le taux TVA est appliqu�
	 */
	public Double calculMontantTva(Double totalHt) {
		parameters = parametresRepository.findAll().get(0);
		Double montantTva = parameters.getTauxtva() * totalHt;
		return montantTva;
	}
	/**
	 * Calcul le montant TTC
	 * 
	 * @param montantHt
	 *            : correspond au montant calcul� par calculTotalHt()
	 * @param montantTva
	 *            : correspond au montant calcul� par calculMontantTva
	 */
	public Double calculTotalTtc(Double montantHt, Double montantTva) {
		return montantHt + montantTva;
	}
	/**
	 * permet d'enregistrer une vente
	 * 
	 * @param idSession
	 *            : identifiant de la session de l'utilisateur effectuant
	 *            l'operation
	 * @param etatVente
	 *            : etat de la vente ['comptant','credit']
	 * @param montantTotalVenteHt
	 *            : montant total hors taxe de la vente
	 * @param montantTotalVenteTva
	 *            : montant total de la tva
	 * @param montantTotalVenteTtc
	 *            : montant total toute taxe comprise
	 **/
	public Vente enregistrerVente(int idSession, Client client,
			String etatVente, double montantTotalVenteHt,
			double montantTotalVenteTva, double montantTotalVenteTtc) {
		vente = new Vente();
		vente.setMontantTotalVenteHt(montantTotalVenteHt);
		vente.setMontantTotalVenteTva(montantTotalVenteTva);
		vente.setMontantTotalVenteTtc(montantTotalVenteTtc);
		session = new Session();
		session.setIdsession(idSession);
		vente.setSession(session);
		etat = new Etat();
		etat.setIdetat(etatDAO.findByName(etatVente).getIdetat());
		vente.setEtat(etat);
		vente.setClient(client);
		session = new Session();
		session.setIdsession(idSession);
		vente.setSession(session);
		vente.setDatevente(new java.util.Date());
		/** date du systeme **/
		return venteDAO.create(vente);
	}
	/**
	 * permet d'enregistrer un nouvelle ligne de vente
	 * 
	 * @param vente
	 *            : objet vente correspondant a la vente courante
	 * @param designation
	 *            : designation du produit de la ligne de vente courante
	 * @param quantiteLigneVente
	 *            : quantite du produit de la ligne de vente
	 * @param montantLigneVenteHt
	 *            : montant ligne hors taxe de la ligne de vente courante
	 * @param montantLigneVenteTva
	 *            : montant tva de ligne de vente courante
	 * @param montantLigneVenteTtc
	 *            : montant toutes taxes comprises de la ligne de vente
	 *            correspondante
	 **/
	public Lignedevente enregistrerLigneDeVente(Vente vente,
			String designation, Integer quantiteLigneVente,
			double montantLigneVenteHt, double montantLigneVenteTva,
			double montantLigneVenteTtc) {
		ligneDeVente = new Lignedevente();
		categorie = categorieService.getCategorie(designation);
		ligneDeVente.setCategorie(categorie);
		ligneDeVente.setQuantitelignevente(quantiteLigneVente);
		ligneDeVente.setMontantligneventeht(montantLigneVenteHt);
		ligneDeVente.setMontantLigneVenteTva(montantLigneVenteTva);
		ligneDeVente.setMontantligneventettc(montantLigneVenteTtc);
		ligneDeVente.setVente(vente);
		return ligneVenteDAO.create(ligneDeVente);
	}
	/**
	 * Enregistre l'operation de vente
	 * 
	 * @param listeDeVente
	 *            : liste chain�e des ventes provenant de la JTable principale
	 *            de vente
	 * @param idSession
	 *            : id de la session de l'utilisateur realisant l'operation
	 * @param etatVente
	 *            : etat de la vente courante ['comptant','credit']
	 * @param montantTotalVenteHt
	 *            : montant total hors taxe de la vente
	 * @param montantTotalVenteTva
	 *            : montant total de la Tva
	 * @param nomClient
	 *            : nom du client
	 * @param prenomClient
	 *            : prenom du client
	 * @param montantTotalVenteTtc
	 *            : montant total toutes taxes comprises
	 **/
	public int enregistreOperationDeVente(
			LinkedList<LigneDeVente> listeDeVente, int idSession,
			String etatVente, double montantTotalVenteHt,
			double montantTotalVenteTva, String nomClient, String prenomClient,
			double montantTotalVenteTtc) {
		int returnValue = SUCCES_OPERATION;
		vente = enregistrerVente(idSession,
				clientDAO.fidByName(nomClient, prenomClient), etatVente,
				montantTotalVenteHt, montantTotalVenteTva, montantTotalVenteTtc);
		/** Enregistrement de la vente **/
		if (vente != null) {
			/** Si l'operation d'enregistrement se passe bien ***/
			ListIterator<LigneDeVente> listeVente = listeDeVente.listIterator();
			LigneDeVente ligneCourante;
			while (listeVente.hasNext()) {
				/** Enregistrement de la ligne de vente ***/
				ligneCourante = listeVente.next();
				if (!ligneCourante.getDesignation().equals("")) {
					/** Pour ecarter les lignes vides ****/
					/*** Enregistrement de la ligne de vente **/
					Lignedevente ligneEnregistre = enregistrerLigneDeVente(
							vente,
							ligneCourante.getDesignation(),
							Integer.parseInt(ligneCourante.getQuantite()),
							Double.parseDouble(ligneCourante.getPrixGlobalHt()),
							Double.parseDouble(ligneCourante.getMontantTva()),
							Double.parseDouble(ligneCourante.getPrixGlobalTtc()));
					if (ligneEnregistre != null) {
						/** mis � jour du stock ***/
						categorie = categorieService.getCategorie(ligneCourante
								.getDesignation());
						/***
						 * Recuperation des informations concernant le produit
						 * courant
						 **/
						int quantiteStock = categorie.getQuantitestock()
								- Integer.parseInt(ligneCourante.getQuantite());
						/*** Calcule de la quantite devant rester en stock ****/
						categorieService.misAJourStock(quantiteStock,
								ligneCourante.getDesignation());
						/*** Mis � jour du stock ***/
					} else {
						returnValue = ECHEC_OPERATION;
						// echec de l'operation d'enregistrement de la ligne de
						// vente
					}
				}
			}
		}

		return returnValue;
	}
	public LinkedList<Lignedevente> retourneHistoriqueVente(Date dateDebut,Date dateFin) {
		return (LinkedList<Lignedevente>) ligneVenteDAO.findAll(dateDebut,dateFin);
	}
	/***Permet de retourner les lignes de ventes enregistr�es dans un intervalle de temps
	 * @param dateDebut: date de debut de l'intervalle
	 * @param dateFin : date de fin de l'intervalle
	 * **/
	public LinkedList<Lignedevente> statistiqueVente(Date dateDebut,Date dateFin,int idUtlisateur){
		return (LinkedList<Lignedevente>) ligneVenteDAO.findByDate(dateDebut, dateFin,idUtlisateur);
	}
	public LinkedList<Client> listeClient() {
		return (LinkedList<Client>) clientDAO.findAll();
	}
	public LinkedList<Lignedevente> retournerVente(int numeroVente){
		return (LinkedList<Lignedevente>) ligneVenteDAO.findAll(numeroVente);
	}
}
