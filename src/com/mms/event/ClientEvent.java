package com.mms.event;

import java.util.EventObject;

import com.mms.interfaces.client.ModifierClient;
import com.mms.interfaces.client.NouveauClient;

@SuppressWarnings("serial")
public class ClientEvent extends EventObject {

	public ClientEvent(NouveauClient source) {
		super(source);
	}

	public ClientEvent(ModifierClient modiferClient) {
		super(modiferClient);
	}

}
