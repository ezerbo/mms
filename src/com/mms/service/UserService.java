package com.mms.service;

import java.util.Date;
import java.util.LinkedList;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.joda.time.YearMonthDay;

import com.mms.dao.MotDePasseDAO;
import com.mms.dao.ParametresDAO;
import com.mms.dao.SessionDAO;
import com.mms.dao.UtilisateurDAO;
import com.mms.pojos.Motdepasse;
import com.mms.pojos.Parametres;
import com.mms.pojos.Session;
import com.mms.pojos.Typeutilisateur;
import com.mms.pojos.Utilisateur;

@SuppressWarnings("deprecation")
public class UserService {
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	private MotDePasseDAO motDePasseDAO = new MotDePasseDAO();
	private SessionDAO sessionDAO = new SessionDAO();
	private Utilisateur utilisateur;
	private static int ADMINISTRATEUR = 1;
	private static int GESTIONNAIRE = 2;
	public  String TYPEUTILISATEUR = "GESTIONNAIRE";

	public boolean egaliteMotDePasse(String motDePasse, String confirmation) {
		return motDePasse.equals(confirmation);
	}
	/***** Retourne les informations concernant un utilisateur grace a son login *****/
	public Utilisateur retourneUtilisateurParLogin(String login) {
		return utilisateurDAO.findByLogin(login);
	}
	/***** Retourne les informations concernant un utilisateur grace a son identifiant *****/
	public Utilisateur retourneUtilisateurParId(int idUtilisateur){
		return utilisateurDAO.findById(idUtilisateur);
	}
	/**
	 * Verifie l'existence du login lors de la creation d'un nouvel utilisateur
	 * dans le systeme
	 ***/
	public boolean ifLoginExiste(String login) {
		utilisateur = utilisateurDAO.findByLogin(login);
		if (utilisateur == null)
			return false;
		else
			return true;
	}
	public int modifierInformationUtilisateur(int idUtilisateur,String nomUtiliasteur,String prenomUtilisateur,String telephoneUtilisateur,String loginUtilisateur){
		utilisateur = new Utilisateur();
		utilisateur.setIdutilisateur(idUtilisateur);
		utilisateur.setNomutilisateur(nomUtiliasteur);
		utilisateur.setPrenomutilisateur(prenomUtilisateur);
		utilisateur.setLoginutilisateur(loginUtilisateur);
		utilisateur.setTelephoneutilisateur(telephoneUtilisateur);
		return utilisateurDAO.update(utilisateur);
	}
	/***** 
	 * Verifie si le mot de passe renseign� a deja �t� utilis� par l'utilisateur
	 * courant
	 ******/
	public boolean verifierExistenceMotDePasse(int idUtilisateur,
			String motDePasse) {
		return motDePasseDAO.verifierExistenceMotDePasse(idUtilisateur,
				motDePasse);
	}
	/**** Cr�e un nouvel utilisateur dans le systeme *******/
	public int creerUtiliateur(String nomUtilisateur, String prenomUtilisateur,
			String telephoneUtilisateur, String loginUtilisateur,
			String valeurMotDePasse, String statutUtilisateur) {
		utilisateur = new Utilisateur();
		Typeutilisateur typeUtilisateur = new Typeutilisateur();
		if (statutUtilisateur == "GESTIONNAIRE")
			typeUtilisateur.setIdtypeutilisateur(GESTIONNAIRE);
		else
			typeUtilisateur.setIdtypeutilisateur(ADMINISTRATEUR);
		
		utilisateur.setNomutilisateur(nomUtilisateur);
		utilisateur.setPrenomutilisateur(prenomUtilisateur);
		utilisateur.setTelephoneutilisateur(telephoneUtilisateur);
		utilisateur.setLoginutilisateur(loginUtilisateur);
		utilisateur.setDateembaucheutilisateur(new java.util.Date());
		utilisateur.setTypeutilisateur(typeUtilisateur);
		utilisateur = utilisateurDAO.create(utilisateur);
		if (utilisateur == null)
			return 1;
		else {
			if (creerNouveauMotDePasse(valeurMotDePasse, utilisateur) == null)
				return 1;
			else
				return 0;
		}
	}
	/***
	 * Permet la creation d'un nouveau mot de passe Prend en parametre la valeur
	 * du mot de passe a enregistrer et l'utilisateur pour qui il doit etre
	 * enregistr�
	 **/
	public Motdepasse creerNouveauMotDePasse(String valeurMotDePasse,
			Utilisateur utilisateur) {
		Motdepasse motDePasse = new Motdepasse();
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		motDePasse.setValeurmdp(passwordEncryptor.encryptPassword(valeurMotDePasse));
		Date date = new java.util.Date();
		motDePasse.setDatecreationmdp(new java.util.Date());
		date.setHours(24 * new UserService().retourneParametres().getDureeviemdp());
		/*** renseignement de la duree de validite du mot de passe ******/
		motDePasse.setDatevaliditemdp(date);
		motDePasse.setUtilisateur(utilisateur);
		return motDePasse = new MotDePasseDAO().create(motDePasse);
	}
	/**** Retourne la liste des utilisateur(except� l'administrateur) ****/
	public LinkedList<Utilisateur> listeGestionnaire() {
		return (LinkedList<Utilisateur>) utilisateurDAO.findAll();
	}
	/*** retourne les information concernant l'administrateur */
	public boolean findAdmin() {
		return utilisateurDAO.ifAdminExiste();
	}
	/** Realise la suppression d'un utilisateur ***/
	public int supprimerUtilisateur(String login) {
		utilisateur = new Utilisateur();
		utilisateur.setLoginutilisateur(login);
		supprimerMotDePasse(login);
		/** suppression de tous les mots de passe de l'utilisateur courant */
		return utilisateurDAO.delete(utilisateur);
		/** sppression de l'utilisateur courant */
	}
	/***
	 * Permet d'authentifier l'utiliasteur souhaitant avoir acces a la fenetre
	 * d'administration de l'application
	 **/
	public boolean authentificationAdministrateur(String motDePasse) {
		boolean testeValue = false;
		Utilisateur utilisateur = utilisateurDAO.findAdmin();
		if (utilisateur != null) {
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			String motDePasseChiffre = new MotDePasseDAO().adminPassword(utilisateur.getIdutilisateur());
			testeValue = passwordEncryptor.checkPassword(motDePasse,motDePasseChiffre);
		}
		return testeValue;
	}
	/***
	 * Effectue la suppression de tous les mots de passe d'un utilisateur
	 * lorsque celui-ci est supprim� su systeme
	 ***/
	public int supprimerMotDePasse(String login) {
		Motdepasse motDePasse = new Motdepasse();
		motDePasse.setUtilisateur(utilisateurDAO.findByLogin(login));
		return new MotDePasseDAO().delete(motDePasse);
	}
	/**
	 * Effectue la mise a jour des parametres lorsqu'ils sont modifi�s par
	 * l'administrateur
	 **/
	public int miseAJourParametres(int longueurMDP, int dureeVieMDP,int nombreTentative, int tempsInactivite) {
		Parametres parametres = new Parametres();
		parametres.setLongueurmdp(longueurMDP);
		parametres.setDureeviemdp(dureeVieMDP);
		parametres.setTempsinactivitesmdp(tempsInactivite);
		parametres.setTentativemdp(nombreTentative);
		return new ParametresDAO().update(parametres);
	}
	/***
	 * Etablit la connexion a l'application l'utilisateur renseigne son login et
	 * son mot de passe
	 */
	public int connecterUtilisateur(String login, String motDePasseSaisi) {
		int returnValue = 1;
		Utilisateur utilisateur = utilisateurDAO.findByLogin(login);
		if (utilisateur != null) {
			Motdepasse motDePasse = new MotDePasseDAO().findUserLastPassword(utilisateur.getIdutilisateur());
			/**
			 * On recupere le mot de passe valide de l'utilisateur souhaitant se
			 * connecter
			 */
			String motDePasseCourant = motDePasse.getValeurmdp();
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			if (passwordEncryptor.checkPassword(motDePasseSaisi,motDePasseCourant)) {
				/**
				 * Comparaison du mot de passe chiff� et du mot de passe saisi
				 * par l'utilisateur
				 */
				Date dateValidite = motDePasse.getDatevaliditemdp();
				/**
				 * Recuperation de la date de validit� du mot de passe de
				 * l'utilisateur
				 */
				Date dateCourante = new java.util.Date();
				/** Recuperation de la date du systeme */
				if (dateValidite.getTime() < dateCourante.getTime()/**
				 * conversion
				 * des date en long et comparaison si la date de validite est
				 * inferieure ou egale a la date du systeme,on propose a
				 * l'utilisateur de changer son mot de passe
				 **/
				) {
					returnValue = 2;
				} else
					returnValue = 0;
			}
		}
		return returnValue;
	}
	public Session derniereSessionUtilisateur(int idUtilisateur) {
		return sessionDAO.findUserLastSession(idUtilisateur);
	}
	public Parametres retourneParametres() {
		/** donne l'ensemble des parametres **/
		return new ParametresDAO().findAll().get(0);
		/*** le premier et l'uniqueelement de la liste */
	}
	public Session mettreInformationEnSession(int idUtilisateur) {
		Session session = new Session();
		utilisateur = new Utilisateur();
		utilisateur.setIdutilisateur(idUtilisateur);
		session.setUtilisateur(utilisateur);
		session.setDatedebutsession(dateCourante());
		session.setDatefinsession("");
		session.setDureesession(0);
		return sessionDAO.create(session);

	}
	public int mettreAJourSession(int idUtilisateur) {
		Session session = new Session();
		utilisateur = new Utilisateur();
		utilisateur.setIdutilisateur(idUtilisateur);
		session.setUtilisateur(utilisateur);
		session.setDatefinsession(dateCourante());
		String dateDebutSession = sessionDAO.findUserLastSession(idUtilisateur).getDatedebutsession();
		String dateFinSession = dateCourante();
		session.setDureesession(calculeDureeSession(dateDebutSession,dateFinSession));
		session.setIdsession(sessionDAO.findUserLastSession(idUtilisateur).getIdsession());
		return sessionDAO.update(session);
	}
	private String dateCourante() {
		/***/
		Date date = new Date();
		YearMonthDay dateDebutSession = YearMonthDay.fromDateFields(new java.util.Date());
		String dateChaine = "" + dateDebutSession.getDayOfMonth() + "/"+ dateDebutSession.getMonthOfYear() + "/"+ dateDebutSession.getYear() + " " + date.getHours() + ":"+ date.getMinutes() + ":" + date.getSeconds();
		return dateChaine;
	}

	private int calculeDureeSession(String dateDebutSession,String dateFinSession) {
		return (int) (Date.parse(dateFinSession) - Date.parse(dateDebutSession));

	}
	public boolean verifierNumeroTelephone(String telephone) {
		boolean testeValue = false;
		if (!telephone.substring(0, 1).equals("")&& !telephone.substring(1, 2).equals("")&& !telephone.substring(5, 6).equals("")&& !telephone.substring(6, 7).equals("")&& !telephone.substring(10, 11).equals("")&& !telephone.substring(11, 12).equals("")&& !telephone.substring(15, 16).equals("")&& !telephone.substring(16, 17).equals("")) {
			if (!telephone.equals("00-00-00-00")) {
				if (!telephone.substring(0, 1).equals("0"))
					testeValue = true;
			}

		}

		return testeValue;
	}
	
	public static void main(String[] args) {}

}
