package com.mms;
import com.mms.ui.user.AdminCreationUI;
import com.mms.ui.auth.LoginUI;
import com.mms.service.UserService;

public class NexusShopApp {

	public static void main(String[] args) {
		if (new UserService().isAdminAccountCreated()) {
			new LoginUI().setVisible(true);
		} else {
			new AdminCreationUI().setVisible(true);
		}
	}

}
