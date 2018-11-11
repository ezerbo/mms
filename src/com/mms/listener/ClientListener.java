package com.mms.listener;

import java.util.EventListener;

import com.mms.event.ClientEvent;

public interface ClientListener extends EventListener {
	public void ajouterClient(ClientEvent event);
	//public void supprimerClient(ClientEvent event);
}
