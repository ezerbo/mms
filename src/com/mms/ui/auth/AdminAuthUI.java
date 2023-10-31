package com.mms.ui.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.mms.ui.common.Configuration;
import com.mms.ui.common.CopyRight;
import com.mms.service.UserService;

public class AdminAuthUI extends JDialog {

	private final JPanel mainPanel = new JPanel();
	private final JPasswordField passwordField = new JPasswordField(20);;

	private final UserService userService = new UserService();

	public AdminAuthUI() {
		setTitle("Authentification");
		init();
	}

	private void init() {
		getContentPane().setLayout(new BorderLayout(2,2));

		JPanel copyRightPanel = new JPanel();
		copyRightPanel.setLayout(new GridLayout(1,1));
		copyRightPanel.add(new CopyRight());

		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(new JLabel("Mot de passe Administrateur :"));
		mainPanel.add(passwordField);

		add(mainPanel, BorderLayout.CENTER);
		add(copyRightPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(460,120));
		setResizable(false);
		setLocationRelativeTo(null);
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					showConfigurationPanel();
				}
			}
		});
		setModal(true);
		setVisible(true);
	}

	public void showConfigurationPanel() {
		if (passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null
					,"Tous les champs sont obligatoires!"
					, "Erreur"
					, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (userService.adminLogin(String.valueOf(passwordField.getPassword()))) {
			mainPanel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			dispose();
			new Configuration();
		} else {
			JOptionPane.showMessageDialog(null
					,"Mot de passe incorrect!"
					, "Echec de l'authentification"
					, JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args){
		new AdminAuthUI();
	}

}
