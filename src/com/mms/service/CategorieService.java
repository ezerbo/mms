package com.mms.service;

import java.util.LinkedList;

import com.mms.dao.CategorieDAO;
import com.mms.pojos.Categorie;

public class CategorieService {
	private CategorieDAO categorieDAO = new CategorieDAO();
	private Categorie categorie;
	public Categorie creerCategorie(String designation,Integer prixUnitaireAchat, Integer prixUniatireVente,Integer quantiteInitiale, Integer quantiteIdeale,Integer quantiteSecurite) {
		categorie = new Categorie();
		categorie.setDesignation(designation);
		categorie.setPrixunitaireachat(prixUnitaireAchat);
		categorie.setPrixunitairevente(prixUniatireVente);
		categorie.setQuantiteIdeale(quantiteIdeale);
		categorie.setQuantiteSecurite(quantiteSecurite);
		categorie.setQuantitestock(quantiteInitiale);
		return categorieDAO.create(categorie);
	}
	public int supprimerCategorie(int idCategorie) {
		categorie = new Categorie();
		categorie.setIdcategorie(idCategorie);
		return categorieDAO.delete(categorie);
	}
	public int mettreAJourCategorie(String nouvelleDesignation,String ancienneDesignation,Integer prixUnitaireAchat, Integer prixUniatireVente,Integer quantiteIdeale, Integer quantiteSecurite) {
		categorie = new Categorie();
		categorie.setDesignation(nouvelleDesignation);
		categorie.setPrixunitaireachat(prixUnitaireAchat);
		categorie.setPrixunitairevente(prixUniatireVente);
		categorie.setQuantiteIdeale(quantiteIdeale);
		categorie.setQuantiteSecurite(quantiteSecurite);
		return categorieDAO.update(categorie,ancienneDesignation);
	}
	public int misAJourStock(int quantite,String designation){
		return categorieDAO.update(quantite, designation);
	}
	public Categorie getCategorie(String designation){
		return categorieDAO.findByName(designation);
	}
	public LinkedList<Categorie> listeCategorie() {
		return (LinkedList<Categorie>) categorieDAO.findAll();
	}

}
