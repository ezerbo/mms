package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.pojos.Categorie;

public class CategorieDAO extends DaoGenerique<Categorie> {

	@Override
	public Categorie create(Categorie categorie) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO categorie(designation,prixunitaireachat,prixunitairevente,quantitestock,quantiteideale,quantitesecurite) VALUES(?,?,?,?,?,?)");
			prepare.setString(1, categorie.getDesignation());
			prepare.setInt(2, categorie.getPrixunitaireachat());
			prepare.setInt(3, categorie.getPrixunitairevente());
			prepare.setInt(4, categorie.getQuantitestock());
			prepare.setInt(5, categorie.getQuantiteIdeale());
			prepare.setInt(6, categorie.getQuantiteSecurite());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			testeValue = 0;
		}
		if (testeValue == 0)
			return null;
		else
			return findByName(categorie.getDesignation());
	}

	@Override
	public int delete(Categorie categorie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Categorie categorie) {
		return 0;
	}
	public int update(Categorie categorie, String designation) {
		// TODO Auto-generated method stub
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE categorie SET designation = ?,prixunitaireachat = ?,prixunitairevente = ?,quantiteideale = ?,quantitesecurite = ? WHERE designation = ?");
			prepare.setString(1, categorie.getDesignation());
			prepare.setInt(2, categorie.getPrixunitaireachat());
			prepare.setInt(3, categorie.getPrixunitairevente());
			prepare.setInt(4, categorie.getQuantiteIdeale());
			prepare.setInt(5, categorie.getQuantiteSecurite());
			prepare.setString(6, designation);
			returnValue = prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}
	public int update(int quantite, String designation) {
		int returnValue = 0;
		PreparedStatement prepare;
		try {
			prepare = connection
					.prepareStatement("UPDATE categorie SET quantitestock= ? WHERE designation = ?");
			prepare.setInt(1, quantite);
			prepare.setString(2, designation);
			returnValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}
	@Override
	public Categorie findByName(String designation) {
		// TODO Auto-generated method stub
		Categorie categorie = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idcategorie,designation,quantitestock,prixunitairevente,prixunitaireachat,quantiteideale,quantitesecurite FROM categorie WHERE designation = ?");
			prepare.setString(1, designation);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next()) {
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				categorie.setDesignation(resultat.getString("designation"));
				categorie.setQuantitestock(resultat.getInt("quantitestock"));
				categorie.setPrixunitaireachat(resultat
						.getInt("prixunitaireachat"));
				categorie.setPrixunitairevente(resultat
						.getInt("prixunitairevente"));
				categorie.setQuantiteIdeale(resultat.getInt("quantiteideale"));
				categorie.setQuantiteSecurite(resultat
						.getInt("quantitesecurite"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		List<Categorie> listeProduit = new LinkedList<Categorie>();
		Categorie categorie = null;
		try {
			Statement prepare = connection.createStatement();
			for (ResultSet resultat = prepare.executeQuery("SELECT idcategorie,designation,quantitestock,prixunitairevente,prixunitaireachat,quantiteideale,quantitesecurite FROM categorie "); resultat.next();) {
				categorie = new Categorie();
				categorie.setDesignation(resultat.getString("designation"));
				categorie.setQuantitestock(resultat.getInt("quantitestock"));
				categorie.setPrixunitaireachat(resultat.getInt("prixunitaireachat"));
				categorie.setPrixunitairevente(resultat.getInt("prixunitairevente"));
				categorie.setQuantiteIdeale(resultat.getInt("quantiteideale"));
				categorie.setQuantiteSecurite(resultat.getInt("quantitesecurite"));
				listeProduit.add(categorie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeProduit;
	}

	@Override
	public Categorie findById(int id) {
		// TODO Auto-generated method stub
		Categorie categorie = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idcategorie,designation,quantitestock,prixunitairevente,prixunitaireachat,quantiteideale,quantitesecurite FROM categorie WHERE idcategorie = ?");
			prepare.setInt(1, id);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next()) {
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				categorie.setDesignation(resultat.getString("designation"));
				categorie.setQuantitestock(resultat.getInt("quantitestock"));
				categorie.setPrixunitaireachat(resultat
						.getInt("prixunitaireachat"));
				categorie.setPrixunitairevente(resultat
						.getInt("prixunitairevente"));
				categorie.setQuantiteIdeale(resultat.getInt("quantiteideale"));
				categorie.setQuantiteSecurite(resultat
						.getInt("quantitesecurite"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}

}
