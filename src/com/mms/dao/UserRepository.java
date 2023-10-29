package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.UserType;
import com.mms.domain.User;

public class UserRepository extends DaoGenerique<User> {

	@Override
	public User create(User user) {
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO Utilisateur(nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur,dateembaucheutilisateur,idtypeutilisateur) VALUES(?,?,?,?,?,?)");
			prepare.setString(1, user.getNomutilisateur());
			prepare.setString(2, user.getPrenomutilisateur());
			prepare.setString(3, user.getLoginutilisateur());
			prepare.setString(4, user.getTelephoneutilisateur());
			prepare.setDate(5, new java.sql.Date(user.getDateembaucheutilisateur().getTime()));
			prepare.setInt(6, user.getUserType().getIdtypeutilisateur());
			return findByLogin(user.getLoginutilisateur());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int delete(User user) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM utilisateur WHERE loginutilisateur=?");
			prepare.setString(1, user.getLoginutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(User user) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE utilisateur SET nomutilisateur = ?,prenomutilisateur = ?,loginutilisateur = ?,telephoneutilisateur = ? WHERE idutilisateur = ?");
			prepare.setString(1, user.getNomutilisateur());
			prepare.setString(2, user.getPrenomutilisateur());
			prepare.setString(3, user.getLoginutilisateur());
			prepare.setString(4, user.getTelephoneutilisateur());
			prepare.setInt(5, user.getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public User findByName(String name) {
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> listeGestionnaire = new LinkedList<User>();
		User user;
		UserType typeUtilisateur = new UserType();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur,idtypeutilisateur FROM utilisateur WHERE idtypeutilisateur <> 1");
			while (resultat.next()) {
				user = new User();
				user.setNomutilisateur(resultat
						.getString("nomutilisateur"));
				user.setPrenomutilisateur(resultat
						.getString("prenomutilisateur"));
				user.setLoginutilisateur(resultat
						.getString("loginutilisateur"));
				user.setTelephoneutilisateur(resultat
						.getString("telephoneutilisateur"));
				typeUtilisateur.setIdtypeutilisateur(resultat
						.getInt("idtypeutilisateur"));
				user.setUserType(typeUtilisateur);
				listeGestionnaire.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeGestionnaire;
	}

	@Override
	public User findById(int idUtilisateur) {

		User user = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idutilisateur,nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur FROM  utilisateur WHERE idutilisateur = ?");
			prepare.setInt(1, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				user = new User();
				user.setIdutilisateur(resultat.getInt("idutilisateur"));
				user.setNomutilisateur(resultat.getString("nomutilisateur"));
				user.setPrenomutilisateur(resultat.getString("prenomutilisateur"));
				user.setTelephoneutilisateur(resultat.getString("telephoneutilisateur"));
				user.setLoginutilisateur(resultat.getString("loginutilisateur"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	
	}

	public User findByLogin(String login) {
		User user = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idutilisateur,nomutilisateur,prenomutilisateur,loginutilisateur,telephoneutilisateur FROM  utilisateur WHERE loginutilisateur = ?");
			prepare.setString(1, login);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				user = new User();
				user.setIdutilisateur(resultat.getInt("idutilisateur"));
				user.setNomutilisateur(resultat.getString("nomutilisateur"));
				user.setPrenomutilisateur(resultat.getString("prenomutilisateur"));
				user.setTelephoneutilisateur(resultat.getString("telephoneutilisateur"));
				user.setLoginutilisateur(resultat.getString("loginutilisateur"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public boolean isAdminAccountCreated() {
		boolean returnValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM utilisateur WHERE idtypeutilisateur = 1");
			if (resultat.next())
				returnValue = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	public User findAdmin(){
		User user =null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idutilisateur,nomutilisateur,prenomutilisateur,telephoneutilisateur,loginutilisateur FROM utilisateur WHERE idtypeutilisateur = 1");
			if (resultat.next()){
				user = new User();
				user.setIdutilisateur(resultat.getInt("idutilisateur"));
				user.setNomutilisateur(resultat
						.getString("nomutilisateur"));
				user.setPrenomutilisateur(resultat
						.getString("prenomutilisateur"));
				user.setTelephoneutilisateur(resultat
						.getString("telephoneutilisateur"));
				user.setLoginutilisateur(resultat
						.getString("loginutilisateur"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public static void main(String[] args) {
	}
}
