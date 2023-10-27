package com.mms.interfaces;

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
import javax.swing.border.TitledBorder;

import com.mms.service.UserService;

public class AdminAuthUI extends JDialog {

	private final JPanel mainPanel;
	private final JPasswordField passwordTextField;

	private final UserService userService = new UserService();

	private final String username;

	public AdminAuthUI(String username) {
		setTitle("Authentification");
		this.username = username;
		getContentPane().setLayout(new BorderLayout(2,2));

		JPanel copyRightPanel = new JPanel();
		copyRightPanel.setLayout(new GridLayout(1,1));
		copyRightPanel.add(new CopyRight());

		mainPanel = new JPanel();
		passwordTextField = new JPasswordField(20);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new TitledBorder(""));
		mainPanel.add(new JLabel("Mot de passe Administrateur :"));
		mainPanel.add(passwordTextField);

		add(mainPanel, BorderLayout.CENTER);
		add(copyRightPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(460,120));
		setResizable(false);
		setLocationRelativeTo(null);
		passwordTextField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					showConfigurationPanel();
			}
		});
		setModal(true);
		setVisible(true);
	}

	public void showConfigurationPanel() {
		if (passwordTextField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null,"Tous les champs sont obligatoires!"
					, "Erreur"
					, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (userService.authentificationAdministrateur(String.valueOf(passwordTextField.getPassword()))) {
			mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			dispose();
			new Configuration(username);
		} else {
			JOptionPane.showMessageDialog(null,"Mot de passe incorrect !!!",
					"Echec de l'authentification", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args){
		new AdminAuthUI("tintin");
	}

}
