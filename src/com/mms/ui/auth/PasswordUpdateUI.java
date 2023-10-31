package com.mms.ui.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import com.mms.domain.User;
import com.mms.service.UserService;
import org.apache.commons.lang3.StringUtils;

public class PasswordUpdateUI extends JDialog {
    private final JPasswordField passwordField = new JPasswordField(10);

    private final JPasswordField passwordConfirmationField = new JPasswordField(15);

    private final UserService userService = new UserService();

    public PasswordUpdateUI() {
        init();
    }

    private void init() {
        setTitle("Changement du mot de passe");
        getContentPane().setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        labelsPanel.add(new JLabel("Mot de passe : "));
        labelsPanel.add(new JLabel("Retappez le mot de passe : "));
        labelsPanel.setBackground(Color.WHITE);
        centerPanel.add(labelsPanel);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        fieldsPanel.add(passwordField);
        fieldsPanel.add(passwordConfirmationField);
        fieldsPanel.setBackground(Color.WHITE);
        centerPanel.add(fieldsPanel);

        centerPanel.setBorder(new TitledBorder(""));
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton saveButton = new JButton("Enregistrer");
        controlsPanel.add(saveButton);

        add(controlsPanel, BorderLayout.SOUTH);
        saveButton.addActionListener(e -> updatePassword());
        passwordConfirmationField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    updatePassword();
                }
            }
        });
        pack();
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    public void updatePassword() {
        User loggedInUser = UserService.getLoggedInUser();
        if (StringUtils.isAnyBlank(getPassword(), getPasswordConfirmation())) {
            JOptionPane.showMessageDialog(null
                    , "Tous les champs sont obligatoires"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!StringUtils.equals(getPassword(), getPasswordConfirmation())) {
            JOptionPane.showMessageDialog(null
                    , "Les mots de passe ne sont pas identiques"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (getPassword().length() < userService.getParameters().getLongueurmdp()) {
            JOptionPane.showMessageDialog(null
                    , "Mot de passe trop court"
                    , "Information"
                    , JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            userService.createPassword(getPassword(), loggedInUser);
            JOptionPane.showMessageDialog(null
                    , "Mot de passe change avec succes!"
                    , "Succes de l'operation"
                    , JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null
                    , "Le mot de passe n'a pas pu etre enregistre"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    private String getPasswordConfirmation() {
        return String.valueOf(passwordConfirmationField.getPassword());
    }

    public static void main(String[] args) {
        new PasswordUpdateUI();
    }
}
