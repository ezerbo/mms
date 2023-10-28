package com.mms.interfaces.pane;

import com.l2fprod.common.swing.JTaskPaneGroup;
import com.mms.interfaces.supplier.CreditFournisseur;
import com.mms.interfaces.supplier.HistoriqueLivraison;
import com.mms.interfaces.supplier.ListeFournisseur;
import com.mms.util.Listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeliveriesPane extends JTaskPaneGroup {

    private JLabel l_achat;

    private JLabel l_creditFournisseur;

    private JLabel l_listeFournisseur;

    public DeliveriesPane() {
        init();
    }

    private void init() {
        setTitle("Gestion des Livraisons");
        l_achat = new JLabel("Livraisons enregistr�es");
        l_creditFournisseur = new JLabel("Credits fournisseurs");
        l_listeFournisseur = new JLabel("Liste des fournisseurs");
        l_achat.setIcon(new ImageIcon("ressources/images/historique.png"));
        l_listeFournisseur.setIcon(new ImageIcon("ressources/images/liste.png"));
        l_creditFournisseur.setIcon(new ImageIcon("ressources/images/credit.png"));
        add(l_achat);
        add(l_creditFournisseur);
        add(l_listeFournisseur);
        l_achat.addMouseListener(new Listener(l_achat, "<html><a href='' color = black>" +
                l_achat.getText() +
                "</a></html>", "Livraisons enregistr\351es"));
        l_achat.setToolTipText("Liste des achats realis\351s");
        l_achat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new HistoriqueLivraison();
            }
        });

        l_creditFournisseur.addMouseListener(new Listener(l_creditFournisseur,
                "<html><a href='' color = black>" +
                        l_creditFournisseur.getText() +
                        "</a></html>",
                "Credits fournisseurs"));
        l_creditFournisseur.setToolTipText("Liste des credits fournisseur");
        l_creditFournisseur.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new CreditFournisseur();
                System.gc();
            }
        });

        l_listeFournisseur.addMouseListener(new Listener(l_listeFournisseur,
                "<html><a href='' color = black>" +
                        l_listeFournisseur.getText() +
                        "</a></html>",
                "Liste des fournisseurs"));
        l_listeFournisseur
                .setToolTipText("Liste des fournisseurs enregistr\351s");
        l_listeFournisseur.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                new ListeFournisseur().setVisible(true);
            }
        });
    }
}
