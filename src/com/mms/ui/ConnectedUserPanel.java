package com.mms.ui;

import com.mms.domain.User;
import com.mms.service.UserService;

import javax.swing.*;
import java.awt.*;

public class ConnectedUserPanel extends JPanel {

    private JToolBar connectedUserToolBar = new JToolBar();
    private JLabel l_utilisateurConnecte = new JLabel("Utilisateur connect� : ");

    private JLabel l_nomUtilisateurConnecte;

    private final User user = UserService.getLoggedInUser();

    public ConnectedUserPanel() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel panelLabelNomUtilisateur = new JPanel();
        l_utilisateurConnecte.setIcon(new ImageIcon("ressources/images/utilisateurConnecte.jpg"));
        l_nomUtilisateurConnecte = new JLabel(user.getLoginutilisateur());

        JPanel panelLabelUtilisateurConnecte = new JPanel();
        panelLabelUtilisateurConnecte.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelLabelUtilisateurConnecte.add(l_utilisateurConnecte);

        panelLabelNomUtilisateur.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelLabelNomUtilisateur.add(l_nomUtilisateurConnecte);

        connectedUserToolBar.add(panelLabelUtilisateurConnecte);
        connectedUserToolBar.add(panelLabelNomUtilisateur);
        add(connectedUserToolBar);
    }
}
