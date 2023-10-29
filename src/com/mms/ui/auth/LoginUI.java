package com.mms.ui.auth;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import com.mms.domain.Parameters;
import com.mms.error.ExpiredPasswordException;
import com.mms.error.InvalidCrdentialsException;
import com.mms.ui.MainUI;
import com.mms.service.UserService;
import com.mms.ui.util.ImagePaths;
import org.apache.commons.lang3.StringUtils;

public class LoginUI extends JFrame {

    private static final int MILLIS_IN_SEC = 1000;
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final UserService userService = new UserService();
    private final Parameters parameters;
    private int attempts = 0;

    public LoginUI() {
        super("Ecran de connexion");
        this.parameters = userService.getParameters();
        init();
    }

    public static void main(String[] args) {
       new LoginUI().setVisible(true);
    }

    // TODO Use text fields with placeholders
    private void init() {
        getContentPane().setLayout(new BorderLayout(10, 10));
        GridLayout layout = new GridLayout(2, 1, 5, 5);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(layout);
        labelsPanel.add(new JLabel("Login :  "));
        labelsPanel.add(new JLabel("Mot de passe :"));
        labelsPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(layout);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);
        fieldsPanel.setBackground(Color.WHITE);

        JPanel iconPanel = new JPanel();
        iconPanel.add(new JLabel(new ImageIcon(ImagePaths.LOGIN_IMG)));
        iconPanel.setBackground(Color.WHITE);

        JPanel componentsPanel = new JPanel();
        componentsPanel.setSize(300, 150);
        componentsPanel.setBackground(Color.WHITE);
        componentsPanel.add(labelsPanel);
        componentsPanel.add(fieldsPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(iconPanel);
        mainPanel.add(componentsPanel);
        mainPanel.setBackground(Color.WHITE);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Annuler");
        controlsPanel.add(loginButton);
        controlsPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
        add(controlsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
        cancelButton.addActionListener(e -> dispose());
        loginButton.addActionListener(e -> login());
    }

    private void login() {
        String username = getUsername();
        String password = getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            JOptionPane.showMessageDialog(null
                    , "tous les champs sont obligatoires!"
                    , "Erreur"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // TODO Show progress while login process in pending
            userService.login(username, password);
            showMainUI();
        } catch (InvalidCrdentialsException e) {
            onInvalidCredentials();
        } catch (ExpiredPasswordException e) {
            onExpiredPassword();
        }
    }

    private void showMainUI() {
        MainUI mainUI = new MainUI();
        mainUI.setVisible(true);
        mainUI.setSize(new Dimension(1100, 720));
        mainUI.setLocationRelativeTo(null);
        dispose();
    }

    private void toggleControls(boolean enabled) {
        usernameField.setEnabled(enabled);
        passwordField.setEnabled(enabled);
    }

    private void onInvalidCredentials() {
        if (++attempts == parameters.getTentativemdp()) {
            toggleControls(false);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toggleControls(true);
                }
            }, parameters.getTempsinactivitesmdp() * MILLIS_IN_SEC);
        }
        JOptionPane.showMessageDialog(null
                , "Echec de la tentative de connexion,\nlogin ou mot de passe incorrect"
                , "Echec de la connexion"
                , JOptionPane.ERROR_MESSAGE);
    }

    private void onExpiredPassword() {
        JOptionPane.showMessageDialog(null
                , "Votre mot de passe n'est plus valide,veuillez le changer svp !!!"
                , "Mot de passe non valide"
                , JOptionPane.INFORMATION_MESSAGE);
        new PasswordUpdateUI();
        passwordField.setText("");
    }

    private String getUsername() {
        return usernameField.getText();
    }

    private String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

}
