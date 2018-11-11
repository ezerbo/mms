package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.pojos.Motdepasse;
import com.mms.pojos.Utilisateur;

public class MotDePasseDAO extends DaoGenerique<Motdepasse> {

	@Override
	public Motdepasse create(Motdepasse motDePasse) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO motdepasse(valeurmdp,datecreationmdp,datevaliditemdp,idutilisateur) VALUES(?,?,?,?)");
			prepare.setString(1, motDePasse.getValeurmdp());
			prepare.setDate(2, new java.sql.Date(motDePasse
					.getDatecreationmdp().getTime()));
			prepare.setDate(3, new java.sql.Date(motDePasse
					.getDatevaliditemdp().getTime()));
			prepare.setInt(4, motDePasse.getUtilisateur().getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findUserLastPassword(motDePasse.getUtilisateur()
					.getIdutilisateur());
	}

	@Override
	public int delete(Motdepasse motDePasse) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM motdepasse WHERE idutilisateur = ?");
			prepare.setInt(1, motDePasse.getUtilisateur().getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}
	public String adminPassword(int idUtilisateur) {
		String returnValue = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT valeurmdp FROM motdepasse WHERE idutilisateur = ?");
			prepare.setInt(1, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				returnValue = resultat.getString("valeurmdp");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}
	@Override
	public int update(Motdepasse motDePasse) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE motdepasse SET valeurMDP = ? WHERE idmdp = ?");
			prepare.setString(1, motDePasse.getValeurmdp());
			prepare.setInt(2, motDePasse.getIdmdp());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}
	public int updateDateMdp(Motdepasse motDePasse) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE motdepasse SET datecreationmdp = ?,datevaliditemdp = ?");
			prepare.setDate(1, new java.sql.Date(motDePasse
					.getDatecreationmdp().getTime()));
			prepare.setDate(2, new java.sql.Date(motDePasse
					.getDatevaliditemdp().getTime()));
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}
	@Override
	public Motdepasse findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Motdepasse> findAll() {
		// TODO Auto-generated method stub
		List<Motdepasse> listeMotDePasse = new LinkedList<Motdepasse>();
		Motdepasse motDePasse;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idmdp,valeurmdp,datecreationmdp,datevaliditemdp,idutilisateur FROM motdepasse");
			while (resultat.next()) {
				motDePasse = new Motdepasse();
				motDePasse.setIdmdp(resultat.getInt("idmdp"));
				motDePasse.setValeurmdp(resultat.getString("valeurmdp"));
				motDePasse.setDatecreationmdp(new java.util.Date(resultat
						.getDate("datecreationmdp").getTime()));
				motDePasse.setDatevaliditemdp(new java.util.Date(resultat
						.getDate("datevaliditemdp").getTime()));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdutilisateur(resultat.getInt("idutilisateur"));
				motDePasse.setUtilisateur(utilisateur);
				listeMotDePasse.add(motDePasse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeMotDePasse;
	}

	@Override
	public Motdepasse findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Motdepasse findUserLastPassword(int idUtilisateur) {
		Motdepasse motDePasse = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idmdp,valeurmdp,datecreationmdp,datevaliditemdp FROM motdepasse WHERE idutilisateur = ? AND idmdp = (SELECT max(idmdp) FROM motdepasse WHERE idutilisateur = ?)");
			prepare.setInt(1, idUtilisateur);
			prepare.setInt(2, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				motDePasse = new Motdepasse();
				motDePasse.setIdmdp(resultat.getInt("idmdp"));
				motDePasse.setValeurmdp(resultat.getString("valeurmdp"));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdutilisateur(idUtilisateur);
				motDePasse.setUtilisateur(utilisateur);
				motDePasse.setDatecreationmdp(new java.util.Date(resultat
						.getDate("datecreationmdp").getTime()));
				motDePasse.setDatevaliditemdp(new java.util.Date(resultat
						.getDate("datevaliditemdp").getTime()));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return motDePasse;
	}
	public boolean verifierExistenceMotDePasse(int idUtilisateur, String mdp) {
		boolean testeValue = false;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT * FROM motdepasse WHERE idutilisateur = ? AND valeurmdp = ?");
			prepare.setInt(1, idUtilisateur);
			prepare.setString(2, mdp);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next())
				testeValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}
}
