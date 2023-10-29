package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.client.CreditClient;
import com.mms.ui.client.ClientListUI;
import com.mms.ui.sale.HistoriqueVente;
import com.mms.ui.util.UIUtil;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesPane extends JTaskPaneGroup {

    private final static String HISTORY_ICON = "ressources/images/historique.png";

    private final static String CREDIT_ICON = "ressources/images/credit.png";

    private final static String LIST_ICON = "ressources/images/liste.png";

    private JLabel salesLabel;
    private JLabel clientsCredit;
    private JLabel clientsList;

    public SalesPane() {
        init();
        registerEvents();
    }

    private void init() {
        setTitle("Gestion des Ventes");
        salesLabel = new JLabel("Ventes enregistrees");
        clientsCredit = new JLabel("Credits clients");
        clientsList = new JLabel("Liste des clients");
        salesLabel.setIcon(new ImageIcon(HISTORY_ICON));
        clientsCredit.setIcon(new ImageIcon(CREDIT_ICON));
        clientsList.setIcon(new ImageIcon(LIST_ICON));
        add(salesLabel);
        add(clientsCredit);
        add(clientsList);
    }

    private void registerEvents() {
        // Sales Events
        salesLabel.addMouseListener(new Listener(salesLabel, UIUtil.htmlWrap(salesLabel.getText())
                , "Ventes enregistr\351es"));
        salesLabel.setToolTipText("Liste des ventes realis\351es");
        salesLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                // TODO Send an event to signify that link has been clicked
                new HistoriqueVente().setVisible(true);
            }
        });

        // Credit Events
        clientsCredit.addMouseListener(new Listener(clientsCredit, UIUtil.htmlWrap(clientsCredit.getText())
                , "Credits clients"));
        clientsCredit.setToolTipText("Liste des credits client");
        clientsCredit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new CreditClient();
            }
        });

        // Clients List Events
        clientsList.addMouseListener(new Listener(clientsList, UIUtil.htmlWrap(clientsList.getText())
                ,"Liste des clients"));
        clientsList.setToolTipText("Liste des clients enregistr\351s");
        clientsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new ClientListUI().setVisible(true);
            }
        });
    }
}
