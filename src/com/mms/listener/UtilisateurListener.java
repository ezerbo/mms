package com.mms.listener;

import java.util.EventListener;

import com.mms.event.UtilisateurEvent;

public interface UtilisateurListener extends EventListener {
	public void ajouterUilisateur(UtilisateurEvent event);
}
