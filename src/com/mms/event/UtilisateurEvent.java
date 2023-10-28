package com.mms.event;

import java.util.EventObject;

import com.mms.interfaces.user.ModifierInformationUtilisateur;
import com.mms.interfaces.user.NouvelUtilisateur;

@SuppressWarnings("serial")
public class UtilisateurEvent extends EventObject {
	public UtilisateurEvent(NouvelUtilisateur source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public UtilisateurEvent(ModifierInformationUtilisateur modifierInformationUtilisateur) {
		// TODO Auto-generated constructor stub
		super(modifierInformationUtilisateur);
	}

}
