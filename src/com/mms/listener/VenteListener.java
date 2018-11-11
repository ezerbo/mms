package com.mms.listener;

import java.util.EventListener;

import com.mms.event.VenteEvent;

public interface VenteListener extends EventListener {
	public void nouvelleLigneDeVente(VenteEvent event);
}
