package com.mms;
import com.mms.interfaces.user.AdminCreationUI;
import com.mms.interfaces.auth.LoginUI;
import com.mms.service.UserService;

public class NexusShopManagementApp {

	public static void main(String[] args) {
		if (new UserService().isAdminAccountCreated()) {
			new LoginUI().setVisible(true);
		} else {
			new AdminCreationUI().setVisible(true);
		}
	}

}
