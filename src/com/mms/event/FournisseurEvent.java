package com.mms.event;

import java.util.EventObject;

import com.mms.ui.inventory.ModifierFournisseur;
import com.mms.ui.inventory.NouveauFournisseur;

@SuppressWarnings("serial")
public class FournisseurEvent extends EventObject {

	public FournisseurEvent(NouveauFournisseur source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public FournisseurEvent(ModifierFournisseur modifierFournisseur) {
		// TODO Auto-generated constructor stub
		super(modifierFournisseur);
	}

}
