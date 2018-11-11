package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.pojos.Categorie;
import com.mms.pojos.Produit;

public class ProduitDAO extends DaoGenerique<Produit> {

	@Override
	public Produit create(Produit produit) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO produit(idcategorie) VALUES(?)");
			prepare.setInt(1, produit.getCategorie().getIdcategorie());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findLast();
	}

	@Override
	public int delete(Produit produit) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM produit WHERE idproduit = ?");
			prepare.setInt(1, produit.getIdproduit());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Produit produit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> findAll() {
		// TODO Auto-generated method stub
		List<Produit> listeProduit = new LinkedList<Produit>();
		Produit produit = null;
		Categorie categorie = null;
		try {
			Statement statement = connection.createStatement();
			for (ResultSet resultat = statement
					.executeQuery("SELECT idproduit,idcategorie FROM produit"); resultat
					.next();) {
				produit = new Produit();
				produit.setIdproduit(resultat.getInt("idproduit"));
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				produit.setCategorie(categorie);
				listeProduit.add(produit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeProduit;
	}
	public Produit findLast() {
		Produit produit = null;
		Categorie categorie = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idproduit,idcategorie FROM produit WHERE idproduit = (SELECT max(idproduit) FROM produit )");
			if (resultat.next()) {
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				produit = new Produit();
				produit.setCategorie(categorie);
				produit.setIdproduit(resultat.getInt("idproduit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produit;
	}
	@Override
	public Produit findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
