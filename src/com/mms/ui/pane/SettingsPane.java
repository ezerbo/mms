package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.auth.AdminAuthUI;
import com.mms.ui.auth.LoginUI;
import com.mms.domain.User;
import com.mms.service.UserService;
import com.mms.ui.util.ImagePaths;
import com.mms.ui.util.UIUtil;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPane extends JTaskPaneGroup {

    private final JLabel exitLabel  = new JLabel("Quitter");;
    private final JLabel disconnectLabel = new JLabel("Déconnexion");;
    private final JLabel adminLabel = new JLabel("Panneau d'administration");;

    private final UserService userService = new UserService();
    private final User loggedInUser = UserService.getLoggedInUser();

    public SettingsPane() {
        init();
        registerEvents();
    }

    private void init() {
        setTitle("Application ");

        exitLabel.setIcon(new ImageIcon(ImagePaths.EXIT_IMG));
        adminLabel.setIcon(new ImageIcon(ImagePaths.ADMIN_IMG));
        disconnectLabel.setIcon(new ImageIcon(ImagePaths.DISCONNECT_IMG));
        adminLabel.setToolTipText("Panneau d'administration de l'application");
        exitLabel.setToolTipText("quitter l'application");
        disconnectLabel.setToolTipText("Déconnectez-vous de l'application");
        add(adminLabel);
        add(disconnectLabel);
        add(exitLabel);
    }

    private void registerEvents() {
        adminLabel.addMouseListener(new Listener(adminLabel
                , UIUtil.htmlWrap(adminLabel.getText())
                , "Panneau d'administration") {
        });
        adminLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new AdminAuthUI();
            }
        });

        exitLabel.addMouseListener(new Listener(exitLabel
                , UIUtil.htmlWrap(exitLabel.getText())
                , "Quitter"));
        exitLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                userService.updateSession(userService
                        .findByLogin(loggedInUser.getLoginutilisateur())
                        .getIdutilisateur());
                System.exit(0);
            }
        });

        disconnectLabel.addMouseListener(new Listener(disconnectLabel
                , UIUtil.htmlWrap(disconnectLabel.getText())
                , "Déconnexion"));
        disconnectLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                int confirmation = JOptionPane.showConfirmDialog(null
                        , "Voulez-vous vraiment vous déconnectez ?"
                        , "Déconnexion"
                        , JOptionPane.YES_NO_OPTION);
                if (confirmation == 0) {
                    userService.updateSession(userService.findByLogin(
                            loggedInUser.getLoginutilisateur()).getIdutilisateur());
                    // dispose();
                    //TODO Implement logic to close main window
                    new LoginUI().setVisible(true);
                }
            }
        });
    }
}
