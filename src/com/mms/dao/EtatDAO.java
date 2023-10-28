package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Etat;

public class EtatDAO extends DaoGenerique<Etat> {

	@Override
	public Etat create(Etat etat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Etat objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Etat objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Etat findByName(String libelleetat) {
		// TODO Auto-generated method stub
		Etat etat = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idetat,libelleetat FROM etat WHERE libelleetat = ?");
			prepare.setString(1, libelleetat);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				etat.setLibelleetat(resultat.getString("libelleetat"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etat;
	}

	@Override
	public List<Etat> findAll() {
		// TODO Auto-generated method stub
		List<Etat> listeEtat = new LinkedList<Etat>();
		Etat etat = null;
		try {
			Statement statement = connection.createStatement();
			for (ResultSet resultat = statement
					.executeQuery("SELECT idetat,libelleetat FROM etat"); resultat
					.next();) {
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				etat.setLibelleetat(resultat.getString("libelleetat"));
				listeEtat.add(etat);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeEtat;
	}

	@Override
	public Etat findById(int id) {
		// TODO Auto-generated method stub

		Etat etat = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idetat,libelleetat FROM etat WHERE idetat = ?");
			prepare.setInt(1, id);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				etat.setLibelleetat(resultat.getString("libelleetat"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etat;
	}

}
