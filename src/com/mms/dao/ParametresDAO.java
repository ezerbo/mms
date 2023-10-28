package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Parametres;

public class ParametresDAO extends DaoGenerique<Parametres> {

	@Override
	public Parametres create(Parametres objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Parametres objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Parametres parametres) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE parametres SET dureeviemdp = ?,longueurmdp = ?,tentativemdp = ?,tempsinactivitesmdp = ?");
			prepare.setInt(1, parametres.getDureeviemdp());
			prepare.setInt(2, parametres.getLongueurmdp());
			prepare.setInt(3, parametres.getTentativemdp());
			prepare.setInt(4, parametres.getTempsinactivitesmdp());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	public int updateTva(float tva) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE parametres SET tauxtva = ?");
			prepare.setFloat(1, tva);
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testeValue;
	}

	@Override
	public Parametres findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parametres> findAll() {
		// TODO Auto-generated method stub
		List<Parametres> listeparametres = new LinkedList<Parametres>();
		Parametres parametres ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT dureeviemdp,longueurmdp,tentativemdp,tempsinactivitesmdp,tauxtva FROM parametres");
			if(resultat.next()){
				parametres = new Parametres();
				parametres.setDureeviemdp(resultat.getInt("dureeviemdp"));
				parametres.setTentativemdp(resultat.getInt("tentativemdp"));
				parametres.setTauxtva(resultat.getDouble("tauxtva"));
				parametres.setTempsinactivitesmdp(resultat.getInt("tempsinactivitesmdp"));
				parametres.setLongueurmdp(resultat.getInt("longueurmdp"));
				listeparametres.add(parametres);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeparametres;
	}

	@Override
	public Parametres findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
