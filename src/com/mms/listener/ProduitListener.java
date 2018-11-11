package com.mms.listener;

import java.util.EventListener;

import com.mms.event.ProduitEvent;

public interface ProduitListener extends EventListener {
	public void ajouterProduit(ProduitEvent event);
}
