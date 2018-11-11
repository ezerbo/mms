package com.mms;
import com.mms.interfaces.AdminCreation;
import com.mms.interfaces.EcranDeConnexion;
import com.mms.service.UtilisateurService;

public class ChargeurMMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (new UtilisateurService().findAdmin()) {
			/** si l'administrateur existe */
			new EcranDeConnexion().setVisible(true);/**Presentation de l'ecran de connexion*/
		} else {
			new AdminCreation().setVisible(true);
			/** Ecran de creation de l'administrateur **/
		}
	}

}
