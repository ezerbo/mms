package com.mms.ui.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.ui.client.CreditClient;
import com.mms.ui.client.ClientListUI;
import com.mms.ui.sale.HistoriqueVente;
import com.mms.ui.util.ImagePaths;
import com.mms.ui.util.UIUtil;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesPane extends JTaskPaneGroup {

    private final JLabel salesLabel = new JLabel("Ventes enregistrées");;
    private final JLabel clientsCredit = new JLabel("Credits clients");;
    private final JLabel clientsList = new JLabel("Liste des clients");;

    public SalesPane() {
        init();
        registerEvents();
    }

    private void init() {
        setTitle("Gestion des Ventes");
        salesLabel.setIcon(new ImageIcon(ImagePaths.HISTORY_IMG));
        clientsCredit.setIcon(new ImageIcon(ImagePaths.CREDIT_MG));
        clientsList.setIcon(new ImageIcon(ImagePaths.LIST_IMG));
        add(salesLabel);
        add(clientsCredit);
        add(clientsList);
    }

    private void registerEvents() {
        // Sales Events
        salesLabel.addMouseListener(new Listener(salesLabel, UIUtil.htmlWrap(salesLabel.getText())
                , "Ventes enregistrées"));
        salesLabel.setToolTipText("Liste des ventes réalisées");
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
        clientsList.setToolTipText("Liste des clients enregistrés");
        clientsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new ClientListUI().setVisible(true);
            }
        });
    }
}
