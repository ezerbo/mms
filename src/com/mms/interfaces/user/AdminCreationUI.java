package com.mms.interfaces.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.mms.interfaces.common.TextFieldUtilisateur;
import com.mms.interfaces.auth.LoginUI;
import com.mms.service.UserService;

public class AdminCreationUI extends JDialog {

	private final static int NUM_COL_PWD_FIELD = 20;

	private final static String PHONE_NUMBER_MASK = "(226) - ## - ## - ## - ##";
	private final TextFieldUtilisateur lastNameTextField = new TextFieldUtilisateur();
	private final TextFieldUtilisateur firstNameTextField = new TextFieldUtilisateur();
	private final TextFieldUtilisateur usernameTextField = new TextFieldUtilisateur();
	private final JPasswordField passwordTextField = new JPasswordField(NUM_COL_PWD_FIELD);
	private final JPasswordField passwordConfirmationTextField = new JPasswordField(NUM_COL_PWD_FIELD);
	private JFormattedTextField phoneNumberTextField;
	private final UserService userService = new UserService();

	public AdminCreationUI() {
		init();
	}

	private void init() {
		getContentPane();
		setLayout(new BorderLayout());

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(0));
		JButton cancelButton = new JButton("Annuler");
		JButton createAdminButton = new JButton("  Creer  ");
		buttonsPanel.add(createAdminButton);
		buttonsPanel.add(cancelButton);

		JPanel textInputsPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		JLabel adminImageLabel = new JLabel();
		imagePanel.setBackground(Color.WHITE);
		textInputsPanel.setBackground(Color.WHITE);

		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new GridLayout(6, 1, 10, 10));
		labelsPanel.setBackground(Color.WHITE);
		labelsPanel.add(new JLabel("Nom :"));
		labelsPanel.add(new JLabel("Prenom :"));
		labelsPanel.add(new JLabel("Telephone :"));
		labelsPanel.add(new JLabel("Login :"));
		labelsPanel.add(new JLabel("Mot de passe :"));
		labelsPanel.add(new JLabel("Ressaisissez le mot de passe :"));
		textInputsPanel.add(labelsPanel);

		JPanel panelField = new JPanel();
		panelField.setBorder(new TitledBorder(""));
		panelField.setBackground(Color.WHITE);
		panelField.setLayout(new GridLayout(6, 1, 5, 5));
		panelField.add(lastNameTextField);
		panelField.add(firstNameTextField);
		try {
			phoneNumberTextField = new JFormattedTextField(new MaskFormatter(PHONE_NUMBER_MASK));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		panelField.add(phoneNumberTextField);
		panelField.add(usernameTextField);
		panelField.add(passwordTextField);
		panelField.add(passwordConfirmationTextField);
		textInputsPanel.add(panelField);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new TitledBorder(""));
		mainPanel.setBackground(Color.WHITE);

		add(mainPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		adminImageLabel.setIcon(new ImageIcon("ressources/images/admin.jpg"));
		imagePanel.add(adminImageLabel);
		mainPanel.add(imagePanel);
		mainPanel.add(textInputsPanel);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
		setTitle("Creation du compte administrateur");
		setLocationRelativeTo(null);
		cancelButton.addActionListener(e -> setVisible(false));
		passwordConfirmationTextField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event){
				if(event.getKeyCode() == KeyEvent.VK_ENTER)
					createAdminUser();
			}
		});
		createAdminButton.addActionListener(e -> createAdminUser());
		setModal(true);
	}

	private boolean isFormProperlyFilled(JTextField... fields) {
		return Arrays.stream(fields)
				.noneMatch(f -> f.getText().isEmpty());
	}

	public void createAdminUser() {
		if (!isFormProperlyFilled(lastNameTextField, firstNameTextField, usernameTextField, passwordTextField, passwordConfirmationTextField)) {
			// All fields are required
			JOptionPane.showMessageDialog(null, "Tous les champs sont obligatoires",
					"Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (passwordTextField.getPassword().length < userService.retourneParametres().getLongueurmdp()) {
			JOptionPane.showMessageDialog(null,
					String.format("Mot de passe trop court,entrez au moins %s caracteres",
							userService.retourneParametres().getLongueurmdp())
					, "Erreur"
					, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!String.valueOf(passwordTextField.getPassword()).equals(String.valueOf(passwordConfirmationTextField.getPassword()))) {
			JOptionPane.showMessageDialog(null, "Les mots de passe ne sont pas identiques!"
					, "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//TODO Refactor to use exceptions
		int isUserCreated = userService.creerUtiliateur(lastNameTextField.getText()
				, firstNameTextField.getText()
				, phoneNumberTextField.getText().substring(8)
				, usernameTextField.getText()
				, String.valueOf(passwordTextField.getPassword())
				, "ADMINISTRATEUR");
		if(isUserCreated == 0) {
			JOptionPane.showMessageDialog(null,
					"Administrateur cree avec succes !!!", "Succes de l'operation",
					JOptionPane.INFORMATION_MESSAGE);
			new LoginUI().setVisible(true);
			dispose();

		} else {
			JOptionPane.showMessageDialog(null,
					"Echec de l'operation de creation d'administrateur", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new AdminCreationUI().setVisible(true);
	}
}
