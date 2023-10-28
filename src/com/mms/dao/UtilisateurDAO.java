package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Typeutilisateur;
import com.mms.domain.Utilisateur;

public class UtilisateurDAO extends DaoGenerique<Utilisateur> {

	@Override
	public Utilisateur create(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO Utilisateur(nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur,dateembaucheutilisateur,idtypeutilisateur) VALUES(?,?,?,?,?,?)");
			prepare.setString(1, utilisateur.getNomutilisateur());
			prepare.setString(2, utilisateur.getPrenomutilisateur());
			prepare.setString(3, utilisateur.getLoginutilisateur());
			prepare.setString(4, utilisateur.getTelephoneutilisateur());
			prepare.setDate(5, new java.sql.Date(utilisateur
					.getDateembaucheutilisateur().getTime()));
			prepare.setInt(6, utilisateur.getTypeutilisateur()
					.getIdtypeutilisateur());
			testeValue = prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findByLogin(utilisateur.getLoginutilisateur());
	}

	@Override
	public int delete(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM utilisateur WHERE loginutilisateur=?");
			prepare.setString(1, utilisateur.getLoginutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Utilisateur utilisateur) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE utilisateur SET nomutilisateur = ?,prenomutilisateur = ?,loginutilisateur = ?,telephoneutilisateur = ? WHERE idutilisateur = ?");
			prepare.setString(1, utilisateur.getNomutilisateur());
			prepare.setString(2, utilisateur.getPrenomutilisateur());
			prepare.setString(3, utilisateur.getLoginutilisateur());
			prepare.setString(4, utilisateur.getTelephoneutilisateur());
			prepare.setInt(5, utilisateur.getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public Utilisateur findByName(String name) {
		return null;
	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> listeGestionnaire = new LinkedList<Utilisateur>();
		Utilisateur utilisateur;
		Typeutilisateur typeUtilisateur = new Typeutilisateur();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur,idtypeutilisateur FROM utilisateur WHERE idtypeutilisateur <> 1");
			while (resultat.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setNomutilisateur(resultat
						.getString("nomutilisateur"));
				utilisateur.setPrenomutilisateur(resultat
						.getString("prenomutilisateur"));
				utilisateur.setLoginutilisateur(resultat
						.getString("loginutilisateur"));
				utilisateur.setTelephoneutilisateur(resultat
						.getString("telephoneutilisateur"));
				typeUtilisateur.setIdtypeutilisateur(resultat
						.getInt("idtypeutilisateur"));
				utilisateur.setTypeutilisateur(typeUtilisateur);
				listeGestionnaire.add(utilisateur);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeGestionnaire;
	}

	@Override
	public Utilisateur findById(int idUtilisateur) {

		Utilisateur utilisateur = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idutilisateur,nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur FROM  utilisateur WHERE idutilisateur = ?");
			prepare.setInt(1, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdutilisateur(resultat.getInt("idutilisateur"));
				utilisateur.setNomutilisateur(resultat.getString("nomutilisateur"));
				utilisateur.setPrenomutilisateur(resultat.getString("prenomutilisateur"));
				utilisateur.setTelephoneutilisateur(resultat.getString("telephoneutilisateur"));
				utilisateur.setLoginutilisateur(resultat.getString("loginutilisateur"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	
	}

	public Utilisateur findByLogin(String login) {
		Utilisateur utilisateur = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idutilisateur,nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur FROM  utilisateur WHERE loginutilisateur = ?");
			prepare.setString(1, login);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdutilisateur(resultat.getInt("idutilisateur"));
				utilisateur.setNomutilisateur(resultat.getString("nomutilisateur"));
				utilisateur.setPrenomutilisateur(resultat.getString("prenomutilisateur"));
				utilisateur.setTelephoneutilisateur(resultat.getString("telephoneutilisateur"));
				utilisateur.setLoginutilisateur(resultat.getString("loginutilisateur"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	public boolean ifAdminExiste() {
		boolean returnValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM utilisateur WHERE idtypeutilisateur = 1");
			if (resultat.next())
				returnValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}
	public Utilisateur findAdmin(){
		Utilisateur utilisateur=null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idutilisateur,nomutilisateur,prenomutilisateur,telephoneutilisateur,loginutilisateur FROM utilisateur WHERE idtypeutilisateur = 1");
			if (resultat.next()){
				utilisateur = new Utilisateur();
				utilisateur.setIdutilisateur(resultat.getInt("idutilisateur"));
				utilisateur.setNomutilisateur(resultat
						.getString("nomutilisateur"));
				utilisateur.setPrenomutilisateur(resultat
						.getString("prenomutilisateur"));
				utilisateur.setTelephoneutilisateur(resultat
						.getString("telephoneutilisateur"));
				utilisateur.setLoginutilisateur(resultat
						.getString("loginutilisateur"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	public static void main(String[] args) {
	}
}
