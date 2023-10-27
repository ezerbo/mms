package com.mms;
import com.mms.interfaces.AdminCreationUI;
import com.mms.interfaces.LoginUI;
import com.mms.service.UserService;

public class ChargeurMMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (new UserService().findAdmin()) {
			new LoginUI().setVisible(true);
		} else {
			new AdminCreationUI().setVisible(true);
		}
	}

}
