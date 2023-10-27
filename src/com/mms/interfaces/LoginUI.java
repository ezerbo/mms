package com.mms.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mms.pojos.Parametres;
import com.mms.service.UserService;

public class LoginUI extends JFrame {

    private int attempts = 0;
    private final JTextField usernameTextField = new JPasswordField(10);

    private final JPasswordField passwordTextField = new JPasswordField(10);

    private final UserService userService = new UserService();

    public LoginUI() {
        super("Ecran de connexion");
        init();
    }

    public static void main(String[] args) {
        new LoginUI().setVisible(true);
    }

    private void init () {
        getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(2, 1));
        labelsPanel.add(new JLabel("Login :  "));
        labelsPanel.add(new JLabel("Mot de passe :"));
        labelsPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        fieldsPanel.add(usernameTextField);
        fieldsPanel.add(passwordTextField);
        fieldsPanel.setBackground(Color.WHITE);

        JPanel iconPanel = new JPanel();
        iconPanel.add(new JLabel(new ImageIcon("ressources/images/user.jpg")));
        iconPanel.setBackground(Color.WHITE);

        JPanel copyRightPanel = new JPanel();
        copyRightPanel.setLayout(new GridLayout(1, 1));
        copyRightPanel.add(new CopyRight());

        JPanel componentsPanel = new JPanel();
        componentsPanel.setBorder(new TitledBorder(""));
        componentsPanel.setBackground(Color.WHITE);
        componentsPanel.add(labelsPanel);
        componentsPanel.add(fieldsPanel);

        JPanel panelCentre = new JPanel();
        panelCentre.setBorder(new TitledBorder(""));
        panelCentre.add(iconPanel);
        panelCentre.add(componentsPanel);
        panelCentre.setBackground(Color.WHITE);

        add(panelCentre, BorderLayout.CENTER);
        add(copyRightPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(500, 210));
        setLocationRelativeTo(null);
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void login() {
        Parametres parametres = userService.retourneParametres();
        if (!usernameTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
            int returValue = userService.connecterUtilisateur(usernameTextField.getText(), passwordTextField.getText());
            if (returValue == 0) {
                setCursor(Cursor.WAIT_CURSOR);
                userService.mettreInformationEnSession(userService.retourneUtilisateurParLogin(usernameTextField.getText()).getIdutilisateur());
                InterfacePrincipale interfacePrincipale = new InterfacePrincipale(usernameTextField.getText());
                interfacePrincipale.setVisible(true);
                interfacePrincipale.setSize(new Dimension(1100, 720));
                interfacePrincipale.setLocationRelativeTo(null);
                dispose();
            } else {
                if (returValue == 2) {
                    //desactivation des champs de connexion
                    JOptionPane.showMessageDialog(null, "Votre mot de passe n'est plus valide,veuillez le changer svp !!!", "Mot de passe non valide", JOptionPane.INFORMATION_MESSAGE);
                    new MiseAJourMotDePasse(userService.retourneUtilisateurParLogin(usernameTextField.getText()).getIdutilisateur());
                    passwordTextField.setText("");
                } else {
                    //incrementation du nombre de tentatives echou�!!
                    if (++attempts == parametres.getTentativemdp()) {
                        usernameTextField.setEnabled(false);
                        passwordTextField.setEnabled(false);
                        //desactivation des champs de connexion
                        try {
                            Thread.sleep(parametres.getTempsinactivitesmdp() * 60000);//desactivation des champs de saisie pendant la duree specifi�e
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        usernameTextField.setEnabled(true);
                        passwordTextField.setEnabled(true);
                    }
                    JOptionPane.showMessageDialog(null, "Echec de la tentative de connexion,\nlogin ou mot de passe incorrect", "Echec de la connexion", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else
            JOptionPane.showMessageDialog(null, "tous les champs sont obligatoires !!", "Erreur", JOptionPane.ERROR_MESSAGE);

    }
}
