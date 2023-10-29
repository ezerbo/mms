package com.mms.ui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.mms.ui.common.NoBlankTextField;
import com.mms.ui.auth.LoginUI;
import com.mms.service.UserService;
import com.mms.ui.util.ImagePaths;
import com.mms.ui.util.UIUtil;

public class AdminCreationUI extends JDialog {

    private final static int NUM_COL_PWD_FIELD = 20;

    private final static String PHONE_NUMBER_MASK = "(226) - ## - ## - ## - ##";
    private final NoBlankTextField lastNameField = new NoBlankTextField();
    private final NoBlankTextField firstNameField = new NoBlankTextField();
    private final NoBlankTextField usernameField = new NoBlankTextField();
    private final JPasswordField passwordField = new JPasswordField(NUM_COL_PWD_FIELD);
    private final JPasswordField passwordConfirmationField = new JPasswordField(NUM_COL_PWD_FIELD);
    private JFormattedTextField phoneNumberField;
    private final UserService userService = new UserService();

    public AdminCreationUI() {
        init();
    }

    private void init() {
        getContentPane();
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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
        panelField.add(lastNameField);
        panelField.add(firstNameField);
        phoneNumberField = new JFormattedTextField(UIUtil.getPhoneNumberFormatter());
        panelField.add(phoneNumberField);
        panelField.add(usernameField);
        panelField.add(passwordField);
        panelField.add(passwordConfirmationField);
        textInputsPanel.add(panelField);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder(""));
        mainPanel.setBackground(Color.WHITE);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        adminImageLabel.setIcon(new ImageIcon(ImagePaths.ADMIN_CREATION_IMG));
        imagePanel.add(adminImageLabel);
        mainPanel.add(imagePanel);
        mainPanel.add(textInputsPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setTitle("Creation du Compte Administrateur");
        setLocationRelativeTo(null);
        cancelButton.addActionListener(e -> setVisible(false));
        passwordConfirmationField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    createAdminUser();
            }
        });
        createAdminButton.addActionListener(e -> createAdminUser());
        setModal(true);
    }

    private boolean validateInputs() {
        return Stream.of(lastNameField, firstNameField, usernameField, passwordField, passwordConfirmationField)
                .noneMatch(f -> f.getText().isEmpty());
    }

    public void createAdminUser() {
        if (!validateInputs()) {
            JOptionPane.showMessageDialog(null
                    , "Tous les champs sont obligatoires"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (passwordField.getPassword().length < userService.getParameters().getLongueurmdp()) {
            JOptionPane.showMessageDialog(null,
                    String.format("Mot de passe trop court,entrez au moins %s caracteres"
                            , userService.getParameters().getLongueurmdp())
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!getPassword().equals(getPasswordConfirmation())) {
            JOptionPane.showMessageDialog(null
                    , "Les mots de passe ne sont pas identiques!"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            userService.create(getLastName()
                    , getFirstName()
                    , getPhoneNumber().substring(8)
                    , getUserName()
                    , getPassword()
                    , "ADMINISTRATEUR");
            JOptionPane.showMessageDialog(null,
                    "Administrateur cree avec succes!"
                    , "Succes de l'operation"
                    , JOptionPane.INFORMATION_MESSAGE);
            new LoginUI().setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null
                    , "Echec de l'operation de creation d'administrateur"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFirstName() {
        return firstNameField.getText();
    }

    private String getLastName() {
        return lastNameField.getName();
    }

    private String getUserName() {
        return usernameField.getText();
    }

    private String getPhoneNumber() {
        return phoneNumberField.getText();
    }

    private String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    private String getPasswordConfirmation() {
        return String.valueOf(passwordConfirmationField.getPassword());
    }

    public static void main(String[] args) {
        new AdminCreationUI().setVisible(true);
    }
}
