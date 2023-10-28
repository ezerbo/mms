package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Fournisseur;

public class FournisseurDAO extends DaoGenerique<Fournisseur> {

	@Override
	public Fournisseur create(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO fournisseur(nomfournisseur,prenomfournisseur,telephonefournisseur) VALUES(?,?,?)");
			prepare.setString(1, fournisseur.getNomFournisseur());
			prepare.setString(2, fournisseur.getPrenomFournisseur());
			prepare.setString(3, fournisseur.getTelephonefournisseur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			testeValue = 0;
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findByTel(fournisseur.getTelephonefournisseur());
	}

	@Override
	public int delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM fournisseur WHERE telephonefournisseur = ?");
			prepare.setString(1, fournisseur.getTelephonefournisseur());
			testeValue = prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE fournisseur SET nomfournisseur = ?,prenomfournisseur = ?,telephonefournisseur = ? WHERE idfournisseur = ?");
			prepare.setString(1, fournisseur.getNomFournisseur());
			prepare.setString(2, fournisseur.getPrenomFournisseur());
			prepare.setString(3, fournisseur.getTelephonefournisseur());
			prepare.setInt(4, fournisseur.getIdfournisseur());
			returnValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Fournisseur findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fournisseur> findAll() {
		// TODO Auto-generated method stub
		List<Fournisseur> listeFournisseur = new LinkedList<Fournisseur>();
		Fournisseur fournisseur = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idfournisseur,nomfournisseur,prenomfournisseur,telephonefournisseur FROM fournisseur");
			while (resultat.next()) {
				fournisseur = new Fournisseur();
				fournisseur.setIdfournisseur(resultat.getInt("idfournisseur"));
				fournisseur.setNomFournisseur(resultat.getString("nomfournisseur"));
				fournisseur.setPrenomFournisseur(resultat
						.getString("prenomfournisseur"));
				fournisseur.setTelephonefournisseur(resultat
						.getString("telephonefournisseur"));
				listeFournisseur.add(fournisseur);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeFournisseur;
	}

	@Override
	public Fournisseur findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Fournisseur findByTel(String telephone) {
		// TODO Auto-generated method stub
		Fournisseur fournisseur = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idfournisseur,nomfournisseur,prenomfournisseur,telephonefournisseur FROM fournisseur WHERE telephonefournisseur = ?");
			prepare.setString(1, telephone);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				fournisseur = new Fournisseur();
				fournisseur.setIdfournisseur(resultat.getInt("idfournisseur"));
				fournisseur.setNomFournisseur(resultat.getString("nomfournisseur"));
				fournisseur.setPrenomFournisseur(resultat
						.getString("prenomfournisseur"));
				fournisseur.setTelephonefournisseur(resultat
						.getString("telephonefournisseur"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fournisseur;
	}
}
