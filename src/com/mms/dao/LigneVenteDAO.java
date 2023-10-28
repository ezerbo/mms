package com.mms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mms.domain.Categorie;
import com.mms.domain.Lignedevente;
import com.mms.domain.Vente;

public class LigneVenteDAO extends DaoGenerique<Lignedevente> {

	@Override
	public Lignedevente create(Lignedevente ligneDeVente) {
		// TODO Auto-generated method stub
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection.prepareStatement("INSERT INTO lignedevente(numerovente,idcategorie,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc) VALUES(?,?,?,?,?,?)");
			prepare.setInt(1, ligneDeVente.getVente().getNumerovente());
			prepare.setInt(2, ligneDeVente.getCategorie().getIdcategorie());
			prepare.setInt(3, ligneDeVente.getQuantitelignevente());
			prepare.setDouble(4, ligneDeVente.getMontantligneventeht());
			prepare.setDouble(5, ligneDeVente.getMontantLigneVenteTva());
			prepare.setDouble(6, ligneDeVente.getMontantligneventettc());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (testeValue == 0)
			return null;
		else
			return getLast();
	}
	public LinkedList<Lignedevente> venteRealisees(Date dateDebut,Date dateFin) {
		LinkedList<Lignedevente> listeCredit = new LinkedList<Lignedevente>();
		Lignedevente ligneDeVente = new Lignedevente();
		try {
			PreparedStatement prepare = connection.prepareStatement("SELECT DISTINCT(idcategorie),SUM(quantitelignevente),SUM(montantligneventeht),SUM(montantligneventetva),SUM(montantligneventettc) FROM lignedevente,vente WHERE vente.numerovente=lignedevente.numerovente AND vente.idetat=1 AND numerovente IN (SELECT numerovente FROM vente WHERE ( datevente>=? AND datevente<=? ) OR ( datevente>=? AND datevente<=? ) ) GROUP BY idcategorie");
			java.sql.Date date1 = new java.sql.Date(dateDebut.getTime());
			java.sql.Date date2 = new java.sql.Date(dateFin.getTime());
			prepare.setDate(1, date1);
			prepare.setDate(2, date2);
			prepare.setDate(3, date2);
			prepare.setDate(4,date1);
			for (ResultSet result = prepare.executeQuery(); result.next(); listeCredit.add(ligneDeVente)) {
				ligneDeVente.setCategorie(new CategorieDAO().findById(result.getInt("idcategorie")));
				ligneDeVente.setQuantitelignevente(result.getInt(2));
				ligneDeVente.setMontantligneventeht(result.getDouble(3));
				ligneDeVente.setMontantLigneVenteTva(result.getDouble(4));
				ligneDeVente.setMontantligneventettc(result.getDouble(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCredit;
	}
	@Override
	public int delete(Lignedevente ligneDeVente) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection.prepareStatement("DELETE FROM lignedevente WHERE idlignevente = ?");
			prepare.setInt(1, ligneDeVente.getIdLigneDeVente());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public int update(Lignedevente ligneDeVente) {
		int testeValue = 0;
		try {
			PreparedStatement prepare = connection.prepareStatement("UPDATE lignedevente SET idcategorie = ?,quantitelignevente = ?,montantligneventeht = ?,montantligneventetva = ?,montantligneventettc = ? WHERE idlignedevente = ?");
			prepare.setInt(1, ligneDeVente.getCategorie().getIdcategorie());
			prepare.setInt(2, ligneDeVente.getQuantitelignevente());
			prepare.setDouble(3, ligneDeVente.getMontantligneventeht());
			prepare.setDouble(4, ligneDeVente.getMontantLigneVenteTva());
			prepare.setDouble(5, ligneDeVente.getMontantligneventettc());
			prepare.setInt(6, ligneDeVente.getIdLigneDeVente());
			testeValue = prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testeValue;
	}

	@Override
	public Lignedevente findByName(String name) {
		return null;
	}
	/***Permet de retourner les statistiques de vente des differentes categorie de produit
	 * @param dateDebut : date de debut
	 * @param dateFin : date de fin***/
	public List<Lignedevente> findByDate(Date dateDebut,Date dateFin,int idUtilisateur) {
		List<Lignedevente> listeLigneDeVente = new LinkedList<Lignedevente>();
		Lignedevente ligneDeVente = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(idcategorie),SUM(quantitelignevente),SUM(montantligneventeht),SUM(montantligneventetva),SUM(montantligneventettc) FROM " +
					"(SELECT lignedevente.numerovente,idcategorie,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc,idsession  FROM lignedevente,vente WHERE vente.idetat = 1 AND idsession IN ( SELECT idsession FROM session WHERE idutilisateur = ?) AND lignedevente.numerovente=vente.numerovente AND lignedevente.numerovente IN " +
					"( SELECT numerovente FROM vente WHERE ( datevente>=? AND datevente<=? ) OR ( datevente>=? AND datevente<=? ) )) AS temp_table GROUP BY idcategorie   ");
			java.sql.Date date1 = new java.sql.Date(dateDebut.getTime());
			java.sql.Date date2 = new java.sql.Date(dateFin.getTime());
			statement.setInt(1, idUtilisateur);
			statement.setDate(2, date1);
			statement.setDate(3, date2);
			statement.setDate(4, date2);
			statement.setDate(5, date1);
			for (ResultSet resultat = statement.executeQuery(); resultat.next();listeLigneDeVente.add(ligneDeVente)) {
				ligneDeVente = new Lignedevente();
				ligneDeVente.setCategorie(new CategorieDAO().findById(resultat.getInt("idcategorie")));
				ligneDeVente.setQuantitelignevente(resultat.getInt(2));
				ligneDeVente.setMontantligneventeht(resultat.getDouble(3));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble(4));
				ligneDeVente.setMontantligneventettc(resultat.getDouble(5));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeLigneDeVente;
	}
	@Override
	public List<Lignedevente> findAll() {
		List<Lignedevente> listeLigneDeVente = new LinkedList<Lignedevente>();
		Lignedevente ligneDeVente = null;
		try {
			Statement statement = connection.createStatement();
			for (ResultSet resultat = statement.executeQuery("SELECT idlignevente,idcategorie,numerovente,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc FROM lignedevente"); resultat.next();) {
				ligneDeVente = new Lignedevente();
				ligneDeVente.setCategorie(new CategorieDAO().findById(resultat.getInt("idcategorie")));
				ligneDeVente.setVente(new VenteDAO().findById(resultat.getInt("numerovente")));
				ligneDeVente.setIdLigneDeVente(resultat.getInt("idlignevente"));
				ligneDeVente.setMontantligneventeht(resultat.getDouble("montantligneventeht"));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble("montantligneventetva"));
				ligneDeVente.setMontantligneventettc(resultat.getDouble("montantligneventettc"));
				ligneDeVente.setQuantitelignevente(resultat.getInt("quantitelignevente"));
				listeLigneDeVente.add(ligneDeVente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeLigneDeVente;
	}
	public List<Lignedevente> findAll(int numeroVente) {
		List<Lignedevente> listeLigneDeVente = new LinkedList<Lignedevente>();
		Lignedevente ligneDeVente = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT idlignevente,idcategorie,numerovente,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc FROM lignedevente WHERE numerovente = ? ");
			statement.setInt(1, numeroVente);
			for (ResultSet resultat = statement.executeQuery();resultat.next();) {
				ligneDeVente = new Lignedevente();
				ligneDeVente.setCategorie(new CategorieDAO().findById(resultat.getInt("idcategorie")));
				ligneDeVente.setVente(new VenteDAO().findById(resultat.getInt("numerovente")));
				ligneDeVente.setIdLigneDeVente(resultat.getInt("idlignevente"));
				ligneDeVente.setMontantligneventeht(resultat.getDouble("montantligneventeht"));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble("montantligneventetva"));
				ligneDeVente.setMontantligneventettc(resultat.getDouble("montantligneventettc"));
				ligneDeVente.setQuantitelignevente(resultat.getInt("quantitelignevente"));
				listeLigneDeVente.add(ligneDeVente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeLigneDeVente;
	}
	public List<Lignedevente> findAll(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		List<Lignedevente> listeLigneDeVente = new LinkedList<Lignedevente>();
		Lignedevente ligneDeVente = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT idlignevente,idcategorie,lignedevente.numerovente,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc FROM lignedevente,vente WHERE vente.numerovente = lignedevente.numerovente AND vente.idetat = 1 AND lignedevente.numerovente IN (SELECT numerovente FROM vente WHERE ( datevente>=? AND datevente<=? ) OR ( datevente>=? AND datevente<=? ))");
		java.sql.Date date1 = new java.sql.Date(dateDebut.getTime());
		java.sql.Date date2 = new java.sql.Date(dateFin.getTime());
			statement.setDate(1, date1);
			statement.setDate(2, date2);
			statement.setDate(3, date2);
			statement.setDate(4, date1);
			for (ResultSet resultat = statement.executeQuery(); resultat.next();) {
				ligneDeVente = new Lignedevente();
				ligneDeVente.setCategorie(new CategorieDAO().findById(resultat.getInt("idcategorie")));
				ligneDeVente.setVente(new VenteDAO().findById(resultat.getInt("numerovente")));
				ligneDeVente.setIdLigneDeVente(resultat.getInt("idlignevente"));
				ligneDeVente.setMontantligneventeht(resultat.getDouble("montantligneventeht"));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble("montantligneventetva"));
				ligneDeVente.setMontantligneventettc(resultat.getDouble("montantligneventettc"));
				ligneDeVente.setQuantitelignevente(resultat.getInt("quantitelignevente"));
				listeLigneDeVente.add(ligneDeVente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeLigneDeVente;
	}

	@Override
	public Lignedevente findById(int id) {
		// TODO Auto-generated method stub
		Lignedevente ligneDeVente = null;
		Categorie categorie = null;
		Vente vente = null;
		try {
			PreparedStatement prepare = connection
					.prepareStatement("SELECT idlignevente,idcategorie,numerovente,quantitelignevente,montantligneventeht,montantligneventetva,montantligneventettc FROM lignedevente WHERE idlignevente = ?");
			prepare.setInt(1, id);
			ResultSet resultat = prepare.executeQuery();
			if (resultat.next()) {
				ligneDeVente = new Lignedevente();
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				ligneDeVente.setCategorie(categorie);
				ligneDeVente.setIdLigneDeVente(resultat.getInt("idlignevente"));
				vente = new Vente();
				vente.setNumerovente(resultat.getInt("numerovente"));
				ligneDeVente.setVente(vente);
				ligneDeVente.setMontantligneventeht(resultat.getDouble("montantligneventeht"));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble("montantligneventetva"));
				ligneDeVente.setMontantligneventettc(resultat.getDouble("montantligneventettc"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Lignedevente getLast() {
		Lignedevente ligneDeVente = null;
		Categorie categorie = null;
		Vente vente = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT idlignevente,numerovente,idcategorie,montantligneventeht,montantligneventetva,montantligneventettc FROM lignedevente WHERE idlignevente = (SELECT max(idlignevente) FROM lignedevente) ");
			if (resultat.next()) {
				ligneDeVente = new Lignedevente();
				categorie = new Categorie();
				categorie.setIdcategorie(resultat.getInt("idcategorie"));
				ligneDeVente.setCategorie(categorie);
				ligneDeVente.setIdLigneDeVente(resultat.getInt("idlignevente"));
				vente = new Vente();
				vente.setNumerovente(resultat.getInt("numerovente"));
				ligneDeVente.setVente(vente);
				ligneDeVente.setMontantligneventeht(resultat.getDouble("montantligneventeht"));
				ligneDeVente.setMontantLigneVenteTva(resultat.getDouble("montantligneventetva"));
				ligneDeVente.setMontantligneventettc(resultat.getDouble("montantligneventettc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ligneDeVente;
	}
	
}
