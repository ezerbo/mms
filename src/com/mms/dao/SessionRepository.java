package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mms.domain.Session;

public class SessionRepository extends DaoGenerique<Session> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Session create(Session session) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO session(idutilisateur,datedebutsession,datefinsession,dureesession) VALUES(?,?,?,?)");
			prepare.setInt(1, session.getUser().getIdutilisateur());
			prepare.setString(2, session.getDatedebutsession());
			prepare.setString(3, session.getDatefinsession());
			prepare.setInt(4, session.getDureesession());
			testeValue = prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return getLatestSession(session.getUser()
					.getIdutilisateur());
	}
	public Session getLatestSession(int idUtilisateur) {

		PreparedStatement prepare;
		Session session = null;
		try {
			prepare = connection
					.prepareStatement("SELECT idsession,datedebutsession,datefinsession,dureesession FROM session WHERE idutilisateur = ? AND idsession = (SELECT max(idsession) FROM session)");
			prepare.setInt(1, idUtilisateur);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				session = new Session();
				session.setIdsession(resultat.getInt("idsession"));
				session.setDatedebutsession(resultat
						.getString("datedebutsession"));
				session.setDatefinsession(resultat.getString("datefinsession"));
				session.setDureesession(resultat.getInt("dureesession"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	}
	@Override
	public int delete(Session session) {
		// TODO Auto-generated method stub
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM session WHERE idutilisateur = ?");
			prepare.setInt(1, session.getUser().getIdutilisateur());
			returnValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public int update(Session session) {
		// TODO Auto-generated method stub
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection.prepareStatement("UPDATE session SET datefinsession = ?,dureesession = ? WHERE idsession = ?");
		prepare.setString(1, session.getDatefinsession());
		prepare.setInt(2, session.getDureesession());
		prepare.setInt(3, session.getIdsession());
		returnValue = prepare.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}

	@Override
	public Session findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Session> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
