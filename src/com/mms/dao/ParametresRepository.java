package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Parameters;

public class ParametresRepository extends DaoGenerique<Parameters> {

	@Override
	public Parameters create(Parameters objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Parameters objet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Parameters parameters) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE parametres SET dureeviemdp = ?,longueurmdp = ?,tentativemdp = ?,tempsinactivitesmdp = ?");
			prepare.setInt(1, parameters.getDureeviemdp());
			prepare.setInt(2, parameters.getLongueurmdp());
			prepare.setInt(3, parameters.getTentativemdp());
			prepare.setInt(4, parameters.getTempsinactivitesmdp());
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
	public Parameters findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parameters> findAll() {
		// TODO Auto-generated method stub
		List<Parameters> listeparametres = new LinkedList<Parameters>();
		Parameters parameters;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT dureeviemdp,longueurmdp,tentativemdp,tempsinactivitesmdp,tauxtva FROM parametres");
			if(resultat.next()){
				parameters = new Parameters();
				parameters.setDureeviemdp(resultat.getInt("dureeviemdp"));
				parameters.setTentativemdp(resultat.getInt("tentativemdp"));
				parameters.setTauxtva(resultat.getDouble("tauxtva"));
				parameters.setTempsinactivitesmdp(resultat.getInt("tempsinactivitesmdp"));
				parameters.setLongueurmdp(resultat.getInt("longueurmdp"));
				listeparametres.add(parameters);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeparametres;
	}

	@Override
	public Parameters findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
