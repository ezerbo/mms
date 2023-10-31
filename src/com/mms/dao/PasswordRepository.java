package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Password;
import com.mms.domain.User;

public class PasswordRepository extends DaoGenerique<Password> {

	@Override
	public Password create(Password motDePasse) {
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
			prepare.setInt(4, motDePasse.getUser().getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findCurrentPwd(motDePasse.getUser()
					.getIdutilisateur());
	}

	@Override
	public int delete(Password motDePasse) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM motdepasse WHERE idutilisateur = ?");
			prepare.setInt(1, motDePasse.getUser().getIdutilisateur());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}
	public String getAdminPassword(int idUtilisateur) {
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
	public int update(Password motDePasse) {
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
	public int updateDateMdp(Password motDePasse) {
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
	public Password findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Password> findAll() {
		// TODO Auto-generated method stub
		List<Password> listeMotDePasse = new LinkedList<Password>();
		Password motDePasse;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idmdp,valeurmdp,datecreationmdp,datevaliditemdp,idutilisateur FROM motdepasse");
			while (resultat.next()) {
				motDePasse = new Password();
				motDePasse.setIdmdp(resultat.getInt("idmdp"));
				motDePasse.setValeurmdp(resultat.getString("valeurmdp"));
				motDePasse.setDatecreationmdp(new java.util.Date(resultat
						.getDate("datecreationmdp").getTime()));
				motDePasse.setDatevaliditemdp(new java.util.Date(resultat
						.getDate("datevaliditemdp").getTime()));
				User user = new User();
				user.setIdutilisateur(resultat.getInt("idutilisateur"));
				motDePasse.setUser(user);
				listeMotDePasse.add(motDePasse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeMotDePasse;
	}

	@Override
	public Password findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Password findCurrentPwd(int idUtilisateur) {
		Password motDePasse = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idmdp,valeurmdp,datecreationmdp,datevaliditemdp FROM motdepasse WHERE idutilisateur = ? AND idmdp = (SELECT max(idmdp) FROM motdepasse WHERE idutilisateur = ?)");
			prepare.setInt(1, idUtilisateur);
			prepare.setInt(2, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				motDePasse = new Password();
				motDePasse.setIdmdp(resultat.getInt("idmdp"));
				motDePasse.setValeurmdp(resultat.getString("valeurmdp"));
				User user = new User();
				user.setIdutilisateur(idUtilisateur);
				motDePasse.setUser(user);
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
	public boolean passwordExists(int idUtilisateur, String mdp) {
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
			e.printStackTrace();
		}
		return testeValue;
	}
}
