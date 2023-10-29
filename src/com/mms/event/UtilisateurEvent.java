package com.mms.event;

import java.util.EventObject;

import com.mms.ui.user.UpdateUserUI;
import com.mms.ui.user.NewUserUI;

@SuppressWarnings("serial")
public class UtilisateurEvent extends EventObject {
	public UtilisateurEvent(NewUserUI source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public UtilisateurEvent(UpdateUserUI updateUserUI) {
		// TODO Auto-generated constructor stub
		super(updateUserUI);
	}

}
