package com.mms.event;

import java.util.EventObject;

import com.mms.ui.product.ModifierProduit;
import com.mms.ui.product.NouveauProduit;

@SuppressWarnings("serial")
public class ProduitEvent extends EventObject {

	public ProduitEvent(NouveauProduit nouveauProduit) {
		super(nouveauProduit);
		// TODO Auto-generated constructor stub
	}

	public ProduitEvent(ModifierProduit modifierProduit) {
		// TODO Auto-generated constructor stub
		super(modifierProduit);
	}

}
