package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.auth.AdminAuthUI;
import com.mms.ui.auth.LoginUI;
import com.mms.domain.User;
import com.mms.service.UserService;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPane extends JTaskPaneGroup {

    private JLabel l_quitter;
    private JLabel l_deconnexion;
    private JLabel l_admin;

    private UserService userService = new UserService();
    private final User loggedInUser = UserService.getLoggedInUser();

    public SettingsPane() {
        init();
    }

    private void init() {
        setTitle("Application ");
        l_quitter = new JLabel("Quitter");
        l_deconnexion = new JLabel("Deconnexion");
        l_admin = new JLabel("Panneau d'administration");
        l_quitter.setIcon(new ImageIcon("ressources/images/quitter.png"));
        l_admin.setIcon(new ImageIcon("ressources/images/administrer.png"));
        l_deconnexion
                .setIcon(new ImageIcon("ressources/images/deconnexion.png"));
        add(l_admin);
        add(l_deconnexion);
        add(l_quitter);

        l_admin.addMouseListener(new Listener(l_admin, "<html><a href = '' color = black>" +
                l_admin.getText() +
                "</a></html>", "Panneau d'administration") {
        });
        l_admin.setToolTipText("Panneau d'administration de l'application");
        l_admin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new AdminAuthUI();
            }
        });

        l_quitter.addMouseListener(new Listener(l_quitter, "<html><a href='' color = black>" +
                l_quitter.getText() +
                "</a></html>", "Quitter"));
        l_quitter.setToolTipText("quitter l'application");
        l_quitter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                /*** mis a jour de la session de l'utilisateur connect� ****/
                userService.updateSession(userService
                        .findByLogin(loggedInUser.getLoginutilisateur())
                        .getIdutilisateur());
                System.exit(0);
            }
        });
        l_deconnexion.addMouseListener(new Listener(l_deconnexion,
                "<html><a href='' color = black>" +
                        l_deconnexion.getText() + "</a></html>", "Deconnexion"));
        l_deconnexion.setToolTipText("Deconnectez-vous de l'application");
        l_deconnexion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                int i = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous vraiment vous deconnectez ?",
                        "Deconnexion", 0);
                if (i == 0) {
                    userService.updateSession(userService
                            .findByLogin(loggedInUser.getLoginutilisateur())
                            .getIdutilisateur());
                   // dispose();
                    //TODO Implement logic to close main window
                    new LoginUI().setVisible(true);
                }
            }
        });
    }
}
