package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Client;

public class ClientDAO extends DaoGenerique<Client> {

	@Override
	public Client create(Client client) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO client(nomclient,prenomclient,telephoneclient) VALUES(?,?,?)");
			prepare.setString(1, client.getNomClient());
			prepare.setString(2, client.getPrenomClient());
			prepare.setString(3, client.getTelephoneclient());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			testeValue = 0;
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return findByTel(client.getTelephoneclient());
	}

	@Override
	public int delete(Client client) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("DELETE FROM client WHERE telephoneclient = ?");
			prepare.setString(1, client.getTelephoneclient());
			testeValue = prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Client client) {
		// TODO Auto-generated method stub
		int returnValue = 0;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("UPDATE client SET nomclient = ?,prenomclient = ?,telephoneclient = ? WHERE idclient = ?");
			prepare.setString(1, client.getNomClient());
			prepare.setString(2, client.getPrenomClient());
			prepare.setString(3, client.getTelephoneclient());
			prepare.setInt(4, client.getIdclient());
			returnValue = prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Client findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public Client fidByName(String nomClient, String prenomClient) {
		Client client = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idclient,telephoneclient FROM client WHERE nomclient = ? AND prenomclient = ?");
			prepare.setString(1, nomClient);
			prepare.setString(2, prenomClient);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				client.setNomClient(prenomClient);
				client.setPrenomClient(prenomClient);
				client.setTelephoneclient(resultat.getString("telephoneclient"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		List<Client> listeClient = new LinkedList<Client>();
		Client client = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement
					.executeQuery("SELECT idclient,nomclient,prenomclient,telephoneclient FROM client");
			while (resultat.next()) {
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				client.setNomClient(resultat.getString("nomclient"));
				client.setPrenomClient(resultat.getString("prenomclient"));
				client.setTelephoneclient(resultat.getString("telephoneclient"));
				listeClient.add(client);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeClient;
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Client findByTel(String telephone) {
		// TODO Auto-generated method stub
		Client client = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idclient,nomclient,prenomclient,telephoneclient FROM client WHERE telephoneclient = ?");
			prepare.setString(1, telephone);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				client = new Client();
				client.setIdclient(resultat.getInt("idclient"));
				client.setNomClient(resultat.getString("nomclient"));
				client.setPrenomClient(resultat.getString("prenomclient"));
				client.setTelephoneclient(resultat.getString("telephoneclient"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

}
