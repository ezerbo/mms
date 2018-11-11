package com.mms.event;

import java.util.EventObject;

import com.mms.interfaces.ModifierClient;
import com.mms.interfaces.NouveauClient;

@SuppressWarnings("serial")
public class ClientEvent extends EventObject {

	public ClientEvent(NouveauClient source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public ClientEvent(ModifierClient modiferClient) {
		// TODO Auto-generated constructor stub
		super(modiferClient);
	}

}
