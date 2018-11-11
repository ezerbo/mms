package com.mms.event;

import java.util.EventObject;

import com.mms.tablemodels.TableModelVente;

@SuppressWarnings("serial")
public class VenteEvent extends EventObject {

	public VenteEvent(TableModelVente source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
