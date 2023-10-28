package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Client;
import com.mms.domain.Etat;
import com.mms.domain.Session;
import com.mms.domain.Vente;

public class VenteDAO extends DaoGenerique<Vente> {

	@Override
	public Vente create(Vente vente) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO vente(idsession,idetat,idclient,montanttotalventeht,montanttotalventetva,montanttotalventettc,datevente) VALUES(?,?,?,?,?,?,?)");
			prepare.setInt(1, vente.getSession().getIdsession());
			prepare.setInt(2, vente.getEtat().getIdetat());
			prepare.setInt(3, vente.getClient().getIdclient());
			prepare.setDouble(4, vente.getMontantTotalVenteHt());
			prepare.setDouble(5, vente.getMontantTotalVenteTva());
			prepare.setDouble(6, vente.getMontantTotalVenteTtc());
			prepare.setDate(7,
					new java.sql.Date(vente.getDatevente().getTime()));
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findLast();
	}

	@Override
	public int delete(Vente vente) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM vente WHERE numerovente = ?");
			prepare.setInt(1, vente.getNumerovente());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Vente vente) {
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE vente SET idclient = ?,montanttotalventeht = ?,montanttotalventetva = ?,montanttotalttc = ? WHERE numerovente = ?");
			prepare.setInt(1, vente.getClient().getIdclient());
			prepare.setDouble(2, vente.getMontantTotalVenteHt());
			prepare.setDouble(3, vente.getMontantTotalVenteTva());
			prepare.setDouble(4, vente.getMontantTotalVenteTtc());
			prepare.setInt(5, vente.getNumerovente());
			returnValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Vente findByName(String name) {
		return null;
	}
	@Override
	public List<Vente> findAll() {
		List<Vente> listeVente = new LinkedList<>();
		Vente vente;
		Client client;
		Etat etat;
		Session session;
		try {
			Statement statement = connection
					.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT numerovente,idsession,idetat,idclient,montanttotalventeht,montanttotalventetva,montanttotalventettc,datevente FROM vente");
			while (resultat.next()) {
				vente = new Vente();
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				vente.setClient(client);
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				vente.setEtat(etat);
				session = new Session();
				session.setIdsession(resultat.getInt("idsession"));
				vente.setSession(session);
				vente.setMontantTotalVenteHt(resultat.getDouble("montanttotalventeht"));
				vente.setMontantTotalVenteTva(resultat.getDouble("montanttotalventetva"));
				vente.setMontantTotalVenteTtc(resultat.getDouble("montanttotalventettc"));
				vente.setDatevente((resultat.getDate("datevente")));
				listeVente.add(vente);
			}
			resultat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVente;

	}

	@Override
	public Vente findById(int id) {

		Vente vente = null;
		Client client;
		Etat etat;
		Session session;
		try {
			PreparedStatement prepare = connection.prepareStatement("SELECT numerovente,idsession,idetat,idclient,montanttotalventeht,montanttotalventetva,montanttotalventettc,datevente FROM vente WHERE numerovente = ? ");
			prepare.setInt(1, id);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				vente = new Vente();
				vente.setNumerovente(id);
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				vente.setClient(client);
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				vente.setEtat(etat);
				session = new Session();
				session.setIdsession(resultat.getInt("idsession"));
				vente.setSession(session);
				vente.setMontantTotalVenteHt(resultat.getDouble("montanttotalventeht"));
				vente.setMontantTotalVenteTva(resultat.getDouble("montanttotalventetva"));
				vente.setMontantTotalVenteTtc(resultat.getDouble("montanttotalventettc"));
				vente.setDatevente(new java.util.Date(resultat.getDate("datevente").getTime()));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vente;

	}
	
	public Vente findLast() {
		Vente vente = null;
		Client client;
		Etat etat;
		Session session;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT numerovente,idsession,idetat,idclient,montanttotalventeht,montanttotalventetva,montanttotalventettc,datevente FROM vente WHERE numerovente = (SELECT max(numerovente) FROM vente)");
			if (resultat.next()) {
				vente = new Vente();
				vente.setNumerovente(resultat.getInt("numerovente"));
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				vente.setClient(client);
				etat = new Etat();
				etat.setIdetat(resultat.getInt("idetat"));
				vente.setEtat(etat);
				session = new Session();
				session.setIdsession(resultat.getInt("idsession"));
				vente.setSession(session);
				vente.setMontantTotalVenteHt(resultat
						.getDouble("montanttotalventeht"));
				vente.setMontantTotalVenteTva(resultat
						.getDouble("montanttotalventetva"));
				vente.setMontantTotalVenteTtc(resultat
						.getDouble("montanttotalventettc"));
				vente.setDatevente(new java.util.Date(resultat.getDate(
						"datevente").getTime()));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vente;
	}
	
}
