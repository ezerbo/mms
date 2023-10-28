package com.mms.service;

import com.mms.dao.ProduitDAO;
import com.mms.domain.Categorie;
import com.mms.domain.Produit;

public class ProduitService {
	private ProduitDAO produitDAO = new ProduitDAO();
	public ProduitService() {

	}
	public Produit creerProduit(int idProduit, Categorie categorie) {
		Produit produit = new Produit();
		produit.setIdproduit(idProduit);
		produit.setCategorie(categorie);
		return produitDAO.create(produit);
	}

	public int supprimerProduit(int idProduit) {
		Produit produit = new Produit();
		produit.setIdproduit(idProduit);
		return produitDAO.delete(produit);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
